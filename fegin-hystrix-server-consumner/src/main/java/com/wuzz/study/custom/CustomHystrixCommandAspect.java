package com.wuzz.study.custom;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 自定义拦截注解切面拦截
 * @author wuzongzhao
 * @date 2020/12/14 16:09
 */
@Component
@Aspect
public class CustomHystrixCommandAspect {
    ExecutorService executorService= Executors.newFixedThreadPool(10);

    @Pointcut(value = "@annotation(com.wuzz.study.custom.CustomHystrixCommand)")
    public void pointCut(){}

    //Around aop 方法调用前和后通知
    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(final ProceedingJoinPoint joinPoint, CustomHystrixCommand hystrixCommand)
            throws InterruptedException,ExecutionException, TimeoutException, NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        //超时时间
        int timeout=hystrixCommand.timeout();
        //前置的判断逻辑
        Future future=executorService.submit(()->{
            try {
                //继续执行目标方法也就是请求
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        Object result;
        try {
            //future多少秒可有返回没有返回则报异常
            result=future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            //取消执行的任务
            future.cancel(true);
            //判断fallback方式是否存在
            if(StringUtils.isBlank(hystrixCommand.fallback())){
                throw e;
            }
            //调用fallback
            result=invokeFallback(joinPoint,hystrixCommand.fallback());
        }

        return result;

    }

    private Object invokeFallback(ProceedingJoinPoint joinPoint,String fallback)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //MethodSignature aop中获取方法名和参数，
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        //MethodSignature aop中获取方法名和参数，
        Class<?>[] parameterTypes=method.getParameterTypes();
        //得到fallback方法
        try {
            Method fallbackMethod=joinPoint.getTarget().getClass().getMethod(fallback,parameterTypes);
            fallbackMethod.setAccessible(true);
            //完成反射调用
            return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
