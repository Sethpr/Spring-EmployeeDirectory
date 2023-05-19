package io.zipcoder.persistenceapp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    Integer id;
    String name;
    Employee manager;
}
