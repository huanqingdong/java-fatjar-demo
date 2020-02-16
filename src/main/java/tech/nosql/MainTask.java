package tech.nosql;

import com.alibaba.fastjson.JSON;
import tech.nosql.pojo.User;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * fatJar主类
 *
 * @author faith.huan 2020-02-16 20:32
 */
public class MainTask {

    public static void main(String[] args) throws InterruptedException, IOException {

        long sleepMillis = getSleepMillis();
        // 执行次数
        int times = 10000;
        for (int i = 0; i < times; i++) {
            // 每秒输出一个用fastjson格式化的的user字符串
            User user = new User();
            user.setName("u_" + i);
            user.setAge(new Random().nextInt(100));
            System.out.println(JSON.toJSONString(user));
            Thread.sleep(sleepMillis);
        }
    }


    private static long getSleepMillis() {
        try {
            Properties properties = new Properties();
            // path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。
            properties.load(MainTask.class.getResourceAsStream("/application.properties"));
            long sleepMillis = Long.parseLong(properties.getProperty("sleep.millis"));
            System.out.println("配置文件中sleep.millis = " + sleepMillis);
            return sleepMillis;
        } catch (IOException ex) {
            System.out.println("读取配置文件异常" + ex.getMessage());
            return 2000;
        }
    }

}
