package isen.projet_devise.service;

import isen.projet_devise.dao.CoursRepository;
import isen.projet_devise.model.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public Iterable<Cours> getAllRates() {
        return coursRepository.findAll();
    }

    public Iterable<Cours> getCoursByTicker(String ticker) {
        return coursRepository.findCoursByTicker(ticker);
    }

}