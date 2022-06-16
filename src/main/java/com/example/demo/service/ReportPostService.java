package com.example.demo.service;

import com.example.demo.dto.ReportPostDTO;
import com.example.demo.model.ReportPost;

import java.util.List;
import java.util.Optional;

public interface ReportPostService {

    Optional<ReportPost> findOne(int id);
    ReportPost getOne(int id);
    ReportPost createReport(ReportPostDTO reportDTO);
    List<ReportPost> findAll();
    ReportPost save(ReportPost report);
    void delete(int id);

}
