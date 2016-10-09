package com.wbl.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created with Simple_love
 * Date: 2016/4/14.
 * Time: 14:52
 */
public class AopPointCut {
        @Pointcut("@annotation(com.wbl.aop.ProvAnnotation)")
        public void operate(){}

        @Pointcut("execution(* com.wbl.service.*.*(..))")
        public static void setDataSource(){}
}
