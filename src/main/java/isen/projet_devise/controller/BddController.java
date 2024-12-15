package isen.projet_devise.controller;

import isen.projet_devise.model.Rate;
import isen.projet_devise.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BddController {

    private final BddService bddService;

    public BddController(BddService bddService) {
        this.bddService = bddService;
    }

    @GetMapping("/")
    public String home() {
        return "index.html"; // Rends le fichier index.html dans templates
    }

    @PostMapping("/rates")
    public ResponseEntity<Boolean> postRate(@RequestPart("rate") MultipartFile rate) {
        try {
            return ResponseEntity.ok(bddService.postRate(rate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tickers")
    public ResponseEntity<List<String>> getTickers() {
        try {
            return ResponseEntity.ok(bddService.getTickers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rates/{ticker}")
    public ResponseEntity<List<Rate>> getTickers(@PathVariable String ticker) {
        try {
            return ResponseEntity.ok(bddService.getRateByTicker(ticker));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
