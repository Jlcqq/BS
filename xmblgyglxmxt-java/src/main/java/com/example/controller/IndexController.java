package com.example.controller;


import com.example.common.Result;
import com.example.dao.*;
import com.example.entity.Imgadv;
import com.example.entity.News;
import com.example.entity.Protype;
import com.example.entity.Xinxi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

//前台首页
@CrossOrigin//解决跨域问题注解
@RestController
public class IndexController {
    @Resource
    NewsDao newsDao;
    @Resource
    ImgadvDao imgadvDao;
    @Resource
    XinxiDao xinxiDao;
    @Resource
    ShujuDao shujuDao;
    @Resource
    ProtypeDao protypeDao;
    @Resource
    AdminDao adminDao;

    //查询前台首页信息
    @ResponseBody
    @RequestMapping("index")
    public Result index(HttpServletRequest request){
        HashMap<String,Object> res = new HashMap<String,Object>();
        String key1 = request.getParameter("key1");
        HashMap map = new HashMap();
        //查询公告信息
        List<News> list = newsDao.selectAll(null);
        //查询轮播图信息
        List<Imgadv> imglist = imgadvDao.selectAll(null);
        //查询公寓信息
        map.put("key1", key1);
        map.put("status", "空闲");
        List<Xinxi> xlist = xinxiDao.selectAll(map);
        for (Xinxi xinxi : xlist) {
            Protype protype = protypeDao.findById(xinxi.getTypeid());
            xinxi.setTypename(protype.getName());
        }

        res.put("nlist", list);
        res.put("imglist", imglist);
        res.put("xlist", xlist);
        return Result.success(res);
    }


}
