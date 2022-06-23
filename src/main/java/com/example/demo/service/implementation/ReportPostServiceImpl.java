package com.example.demo.service.implementation;

import com.example.demo.dto.ReportPostDTO;
import com.example.demo.model.ReportPost;
import com.example.demo.repository.ReportRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.ReportPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportPostServiceImpl implements ReportPostService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public Optional<ReportPost> findOne(int id) {
        return reportRepository.findById(id);
    }

    @Override
    public ReportPost getOne(int id) {
        return reportRepository.getById(id);
    }

    @Override
    public ReportPost createReport(ReportPostDTO reportDTO) {

        Optional<ReportPost> reportPost = reportRepository.findById(reportDTO.getReportId());

        if(reportPost.isPresent()){
            return null;
        }

        ReportPost newReport = new ReportPost();
        newReport.setReason(reportDTO.getReason());
        newReport.setTimestamp(LocalDate.now());
        newReport.setByUser(userService.findOneById(reportDTO.getByUser()));
        newReport.setPost(postService.getOne(reportDTO.getPost()));
        newReport.setAccepted(false);
        newReport.setActive("true");

        newReport = reportRepository.save(newReport);


        return newReport;
    }

    @Override
    public List<ReportPost> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public ReportPost save(ReportPost report) {
        try{
            return reportRepository.save(report);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        reportRepository.deleteById(id);
    }
}
