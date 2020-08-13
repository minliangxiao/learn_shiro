package club.huangliang.learn_shiro.shiro.realms;

import club.huangliang.learn_shiro.pojo.Perms;
import club.huangliang.learn_shiro.pojo.Role;
import club.huangliang.learn_shiro.pojo.User;
import club.huangliang.learn_shiro.service.UserService;
import club.huangliang.learn_shiro.shiro.salt.MyByteSource;
import club.huangliang.learn_shiro.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义的realm
 */
public class CustomerRealm extends AuthorizingRealm {
    /**授权
     * @param principalCollection ：身份凭证
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //  先获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal(); //获取到主身份信息

        //  从数据库获取到授权信息
        UserService userServiceImpl = (UserService) ApplicationContextUtils.getBean("userServiceImpl");

        User user = userServiceImpl.findRolesByUserName(primaryPrincipal);  //得到角色集合
        // 对角色集合进行非空判断，然后再将角色添加进AuthorizationInfo
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName());
                List<Perms> perms=userServiceImpl.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    // 向simpleAuthorizationInfo 添加权限字符
                    perms.forEach(perms1 ->{
                        simpleAuthorizationInfo.addStringPermission(perms1.getName());
                    } );
                }
            });
            return simpleAuthorizationInfo;

        }


        /*if ("xiaocheng".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("user");
            simpleAuthorizationInfo.addStringPermission("user:*:*"); //权限字符串符的写法
            return simpleAuthorizationInfo;
        }*/
        return null;
    }

    /**认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String  principal = (String) token.getPrincipal();
        //获取工厂中的Service对象
        UserService userServiceImpl = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userServiceImpl.findByUserName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),new MyByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
