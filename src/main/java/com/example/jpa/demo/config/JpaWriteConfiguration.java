package com.example.jpa.demo.config;

import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    entityManagerFactoryRef = "writeDemoEntityManagerFactory",
    transactionManagerRef = "writeDemoTransactionManager",
    basePackages = {"com.example.jpa.demo.repository.write"})
public class JpaWriteConfiguration extends BaseJpaConfiguration {

  @Resource(name = "writeDataSource")
  private DataSource dataSource;

  @Primary
  @Bean(name = "writeDemoEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder factoryBuilder) {
    log.info("write demo entity manager init");
    Map<String, Object> vendorProperties = getVendorProperties();
    return factoryBuilder.dataSource(dataSource)
        .packages("com.example.jpa.demo.entity")
        .properties(vendorProperties)
        .mappingResources(getMappingResources())
        .persistenceUnit("writePersistenceUnit")
        .build();
  }

  @Primary
  @Bean(name = "writeDemoTransactionManager")
  public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder factoryBuilder) {
    log.info("write demo transaction manager init");
    return new JpaTransactionManager(entityManagerFactory(factoryBuilder).getObject());
  }

}
