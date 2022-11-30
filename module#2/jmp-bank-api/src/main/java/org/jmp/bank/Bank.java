package org.jmp.bank;

import org.jmp.dto.BankCard;
import org.jmp.dto.BankCardType;
import org.jmp.dto.User;

@FunctionalInterface
public interface Bank {

    BankCard createBankCard(User user, BankCardType bankCardType);

}
