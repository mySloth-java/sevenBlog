package com.cg.annotation.aspect;

import com.alibaba.fastjson.JSON;
import com.cg.annotation.LogPrint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author cgJavaAfter
 * @date 2023-06-03 14:32
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    //定义切点
    @Pointcut("@annotation(com.cg.annotation.LogPrint)")
    public void LogPrintPt(){}

    //定义AOP环绕通知，指定环绕的切点或者使用execution切入点表达式
    @Around("LogPrintPt()")
    public Object LogPrintAround(ProceedingJoinPoint joinPoint) throws Throwable {//参数获取目标对象
        try {
            //执行方法前
            handleBefore(joinPoint);

            Object proceed = joinPoint.proceed();//拿到目标对象返回值，即执行目标方法
            
            //执行方法后
            log.info(JSON.toJSONString(proceed));//打印出参
        } finally {
            log.info("=======END=======" + System.lineSeparator());//后面为系统的换行符，不同系统换行符不一样，直接获取到当前系统换行符
        }
        return joinPoint.proceed();
    }

    //TODO 将日志信息输入本地存储
    private void handleBefore(ProceedingJoinPoint joinPoint) {
        log.info("=======项目访问日志=======");

        //通过RequestContextHolder获取当前线程
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();//强转接口实现类拿到request对象
        HttpServletRequest request = requestAttributes.getRequest();//面向对象思想，将请求报文的request封装成对象

        //通过获取当前对象签名，并强转为method签名获取方法对应的全部信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LogPrint annotation = methodSignature.getMethod().getAnnotation(LogPrint.class);

        //打印请求的URL
        log.info("URL           : {}",request.getRequestURL());
        //打印描述信息(自定义注解属性)
        log.info("conName       : {}",annotation.conName());
        //打印请求method信息
        log.info("HTTP METHOD   : {}",request.getMethod());
        //打印controller全路径 and 执行方法名
        log.info("Class Method  : {}.{}",methodSignature.getDeclaringTypeName()
                ,methodSignature.getName());
        //打印请求IP
        log.info("IP            : {}",request.getRemoteHost());
        //打印请求入参
        log.info("Request       : {}", JSON.toJSONString(joinPoint.getArgs()));

    }


}
