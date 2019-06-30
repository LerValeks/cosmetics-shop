package repository;

import java.util.List;

public abstract class DAOitemAbstr<T> implements DAOitem<T> {

    @Override
    public T add(T domain) {
    return null;
    }

    @Override
    public T delete(T domain) {
        return null;
    }

    @Override
    public T update(T domain) {
        return null;
    }

    @Override
    public Class<?> getType(T domain) {
        return domain.getClass();
    }

    @Override
    public T get(Number id) {
        return null;
    }

    @Override
    public List<T> loadAll() {
        return null;
    }
}
