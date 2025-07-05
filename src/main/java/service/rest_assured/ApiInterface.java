package service.rest_assured;

public interface ApiInterface {
    Object delete(int id);

    Object getById(int id);

    <T> Object create(T data);
}
