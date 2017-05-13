package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.exchangerate.api.*;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeProcess;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeRateManagement;
import pl.com.bottega.exchangerate.domain.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

@Configuration
public class Config {

    @Bean
    public ExchangeProcess exchangeProcess(ExchangeRateCalculator exchangeRateCalculator) {
        return new StandardExchangeProcess(exchangeRateCalculator);
    }

    @Bean
    public ExchangeRateRepository exchangeRateRepository() {
        return new JPAExchangeRateRepository();
    }

    @Bean
    public ExchangeRateCalculator exchangeRateCalculator(ExchangeRateRepository exchangeRateRepository) {
        return new ExchangeRateCalculator(exchangeRateRepository);
    }

    @Bean
    public ExchangeRateManagement exchangeRateManagement(ExchangeRateRepository exchangeRateRepository) {
        return new StandardExchangeRateManagement(exchangeRateRepository);
    }

}
