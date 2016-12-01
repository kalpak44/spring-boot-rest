package pavel.usanli.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Pavel Usanli on 26.11.2016.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personalId;
    private String name;
    private String surname;

    public User(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
    }
    public User() {
    }

    public Long getPersonalId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    public User setName(final String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(final String surname) {
        this.surname = surname;
        return this;
    }

}
