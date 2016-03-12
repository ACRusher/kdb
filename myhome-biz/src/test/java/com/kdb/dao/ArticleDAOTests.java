package com.kdb.dao;

import com.kdb.BaseTest;
import com.kdb.model.Articles;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午1:12
 */
public class ArticleDAOTests extends BaseTest {

    @Autowired
    private ArticleDAO articleDAO;
    private String title;
    private String content;
    private int type;

    @Test
    public void testInsert() {

        title="宽带错误651解决办法";
        type=0;
        String[]arr={
             "通过“WIN+R”键打开运行窗口，输入CMD后回车；",
             "在打开的窗口输入：ping 114.114.114.114后回车，如ping通该地址，说明网络已经连通，可能是计算机浏览器问题或系统故障，建议您重装浏览器或操作系统；如果Ping该地址不通，可能为DNS无法解析或拨号后IP地址获取有问题，可重新拨号测试。",
        };
        StringBuilder sb=new StringBuilder();
        for(String s :arr){
            sb.append(s).append("<br/>");
        }

        Articles articles = new Articles();

        articles.setGmtCreate(new Date());
        articles.setGmtModify(new Date());
        articles.setAuthorId(1L);
//        articles.setTag("tag-tag");
        articles.setAuthorName("157****9698");

        articles.setTitle(title);
        articles.setContent(sb.toString());
        articles.setType(type); //0 故障 1 安装 2 宽带

        articleDAO.addModel(articles);
    }

    @Test
    public void testReadFile() throws IOException {
        File file=new File("/data/tmp.txt");
        FileReader reader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(reader);
        String line;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }
    }
}
