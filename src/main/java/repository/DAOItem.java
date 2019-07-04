package repository;

import java.util.List;

public interface DAOItem<T> {

    T add(T domain);

    T delete(T domain);


    T update(T domain);

    T getItem(Long id);

    List<T> getAllItems();
}