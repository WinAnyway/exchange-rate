package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAExchangeRateRepository implements ExchangeRateRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(ExchangeRate exchangeRate) {
        entityManager.persist(exchangeRate);
    }

    @Override
    public ExchangeRate get(String currency) {
        return entityManager.find(ExchangeRate.class, currency);
    }

}
