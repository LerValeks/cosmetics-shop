package repository;

import java.util.List;

public interface DatabaseInteraction<T> {

    T addToDatabase(T databaseItem);

    T deleteFromDatabase(T databaseItem);

    T updateDatabase(T databaseItem);

    T getItemFromDatabase(Integer itemID);

    List<T> getAllItemsFromDatabase();
}