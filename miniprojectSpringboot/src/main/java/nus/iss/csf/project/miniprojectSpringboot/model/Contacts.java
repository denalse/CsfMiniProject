package nus.iss.csf.project.miniprojectSpringboot.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Contacts {
    
    private String name;
    private String email;
    private int mobile;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = Integer.parseInt(mobile);
    }

    public static Contacts create(String json) {
        
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject data = reader.readObject();
        
        final Contacts contacts = new Contacts();
        contacts.setName(data.getString("name"));
        contacts.setEmail(data.getString("email"));
        contacts.setMobile(data.getString("mobile"));

        //JsonObjectBuilder builder = Json.createObjectBuilder()
        return contacts;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("name", name)
        .add("email", email)
        .add("mobile", mobile)
        .build();
    }

    public static Contacts createSql(SqlRowSet rs) {
        Contacts contact = new Contacts();
        contact.setName(rs.getString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setMobile(rs.getString("mobile"));
    
        return contact;
    }
    
}
