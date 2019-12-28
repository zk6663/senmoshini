package com.etoak.service.impl;

import com.etoak.bean.*;
import com.etoak.mapper.MovieMapper;
import com.etoak.mapper.MovieReviewMapper;
import com.etoak.service.MovieService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieReviewMapper movieReviewMapper;

    @Override
    public int addMovie(Movie movie) {
        int result = movieMapper.insert(movie);
        return result;
    }

    @Override
    public Page<Movie> queryList(int pageNum, int pageSize, Tiaojian tj) {
        //System.out.println(tj);
        MovieExample example = new MovieExample();
        MovieExample.Criteria cri = example.createCriteria();
        if(tj!=null && tj.getTjName()!=null && !tj.getTjName().equals("")) {
            System.out.println("zoul");
            cri.andMovieNameLike("%"+tj.getTjName()+"%");
        }
        if(tj!=null && tj.getTjActor()!=null && !tj.getTjActor().equals("")) {
            cri.andMovieActorLike("%"+tj.getTjActor()+"%");
        }
        if(tj!=null && tj.getTjType()!=null && !tj.getTjType().equals("")) {
            cri.andMovieTypeLike("%"+tj.getTjType()+"%");
        }
        if(tj!=null && tj.getTjOntimeStart()!=null && !tj.getTjOntimeStart().equals("")) {
            cri.andMovieOntimeGreaterThanOrEqualTo(tj.getTjOntimeStart());
        }
        if(tj!=null && tj.getTjOntimeEnd()!=null && !tj.getTjOntimeEnd().equals("")) {
            cri.andMovieOntimeLessThanOrEqualTo(tj.getTjOntimeEnd());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Movie> data = movieMapper.selectByExample(example);
        PageInfo<Movie> pageInfo = new PageInfo<>(data);
        Page<Movie> page = new Page<>();
        page.setRows(pageInfo.getList());
        page.setTotal((int)pageInfo.getTotal());
        page.setPageNumber(pageNum);
        page.setPageSize(pageSize);
        return page;
    }

    @Override
    public Movie queryOneById(String movieId) {
        return movieMapper.selectByPrimaryKey(Integer.parseInt(movieId));
    }

    @Override
    public void updateMovie(Movie movie) {
        movieMapper.updateByPrimaryKey(movie);
    }

    @Override
    public List<MovieReview> queryReview(String movieId) {
        MovieReviewExample example = new MovieReviewExample();
        MovieReviewExample.Criteria cri = example.createCriteria();
        cri.andMovieIdEqualTo(Integer.parseInt(movieId));
        List<MovieReview> data = movieReviewMapper.selectByExample(example);
        return data;
    }

    @Override
    public void addReview(MovieReview mr) {
        movieReviewMapper.insert(mr);
    }
}
