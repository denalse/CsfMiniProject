package nus.iss.csf.project.miniprojectSpringboot.repositories;

public interface Queries {

    public static final String SQL_INSERT_CONTACT =
    "insert into contacts(name, email, mobile) values (?, ?, ?)";

    public static final String SQL_LIST_CONTACT =
    "select * from contacts";
}
