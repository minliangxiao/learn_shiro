package club.huangliang.learn_shiro.dao;

import club.huangliang.learn_shiro.pojo.Perms;
import club.huangliang.learn_shiro.pojo.Role;
import club.huangliang.learn_shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);

    User findByuserName(String userName);
    // 根据username查询用户得角色
    User findRolesByUserName(String username);
    //根据 角色id 查询角色对应的权限集合
    List<Perms> findPermsByRoleId(String id);

}
