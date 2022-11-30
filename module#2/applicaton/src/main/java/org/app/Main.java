package org.app;

import org.jmp.cloudbank.CloudBankImpl;
import org.jmp.cloudservice.ServiceImpl;
import org.jmp.dto.*;
import org.jmp.service.NoSubscriptionException;
import org.jmp.service.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) throws NoSubscriptionException {
        CloudBankImpl cloudBank = new CloudBankImpl();
        ServiceImpl service = new ServiceImpl();
        Service serviceWithLoader = getServiceWithServiceLoader();


        var user1 = new User("Berkant", "Duman", LocalDate.of(1998, 4, 12));
        var user4 = new User("Berkant4", "Duman", LocalDate.of(2010, 7, 12));


        System.out.println("Users With Constructor Service: ");
        service.getAllUsers().forEach(Main::printUsers);

        System.out.println("\nUsers With Service Loader: ");
        serviceWithLoader.getAllUsers().forEach(Main::printUsers);

        BankCard bankCard1 = cloudBank.createBankCard(user1, BankCardType.CREDIT);
        BankCard bankCard2 = cloudBank.createBankCard(user4, BankCardType.DEBIT);


        System.out.println("\nSubscribe Bank Card: " + bankCard1.getNumber());
        service.subscribe(bankCard1);

        Subscription subs = service.getSubscriptionByBankCardNumber(bankCard1.getNumber());

        System.out.println("BankCardByNumber: " + subs.getBankCard());


        System.out.printf("Payable user %s: %b %n", user1.getName(), service.isPayableUser(user1));

        System.out.printf("Not payable user %s: %b %n", user4.getName(), service.isPayableUser(user4));

        System.out.println("Average age of users: " + service.getAverageUsersAge());

        System.out.println("Get subscription with condition: " + service.getAllSubscriptionsByCondition(s -> s.getBankCard().equals(bankCard1.getNumber())).get(0).getBankCard());
    }


    public static void printUsers(User user) {
        System.out.println(String.format("{ Name: %s, Surname: %s, Age: %d }", user.getName(), user.getSurname(), ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now())));
    }

    public static Service getServiceWithServiceLoader() {
        // load our plugin
        ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);
        for (Service provider : serviceLoader) {
            return provider;
        }
        throw new NoClassDefFoundError("Unable to load a driver " + Service.class.getName());
    }
}