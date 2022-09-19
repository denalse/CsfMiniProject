package nus.iss.csf.project.miniprojectSpringboot.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.iss.csf.project.miniprojectSpringboot.model.Contacts;

import static nus.iss.csf.project.miniprojectSpringboot.repositories.Queries.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate template;

    public boolean insertContact(Contacts cts) {

        int count = template.update(SQL_INSERT_CONTACT
            , cts.getName(), cts.getEmail(), cts.getMobile());

        return 1 == count;
    }

    // public Optional<List<Contacts>> getAllContacts() {
       
    //     final SqlRowSet result = template.queryForRowSet(
    //         SQL_LIST_CONTACT
    //     );

    //     if (!result.next())
    //         return Optional.empty();

    //     return Optional.of(Contacts.createSql(result));
    // } 

    public List<Contacts> getAllContacts() {

        final List<Contacts> contacts = new ArrayList<>();

        final SqlRowSet result = template.queryForRowSet(
            SQL_LIST_CONTACT
        );

        while (result.next()) {
            Contacts contact = Contacts.createSql(result);
            contacts.add(contact);
        }

        return contacts;
    }
}

