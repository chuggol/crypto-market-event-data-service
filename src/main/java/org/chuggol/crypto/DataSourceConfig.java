package org.chuggol.crypto;

import org.chuggol.crypto.service.marketdata.MarketDataReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    public DataSource getDataSource(Environment env) {

        String url = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=%s",
                env.getRequiredProperty("MEDS_DATASOURCE_DATABASE"),
                env.getRequiredProperty("MEDS_DATASOURCE_INSTANCE"),
                com.google.cloud.sql.mysql.SocketFactory.class.getCanonicalName()
        );

        LOG.info("url={}", url);
        LOG.info("MEDS_DATASOURCE_USER={}", env.getRequiredProperty("MEDS_DATASOURCE_USER"));
        LOG.info("MEDS_DATASOURCE_PASSWORD={}", env.getRequiredProperty("MEDS_DATASOURCE_PASSWORD"));

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
