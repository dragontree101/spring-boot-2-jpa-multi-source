package com.example.jpa.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created on 2017/5/21.
 */
@Configuration
public class DataSourceConfig {

  @Bean(name = "writeDataSource")
  @ConfigurationProperties(prefix="spring.datasource.write")
  public DataSource writeDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "readDataSource")
  @Primary
  @ConfigurationProperties(prefix="spring.datasource.read")
  public DataSource readDataSource() {
    return DataSourceBuilder.create().build();
  }
}
