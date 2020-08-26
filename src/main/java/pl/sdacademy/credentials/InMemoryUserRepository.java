package pl.sdacademy.credentials;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    private List<User> userList = new ArrayList<>();

    public InMemoryUserRepository() {
        User user = new User(0, "Przemek", "Piwonski", LocalDate.of(1991, 9, 17), true);
        userList.add(user);
    }

    @Override
    public void readById(int id) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
