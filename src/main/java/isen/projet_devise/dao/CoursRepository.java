package isen.projet_devise.dao;

import isen.projet_devise.model.Cours;
import org.springframework.data.repository.CrudRepository;

public interface CoursRepository extends CrudRepository<Cours, Long> {

    Iterable<Cours> findCoursByTicker(String Ticker);

}
