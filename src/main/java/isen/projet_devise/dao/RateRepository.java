package isen.projet_devise.dao;

import isen.projet_devise.model.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RateRepository extends CrudRepository<Rate, Long> {

    Iterable<Rate> findRateByTicker(String Ticker);

}
