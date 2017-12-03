package org.chuggol.crypto.service.marketdata.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Builder
@AllArgsConstructor
@Getter
public class Trade {
    @Id
    private String id;
    private Instant executionTime;
    private String market;
    private String tradedAsset;
    private String currency;
    private BigDecimal price;
    private BigDecimal quantity;
    private String side;

    public Trade() {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        return new EqualsBuilder()
                .append(id, trade.id)
                .append(executionTime, trade.executionTime)
                .append(market, trade.market)
                .append(tradedAsset, trade.tradedAsset)
                .append(currency, trade.currency)
                .append(price, trade.price)
                .append(quantity, trade.quantity)
                .append(side, trade.side)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("executionTime", executionTime)
                .append("market", market)
                .append("tradedAsset", tradedAsset)
                .append("currency", currency)
                .append("price", price)
                .append("quantity", quantity)
                .append("side", side)
                .toString();
    }
}
