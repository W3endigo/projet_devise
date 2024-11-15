package isen.projet_devise.controller;

import isen.projet_devise.model.Cours;
import isen.projet_devise.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @GetMapping
    public Iterable<Cours> getAllRates() {
        return coursService.getAllRates();
    }

    @GetMapping("/{ticker}")
    public Iterable<Cours> getCoursByTicker(@PathVariable String ticker) {
        return coursService.getCoursByTicker(ticker);
    }
}

