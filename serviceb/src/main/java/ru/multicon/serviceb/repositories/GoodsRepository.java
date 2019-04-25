package ru.multicon.serviceb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.multicon.serviceb.entities.Good;

@Repository
public interface GoodsRepository extends JpaRepository<Good, Integer> {
}
