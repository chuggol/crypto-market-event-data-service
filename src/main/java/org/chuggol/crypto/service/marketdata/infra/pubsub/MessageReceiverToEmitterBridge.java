package org.chuggol.crypto.service.marketdata.infra.pubsub;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import io.reactivex.Emitter;

import java.nio.charset.Charset;

public class MessageReceiverToEmitterBridge implements MessageReceiver {
    private final Emitter<String> emitter;
    private final Charset charset;

    public MessageReceiverToEmitterBridge(Emitter<String> emitter, Charset charset) {
        this.emitter = emitter;
        this.charset = charset;
    }

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        ByteString data = pubsubMessage.getData();
        emitter.onNext(data.toString(charset));
        ackReplyConsumer.ack();
    }
}
