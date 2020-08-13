package club.huangliang.learn_shiro.service;

import club.huangliang.learn_shiro.pojo.Perms;
import club.huangliang.learn_shiro.pojo.Role;
import club.huangliang.learn_shiro.pojo.User;

import java.util.List;

public interface UserService {
    void register(User user);
    User findByUserName(String userName);
    User findRolesByUserName(String username);
    //根据 角色id 查询角色对应的权限集合
    List<Perms> findPermsByRoleId(String id);

}
