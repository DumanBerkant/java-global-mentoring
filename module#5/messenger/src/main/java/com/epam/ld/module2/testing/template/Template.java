package com.epam.ld.module2.testing.template;

/**
 * The type Template.
 */
public class Template {

    private String subject;
    private String message;
    private String topic;

    public Template(String subject, String topic, String message){
        this.topic = topic;
        this.message = message;
        this.subject = subject;

    }


    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getTopic() {
        return topic;
    }

}
