package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.DefineRateCommand;

public interface ExchangeProcess {

    void defineExchangeRate(DefineRateCommand defineRateCommand);

    CalculationResult calculate(CalculationData calculationData);

}
