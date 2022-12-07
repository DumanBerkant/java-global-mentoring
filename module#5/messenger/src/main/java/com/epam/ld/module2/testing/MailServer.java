package com.epam.ld.module2.testing;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses      the addresses
     * @param messageContent the message content
     */
    public int send(String addresses, String messageContent) {

        if (isParametersValid(addresses, messageContent)) {
            if (isAddressNotValid(addresses))
                throw new IllegalArgumentException("Mail address must contain @, .com");
            return 200;
        }

        throw new NullPointerException("address or messageContent can not be null");

    }

    private boolean isParametersValid(String addresses, String messageContent) {
        return addresses != null && messageContent != null;
    }

    private boolean isAddressNotValid(String addresses) {
        return !(addresses.contains("@") && addresses.contains(".com"));
    }
}
