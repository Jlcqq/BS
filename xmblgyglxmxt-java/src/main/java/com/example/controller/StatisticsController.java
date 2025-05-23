package com.example.controller;


import com.example.common.Result;
import com.example.dao.*;
import com.example.entity.Protype;
import com.example.entity.Shenqing;
import com.example.entity.Xinxi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin//解决跨域问题注解
@RestController
public class StatisticsController {
    @Resource
    XinxiDao xinxiDao;
    @Resource
    MemberDao memberDao;
    @Resource
    ShujuDao shujuDao;
    @Resource
    ShenqingDao shenqingDao;
    @Resource
    ProtypeDao protypeDao;


  //公寓分类统计（饼图）
    @ResponseBody
    @RequestMapping("bie")
    public Result tjorder(HttpServletRequest request) {
        List dataval = new ArrayList();
        List<Protype> list = protypeDao.selectAll(null);
        for(Protype protype:list){
            HashMap map1 = new HashMap();
            map1.put("typeid",protype.getId());
            List<Xinxi> glist = xinxiDao.selectAll(map1);
            map1.put("value", glist.size());
            map1.put("name", protype.getName());
            dataval.add(map1);
        }
        return Result.success(dataval);
    }

    //费用统计
    @ResponseBody
    @RequestMapping("tjMoney")
    public Result tjMoney(HttpServletRequest request) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        String key = request.getParameter("key");
        HashMap map = new HashMap();
        map.put("fkstatus", "已付款");
        List<Shenqing> olist = shenqingDao.selectTJ(map);
        //ArrayList nslist = new ArrayList();
        ArrayList nlist = new ArrayList();
        ArrayList slist = new ArrayList();
        for(Shenqing shenqing:olist){
            nlist.add(shenqing.getSavetime());
            slist.add(shenqing.getTotal());
        }
        res.put("xmaindata", nlist);
        res.put("ymaindata", slist);
        return Result.success(res);
    }

}
