package com.sccl.data_source_change;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement //开启事务
@MapperScan("com.sccl.data_source_change.mapper")
public class DataSourceChangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceChangeApplication.class, args);
    }

}
