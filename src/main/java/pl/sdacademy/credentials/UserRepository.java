package pl.sdacademy.credentials;


public interface UserRepository {

    //C2. Utwórz interfejs UserRepository z metodami CRUD (readById, readAll, create, update, delete)

    void readById();

    void readAll();

    void create();

    void update();

    void delete();


}

