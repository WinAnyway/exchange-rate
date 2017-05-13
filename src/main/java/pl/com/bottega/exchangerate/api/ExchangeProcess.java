package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.CalculationData;

public interface ExchangeProcess {

    CalculationResult calculate(CalculationData calculationData);

}
