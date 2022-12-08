package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.annotation.Fast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateEngineTest {

    TemplateEngine templateEngine;
    Client client;


    @BeforeEach
    public void before() {
        client = new Client();
        client.setAddresses("deneme@deneme.com");
        this.templateEngine = new TemplateEngine();
    }


    @Test
    @Fast
    public void When_RuntimeVariablesGiven_Expect_ReplaceVariablesWithPlaceholders() {
        Template template = new Template("This is Subject", "Topic A", "This is my message to all world");
        String message = templateEngine.generateMessage(template, client);

        assertTrue(
                message.contains(template.getMessage())
                        && message.contains(template.getSubject())
                        && message.contains(template.getTopic())
        );

    }

    @Test
    public void When_AtLeastOnePlaceholderVariableIsMissing_Expect_TemplateThrowAnException() {
        Template template = new Template(null, "Topic A", null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> templateEngine.generateMessage(template, client)
        );

        assertEquals("All template placeholders must provide", exception.getMessage());
        assertNull(exception.getCause());

    }

    @Test
    public void When_VariableHasPlaceholderRegex_Expect_TemplatePassValue() {
        Template template = new Template("This is Subject", "Topic A", "${tag}");
        String message = templateEngine.generateMessage(template, client);

        assertTrue(
                message.contains(template.getMessage())
                        && message.contains(template.getSubject())
                        && message.contains(template.getTopic())
        );
    }

    @Test
    public void When_ReplacedThePlaceHolders_Expect_SystemSupportAllLatin1Chars() {
        Template template = new Template("This is Subject", "Topic A", "This is my message to all world");
        String message = templateEngine.generateMessage(template, client);

        assertFalse(message.matches("[^\\x00-\\xFF]"));
    }


    @Test
    public void When_ClientIsNotValid_Expect_ThrownAnException() {
        Template template = new Template("This is a Subject", "Topic A", "This is my message");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> templateEngine.generateMessage(template,  null)
        );

        assertEquals("Client address must be defined", exception.getMessage());
        assertNull(exception.getCause());
    }


    @Disabled("This test is disabled")
    public void disabledTest() {

    }


}
