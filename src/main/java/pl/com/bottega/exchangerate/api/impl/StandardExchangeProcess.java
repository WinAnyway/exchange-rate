package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.CalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeProcess;
import pl.com.bottega.exchangerate.domain.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.domain.commands.CalculationData;

public class StandardExchangeProcess implements ExchangeProcess {

    ExchangeRateCalculator calculator;

    public StandardExchangeProcess(ExchangeRateCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public CalculationResult calculate(CalculationData calculationData) {
        return calculator.calculate(calculationData);
    }

}
