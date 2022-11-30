package org.jmp.cloudbank;

import org.jmp.bank.Bank;
import org.jmp.dto.*;

import java.util.function.BiFunction;

public class CloudBankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {

        if (bankCardType.equals(BankCardType.CREDIT)) {
            BiFunction<String, User, CreditBankCard> creditCard = CreditBankCard::new;
            return creditCard.apply(String.valueOf(Math.random() * 6), user);
        } else {
            BiFunction<String, User, DebitBankCard> creditCard = DebitBankCard::new;
            return creditCard.apply(String.valueOf(Math.random() * 6), user);
        }

    }
}
