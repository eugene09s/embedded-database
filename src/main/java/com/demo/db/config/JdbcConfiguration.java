package com.demo.db.config;

import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.demo.db.dao")
public class JdbcConfiguration {

  @Bean
  public DataSource mysqlDataSource(
      @Value("${jdbc.url}") String msrDatabaseConnectionUrl,
      @Value("${jdbc.user}") String msrDatabaseUsername,
      @Value("${jdbc.password:}") String msrDatabasePassword) {

    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName(Driver.class.getName());
    dataSource.setJdbcUrl(msrDatabaseConnectionUrl);
    dataSource.setUsername(msrDatabaseUsername);
    dataSource.setPassword(msrDatabasePassword);
    dataSource.setMaximumPoolSize(1);

    return dataSource;
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }
}
