package org.jmp.service;

import org.jmp.dto.BankCard;
import org.jmp.dto.Subscription;
import org.jmp.dto.User;

import java.util.List;
import java.util.function.Predicate;

public interface Service {
    void subscribe(BankCard bankCard);
    Subscription getSubscriptionByBankCardNumber(String number) throws NoSubscriptionException;
    List<User> getAllUsers();
    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);


}
