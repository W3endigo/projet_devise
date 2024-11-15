package isen.projet_devise.controller;

import isen.projet_devise.model.Rate;
import isen.projet_devise.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class BddController {

    private final BddService bddService;

    public BddController(BddService bddService) {
        this.bddService = bddService;
    }

    @PostMapping("/rate")
    public ResponseEntity<Boolean> postRate(@RequestPart("rate") MultipartFile rate) {
        try {
            return ResponseEntity.ok(bddService.postRate(rate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
