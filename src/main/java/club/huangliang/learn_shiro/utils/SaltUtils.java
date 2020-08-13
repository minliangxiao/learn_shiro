package club.huangliang.learn_shiro.utils;

import org.junit.Test;

import java.util.Random;

public class SaltUtils {
    /**生成盐的静态方法
     * @param n  生成几位
     * @return 一个随机盐字符串
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwsyz1234567890!@#$%^&*()".toCharArray();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < n; i++) {
            char achar=chars[new Random().nextInt(chars.length)];
            sb.append(achar);
        }
        return sb.toString();
    }
    @Test
    public  void  test(){
        String salt=getSalt(4);
        System.out.println(salt);
    }
}
