package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.Fast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateEngineTest {

    TemplateEngine templateEngine;


    @BeforeEach
    public void before() {
        this.templateEngine = new TemplateEngine();
    }


    @Test
    @Fast
    public void When_RuntimeVariablesGiven_Expect_ReplaceVariablesWithPlaceholders() {
        Template template = new Template("This is Subject", "Topic A", "This is my message to all world");
        Client client = new Client();
        client.setAddresses("deneme@deneme.com");
        String message = templateEngine.generateMessage(template, client);

        assertTrue(
                message.contains(template.getMessage())
                        && message.contains(template.getSubject())
                        && message.contains(template.getTopic())
        );

    }

    @Test
    public void When_AtLeastOnePlaceholderVariableIsMissing_Expect_TemplateThrowAnException() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template(null, "Topic A", null);
        Client client = new Client();
        client.setAddresses("deneme@deneme.com");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> templateEngine.generateMessage(template, client)
        );

        // Use the assertEquals() method to check the properties of the thrown exception
        assertEquals("All template placeholders must provide", exception.getMessage());
        assertNull(exception.getCause());

    }

    @Test
    public void When_VariableHasPlaceholderRegex_Expect_TemplatePassValue() {
        Template template = new Template("This is Subject", "Topic A", "${tag}");
        Client client = new Client();
        client.setAddresses("deneme@deneme.com");
        String message = templateEngine.generateMessage(template, client);

        assertTrue(
                message.contains(template.getMessage())
                        && message.contains(template.getSubject())
                        && message.contains(template.getTopic())
        );
    }

    @Test
    public void When_ReplacedThePlaceHolders_Expect_SystemSupportAllLatin1Chars() {
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("This is Subject", "Topic A", "This is my message to all world");
        Client client = new Client();
        client.setAddresses("deneme@deneme.com");
        String message = templateEngine.generateMessage(template, client);

        assertFalse(message.matches("[^\\x00-\\xFF]"));
    }


    @Disabled("This test is disabled")
    public void disabledTest() {

    }


}
