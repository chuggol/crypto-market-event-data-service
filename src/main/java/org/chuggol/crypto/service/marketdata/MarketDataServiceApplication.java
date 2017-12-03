package org.chuggol.crypto.service.marketdata;

import org.chuggol.crypto.service.marketdata.infra.persistence.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(DataSourceConfig.class)
public class MarketDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketDataServiceApplication.class, args);
    }
}
