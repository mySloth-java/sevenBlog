package com.cg.runner;

import com.cg.entity.Article;
import com.cg.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cg.util.SystemConstants.ARTICLE_VIEW_COUNT;


/**
 * @author cgJavaAfter
 * @date 2023-06-04 16:33
 */
//实现CommandLineRunner，在项目启动时执行初始化redis操作，实现数据同步
@Component
public class ViewRunner implements CommandLineRunner {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public void run(String... args) throws Exception {
        //查询数据库获取到文章id和浏览量
        List<Article> articles = articleMapper.GetAllArticle();

        //通过stream获取文章的id和浏览量
        Map<String , String> collect = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString()
                        , article -> {
                            return article.getViewCount().toString();
                        })
                );

        //存入redis
        stringRedisTemplate.opsForHash().putAll(ARTICLE_VIEW_COUNT,collect);
    }
}
