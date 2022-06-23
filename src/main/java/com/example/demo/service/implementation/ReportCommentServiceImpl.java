package com.example.demo.service.implementation;

import com.example.demo.dto.ReportCommentDTO;
import com.example.demo.model.ReportComment;
import com.example.demo.model.ReportPost;
import com.example.demo.repository.ReportCommentRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.ReportCommentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportCommentServiceImpl implements ReportCommentService {

    @Autowired
    private ReportCommentRepository reportCommentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Override
    public Optional<ReportComment> findOne(int id) {
        return reportCommentRepository.findById(id);
    }

    @Override
    public ReportComment getOne(int id) {
        return reportCommentRepository.getById(id);
    }

    @Override
    public ReportComment createReport(ReportCommentDTO reportDTO) {
        Optional<ReportComment> reportComment = reportCommentRepository.findById(reportDTO.getReportId());

        if(reportComment.isPresent()){
            return null;
        }

        ReportComment newReport = new ReportComment();
        newReport.setReason(reportDTO.getReason());
        newReport.setTimestamp(LocalDate.now());
        newReport.setByUser(userService.findOneById(reportDTO.getByUser()));
        newReport.setComment(commentService.getOne(reportDTO.getComment()));
        newReport.setAccepted(false);
        newReport.setActive("true");

        newReport = reportCommentRepository.save(newReport);


        return newReport;
    }

    @Override
    public List<ReportComment> findAll() {
        return reportCommentRepository.findAll();
    }

    @Override
    public ReportComment save(ReportComment report) {
        try{
            return reportCommentRepository.save(report);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        reportCommentRepository.deleteById(id);
    }
}
