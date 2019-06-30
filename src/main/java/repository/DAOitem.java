package repository;

public interface DAOitem<T> {
    T add(T domain);
    T delete (T domain);
    T  update (T domain);

}
