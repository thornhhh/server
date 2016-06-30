package com.call110.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.call110.common.pageable.PageInterceptor;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {
	private final static Log log = LogFactory.getLog(MybatisConfig.class);
    @Autowired
    private Environment env;
    @Bean
    public DataSource dataSource(){
    	log.info("DataSource");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.driverClassName"));
        dataSource.setUrl(env.getProperty("mysql.url"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("mysql.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("mysql.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("mysql.maxActive")));
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    	log.info("SqlSessionFactory");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 获取properties中的对应配置信息
        String mapperPackage = env.getProperty("spring.mybatis.mapperPackage");
        String dialect = env.getProperty("spring.mybatis.dialect");
        Properties properties = new Properties();
        properties.setProperty("dialect", dialect);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigurationProperties(properties);
        // 设置MapperLocations路径
        if(!StringUtils.isEmpty(mapperPackage)){
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            sessionFactory.setMapperLocations(resourcePatternResolver.getResources(mapperPackage));
        }
        // 设置插件
        sessionFactory.setPlugins(new Interceptor[]{new PageInterceptor()});
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
    	log.info("SqlSessionTemplate");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dataSource).build();
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
////        properties.setProperty("hibernate.hbm2ddl.auto", "none");
//        entityManagerFactory.setPackagesToScan("com.call110.business.entity");
//        entityManagerFactory.setJpaProperties(properties);
//
//        return entityManagerFactory;
//    }
    
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
    	log.info("DataSourceTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }
    
}
