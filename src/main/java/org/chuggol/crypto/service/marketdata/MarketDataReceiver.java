package org.chuggol.crypto.service.marketdata;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.SubscriptionName;
import com.google.pubsub.v1.TopicName;
import org.chuggol.crypto.service.marketdata.trade.Trade;
import org.chuggol.crypto.service.marketdata.trade.TradeRepository;
import org.chuggol.crypto.service.marketdata.trade.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class MarketDataReceiver implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MarketDataReceiver.class);

    @Autowired
    TradeService service;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOG.info("Creating subscription");
        SubscriptionName subscriptionName = SubscriptionName.create("crypto-175617", "inbound-trades-to-market-event-data-service");
        Subscriber subscriber = Subscriber.defaultBuilder(subscriptionName, new MessageReceiver() {
            @Override
            public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
                ByteString data = pubsubMessage.getData();
                String dataString = data.toString(Charset.defaultCharset());
                Trade tradePojo = new Gson().fromJson(dataString, Trade.class);
                service.save(tradePojo);
                LOG.info("Received: {}", tradePojo);
                ackReplyConsumer.ack();
            }
        }).build();
        subscriber.startAsync();

    }

}
