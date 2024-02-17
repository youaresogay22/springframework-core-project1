package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CsvStudents implements Students {


    /**
     * TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    private static CsvStudents csvStudents;

    public static Students getInstance() {
        if (csvStudents == null) {
            csvStudents = new CsvStudents();
        }
        return csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    private final Collection<Student> students = new ArrayList<>();

    @Override
    public void load() {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(CsvScores.class.getResourceAsStream("/data/score.csv")));
            List<String[]> lines = csvReader.readAll();
            lines.forEach(line -> students.add(new Student(Integer.parseInt(line[0]), line[1])));

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     *
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for (Student student : students) {
            for (Score score : scores) {
                if (student.getSeq() == score.getStudentSeq()) {
                    student.setScore(score);
                }
            }
        }
    }
}
