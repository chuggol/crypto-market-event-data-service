package org.chuggol.crypto.service.marketdata.infra.pubsub;

import io.reactivex.Flowable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class PubSubTestConfig {
    @Bean
    public Flowable<String> tradeFlowable() {
        return Flowable.empty();
    }
}