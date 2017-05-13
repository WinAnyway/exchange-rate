package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.CalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeProcess;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;
import pl.com.bottega.exchangerate.domain.NoRateException;
import pl.com.bottega.exchangerate.domain.commands.CalculationData;

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
    public CalculationResult calculate(CalculationData calculationData) {
        LocalDate date = LocalDate.parse(calculationData.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BigDecimal amount = calculationData.getAmount();
        if (calculationData.getFrom().equals(MAIN_CURRENCY))
            return calculateFromMainCurrency(calculationData, date, amount);
        else if (calculationData.getTo().equals(MAIN_CURRENCY))
            return calculateToMainCurrency(calculationData, date, amount);
        else
            return calculateWithoutMainCurrency(calculationData, date, amount);
    }

    private CalculationResult calculateWithoutMainCurrency(CalculationData calculationData, LocalDate date, BigDecimal amount) {
        ExchangeRate fromRate = exchangeRateRepository.get(calculationData.getFrom(), date);
        ExchangeRate toRate = exchangeRateRepository.get(calculationData.getTo(), date);
        ensureExists(fromRate);
        ensureExists(toRate);
        return calculator.calculateRate(fromRate, toRate, date, amount);
    }

    private CalculationResult calculateToMainCurrency(CalculationData calculationData, LocalDate date, BigDecimal amount) {
        ExchangeRate fromRate = exchangeRateRepository.get(calculationData.getFrom(), date);
        ensureExists(fromRate);
        return calculator.calculateToMainCurrency(fromRate, date, amount);
    }

    private CalculationResult calculateFromMainCurrency(CalculationData calculationData, LocalDate date, BigDecimal amount) {
        ExchangeRate toRate = exchangeRateRepository.get(calculationData.getTo(), date);
        ensureExists(toRate);
        return calculator.calculateFromMainCurrency(toRate, date, amount);
    }

    private void ensureExists(ExchangeRate rate) {
        if (rate == null)
            throw new NoRateException();
    }

}
