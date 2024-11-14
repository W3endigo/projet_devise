package isen.projet_devise.service;

import isen.projet_devise.dao.BddDAO;
import org.springframework.stereotype.Service;

@Service
public class BddService {

    private final BddDAO bddDAO;

    public BddService(BddDAO bddDAO) {
        this.bddDAO = bddDAO;
    }
}
