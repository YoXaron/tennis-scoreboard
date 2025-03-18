package com.yoxaron.tennis_scoreboard.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {

    List<E> findAll();

    Optional<E> findById(K id);

    E save(E entity);

    E update(E entity);

    void delete(E entity);
}
