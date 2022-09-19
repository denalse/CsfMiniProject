package nus.iss.csf.project.miniprojectSpringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.csf.project.miniprojectSpringboot.model.Contacts;
import nus.iss.csf.project.miniprojectSpringboot.repositories.ContactRepository;

@Service
public class contactsService {
   
    @Autowired
    private ContactRepository ctsRepo; 

    public Optional<String> createContact(Contacts cts) {
        return Optional.empty();
    }

    public List<Contacts> getAllContacts() {
        List<Contacts> contactList = ctsRepo.getAllContacts();

        return contactList;
    }
}