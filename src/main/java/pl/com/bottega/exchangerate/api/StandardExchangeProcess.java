package pl.com.bottega.exchangerate.api;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.domain.commands.CalculationData;
import pl.com.bottega.exchangerate.domain.commands.DefineRateCommand;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StandardExchangeProcess  implements ExchangeProcess{

    ExchangeRateRepository exchangeRateRepository;
    ExchangeRateCalculator calculator;

    public StandardExchangeProcess(ExchangeRateRepository exchangeRateRepository, ExchangeRateCalculator calculator) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.calculator = calculator;
    }

    @Override
    @Transactional
    public void defineExchangeRate(DefineRateCommand cmd) {
        exchangeRateRepository.put(new ExchangeRate(cmd.getCurrency(), cmd.getDate(), cmd.getRate()));
    }

    @Override
    public CalculationResult calculate(CalculationData calculationData) {
        LocalDate date = calculationData.getDate();
        ExchangeRate fromRate = exchangeRateRepository.get(calculationData.getFrom(), date);
        ExchangeRate toRate = exchangeRateRepository.get(calculationData.getTo(), date);
        BigDecimal amount = calculationData.getAmount();
        return calculator.calculateRate(fromRate, toRate, date, amount);
    }

}
