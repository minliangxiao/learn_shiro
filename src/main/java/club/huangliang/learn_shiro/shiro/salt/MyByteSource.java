package club.huangliang.learn_shiro.shiro.salt;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * 因为盐 shiro没有对盐实现序列化，所以必须自定义实现序列化，不然进redis库会出错
 */
public class MyByteSource extends SimpleByteSource implements Serializable {
    public MyByteSource(ByteSource source) {
        super(source);
    }

    public MyByteSource(String string) {
        super(string);
    }
}
