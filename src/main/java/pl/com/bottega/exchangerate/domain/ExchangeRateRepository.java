package pl.com.bottega.exchangerate.domain;

public interface ExchangeRateRepository {

    void put(ExchangeRate exchangeRate);

    ExchangeRate get(String currency);
}
