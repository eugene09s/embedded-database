package com.demo.db.config;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.function.Consumer;

@Configuration
public class EmbeddedPostgresConfiguration {

  @Bean
  public Consumer<EmbeddedPostgres.Builder> embeddedPostgresCustomizer() {
    return builder -> builder
        .setPGStartupWait(Duration.ofSeconds(20L));
  }

  @Bean
  public Flyway flyway(DataSource mysqlDataSource) {
    return Flyway.configure()
        .dataSource(mysqlDataSource)
//        .locations("filesystem:..db.migration")
        .connectRetries(10)//Sometimes flyway cannot connect to Docker PostgreSQL on the first try
        .load();
  }
}
