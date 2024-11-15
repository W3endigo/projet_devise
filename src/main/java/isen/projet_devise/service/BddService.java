package isen.projet_devise.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import isen.projet_devise.dao.BddDAO;
import isen.projet_devise.dao.RateRepository;
import isen.projet_devise.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BddService {

    private final BddDAO bddDAO;

    public BddService(BddDAO bddDAO) {
        this.bddDAO = bddDAO;
    }

    public boolean postRate(MultipartFile rate) throws Exception {
        if (!isCsvFile(rate)) {
            throw new IllegalArgumentException("File is not a CSV");
        }

        try (CSVReader reader = new CSVReader(new InputStreamReader(rate.getInputStream()))) {
            List<String[]> records = reader.readAll();
            List<Rate> rates = new ArrayList<>();

            var ticker = records.getFirst()[1].split("_")[0];

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                var date = Timestamp.valueOf(LocalDateTime.parse(record[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
                var cours = Double.parseDouble(record[1]);
                rates.add(new Rate(date, cours, ticker));
            }
            checkTicker(ticker);
            bddDAO.insertRates(rates);
        } catch (CsvException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        return true;
    }

    // Checks if the ticker is in the database
    private void checkTicker(String ticker) {
        if (!bddDAO.checkTicker(ticker)) {
            bddDAO.insertTicker(ticker);
        }
    }

    private boolean isCsvFile(MultipartFile file) {
        String contentType = file.getContentType();
        return "text/csv".equals(contentType) || Objects.requireNonNull(file.getOriginalFilename()).endsWith(".csv");
    }

    @Autowired
    private RateRepository coursRepository;

    public Iterable<Rate> getAllRates() {
        return coursRepository.findAll();
    }

    public Iterable<Rate> getRateByTicker(String ticker) {
        return coursRepository.findRateByTicker(ticker);
    }

}