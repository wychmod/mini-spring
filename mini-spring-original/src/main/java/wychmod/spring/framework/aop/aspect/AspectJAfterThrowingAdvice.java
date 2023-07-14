package wychmod.spring.framework.aop.aspect;

import com.wychmod.spring.framework.aop.intercept.MethodInterceptor;
import com.wychmod.spring.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: wychmod
 * @date: 2023/6/30
 */
public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice implements MethodInterceptor {

    private String throwName;

    public AspectJAfterThrowingAdvice(Object aspect, Method adviceMethod) {
        super(aspect, adviceMethod);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable ex) {
            invokeAdviceMethod(mi, null, ex.getCause());
            throw ex;
        }
    }

    public void setThrowName(String throwName) {
        this.throwName = throwName;
    }
}
