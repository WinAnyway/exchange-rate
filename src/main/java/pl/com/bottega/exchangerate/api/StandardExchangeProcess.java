package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.DefineRateCommand;

public class StandardExchangeProcess  implements ExchangeProcess{

    @Override
    public void defineExchangeRate(DefineRateCommand defineRateCommand) {

    }

    @Override
    public CalculationResult calculate(CalculationData calculationData) {
        return null;
    }

}
