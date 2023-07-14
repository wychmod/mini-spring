package wychmod.spring.framework.beans;

/**
 * @description: Bean包装类
 * @author: wychmod
 * @date: 2023/6/24
 */
public class BeanWrapper {
    private Object wrapperInstance;
    private Class<?> wrappedClass;

    public BeanWrapper(Object Instance) {
        this.wrapperInstance = Instance;
        this.wrappedClass = Instance.getClass();
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
