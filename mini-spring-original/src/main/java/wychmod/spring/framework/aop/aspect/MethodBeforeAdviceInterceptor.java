package wychmod.spring.framework.aop.aspect;

import com.wychmod.spring.framework.aop.intercept.MethodInterceptor;
import com.wychmod.spring.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @description: 前置通知拦截器
 * @author: wychmod
 * @date: 2023/6/30
 */
public class MethodBeforeAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor {

    private JoinPoint jp;

    public MethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectTarget, aspectMethod);
    }

    public void before(Method method, Object[] args, Object target) throws Throwable {
        this.invokeAdviceMethod(this.jp,null,null);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        jp = invocation;
        this.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
