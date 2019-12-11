package com.xyafu.gtms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication/*(exclude= {DataSourceAutoConfiguration.class})*/
@MapperScan("com.xyafu.gtms.dao")
public class GtmsApplication {

    public static void main(String[] args) {

        SpringApplication.run(GtmsApplication.class, args);
    }

}
