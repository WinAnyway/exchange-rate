package pl.com.bottega.exchangerate.domain;

import pl.com.bottega.exchangerate.api.CalculationResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ExchangeRateCalculator {

    public static String MAIN_CURRENCY = "PLN";

    public CalculationResult calculateRate(ExchangeRate fromRate, ExchangeRate toRate, LocalDate date, BigDecimal amount) {
        BigDecimal calculatedAmount = amount.multiply(fromRate.getRate()).divide(toRate.getRate(), 2, RoundingMode.HALF_UP);
        return new CalculationResult(fromRate.getCurrency(), toRate.getCurrency(), amount, calculatedAmount, date);
    }

    public CalculationResult calculateFromMainCurrency(ExchangeRate toRate, LocalDate date, BigDecimal amount) {
        BigDecimal calculatedAmount = amount.divide(toRate.getRate(), 2, RoundingMode.HALF_UP);
        return new CalculationResult(MAIN_CURRENCY, toRate.getCurrency(), amount, calculatedAmount, date);
    }

    public CalculationResult calculateToMainCurrency(ExchangeRate fromRate, LocalDate date, BigDecimal amount) {
        BigDecimal calculatedAmount = amount.multiply(fromRate.getRate());
        return new CalculationResult(fromRate.getCurrency(), MAIN_CURRENCY, amount, calculatedAmount, date);
    }
}
