package repository;

import java.util.List;

public interface DAOitem<T> {
    T add(T domain);
    T delete (T domain);
    T  update (T domain);
    Class<? extends Object> getType(T domain);
    T get (Number id);
    List<T> loadAll();

}
