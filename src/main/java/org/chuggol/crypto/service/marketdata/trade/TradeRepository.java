package org.chuggol.crypto.service.marketdata.trade;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends PagingAndSortingRepository<Trade, String> {
}
