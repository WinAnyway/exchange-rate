package pl.com.bottega.exchangerate.domain.commands;

import java.math.BigDecimal;

public class CalculationData implements Validatable {

    String date;
    String from;
    String to;
    BigDecimal amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        else if (from.equals(to)) {
            errors.add("from", "must be different than to");
            errors.add("to", "must be different than from");
        }
        if (isEmpty(to))
            errors.add("to", "is required");
        if (amount == null)
            errors.add("amount", "is required");
        if (isEmpty(date))
            errors.add("date", "is required");
    }
}
