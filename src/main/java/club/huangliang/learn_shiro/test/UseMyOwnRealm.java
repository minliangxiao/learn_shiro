package club.huangliang.learn_shiro.test;

import club.huangliang.learn_shiro.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 调用自定义Realm的测试方法
 */
public class UseMyOwnRealm {
    @Test
    public void testUseMyOwnRealm(){
        //    1.创建securityManager
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        //    2.设置自定义的Realm
        securityManager.setRealm(new CustomerRealm());
//        3.设置进入安全管理器
        SecurityUtils.setSecurityManager(securityManager);
//        4.获取主体对象
        Subject subject=SecurityUtils.getSubject();
//        5.创建token令牌
        UsernamePasswordToken token=new UsernamePasswordToken("hl","123");
        try {
            subject.login(token);
            System.out.println("用户认证状态"+subject.isAuthenticated());
            System.out.println("认证成功");
        } catch (UnknownAccountException e){
            System.out.println("用户名错误");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            e.printStackTrace();
        }

    }

}
