package club.huangliang.learn_shiro.service.Impl;

import club.huangliang.learn_shiro.dao.UserMapper;
import club.huangliang.learn_shiro.pojo.Perms;
import club.huangliang.learn_shiro.pojo.Role;
import club.huangliang.learn_shiro.pojo.User;
import club.huangliang.learn_shiro.service.UserService;
import club.huangliang.learn_shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class userServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {

        // 1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //  2.将随机盐保存到数据库
        user.setSalt(salt);
        //  3.明文密码进行md5 + salt+ hash 散列(1024哈希散列排布)
        Md5Hash md5Hash=new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userMapper.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByuserName(userName);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userMapper.findPermsByRoleId(id);
    }
}
