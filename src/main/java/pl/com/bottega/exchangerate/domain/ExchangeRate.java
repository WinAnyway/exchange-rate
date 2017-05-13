package pl.com.bottega.exchangerate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue
    Long id;

    String currency;
    LocalDate date;
    BigDecimal rate;

    ExchangeRate() {}

    public ExchangeRate(String currency, LocalDate date, BigDecimal rate) {
        this.currency = currency;
        this.date = date;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
