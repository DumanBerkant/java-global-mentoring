package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MailServerTest {


    @CsvSource({
            "berkant@duman.com, message1",
            "berkantduman@outlook.com, message2"
    })
    @ParameterizedTest(name = "{index} => address=''{0}'', message=''{1}'' ")
    public void When_ParametersAreValid_Expect_NotificationShouldPushToQueue(String address, String message) {
        System.out.println(address + " - " +  message);
    }

    @Test
    public void When_AddressDoesNotValid_Expect_ThrowInvalidArgumentException() {
    }

    @Test
    public void When_AtLeastOneParameterIsNull_Expect_ThrowNullPointerException() {
    }

}
