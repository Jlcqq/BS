package com.example.controller;

import com.example.common.Result;
import com.example.dao.ProtypeDao;
import com.example.dao.ShenqingDao;
import com.example.dao.XinxiDao;
import com.example.entity.Protype;
import com.example.entity.Xinxi;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 公寓信息(Xinxi)表控制层
 *
 */
@RestController
public class XinxiController {
    @Resource
    XinxiDao xinxiDao;
    @Resource
    ProtypeDao protypeDao;
    @Resource
    ShenqingDao shenqingDao;


    //信息列表
    @ResponseBody
    @RequestMapping("xinxiList")
    public Result xinxiList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,
                            HttpServletRequest request){
        String key = request.getParameter("key");
        String key1 = request.getParameter("key1");
        String adminid = request.getParameter("adminid");
        HashMap map = new HashMap();
        map.put("adminid", adminid);
        map.put("key", key);
        map.put("key1", key1);
        PageHelper.startPage(pageNum, pageSize);
        List<Xinxi> list = xinxiDao.selectAll(map);
        for (Xinxi xinxi : list) {
            Protype protype = protypeDao.findById(xinxi.getTypeid());
            xinxi.setTypename(protype.getName());
        }
        PageInfo<Xinxi> pageInfo = new PageInfo<Xinxi>(list);
        return Result.success(pageInfo);
    }


    //添加或编辑信息
    @ResponseBody
    @RequestMapping("xinxiAdd")
    public Result xinxiAdd(Xinxi xinxi ,HttpServletRequest request) {
        if(xinxi.getId()==null){
            xinxiDao.add(xinxi);
        }else{
            xinxiDao.update(xinxi);
        }
        return Result.success();
    }

    //查询信息详情
    @ResponseBody
    @RequestMapping("xinxiDateil")
    public Result xinxiDateil(int id, HttpServletRequest request) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        Xinxi xinxi = xinxiDao.findById(id);
        Protype protype = protypeDao.findById(xinxi.getTypeid());
        xinxi.setTypename(protype.getName());
        //查询其它信息
        List<Xinxi> list = xinxiDao.selectAll(null);
        res.put("xinxi", xinxi);
        res.put("list", list);
        return Result.success(res);
    }

    //删除信息
    @ResponseBody
    @RequestMapping("xinxiDel")
    public Result xinxiDel(int id, HttpServletRequest request) {
        xinxiDao.delete(id);
        return Result.success();
    }
    
    //批量删除信息
    @ResponseBody
    @RequestMapping("xinxiDeleteAll")
    public Result typeDeleteAll(HttpServletRequest request) {
        String vals = request.getParameter("vals");
        String[] val = vals.split(",");
        for(int i=0;i<val.length;i++){
            xinxiDao.delete(Integer.parseInt(val[i]));
        }
        return Result.success();
    }

}

