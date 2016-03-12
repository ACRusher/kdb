package com.kdb.web.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 下午12:45
 */
@Controller
@RequestMapping("/upload")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UploadController extends BaseController {

    @RequestMapping("/image")
    public String upload(@RequestParam(value = "file",required = false) MultipartFile multipartFile ,
                       HttpServletRequest request,HttpServletResponse response){
        if(!multipartFile.isEmpty()){
            try {
                multipartFile.transferTo(new File("/tmp/test.jpg"));
            } catch (IOException e) {
                logger.error("",e);
            }
        }
        return "success";
    }
}
