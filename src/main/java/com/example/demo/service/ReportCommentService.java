package com.example.demo.service;

import com.example.demo.dto.ReportCommentDTO;
import com.example.demo.dto.ReportPostDTO;
import com.example.demo.model.ReportComment;
import com.example.demo.model.ReportPost;

import java.util.List;
import java.util.Optional;

public interface ReportCommentService {

    Optional<ReportComment> findOne(int id);
    ReportComment getOne(int id);
    ReportComment createReport(ReportCommentDTO reportDTO);
    List<ReportComment> findAll();
    ReportComment save(ReportComment report);
    void delete(int id);

}
