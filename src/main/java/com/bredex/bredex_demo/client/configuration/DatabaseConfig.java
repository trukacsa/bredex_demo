package com.bredex.bredex_demo.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories
public class DatabaseConfig {

    @Value("${db.schema.sql}")
    private String schema;
    @Value("${db.packages.to.scan}")
    private String packages;
    @Value("${db.persistence.unit}")
    private String persistenceUnit;
    @Value("${db.hybernate.dialect.key}")
    private String dialectKey;
    @Value("${db.hybernate.dialect.value}")
    private String dialectValue;
    @Value("${db.hybernate.show.sql.key}")
    private String showSqlKey;
    @Value("${db.hybernate.show.sql.value}")
    private String showSqlValue;
    @Value("${db.entity.manager.factory.name}")
    private String entityManagerFactoryName;
    @Value("${db.entity.manager.factory}")
    private String entityManagerFactory;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(schema)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan(packages);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(jpaProperties());
        emf.setPersistenceUnitName(persistenceUnit);
        return emf;
    }

    private Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty(dialectKey, dialectValue);
        props.setProperty(showSqlKey, showSqlValue);
        props.setProperty(entityManagerFactoryName, entityManagerFactory);
        return props;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}

