package com.etoak.controller;

import com.etoak.bean.MovieReview;
import com.etoak.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */
@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    MovieService movieService;

    @RequestMapping("queryReview")
    @ResponseBody
    public List<MovieReview> queryReview(
            @RequestParam("mid") String movieId
    ){
        List<MovieReview> result = movieService.queryReview(movieId);
        return result;
    }

    @RequestMapping("/addReview")
    @ResponseBody
    public String queryReview(
            @RequestParam("movieId") String movieId,
            @RequestParam("username") String username,
            @RequestParam("content") String content
    ){
        MovieReview mr = new MovieReview();
        mr.setMovieId(Integer.parseInt(movieId));
        mr.setContent(content);
        mr.setUsername(username);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = sdf.format(date);
        System.out.println(ss);
        mr.setReviewCreate(ss);
        movieService.addReview(mr);
        return "success";
    }

}
