package org.chuggol.crypto.service.marketdata.infra.pubsub;

import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.SubscriptionName;
import io.reactivex.Emitter;

import java.nio.charset.Charset;

public class PubSubFactory {
    public Subscriber createTopicToEmitterSubscriber(String projectId, String subscription, Emitter<String> emitter) {

        SubscriptionName subscriptionName = SubscriptionName.of(projectId, subscription);

        return Subscriber.newBuilder(
                subscriptionName,
                new MessageReceiverToEmitterBridge(emitter, Charset.defaultCharset()))
                .build();
    }
}
