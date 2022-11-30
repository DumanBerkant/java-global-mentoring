package org.jmp.cloudservice;

import org.jmp.dto.BankCard;
import org.jmp.dto.Subscription;
import org.jmp.dto.User;
import org.jmp.service.NoSubscriptionException;
import org.jmp.service.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    List<Subscription> subscriptions = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public ServiceImpl(){
        this.users.add(new User("Berkant", "Duman", LocalDate.of(1998, 4, 12)));
        this.users.add(new User("Berkant2", "Duman", LocalDate.of(1999, 5, 12)));
        this.users.add(new User("Berkant3", "Duman", LocalDate.of(2000, 6, 12)));
        this.users.add(new User("Berkant4", "Duman", LocalDate.of(2010, 7, 12)));
        this.users.add(new User("Berkant5", "Duman", LocalDate.of(2007, 8, 12)));
        this.users = users.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void subscribe(BankCard bankCard) {
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Subscription getSubscriptionByBankCardNumber(String number) throws NoSubscriptionException {
        return subscriptions.stream().filter(s -> s.getBankCard().equals(number)).findFirst().orElseThrow(() -> new NoSubscriptionException("Subscription couldn't find"));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.stream().filter(predicate).toList();
    }

    public double getAverageUsersAge(){
        return getAllUsers().stream().mapToDouble(u -> ChronoUnit.YEARS.between(u.getBirthDate(), LocalDate.now())).average().getAsDouble();
    }

    public boolean isPayableUser(User user){
       return getAllUsers().stream().anyMatch(u -> user.getName().equals(u.getName()) && ChronoUnit.YEARS.between(u.getBirthDate(), LocalDate.now()) > 18);
    }
}
