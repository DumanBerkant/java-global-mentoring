package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MailServerTest {
    MailServer mailServer;

    @BeforeEach
    public void before() {
        mailServer = new MailServer();
    }


    @CsvSource({
            "berkant@duman.com, message1",
            "berkantduman@outlook.com, message2"
    })
    @ParameterizedTest(name = "{index} => address=''{0}'', message=''{1}'' ")
    public void When_ParametersAreValid_Expect_NotificationShouldPushToQueue(String address, String message) {
        int responseCode = mailServer.send(address, message);
        assertEquals(200, responseCode);
    }


    @CsvSource({
            "berkantduman.com, message1",
            "berkantduma@noutlook.net, message2"
    })
    @ParameterizedTest(name = "{index} => address=''{0}'', message=''{1}'' ")
    public void When_AddressDoesNotValid_Expect_ThrowInvalidArgumentException(String address, String message) {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> mailServer.send(address, message)
        );

        assertEquals("Mail address must contain @, .com", exception.getMessage());
        assertNull(exception.getCause());
    }

    @CsvSource({
            "berkant@duman.com,",
            ", message2"
    })
    @ParameterizedTest(name = "{index} => address=''{0}'', message=''{1}'' ")
    public void When_AtLeastOneParameterIsNull_Expect_ThrowNullPointerException(String address, String message) {

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> mailServer.send(address, message)
        );

        assertEquals("address or messageContent can not be null", exception.getMessage());
        assertNull(exception.getCause());
    }

}
