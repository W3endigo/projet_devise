package isen.projet_devise.dao;

import isen.projet_devise.model.Rate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BddDAO {

    boolean checkTicker(String ticker);

    void insertTicker(String ticker);

    void insertRates(List<Rate> rates);

    List<String> getTickers();

    List<Rate> getRatesByTicker(String ticker);
}
