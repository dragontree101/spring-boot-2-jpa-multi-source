package com.example.jpa.demo.config;

import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created on 2018/3/16.
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "readDemoEntityManagerFactory",
    transactionManagerRef = "readDemoTransactionManager",
    basePackages = {"com.example.jpa.demo.repository.read"})
public class JpaReadConfiguration extends BaseJpaConfiguration {

  @Resource(name = "readDataSource")
  private DataSource dataSource;

  @Bean(name = "readDemoEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder factoryBuilder) {
    log.info("read demo entity manager init");
    Map<String, Object> vendorProperties = getVendorProperties();
    return factoryBuilder.dataSource(dataSource)
        .packages("com.example.jpa.demo.entity")
        .properties(vendorProperties)
        .mappingResources(getMappingResources())
        .persistenceUnit("readPersistenceUnit")
        .build();
  }

  @Bean(name = "readDemoTransactionManager")
  public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder factoryBuilder) {
    log.info("read demo transaction manager init");
    return new JpaTransactionManager(entityManagerFactory(factoryBuilder).getObject());
  }

}
