package by.example.cashier.service.impl;

import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.service.ConsoleService;
import by.example.cashier.service.ValidateService;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

import static by.example.cashier.Application.locale;

@Slf4j
public class ValidateServiceImpl implements ValidateService {

    private final ResourceBundle bundle = ResourceBundle.getBundle("validate", locale);

    private final String REGEX_CARD_NUMBER = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";

    private final ConsoleService consoleService = ApplicationConfig.getConsoleService();

    @Override
    public String validateCardNumber() {
        while (true) {
            consoleService.writeMessage(bundle.getString("specify.card.number"));
            String numberCard = null;
            numberCard = consoleService.readString();

            if (!numberCard.matches(REGEX_CARD_NUMBER)) {
                consoleService.writeMessage(bundle.getString("try.again.with.details"));
                continue;
            }

            return numberCard;
        }
    }

    @Override
    public Integer validatePositiveNumber() {
        Integer number = -1;
        do {
            consoleService.writeMessage(bundle.getString("specify.positive.number"));
            try {
                number = Integer.parseInt(consoleService.readString());
            } catch (NumberFormatException e) {
                System.out.println("Number format error!!!");
            }
        } while (number < 0);

        System.out.println(number);
        return number;
    }

    @Override
    public Integer validatePinNumber() {
        Integer pinNumber;
        do {
            consoleService.writeMessage("\nPlease specify valid pin code - 4 digits.\n");
            pinNumber = validatePositiveNumber();
        } while (pinNumber > 9999 || pinNumber < 1111);

        return pinNumber;
    }
}
