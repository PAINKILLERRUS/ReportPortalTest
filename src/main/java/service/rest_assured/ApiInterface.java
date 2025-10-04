package service.rest_assured;

import java.util.List;

public interface ApiInterface {
    Object delete(int id);

    Object getById(int id);

    <T> Object create(T data);

    List<? extends Dto> getAll();
}
