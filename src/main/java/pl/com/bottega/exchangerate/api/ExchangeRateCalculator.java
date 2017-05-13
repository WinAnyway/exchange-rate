package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeRateCalculator {

    public CalculationResult calculateRate(ExchangeRate fromRate, ExchangeRate toRate, LocalDate date, BigDecimal amount) {
        if(toRate.getCurrency().equals(fromRate.getCurrency()))

        if(fromRate.getCurrency().equals("PLN")) {
            BigDecimal calculatedAmount = amount.divide(toRate.getRate());
            return new CalculationResult(fromRate.getCurrency(), toRate.getCurrency(), amount, calculatedAmount, date);
        }
        return null;
    }

}
