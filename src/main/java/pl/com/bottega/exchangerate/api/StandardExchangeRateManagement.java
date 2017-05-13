package pl.com.bottega.exchangerate.api;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;
import pl.com.bottega.exchangerate.domain.commands.DefineRateCommand;

public class StandardExchangeRateManagement implements ExchangeRateManagement{

    private ExchangeRateRepository exchangeRateRepository;

    public StandardExchangeRateManagement(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    @Transactional
    public void defineExchangeRate(DefineRateCommand cmd) {
        exchangeRateRepository.put(new ExchangeRate(cmd.getCurrency(), cmd.getDate(), cmd.getRate()));
    }
}
