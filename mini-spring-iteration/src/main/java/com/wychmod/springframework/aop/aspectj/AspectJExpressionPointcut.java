package com.wychmod.springframework.aop.aspectj;

import com.wychmod.springframework.aop.ClassFilter;
import com.wychmod.springframework.aop.MethodMatcher;
import com.wychmod.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 切点表达式,作用是基于 AspectJ 表达式来定义切点。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 判断表达式匹配范围内是否存在给定的类、接口
     */
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 判断表达式匹配范围内是否存在给定类的给定方法
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * 返回此切入点的类筛选器。
     */
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    /**
     * 返回此切入点的 MethodMatcher。
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

}
