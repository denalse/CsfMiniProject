package nus.iss.csf.project.miniprojectSpringboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import nus.iss.csf.project.miniprojectSpringboot.model.Contacts;
import nus.iss.csf.project.miniprojectSpringboot.model.Response;
import nus.iss.csf.project.miniprojectSpringboot.repositories.ContactRepository;
import nus.iss.csf.project.miniprojectSpringboot.services.contactsService;

@RestController
@RequestMapping(path="/api")
public class ContactsController {
    
    @Autowired
    private ContactRepository repo;

    @Autowired
    private contactsService contactSvc;

    private Logger logger = Logger.getLogger(ContactsController.class.getName());

    @PostMapping(path="/addContact", consumes = MediaType.APPLICATION_JSON_VALUE )
    public Optional<Contacts> createContact(@RequestBody String payload) {

        Contacts contacts;
        Response resp;

        logger.info("Payload: %s".formatted(payload));

        try {
            contacts = Contacts.create(payload);
            repo.insertContact(contacts);

            resp = new Response();
            resp.setCode(201);
            resp.setMessage("abc");
            resp.setData(contacts.toJson().toString());

            return Optional.of(contacts);
        } catch(Exception ex) {
            resp = new Response();
            resp.setCode(400);
            resp.setMessage(ex.getMessage());

            logger.info("Error: %s".formatted(resp.toJson().toString()));
            return Optional.empty();
        }     
    }

    @GetMapping(path="/listContacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllContacts() {
        Response resp = new Response();

        List<Contacts> cl = contactSvc.getAllContacts();
        // JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        JsonArray contactJson;

        for (Contacts c: cl)
            arrBuilder.add(c.toJson());
            contactJson = arrBuilder.build();

        logger.info(arrBuilder.build().toString());
        resp.setData(contactJson.toString());
        resp.setCode(HttpStatus.OK.value());
        resp.setMessage("Success");
        return ResponseEntity.ok(resp.toJson().toString());
    }

}
