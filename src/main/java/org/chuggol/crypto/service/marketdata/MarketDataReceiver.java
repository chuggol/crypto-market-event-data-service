package org.chuggol.crypto.service.marketdata;

import com.google.gson.Gson;
import io.reactivex.Flowable;
import org.chuggol.crypto.service.marketdata.trade.Trade;
import org.chuggol.crypto.service.marketdata.trade.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class MarketDataReceiver implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MarketDataReceiver.class);

    @Autowired
    TradeService service;

    @Autowired
    Flowable<String> tradesFlowable;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOG.info("Creating subscription");

        tradesFlowable.subscribe(s -> {
            Trade tradePojo = new Gson().fromJson(s, Trade.class);
            service.save(tradePojo);
            LOG.info("Received: {}", tradePojo);
        });
    }

}
