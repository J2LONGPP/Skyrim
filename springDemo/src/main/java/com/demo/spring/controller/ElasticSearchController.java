package com.demo.spring.controller;

import com.demo.spring.entity.Article;
import com.demo.spring.entity.Author;
import com.demo.spring.entity.Tutorial;
import com.demo.spring.mapper.ElasticSearchMapper;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;

/**
 * 测试es
 */
@RestController
public class ElasticSearchController {
   /* @Autowired
    private ElasticSearchMapper elasticSearchMapper;

    *//**
     * 向ES库中增加一天记录
     *//*
    @RequestMapping("/add")
    @ResponseBody
    public void testSaveArticleIndex(){
        Author author=new Author();
        author.setId(1L);
        author.setName("j2longpp");
        author.setRemark("java programmer");

        Tutorial tutorial=new Tutorial();
        tutorial.setId(1L);
        tutorial.setName("elasticsearch");

        Article article=new Article();
        article.setId(1L);
        article.setTitle("spring boot integerate elasticsearch");
        article.setAbstracts("springboot integreate elasticsearch is very easy");
        article.setTutorial(tutorial);
        article.setAuthor(author);
        article.setContent("elasticsearch based on lucene,"+
                "spring-data-elasticsearch based on elasticsearch," +
                "this tutorial tell you how to integreate springboot with spring-data-elasticsearch");
        article.setPostTime(new Date());
        article.setClickCount(1L);
        elasticSearchMapper.save(article);
    }

    *//**
     * 从es库中查询数据
     *//*
    @RequestMapping("/query")
    @ResponseBody
    public void testSearch(){
       String queryStr="springboot";//搜索关键字
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(queryStr);
        Iterable<Article> searchResult=elasticSearchMapper.search(builder);
        Iterator<Article> iterator=searchResult.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getAuthor().getName());
        }
    }*/
}
