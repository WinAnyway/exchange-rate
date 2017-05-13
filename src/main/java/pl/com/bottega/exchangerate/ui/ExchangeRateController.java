package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.exchangerate.api.CalculationData;
import pl.com.bottega.exchangerate.api.CalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeProcess;
import pl.com.bottega.exchangerate.domain.DefineRateCommand;

@RestController
public class ExchangeRateController {

    private ExchangeProcess exchangeProcess;

    public ExchangeRateController(ExchangeProcess exchangeProcess) {
        this.exchangeProcess = exchangeProcess;
    }

    @PutMapping("exchange-rate")
    public void defineExchangeRate(@RequestBody DefineRateCommand defineRateCommand) {
        exchangeProcess.defineExchangeRate(defineRateCommand);
    }

    @GetMapping("/calculation")
    public CalculationResult calculate(CalculationData calculationData) {
        return exchangeProcess.calculate(calculationData);
    }
}
