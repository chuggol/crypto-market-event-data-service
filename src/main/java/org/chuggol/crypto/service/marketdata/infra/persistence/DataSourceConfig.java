package org.chuggol.crypto.service.marketdata.infra.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@Profile("prod")
public class DataSourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties getDataSource(Environment env) {

        String url = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=%s&autoReconnect=true&nullNamePatternMatchesAll=true",
                env.getRequiredProperty("MEDS_DATASOURCE_DATABASE"),
                env.getRequiredProperty("MEDS_DATASOURCE_INSTANCE"),
                com.google.cloud.sql.mysql.SocketFactory.class.getCanonicalName()
        );

        DataSourceProperties properties = new DataSourceProperties();
        properties.setUrl(url);
        properties.setUsername(env.getRequiredProperty("MEDS_DATASOURCE_USER"));
        properties.setPassword(env.getRequiredProperty("MEDS_DATASOURCE_PASSWORD"));

        return properties;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
