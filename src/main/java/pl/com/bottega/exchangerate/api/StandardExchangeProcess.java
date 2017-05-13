package pl.com.bottega.exchangerate.api;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.domain.NoRateException;
import pl.com.bottega.exchangerate.domain.commands.CalculationData;
import pl.com.bottega.exchangerate.domain.commands.DefineRateCommand;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StandardExchangeProcess implements ExchangeProcess {

    private static final String MAIN_CURRENCY = "PLN";
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
        LocalDate date = LocalDate.parse(calculationData.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BigDecimal amount = calculationData.getAmount();
        if (calculationData.getFrom().equals(MAIN_CURRENCY)) {
            ExchangeRate toRate = exchangeRateRepository.get(calculationData.getTo(), date);
            ensureExists(toRate);
            return calculator.calculateFromMainCurrency(toRate, date, amount);
        } else if (calculationData.getTo().equals(MAIN_CURRENCY)) {
            ExchangeRate fromRate = exchangeRateRepository.get(calculationData.getFrom(), date);
            ensureExists(fromRate);
            return calculator.calculateToMainCurrency(fromRate, date, amount);
        } else if (!calculationData.getTo().equals(MAIN_CURRENCY) && !calculationData.getFrom().equals(MAIN_CURRENCY)) {
            ExchangeRate fromRate = exchangeRateRepository.get(calculationData.getFrom(), date);
            ExchangeRate toRate = exchangeRateRepository.get(calculationData.getTo(), date);
            ensureExists(fromRate);
            ensureExists(toRate);
            return calculator.calculateRate(fromRate, toRate, date, amount);
        }
        return null;
    }

    private void ensureExists(ExchangeRate rate) {
        if (rate == null)
            throw new NoRateException();
    }

}
