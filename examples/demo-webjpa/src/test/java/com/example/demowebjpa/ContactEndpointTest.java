package com.example.demowebjpa;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactEndpointTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ContactRepository contactRepository;

    @Test
    public void contactResponseContainsExpectedFields() throws Exception {

        Contact contact = new Contact() {{
            id = 1L;
            firstName = "Bent";
            lastName = "Solheim";
            email = "bent@example.com";
        }};

        given(contactRepository.findById(1L)).willReturn(Optional.of(contact));

        MvcResult result = mvc.perform(get("/contact/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(contact.getId().intValue())))
            .andExpect(jsonPath("$.firstName", is(contact.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(contact.getLastName())))
            .andExpect(jsonPath("$.email", is(contact.getEmail())))
            .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
