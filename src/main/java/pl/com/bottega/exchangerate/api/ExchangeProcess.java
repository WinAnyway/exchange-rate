package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.CalculationData;
import pl.com.bottega.exchangerate.domain.commands.DefineRateCommand;

public interface ExchangeProcess {

    void defineExchangeRate(DefineRateCommand defineRateCommand);

    CalculationResult calculate(CalculationData calculationData);

}
