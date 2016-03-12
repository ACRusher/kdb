package com.kdb.web.controller;

import com.alibaba.fastjson.JSON;
import com.kdb.dto.BaiduVillageDTO;
import com.kdb.manager.ArticleManager;
import com.kdb.manager.OperatorManager;
import com.kdb.manager.VillageManager;
import com.kdb.manager.VillageOperatorsManager;
import com.kdb.model.Operators;
import com.kdb.model.Village;
import com.kdb.model.VillageOperators;
import com.kdb.util.IpUtil;
import com.kdb.util.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 通过小区筛选运营商列表
 *
 * @author xiliang.zxl
 * @date 2016-01-31 下午4:04
 */
@Controller
@RequestMapping("/")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchOperatorController extends BaseController {

    private final String page = "4";

    @Autowired
    private VillageOperatorsManager villageOperatorsManager;
    @Autowired
    private VillageManager villageManager;
    @Autowired
    private OperatorManager operatorManager;

    @RequestMapping(value = "/4")
    public ModelAndView serve(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        try {
            return doBiz(request, response, model);
        } catch (Exception e) {
            logger.error("", e);
        }
        return new ModelAndView(page);
    }

    private ModelAndView doBiz(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        String baiduSearchResult=request.getParameter("searchBd");
        if(baiduSearchResult==null){
            model.addObject("operatorList", new ArrayList());
            model.setViewName(page);
            return model;
        }
        //获取用户小区 没查到则新建
        BaiduVillageDTO baiduVillageDTO=JSON.parseObject(baiduSearchResult, BaiduVillageDTO.class);
        Village village=villageManager.getByName(baiduVillageDTO.getCity(),
                baiduVillageDTO.getDistrict(), baiduVillageDTO.getName());
        if(village==null){
            village=addVillage(baiduVillageDTO);
        }
        //获取用户运营商 没查到则新建
        Operators operators=getOperators();
        if(operators==null){
            model.addObject("operatorList", new ArrayList());
            model.setViewName(page);
            return model;
        }

        //根据小区查询运营商
        List<VillageOperators> villageOperatorsList=villageOperatorsManager.getByVillageId(village.getId());
        //如果没有查询到 则返回默认的列表
        if(villageOperatorsList==null || villageOperatorsList.isEmpty()){
            villageOperatorsList=villageOperatorsManager.getDefaultVillageOperators();
        }

        //初始化用户所在小区的运营商
        boolean isContainVillageOperatorOfUser = false;
        for(VillageOperators v : villageOperatorsList){
            if(v.getVillageId().equals(village.getId()) && v.getOperatorId().equals(operators.getId())){
                isContainVillageOperatorOfUser=true;
            }
        }
        if(!isContainVillageOperatorOfUser){
            createVillageOperator(village, operators);
        }

        model.addObject("operatorList", villageOperatorsList);
        model.addObject("operatorMap",getOperatorMap());
        model.addObject("keyword",village.getCity()+" "+village.getArea()+" "+village.getVillage());
        model.setViewName(page);
        return model;
    }

    private Map<Long,Operators> getOperatorMap(){
        List<Operators> list=operatorManager.getAllOperators();
        Map<Long,Operators> map=new HashMap<Long, Operators>();
        for(Operators operators: list){
            map.put(operators.getId(),operators);
        }
        return map;
    }

    private VillageOperators createVillageOperator(Village village,Operators operators){
        VillageOperators villageOperators=new VillageOperators();
        villageOperators.setGmtCreate(new Date());
        villageOperators.setGmtModify(new Date());
        villageOperators.setVillageId(village.getId());
        villageOperators.setOperatorId(operators.getId());

        villageOperators.setCommentCount(1);
        villageOperators.setStar("4");
        villageOperators.setService("4");
        villageOperators.setStable("4");
        villageOperators.setSpeed("4");

        villageOperators.setSpeedCount(1);
        villageOperators.setUpload("1.2");
        villageOperators.setDownload("1.5");
        villageOperators.setPing("200");

        villageOperatorsManager.addVillageOperators(villageOperators);
        return villageOperators;
    }

    private Operators getOperators(){
        String operatorName=getRequest().getParameter("isp");
        if(StringUtils.isBlank(operatorName)) {
            String ip = RequestUtils.getRemoteAddr(getRequest());
            operatorName = IpUtil.getOperatorName(ip);
            if (ip.equals("127.0.0.1")) operatorName = "联通";
        }
        if(operatorName==null) return null;
        if(!operatorName.contains("宽带")){
            operatorName=operatorName+"宽带";
        }
        Operators operators=operatorManager.getByName(operatorName);
        if(operators==null){
            operators=new Operators();
            operators.setGmtModify(new Date());
            operators.setGmtCreate(new Date());
            operators.setName(operatorName);
            operatorManager.addOperator(operators);
        }
        return operators;
    }

    private Village addVillage(BaiduVillageDTO baiduVillageDTO){
        Village village=new Village();
        village.setGmtCreate(new Date());
        village.setGmtModify(new Date());
        village.setCity(baiduVillageDTO.getCity());
        village.setArea(baiduVillageDTO.getDistrict());
        village.setVillage(baiduVillageDTO.getName());
        villageManager.addVillage(village);
        return village;
    }
}
