package pl.com.bottega.exchangerate.domain;

import java.time.LocalDate;

public interface ExchangeRateRepository {

    void put(ExchangeRate exchangeRate);

    ExchangeRate get(String currency, LocalDate date);
}
