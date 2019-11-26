package com.sccl.data_source_change.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.sccl.data_source_change.datasource.DynamicDataSource;
import com.sccl.data_source_change.enumConst.DataSourceEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 *
 * @author sccl
 */
@Configuration
public class DruidMutilConfig {
    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    //该注解表示：读取配置时，比较open属性的值和havingValue的值是否一致，二者相同时本配置才生效
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "open", havingValue = "true")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
    /**
     * 如果还有数据源,在这继续添加 DataSource Bean
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Nullable @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER.getName(), masterDataSource);
        ((DruidDataSource)masterDataSource).setPassword(((DruidDataSource)masterDataSource).getPassword());//解密数据源密码

        if (slaveDataSource != null){
            targetDataSources.put(DataSourceEnum.SLAVE.getName(), slaveDataSource);
            ((DruidDataSource)slaveDataSource).setPassword(((DruidDataSource)slaveDataSource).getPassword());
        }

        // 还有数据源,在targetDataSources中继续添加

        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

}
