package com.example.demo.service.implementation;

import com.example.demo.dto.ReportDTO;
import com.example.demo.model.Reaction;
import com.example.demo.model.Report;
import com.example.demo.repository.ReportRepository;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Optional<Report> findOne(int id) {
        return reportRepository.findById(id);
    }

    @Override
    public Report getOne(int id) {
        return reportRepository.getById(id);
    }

    @Override
    public Report createReport(ReportDTO reportDTO) {
        return null;
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report save(Report report) {
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
