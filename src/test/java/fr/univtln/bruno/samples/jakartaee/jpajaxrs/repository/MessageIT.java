package fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageIT {

    @Test
    void of() {
        Message message = Message.of("Hello");
        assertEquals("Hello", message.getContent());
    }
}