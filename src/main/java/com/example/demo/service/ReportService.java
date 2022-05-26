package com.example.demo.service;

import com.example.demo.dto.ReportDTO;
import com.example.demo.model.Post;
import com.example.demo.model.Reaction;
import com.example.demo.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    Optional<Report> findOne(int id);
    Report getOne(int id);
    Report createReport(ReportDTO reportDTO);
    List<Report> findAll();
    Report save(Report report);
    void delete(int id);

}
