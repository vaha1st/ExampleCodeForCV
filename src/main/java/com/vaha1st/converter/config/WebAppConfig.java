package com.vaha1st.converter.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * {@code WebAppConfig} pure java MVC+Hibernate конфигурационный класс.
 *
 * @author Руслан Вахитов
 * @version 1.00 24 May 2020
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.vaha1st.converter")
public class WebAppConfig implements WebMvcConfigurer {

    // Определение ViewResolver
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // Hibernate конфигурация
    // 1. Обозначить Database DataSource / connection pool
    @Bean
    public DataSource myDataSource() {

        // c3p0 connection pool
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();

        // Установка драйвера mySql
        try {
            myDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // Установка параметров подключения к БД
        myDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/berkut_converter?useSSL=false&serverTimezone=UTC");
        myDataSource.setUser("berkutconverter");
        myDataSource.setPassword("berkutconverter");

        // Установка параметров пула
        myDataSource.setInitialPoolSize(5);
        myDataSource.setMinPoolSize(5);
        myDataSource.setMaxPoolSize(20);
        myDataSource.setMaxIdleTime(30000);

        return myDataSource;
    }

    // 2. Создание параметров Hibernate
    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.show_sql", "true");

        return props;
    }

    // 3. Настройка Hibernate фабрики сессий
    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        // Создание локальной фабрики сессий
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // Установка параметров
        sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan("com.vaha1st.converter.entity");
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    // 3. Настройка Hibernate менеджера транзакций
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }


    // Поддержка web ресурсов: css, js, images...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    // Загрузка ресурсов для кастомизации сообщений. Использована замена сообщения на ошибке несовпадения типа вводимых
    // данных, если пользователь введет строк вместо цифр.
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("WEB-INF/resources/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // Регистрация messageSource с валидатором
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }


}
