package pl.com.bottega.exchangerate.domain.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculationData implements Validatable {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    String from;
    String to;
    BigDecimal amount;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(from))
            errors.add("from", "is required");
        if (isEmpty(to))
            errors.add("to", "is required");
        if (amount == null)
            errors.add("amount", "is required");
        if (date == null)
            errors.add("date", "is required");
    }
}
