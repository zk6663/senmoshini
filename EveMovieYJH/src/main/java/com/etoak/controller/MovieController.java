package com.etoak.controller;

import com.etoak.bean.Movie;
import com.etoak.bean.Page;
import com.etoak.bean.Tiaojian;
import com.etoak.service.MovieService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */

@Controller
@RequestMapping("/movie")
public class MovieController {

    private static final String DEST_FILE_DIR = "d:/upload";

    @Autowired
    MovieService movieService;

    @RequestMapping("/add")
    public String addUser(Movie movie,
                          @RequestParam("type") String type,
                          @RequestParam("pic") MultipartFile file
    ) throws IllegalStateException, IOException {
        //获取文件名称
        String oldFilename = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(oldFilename);
        //获取一个新的文件名
        String newName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
        //创建新的文件
        File destFile = new File(DEST_FILE_DIR, newName);
        file.transferTo(destFile);
        movie.setMoviePic("/pic/" + newName);
        type = type.replaceAll(",","/");
        System.out.println(type);
        movie.setMovieType(type);
        movie.setMovieOperation(0);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = sdf.format(date);
        System.out.println(ss);
        movie.setMovieCreate(ss);
        int result = movieService.addMovie(movie);
        System.out.println(result);
        return "redirect:/user/to_admin";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Page<Movie> queryList(
            @RequestParam(required=false,defaultValue="1") int pageNumber,
            @RequestParam(required=false,defaultValue="2") int pageSize,
            @RequestParam("tjName") String tjName,
            @RequestParam("tjActor") String tjActor,
            @RequestParam("tjType") String tjType,
            @RequestParam("tjOntimeStart") String tjOntimeStart,
            @RequestParam("tjOntimeEnd") String tjOntimeEnd
    ){
        System.out.println(pageNumber + "  " + pageSize);
        Tiaojian tj = new Tiaojian();
        tj.setTjName(tjName);
        tj.setTjActor(tjActor);
        tj.setTjType(tjType);
        tj.setTjOntimeStart(tjOntimeStart);
        tj.setTjOntimeEnd(tjOntimeEnd);
        System.out.println(tj.getTjType());
        Page<Movie> page = movieService.queryList(pageNumber, pageSize,tj);
        return page;
    }



    @RequestMapping("/changelevel")
    @ResponseBody
    public String changeLevel(
            @RequestParam("movieid") String movieId
    ){
        System.out.println(movieId);
        Movie movie = movieService.queryOneById(movieId);
        if(movie.getMovieOperation()==0){
            System.out.println("进0的里了");
            movie.setMovieOperation(1);
            movieService.updateMovie(movie);
            System.out.println("进0的里了");
            return "success";
        }else{
            System.out.println("进1的里了");
            movie.setMovieOperation(0);
            movieService.updateMovie(movie);
            System.out.println("进1的里了");
            return "success";
        }

    }

    @RequestMapping("/allList")
    @ResponseBody
    public Page<Movie> queryList(
            @RequestParam(required=false,defaultValue="1") int pageNumber,
            @RequestParam(required=false,defaultValue="2") int pageSize
    ){
        System.out.println(pageNumber + "  " + pageSize);
        Tiaojian tj = new Tiaojian();
        System.out.println(tj.getTjType());
        Page<Movie> page = movieService.queryList(pageNumber, pageSize,tj);
        return page;
    }

    @RequestMapping("/xq")
    public String queryOneById(
            @RequestParam("movieId") String movieId,
            HttpServletRequest request
    ){
        Movie movie = movieService.queryOneById(movieId);
        HttpSession session = request.getSession();
        session.setAttribute("movie",movie);
        return "redirect:/to_page/to_xq";
    }

    @RequestMapping("/update")
    public String updateMovie(Movie movie,
                              @RequestParam("type") String type,
                              @RequestParam("pic") MultipartFile file,
                              @RequestParam("hiddenpic") String hpic
    ) throws IllegalStateException, IOException{
        System.out.println("修改中的type"+type);
        System.out.println("修改中的file "+file.getOriginalFilename());
        System.out.println("修改中的file "+file.getContentType());
        System.out.println("修改中的file "+file.getSize());
        System.out.println("修改中的file "+(int)file.getSize());
        type = type.replaceAll(",","/");
        System.out.println(type);
        movie.setMovieType(type);
        if((int)file.getSize()!=0){
            String oldFilename = file.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFilename);
            //获取一个新的文件名
            String newName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            //创建新的文件
            File destFile = new File(DEST_FILE_DIR, newName);
            file.transferTo(destFile);
            movie.setMoviePic("/pic/" + newName);
        }else{
        movie.setMoviePic(hpic);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = sdf.format(date);
        System.out.println(ss);
        movie.setMovieCreate(ss);
        movieService.updateMovie(movie);
        return "redirect:/user/to_admin";

    }

}
