package club.huangliang.learn_shiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 根据bean的名字获取到Spring容器中的bean对象
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    /**
     * 根据bean名字获取到bean对象
     * @param beanName
     * @return
     * 注意：spring容器中的bean对象默认为类名（首字母小写）
     *
     */
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
