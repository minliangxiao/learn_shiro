package club.huangliang.learn_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * shiro编程式授权 和注解式授权案例
 */
@Controller
@RequestMapping("roder")
public class OderController {
    @RequestMapping("save")
    @RequiresRoles("admin")  //shiro用于判断用户角色权限的注解
    //@RequiresRoles(value = {"",""})  //这种方式可以判断出多个角色
//    @RequiresPermissions("")//shiro用于判断权限字符串的注解
    public String  save(){
        // 获取主体对象
        Subject subject= SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("有权访问");
        } else {
            System.out.println("无权访问");
        }
        return "redirect:/index.jsp";
    }

}
