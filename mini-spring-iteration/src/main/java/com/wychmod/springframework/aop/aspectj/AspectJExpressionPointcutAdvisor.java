package com.wychmod.springframework.aop.aspectj;

import com.wychmod.springframework.aop.Pointcut;
import com.wychmod.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @description: Spring AOP Advisor，可用于任何AspectJ切入点表达式。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return (Pointcut) pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}
