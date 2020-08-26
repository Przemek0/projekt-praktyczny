package pl.sdacademy.credentials;

import pl.sdacademy.entities.AbstractEntity;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class User extends AbstractEntity {
    //C1 Utwórz klasę User w pakiecie pl.sdacademy.credentials,
    // o polach: id, firstName, lastName, dateOfBirth, admin (boolean)
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean admin;

    public User() {
    }

    public User(String firstName, String lastName, LocalDate dateOfBirth, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.admin = admin;
    }

    public User(int id, String firstName, String lastName, LocalDate dateOfBirth, boolean admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.admin = admin;
    }


}
