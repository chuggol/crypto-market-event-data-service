package org.chuggol.crypto.service.marketdata;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.chuggol.crypto.service.marketdata.trade.Trade;
import org.chuggol.crypto.service.marketdata.trade.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TradeController {
    @Autowired
    TradeRepository repository;

    @RequestMapping("/trades")
    public List<Trade> getTrades() {

        ArrayList<Trade> trades = Lists.newArrayList();
        return trades;
    }
}
