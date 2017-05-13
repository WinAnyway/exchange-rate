package pl.com.bottega.exchangerate.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculationResult {

    private final String from;
    private final String to;
    private final BigDecimal amount;
    private final BigDecimal calculatedAmount;
    private final LocalDate date;

    public CalculationResult(String from, String to, BigDecimal amount, BigDecimal calculatedAmount, LocalDate date) {

        this.from = from;
        this.to = to;
        this.amount = amount;
        this.calculatedAmount = calculatedAmount;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCalculatedAmount() {
        return calculatedAmount;
    }

    public LocalDate getDate() {
        return date;
    }
}
