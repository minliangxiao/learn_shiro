package club.huangliang.learn_shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class AotherQuickStartShiro {
    @Test
    public  void testShiro(){
        //1.获取shiro中最主要的sercurityManager
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
//        2.给安全管理器设置Realm
        securityManager.setRealm(new IniRealm("classpath:shiroconfig/shiro.ini"));
        //3.将sercurityManager放进SecurityUtils里面
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取主体，
        Subject subject=SecurityUtils.getSubject();
        //5.创建安全令牌
        UsernamePasswordToken token=new UsernamePasswordToken("g","123");
        try {
            System.out.println("用户认证状态"+subject.isAuthenticated());
            subject.login(token);
            System.out.println("用户认证状态"+subject.isAuthenticated());
        }catch (UnknownAccountException e){
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
        }

        Realm realm;

    }
}
