package club.huangliang.learn_shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("club.huangliang.learn_shiro.dao")
//@SpringBootApplication
public class LearnShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnShiroApplication.class, args);
    }

}
