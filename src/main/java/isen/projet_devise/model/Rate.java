package isen.projet_devise.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Rate {
    private Date date;
    private double cours;
    private String ticker;
}