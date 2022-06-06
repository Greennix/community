package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommnuityApplication.class)
public class MapperTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(103);
        System.out.println(user);

        user=userMapper.selectByName("zhangfei");
        System.out.println(user);

        user=userMapper.selectByEmail("nowcoder112@sina.com");
        System.out.println(user);

    }@Test
    public void testInsertUsers(){

       User user=new User();
        user.setUsername("genji");
        user.setPassword("ymjfksdhf");
        user.setSalt("fff");
        user.setEmail("1530965088@qq.com");
        user.setHeaderUrl("http:www.nowcoder.com/101.pnj");
        user.setActivationCode("fff");


        user.setStatus(0);
        user.setCreateTime(new Date());
        user.setType(1);


        int row=userMapper.insertUser(user);
        System.out.println(row);


    }

    @Test
    public void testUpdate(){
        int row=userMapper.updateStatus(155,1);
        User user=userMapper.selectById(155);
        System.out.println(user);
        row=userMapper.updateHeader(156,"fff");
        user=userMapper.selectById(156);
        System.out.println(user);
        row=userMapper.updatePassword(157,"kkkkk");
        user=userMapper.selectById(157);
        System.out.println(user);
    }

    @Test
    public void testSelectPost(){
        List<DiscussPost> posts=discussPostMapper.selectDiscussPosts(0,0,10);

        for(DiscussPost post:posts){
            System.out.println(post);
        }

        int rows=discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

}
