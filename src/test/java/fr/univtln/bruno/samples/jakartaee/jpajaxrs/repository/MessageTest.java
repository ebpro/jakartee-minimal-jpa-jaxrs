package fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void of() {
        Message message = Message.of("Hello");
        assertEquals("Hello", message.getContent());
    }
}