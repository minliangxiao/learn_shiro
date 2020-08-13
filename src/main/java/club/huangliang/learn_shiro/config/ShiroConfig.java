package club.huangliang.learn_shiro.config;

import club.huangliang.learn_shiro.shiro.cache.RedisCacheManager;
import club.huangliang.learn_shiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/*
* 用来整合shiro框架相关的配置类
* */
@Configuration
public class ShiroConfig {
//    1.创建一个shirofilter(负责拦截请求)
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置系统的受限资源
        Map<String ,String > map=new HashMap<String, String>();
        map.put("/user/login","anon"); // anon 代表公共资源，注意公共资源一般需要设置在受限资源上边
        map.put("/user/register","anon"); // anon 代表公共资源，注意公共资源一般需要设置在受限资源上边
        map.put("/register.jsp","anon"); // anon 代表公共资源，注意公共资源一般需要设置在受限资源上边
        map.put("/**","authc"); // authc请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //配置系统的公共资源

        //设置默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp"); //其实默认就是jsp;


        return shiroFilterFactoryBean;
    }
//    2.创建一个安全管理器(web环境下构建DefaultSecurityManager,SecurityManager是不具备web功能的)
    @Bean("SecurityManager")
    public DefaultSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm){
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置Realm
        defaultSecurityManager.setRealm(realm);
        return defaultSecurityManager;
    }
//    3.配置自定义的Ream
    @Bean("realm")
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        // 因为业务上要使用MD5+随机盐+hash散列  所以必须修改 realm中默认的凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        // 设置为md5加密
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        //将 credentialsMatcher添加进自定义Realm
        customerRealm.setCredentialsMatcher(credentialsMatcher);


        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);  //开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true); //开启认证缓存
        customerRealm.setAuthenticationCacheName("authenticatinCache"); //设置认证缓存器的名字
        customerRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache"); //设置授权缓存器的名字

        return customerRealm;
    }
}
