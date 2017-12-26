package org.chuggol.crypto.service.marketdata.infra.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DataSourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    public DataSource getDataSource(Environment env) {

        String url = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=%s&autoReconnect=true",
                env.getRequiredProperty("MEDS_DATASOURCE_DATABASE"),
                env.getRequiredProperty("MEDS_DATASOURCE_INSTANCE"),
                com.google.cloud.sql.mysql.SocketFactory.class.getCanonicalName()
        );

        return DataSourceBuilder
                .create()
                .url( url )
                .username( env.getRequiredProperty("MEDS_DATASOURCE_USER") )
                .password( env.getRequiredProperty("MEDS_DATASOURCE_PASSWORD") )
                .build();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
