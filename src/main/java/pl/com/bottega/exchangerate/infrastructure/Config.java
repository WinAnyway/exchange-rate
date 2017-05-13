package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.exchangerate.api.ExchangeProcess;
import pl.com.bottega.exchangerate.api.StandardExchangeProcess;

@Configuration
public class Config {

    @Bean
    public ExchangeProcess exchangeProcess() {
        return new StandardExchangeProcess();
    }

}
