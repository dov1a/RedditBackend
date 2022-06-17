package com.example.demo.controller;

import com.example.demo.dto.ReportCommentDTO;
import com.example.demo.dto.ReportPostDTO;
import com.example.demo.model.ReportComment;
import com.example.demo.model.ReportPost;
import com.example.demo.service.ReportCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reportComment")
public class ReportCommentController {

    @Autowired
    private ReportCommentService reportCommentService;

    @GetMapping
    public ResponseEntity<List<ReportCommentDTO>> findAll(){

        List<ReportComment> reportCommentList = reportCommentService.findAll();
        List<ReportCommentDTO> reportCommentDTOS = new ArrayList<>();
        for (ReportComment reportComment : reportCommentList){
            if (!reportComment.isAccepted() && reportComment.getActive().equals("true")){
                reportCommentDTOS.add(new ReportCommentDTO(reportComment));
            }

        }

        return new ResponseEntity<>(reportCommentDTOS, HttpStatus.OK);
    };

    @PostMapping("/create")
    public ResponseEntity<ReportCommentDTO> create(@RequestBody ReportCommentDTO newReport){

        ReportComment createdReport = reportCommentService.createReport(newReport);


        if(createdReport == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReportCommentDTO reportCommentDTO = new ReportCommentDTO(createdReport);

        return new ResponseEntity<>(reportCommentDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/acceptReport/{id}")
    public ResponseEntity<ReportCommentDTO> acceptReportComment(@PathVariable Integer id) {

        ReportComment reportComment = reportCommentService.getOne(id);

        if (reportComment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reportComment.setActive("false");
        reportComment.setAccepted(true);

        reportComment = reportCommentService.save(reportComment);

        return new ResponseEntity<>(new ReportCommentDTO(reportComment), HttpStatus.OK);
    }

    @DeleteMapping(value = "/declineReport/{id}")
    public ResponseEntity<ReportCommentDTO> declineReportComment(@PathVariable Integer id) {

        ReportComment reportComment = reportCommentService.getOne(id);

        if (reportComment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reportComment.setActive("false");

        reportComment = reportCommentService.save(reportComment);

        return new ResponseEntity<>(new ReportCommentDTO(reportComment), HttpStatus.OK);
    }


}
