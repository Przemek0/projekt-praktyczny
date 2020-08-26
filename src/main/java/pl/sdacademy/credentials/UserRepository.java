package pl.sdacademy.credentials;

import java.util.List;

//C2. Utwórz interfejs UserRepository z metodami CRUD (readById, readAll, create, update, delete)
public interface UserRepository {
    public void readById(int id);

    public List<User> readAll();

    public void create();

    public void update();

    public void delete();
}

