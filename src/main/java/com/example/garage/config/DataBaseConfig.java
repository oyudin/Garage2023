//package com.example.garage.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataBaseConfig {
//    @Bean
//    @Profile("prod")
//    public DataSource postgreDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/Garage2");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("q1w2e3r4t5y6!");
//        return dataSource;
//    }
//
//    @Bean
//    @Profile("dev")
//    public DataSource h2DataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:testdb");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("password");
//        return dataSource;
//    }
//}