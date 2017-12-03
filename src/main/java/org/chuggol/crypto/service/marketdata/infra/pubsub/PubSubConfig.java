package org.chuggol.crypto.service.marketdata.infra.pubsub;

import com.google.cloud.pubsub.v1.Subscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class PubSubConfig {
    @Value("${gcloud.projectName}")
    private String gcloudProject;

    @Bean
    public Flowable<String> tradeFlowable(@Value("${meds.trades.subscriptionName}") String subscriptionName) {

        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                Subscriber subscriber = new PubSubFactory().createTopicToEmitterSubscriber(gcloudProject, subscriptionName, emitter);
                subscriber.startAsync();
            }

        }, BackpressureStrategy.BUFFER);
    }
}
