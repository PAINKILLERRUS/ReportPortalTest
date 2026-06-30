package service.rest_assured.crud_interfaces;

import service.rest_assured.Dto;

import java.util.List;

public interface GetAllAndDelete {
    Object delete(int id);

    List<? extends Dto> getAll();
}
