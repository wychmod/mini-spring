package wychmod.spring.framework.aop;

import com.wychmod.spring.framework.aop.support.AdvisedSupport;

/**
 * @description: 默认代理工厂类，判断返回哪种代理对象
 * @author: wychmod
 * @date: 2023/6/30
 */
public class DefaultAopProxyFactory {
    public AopProxy createAopProxy(AdvisedSupport config){
        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return new JdkDynamicAopProxy(config);
        }
        return new CglibAopProxy(config);
    }

}
