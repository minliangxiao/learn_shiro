package club.huangliang.learn_shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/*
* 自定义Realm
* */
public class CustomerRealm extends AuthorizingRealm {
    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取token中的用户名(用户凭证)
        String principal = (String) token.getPrincipal();
        System.out.println("用户凭证"+principal);
        // 根据用户凭证 查询数据库相关信息
        if ("hl".equals(principal)){
            SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(principal,"123",this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
