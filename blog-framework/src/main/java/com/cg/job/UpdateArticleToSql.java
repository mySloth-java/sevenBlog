package com.cg.job;

import cn.hutool.core.bean.BeanUtil;
import com.cg.entity.Article;
import com.cg.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.cg.util.SystemConstants.ARTICLE_VIEW_COUNT;

/**
 * @author cgJavaAfter
 * @date 2023-06-04 17:26
 */
//定期将redis的数据同步到sql中
@Component
public class UpdateArticleToSql {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ArticleMapper articleMapper;

    @Scheduled(cron = "0/10 * * * * ?")
    public void UpdateViewCount(){
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(ARTICLE_VIEW_COUNT);

        //TODO stream流截取 / 包装
        for(Map.Entry<Object,Object> entry : entries.entrySet()){
            Long id = Long.valueOf((String) entry.getKey());
            Long viewCount = Long.valueOf((String) entry.getValue());
            Article article = new Article();
            article.setId(id);
            article.setViewCount(viewCount);

            Integer update = articleMapper.Update(article);
        }
    }

}
