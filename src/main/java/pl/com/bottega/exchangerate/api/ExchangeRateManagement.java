package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.DefineRateCommand;

public interface ExchangeRateManagement {

    void defineExchangeRate(DefineRateCommand defineRateCommand);

}
