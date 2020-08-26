package pl.sdacademy.credentials;

import java.util.List;

//C2. Utwórz interfejs UserRepository z metodami CRUD (readById, readAll, create, update, delete)
public interface UserRepository {
    public User readById(int id);

    public List<User> readAll();

    public void create(User user);

    public void update(User user);

    public void delete(int id);
}

