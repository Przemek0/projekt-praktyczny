package pl.sdacademy.credentials;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> userList = new ArrayList<>();

    public InMemoryUserRepository() {
        User user = new User(0, "Przemek", "Piwonski", LocalDate.of(1991, 9, 17), true);
        userList.add(user);
    }

    @Override
    public User readById(int id) {
        User user;
        try {
            user = userList.stream()
                    .filter(u -> u.getId() == id)
                    .collect(Collectors.toList())
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            user = null;
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        return userList;
    }

    @Override
    public void create(User user) {
        userList.add(user);
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(int id) {

    }
}
