package com.example.jpa.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Created on 2018/3/16.
 */

@EnableConfigurationProperties(JpaProperties.class)
public abstract class BaseJpaConfiguration {

  @Autowired(required = false)
  private ImplicitNamingStrategy implicitNamingStrategy;

  @Autowired(required = false)
  private PhysicalNamingStrategy physicalNamingStrategy;

  @Autowired(required = false)
  private Collection<HibernatePropertiesCustomizer> hibernatePropertiesCustomizers;

  @Autowired
  private JpaProperties jpaProperties;

  @Bean
  public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
      JpaVendorAdapter jpaVendorAdapter,
      ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
    return new EntityManagerFactoryBuilder(
        jpaVendorAdapter, jpaProperties.getProperties(),
        persistenceUnitManager.getIfAvailable());
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setShowSql(jpaProperties.isShowSql());
    adapter.setDatabase(Database.MYSQL);
    adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
    adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
    return adapter;
  }

  protected Map<String, Object> getVendorProperties() {
    String defaultDdlMode = "none";
    return new LinkedHashMap<>(jpaProperties
        .getHibernateProperties(new HibernateSettings().ddlAuto(defaultDdlMode)
            .implicitNamingStrategy(this.implicitNamingStrategy)
            .physicalNamingStrategy(this.physicalNamingStrategy)
            .hibernatePropertiesCustomizers(
                Optional.ofNullable(this.hibernatePropertiesCustomizers).orElse(new ArrayList<>()))));
  }

  protected String[] getMappingResources() {
    List<String> mappingResources = jpaProperties.getMappingResources();
    return (!ObjectUtils.isEmpty(mappingResources)
        ? StringUtils.toStringArray(mappingResources) : null);
  }
}
