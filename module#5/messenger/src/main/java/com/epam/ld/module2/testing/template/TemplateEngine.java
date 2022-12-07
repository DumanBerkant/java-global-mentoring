package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        isAllPlaceHoldersProvided(template);
        isClientValid(client);
        return replacePlaceHoldersWithValues(template);
    }

    private String replacePlaceHoldersWithValues(Template template) {
        String initialTemplate =  "Subject: #{subject}" +
                "\n" +
                "Topic: #{topic}" +
                "\n" +
                "To: #{to}" +
                "\n" +
                "Message: #{message}";

        initialTemplate = initialTemplate.replace("#{subject}", template.getSubject());
        initialTemplate = initialTemplate.replace("#{topic}", template.getTopic());
        initialTemplate = initialTemplate.replace("#{message}", template.getMessage());

        return initialTemplate;
    }

    private void isAllPlaceHoldersProvided(Template template){
        if(template.getMessage() == null || template.getTopic() == null || template.getSubject() == null)
            throw new IllegalArgumentException("All template placeholders must provide");
    }

    private void isClientValid(Client client){
        if(client == null || client.getAddresses() == null)
            throw new IllegalArgumentException("Client address must be defined");
    }

}
