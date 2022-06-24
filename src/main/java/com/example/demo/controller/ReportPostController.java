package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.dto.ReportPostDTO;
import com.example.demo.enums.ReactionType;
import com.example.demo.model.Post;
import com.example.demo.model.ReactionPost;
import com.example.demo.model.ReportPost;
import com.example.demo.service.PostService;
import com.example.demo.service.ReportPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/reportPost")
public class ReportPostController {

    @Autowired
    private ReportPostService reportPostService;

    @Autowired
    private PostService postService;

    Logger logger = Logger.getLogger(ReportPostController.class.getName());

    @GetMapping
    public ResponseEntity<List<ReportPostDTO>> findAll(){

        List<ReportPost> reportPostList = reportPostService.findAll();
        List<ReportPostDTO> reportPostDTOS = new ArrayList<>();
        for (ReportPost reportPost : reportPostList){
            if (!reportPost.isAccepted() && reportPost.getActive().equals("true")){
                reportPostDTOS.add(new ReportPostDTO(reportPost));
            }

        }

        return new ResponseEntity<>(reportPostDTOS, HttpStatus.OK);
    };

    @PostMapping("/create")
    public ResponseEntity<ReportPostDTO> create(@RequestBody ReportPostDTO newReport){

        ReportPost createdReport = reportPostService.createReport(newReport);


        if(createdReport == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReportPostDTO reportPostDTO = new ReportPostDTO(createdReport);

        logger.info("USER HAS REPORTED POST");

        return new ResponseEntity<>(reportPostDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/acceptReport/{id}")
    public ResponseEntity<ReportPostDTO> acceptReportPost(@PathVariable Integer id) {

        ReportPost reportPost = reportPostService.getOne(id);
        Post post = postService.getOne(reportPost.getPost().getPostId());

        if (reportPost == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reportPost.setActive("false");
        reportPost.setAccepted(true);

        reportPost = reportPostService.save(reportPost);

        post.setActive("false");
        postService.save(post);

        logger.info("THE MODERATOR ACCEPTED THE REPORTED POST. THE POST WILL BE DELETED.");

        return new ResponseEntity<>(new ReportPostDTO(reportPost), HttpStatus.OK);
    }

    @DeleteMapping(value = "/declineReport/{id}")
    public ResponseEntity<ReportPostDTO> declineReportPost(@PathVariable Integer id) {


        ReportPost reportPost = reportPostService.getOne(id);

        if (reportPost == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reportPost.setActive("false");

        reportPost = reportPostService.save(reportPost);

        logger.info("THE MODERATOR DECLINE THE REPORTED POST.");

        return new ResponseEntity<>(new ReportPostDTO(reportPost), HttpStatus.OK);
    }

}
