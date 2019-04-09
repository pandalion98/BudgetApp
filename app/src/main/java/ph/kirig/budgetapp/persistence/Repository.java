package ph.kirig.budgetapp.persistence;

import java.util.List;

/**
 * Created by Gene on 09/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public interface Repository<T> {
    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    List<T> query(Query q);
}
