package club.huangliang.learn_shiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限信息表
 */
@Data
@Accessors(chain = true) //链式调用
@AllArgsConstructor  //有参构造
@NoArgsConstructor // 无参构造
public class Perms  implements Serializable {
    private String id;
    private String name;
    private String url;

}
