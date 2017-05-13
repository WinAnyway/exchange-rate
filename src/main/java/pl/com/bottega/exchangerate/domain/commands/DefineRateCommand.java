package pl.com.bottega.exchangerate.domain.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DefineRateCommand implements Validatable {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    String currency;
    BigDecimal rate;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (date == null)
            errors.add("date", "is required");
        if (isEmpty(currency))
            errors.add("currency", "is required");
        else if (currency.length() > 3)
            errors.add("currency", "has invalid format");
        if (rate == null)
            errors.add("rate", "is required");
        else if (rate.compareTo(BigDecimal.ZERO) < 1)
            errors.add("rate", "must be > than 0.0");
    }
}
