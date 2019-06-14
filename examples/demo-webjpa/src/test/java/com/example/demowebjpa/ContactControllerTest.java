package com.example.demowebjpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactControllerTest {

    @Autowired
    ContactController contactController;

    @MockBean
    ContactRepository contactRepository;

    @Test
    public void returnsStatusCodeNotFoundForNonexistingContacts() {

        ResponseEntity<Contact> contact = contactController.getContact(1L);

        assertThat(contact.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void returnsStatusCodeOkForExistingContacts() {

        given(contactRepository.findById(1L)).willReturn(Optional.of(new Contact()));
        ResponseEntity<Contact> contact = contactController.getContact(1L);

        assertThat(contact.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
