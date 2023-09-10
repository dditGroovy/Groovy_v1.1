package kr.co.groovy.utils;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class BeanInvoker {
    @Autowired
    private ApplicationContext applicationContext;

    public Object invokeBeanMethod(String beanName, String methodName, Object... args) throws Exception {
        try {
            // 빈 이름으로 빈을 가져옴
            Object bean = applicationContext.getBean(beanName);

            // 빈이 존재하지 않으면 예외 처리
            if (bean == null) {
                throw new NoSuchBeanDefinitionException(beanName);
            }

            // 메서드를 동적으로 호출
            Method method = findMethod(bean.getClass(), methodName, args);
            return method.invoke(bean, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Method findMethod(Class<?> beanClass, String methodName, Object[] args) throws NoSuchMethodException {
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return beanClass.getMethod(methodName, parameterTypes);
    }
}