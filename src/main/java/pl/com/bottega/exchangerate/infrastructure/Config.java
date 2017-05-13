package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.exchangerate.api.ExchangeProcess;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.api.StandardExchangeProcess;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

@Configuration
public class Config {

    @Bean
    public ExchangeProcess exchangeProcess(ExchangeRateRepository exchangeRateRepository, ExchangeRateCalculator exchangeRateCalculator) {
        return new StandardExchangeProcess(exchangeRateRepository, exchangeRateCalculator);
    }

    @Bean
    public ExchangeRateRepository exchangeRateRepository() {
        return new JPAExchangeRateRepository();
    }

    @Bean
    public ExchangeRateCalculator exchangeRateCalculator() {
        return new ExchangeRateCalculator();
    }

}
