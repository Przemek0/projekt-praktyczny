package pl.sdacademy.credentials;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    //C1 Utwórz klasę User w pakiecie pl.sdacademy.credentials, o polach: id, firstName, lastName, dateOfBirth, admin (boolean)
    int id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    boolean admin;

}
