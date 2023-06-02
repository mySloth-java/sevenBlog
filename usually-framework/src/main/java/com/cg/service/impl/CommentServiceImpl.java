package com.cg.service.impl;

import com.cg.entity.Comment;
import com.cg.entity.LoginUser;
import com.cg.entity.LoginUserDetails;
import com.cg.mapper.CommentMapper;
import com.cg.mapper.LoginMapper;
import com.cg.service.CommentService;
import com.cg.util.BeanCopyUtils;
import com.cg.util.ResponseResult;
import com.cg.vo.CommentPageVo;
import com.cg.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-21 14:41
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LoginMapper loginMapper;

    //查询文章评论
    @Override
    public ResponseResult GetComment(Long articleId, Integer pageNum, Integer pageSize) {
        //根据文章id查询所有根目录下的顶级评论
        List<Comment> comments = commentMapper.GetComment(articleId, pageNum, pageSize);
        //查询总条数
        Integer count = commentMapper.GetCommentByArticleIdCount(articleId);

        //调取封装好的方法对齐属性赋值
        List<CommentVo> commentVos = commentVoField(comments);

        //为每一个根评论的子评论进行赋值
        for(CommentVo commentVo : commentVos){
            //当root_id = id时，就为该Id的子评论
            List<Comment> children = commentMapper.GetCommentById(commentVo.getId());
            //此时得到的children没有name和toCommentName
            List<CommentVo> childrenFinal = commentVoField(children);

            //设置完属性后设置comment的子评论
            commentVo.setChildren(childrenFinal);
        }

        //最后再创建Vo，存储总页数和CommentVo信息
        CommentPageVo commentPageVo = new CommentPageVo(commentVos,count);

        return ResponseResult.okResult(commentPageVo);
    }

    //发送评论
    @Override
    public ResponseResult AddComment(Comment comment) {
        //前端发送的数据并没有name和toCommentName
        //从securityHolder中取出用户信息
        LoginUserDetails principal = (LoginUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取用户Id并设置
        Long id = principal.getUser().getId();

        //TODO 对id进行判断操作

        comment.setCreateBy(id);
        comment.setUpdateBy(id);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());

        //在添加comment前进行属性的赋值
        Integer integer = commentMapper.AddComment(comment);


        return ResponseResult.okResult();
    }

    //把对Comments属性赋值进行方法封装，children的comments再次赋值属性直接调取即可
    private List<CommentVo> commentVoField(List<Comment> comments){
        //将获取的评论数据拷贝
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);
        //此时拷贝的commentVos里面并没有name和回复评论的Id
        //根据createBy查询name；根据回复评论的toCommentUserId查询toCommentName

        //TODO stream流写法改进 数据库查询过多，看是否可以优化
        for(int i = 0;i < comments.size();i++){
            //获取createBy
            Long createBy = comments.get(i).getCreateBy();
            //获取toCommentId
            Long toCommentUserId = comments.get(i).getToCommentUserId();

            //当toCommentId为-1默认值时，即没有子评论，就不进行获取值
            if(toCommentUserId != -1){
                LoginUser toCommentUser = loginMapper.GetLoginById(toCommentUserId);
                //设置toCommentName
                commentVos.get(i).setToCommentName(toCommentUser.getNickName());
            }

            //数据库查询
            LoginUser commentUser = loginMapper.GetLoginById(createBy);

            //设置name
            commentVos.get(i).setName(commentUser.getNickName());

        }

        return commentVos;

    }

}
