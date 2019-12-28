package com.etoak.service;

import com.etoak.bean.Movie;
import com.etoak.bean.MovieReview;
import com.etoak.bean.Page;
import com.etoak.bean.Tiaojian;

import java.util.List;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */
public interface MovieService {

    int addMovie(Movie movie);

    Page<Movie> queryList(int pageNum, int pageSize,Tiaojian tj);

    Movie queryOneById(String movieId);

    void updateMovie(Movie movie);

    List<MovieReview> queryReview(String movieId);

    void addReview(MovieReview mr);
}
