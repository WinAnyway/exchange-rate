package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.exchangerate.api.ExchangeRateManagement;
import pl.com.bottega.exchangerate.domain.commands.CalculationData;
import pl.com.bottega.exchangerate.api.CalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeProcess;
import pl.com.bottega.exchangerate.domain.commands.DefineRateCommand;

@RestController
public class ExchangeRateController {

    private ExchangeProcess exchangeProcess;
    private ExchangeRateManagement exchangeRateManagement;

    public ExchangeRateController(ExchangeProcess exchangeProcess, ExchangeRateManagement exchangeRateManagement) {
        this.exchangeProcess = exchangeProcess;
        this.exchangeRateManagement = exchangeRateManagement;
    }

    @PutMapping("exchange-rate")
    public void defineExchangeRate(@RequestBody DefineRateCommand defineRateCommand) {
        exchangeRateManagement.defineExchangeRate(defineRateCommand);
    }

    @GetMapping("/calculation")
    public CalculationResult calculate(CalculationData calculationData) {
        return exchangeProcess.calculate(calculationData);
    }
}
