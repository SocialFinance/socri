package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import play.Play;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
public class DataConfig {
    @SuppressWarnings("serial")
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan(getConfig("scan.data.packages"));
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setDatabasePlatform(getConfig("db.default.dialect"));
        emf.setJpaVendorAdapter(va);
        emf.setDataSource(dataSource());
        emf.setJpaPropertyMap(new HashMap<String, String>() {
            {
                put("hibernate.hbm2ddl.auto", getConfig("db.default.ddl.strategy"));
            }
        });
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName(getConfig("db.default.driver"));
        dmds.setUrl(getConfig("db.default.url"));
        dmds.setUsername(getConfig("db.default.user"));
        dmds.setPassword(getConfig("db.default.pass"));
        return dmds;
    }

    @Bean
    public play.mvc.Security.AuthenticatedAction security$AuthenticatedAction() {
        return new play.mvc.Security.AuthenticatedAction();
    }

    private String getConfig(String key) {
        return Play.application().configuration().getString(key);
    }
}
