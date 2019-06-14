package com.example.demowebjdbc;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public List<Contact> getAll() {

        return contactService.findAllContacts();
    }

    @PostMapping("/")
    void postContact(@RequestBody Contact contact) {

        contactService.createContact(contact);
    }
}

@Service
class ContactService {

    private JdbcTemplate jdbcTemplate;

    public ContactService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Contact> findAllContacts() {

        return jdbcTemplate.query("select * from CONTACT", new BeanPropertyRowMapper<>(Contact.class));
    }

    public void createContact(Contact c) {
        jdbcTemplate.update("insert into CONTACT (FIRST_NAME, LAST_NAME, EMAIL) values (?, ?, ?)", c.firstName, c.lastName, c.email);
    }
}

class Contact {
    Long id;
    String firstName;
    String lastName;
    String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
