package com.hiyoon.springbatchjenkins.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class People {
    private long personId;
    private String firstName;
    private String lastName;

    @Id
    @Column(name = "person_id")
    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return personId == people.personId && Objects.equals(firstName, people.firstName) && Objects.equals(lastName, people.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName);
    }
}
