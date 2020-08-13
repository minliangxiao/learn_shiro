package club.huangliang.learn_shiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true) //链式调用
@AllArgsConstructor  //有参构造
@NoArgsConstructor // 无参构造
public class User  implements Serializable {
    private String id;
    private String username;
    private String password;
    private String salt;

    //定义一个角色集合
    private List<Role> roles;
}
