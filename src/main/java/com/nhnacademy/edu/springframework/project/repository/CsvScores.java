package com.nhnacademy.edu.springframework.project.repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    private CsvScores() {
    }

    /**
     * TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    private static CsvScores csvScores;

    public static Scores getInstance() {
        if (csvScores == null) {
            csvScores = new CsvScores();
        }
        return csvScores;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    private final List<Score> scores = new ArrayList<>();

    @Override
    public void load() {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(CsvScores.class.getResourceAsStream("/data/score.csv")));
            List<String[]> lines = csvReader.readAll();
            lines.forEach(line -> scores.add(new Score(Integer.parseInt(line[0]), Integer.parseInt(line[1]))));

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scores;
    }
}
