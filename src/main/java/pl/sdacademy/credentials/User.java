package pl.sdacademy.credentials;

import pl.sdacademy.entities.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class User extends AbstractEntity {


    //C1 Utwórz klasę User w pakiecie pl.sdacademy.credentials, o polach: id, firstName, lastName, dateOfBirth, admin (boolean)
    int id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    boolean admin;


}
