package com.hbxy.smartnursery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
@SpringBootApplication
public class SmartNurseryApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext app =
                SpringApplication.run(SmartNurseryApplication.class,
                        args);
        String logo = " . ____ _ __ _ _\n" +
                " /\\\\ / ___'_ __ _ _(_)_ __ __ _ \\ \\ \\ \\\n" +
                "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\\n" +
                " \\\\/ ___)| |_)| | | | | || (_| | ) ) ) )\n" +
                " ' |____| .__|_| |_|_| |_\\__, | / / / /\n" +
                " =========|_|==============|___/=/_/_/_/";
        System.out.println(logo);
        Environment env = app.getEnvironment();
        String name = InetAddress.getLocalHost().getHostName();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }

        System.out.println("Application is Running! Access URLs:");
        System.out.println("本机访问：\thttp://localhost:" + port + path);
        System.out.println("外部访问：\thttp://" + ip + ":" + port + path);
    }
}