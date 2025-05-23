package com.example.controller;

import com.example.common.Result;
import com.example.dao.LinksDao;
import com.example.entity.Links;
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
 * 友情链接(Links)表控制层
 *
 */
@RestController
public class LinksController {
    @Resource
    LinksDao linksDao;

    //信息列表
    @ResponseBody
    @RequestMapping("linksList")
    public Result linksList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize, HttpServletRequest request){
        String key = request.getParameter("key");
        HashMap map = new HashMap();
        map.put("key", key);
        PageHelper.startPage(pageNum, pageSize);
        List<Links> list = linksDao.selectAll(map);
        PageInfo<Links> pageInfo = new PageInfo<Links>(list);
        return Result.success(pageInfo);
    }

    //查询全部信息列表
    @ResponseBody
    @RequestMapping("linksListAll")
    public Result linksListAll(HttpServletRequest request){
        List<Links> list = linksDao.selectAll(null);
        return Result.success(list);
    }
    
    //添加或编辑信息
    @ResponseBody
    @RequestMapping("linksAdd")
    public Result linksAdd(Links links ,HttpServletRequest request) {
        if(links.getId()==null){
            linksDao.add(links);
        }else{
            linksDao.update(links);
        }
        return Result.success();
    }
    

    //删除信息
    @ResponseBody
    @RequestMapping("linksDel")
    public Result linksDel(int id,HttpServletRequest request) {
        linksDao.delete(id);
        return Result.success();
    }
    
    //批量删除信息
    @ResponseBody
    @RequestMapping("linksDeleteAll")
    public Result typeDeleteAll(HttpServletRequest request) {
        String vals = request.getParameter("vals");
        String[] val = vals.split(",");
        for(int i=0;i<val.length;i++){
            linksDao.delete(Integer.parseInt(val[i]));
        }
        return Result.success();
    }
}

