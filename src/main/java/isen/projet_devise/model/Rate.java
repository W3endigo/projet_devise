package isen.projet_devise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    private Timestamp date;
    private double rate;
    private String ticker;
}
