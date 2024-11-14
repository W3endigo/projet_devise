package isen.projet_devise.controller;

import isen.projet_devise.service.BddService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
public class BddController {

    private final BddService bddService;

    public BddController(BddService bddService) {
        this.bddService = bddService;
    }
}
