package org.chuggol.crypto.service.marketdata.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TradeService {
    @Autowired
    TradeRepository repository;

    @Transactional
    public void save(Trade t) {
        repository.save(t);
    }
}
