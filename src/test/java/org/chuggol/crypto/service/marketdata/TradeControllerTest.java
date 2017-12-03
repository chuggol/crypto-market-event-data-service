package org.chuggol.crypto.service.marketdata;

import com.google.gson.Gson;
import io.reactivex.Flowable;
import io.restassured.RestAssured;
import org.chuggol.crypto.service.marketdata.trade.Trade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TradeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int servingPort;

    @Test
    public void returnsSingleTrade() {

        RestAssured.given().port(servingPort)
                .expect().body("", hasSize(1))
                .and().body("currency", hasItem("USD"))
                .when().get("/trades");
    }

    @TestConfiguration
    @Profile("test")
    public static class PubSubTestConfig {
        @Bean
        @Primary
        public Flowable<String> tradeFlowable() {
            Trade trade = Trade.builder().id("123").currency("USD").price(BigDecimal.ONE).build();
            String s = new Gson().toJson(trade);
            return Flowable.just(s);
        }
    }
}
