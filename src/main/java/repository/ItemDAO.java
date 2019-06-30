package repository;

public interface ItemDAO<T> {
    T save(T domain);

    T update(T domain);

    T delete(T domain);
}
