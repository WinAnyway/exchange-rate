package pl.com.bottega.exchangerate.domain;

import pl.com.bottega.exchangerate.api.CalculationResult;
import pl.com.bottega.exchangerate.domain.commands.CalculationData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExchangeRateCalculator {

    public static String MAIN_CURRENCY = "PLN";

    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateCalculator(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

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
        BigDecimal calculatedAmount = amount.multiply(fromRate.getRate()).divide(toRate.getRate(), 2, RoundingMode.HALF_UP);
        return new CalculationResult(fromRate.getCurrency(), toRate.getCurrency(), amount, calculatedAmount, date);
    }

    private CalculationResult calculateToMainCurrency(CalculationData calculationData, LocalDate date, BigDecimal amount) {
        ExchangeRate fromRate = exchangeRateRepository.get(calculationData.getFrom(), date);
        ensureExists(fromRate);
        BigDecimal calculatedAmount = amount.multiply(fromRate.getRate());
        return new CalculationResult(fromRate.getCurrency(), MAIN_CURRENCY, amount, calculatedAmount, date);
    }

    private CalculationResult calculateFromMainCurrency(CalculationData calculationData, LocalDate date, BigDecimal amount) {
        ExchangeRate toRate = exchangeRateRepository.get(calculationData.getTo(), date);
        ensureExists(toRate);
        BigDecimal calculatedAmount = amount.divide(toRate.getRate(), 2, RoundingMode.HALF_UP);
        return new CalculationResult(MAIN_CURRENCY, toRate.getCurrency(), amount, calculatedAmount, date);
    }

    private void ensureExists(ExchangeRate rate) {
        if (rate == null)
            throw new NoRateException();
    }
}
