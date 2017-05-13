package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class JPAExchangeRateRepository implements ExchangeRateRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(ExchangeRate exchangeRate) {
        ExchangeRate er = getExchangeRateOfSameDate(exchangeRate);
        if (er != null)
            entityManager.remove(er);
        entityManager.persist(exchangeRate);
    }

    private ExchangeRate getExchangeRateOfSameDate(ExchangeRate exchangeRate) {
        Query query = entityManager.createQuery("select er from ExchangeRate er where er.date = :date AND er.currency = :currency");
        query.setParameter("date", exchangeRate.getDate());
        query.setParameter("currency", exchangeRate.getCurrency());
        List<ExchangeRate> resultList = query.getResultList();
        if (!resultList.isEmpty())
            return resultList.get(0);
        else
            return null;
    }

    @Override
    public ExchangeRate get(String currency, LocalDate date) {
        Query query = entityManager.createQuery("select er from ExchangeRate er where er.currency = :currency and er.date = :date");
        return (ExchangeRate) query.getSingleResult();
    }

}
