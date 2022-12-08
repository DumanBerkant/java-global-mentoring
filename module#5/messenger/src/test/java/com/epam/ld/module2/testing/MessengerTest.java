package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MessengerTest {

    Client client;
    Template template;

    @BeforeEach
    public void before() {
        template = new Template("This is Subject", "This is Topic", "This is message");
        client = new Client();
        client.setAddresses("berkant_duman@epam.com");

    }

    @Test
    public void When_MessageSended_Expect_generateMessageShouldBeCalled() {
        TemplateEngine templateEngine = Mockito.mock(TemplateEngine.class);
        MailServer mailServer = Mockito.mock(MailServer.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        messenger.sendMessage(client, template);

        Mockito.verify(templateEngine, Mockito.times(1)).generateMessage(template, client);
    }



    @Test
    public void When_MessageSended_Expect_generateMessageShouldReturnCorrectValue() {
        TemplateEngine templateEngine = Mockito.spy(TemplateEngine.class);
        MailServer mailServer = Mockito.mock(MailServer.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        messenger.sendMessage(client, template);

        Mockito.verify(templateEngine).generateMessage(template, client);
        Mockito.verify(mailServer).send("berkant_duman@epam.com",
                "Subject: This is Subject" +
                        "\n" +
                        "Topic: This is Topic" +
                        "\n" +
                        "Message: This is message");
    }


    @Test
    @Disabled
    public void When_MessageSended_Expect_MailServerSendShouldBeCalled() {
        TemplateEngine templateEngine = Mockito.mock(TemplateEngine.class);
        MailServer mailServer = Mockito.mock(MailServer.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        messenger.sendMessage(client, template);

        Mockito.verify(mailServer, Mockito.times(1));


    }

}