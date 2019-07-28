package repository;

import java.util.Set;

public interface DAOItem<T> {

    T add(T domain);

    T update(T domain);

    T delete(T domain);

    T getItem(String phoneNumber);

    Set<T> getAllItems();
}