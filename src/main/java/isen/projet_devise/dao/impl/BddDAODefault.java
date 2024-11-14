package isen.projet_devise.dao.impl;

import isen.projet_devise.dao.BddDAO;
import isen.projet_devise.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BddDAODefault implements BddDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean checkTicker(String ticker) {
        String sql = "SELECT COUNT(*) FROM devise WHERE ticker = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, ticker) > 0;
    }

    @Override
    public void insertTicker(String ticker) {
        String sql = "INSERT INTO devise (ticker) VALUES (?)";
        jdbcTemplate.update(sql, ticker);
    }

    @Override
    public void insertRates(List<Rate> rates) {
        String sql = "INSERT INTO cours (date, cours, ticker) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, rates, rates.size(), (ps, rate) -> {
            ps.setString(1, rate.getDate().toString());
            ps.setDouble(2, rate.getCours());
            ps.setString(3, rate.getTicker());
        });
    }


}
