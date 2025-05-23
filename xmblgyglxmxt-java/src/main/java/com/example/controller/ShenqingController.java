package com.example.controller;

import com.example.common.CustomException;
import com.example.common.Result;
import com.example.dao.AdminDao;
import com.example.dao.MemberDao;
import com.example.dao.ShenqingDao;
import com.example.dao.XinxiDao;
import com.example.entity.Shenqing;
import com.example.entity.Xinxi;
import com.example.util.CheckCode;
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
 * 公寓申请(Shenqing)表控制层
 *
 */
@RestController
public class ShenqingController {
    @Resource
    ShenqingDao shenqingDao;
    @Resource
    MemberDao memberDao; 
    @Resource
    XinxiDao xinxiDao;
    @Resource
    AdminDao adminDao;
    
    //信息列表
    @ResponseBody
    @RequestMapping("shenqingList")
    public Result shenqingList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize, HttpServletRequest request){
        String key = request.getParameter("key");
        String memberid = request.getParameter("memberid");
        HashMap map = new HashMap();
        map.put("key", key);
        map.put("memberid", memberid);
        PageHelper.startPage(pageNum, pageSize);
        List<Shenqing> list = shenqingDao.selectAll(map);
        for (Shenqing shenqing : list) {
            Xinxi xinxi = xinxiDao.findById(shenqing.getXinxiid());
            shenqing.setXinxiname(xinxi.getName());
        }
        PageInfo<Shenqing> pageInfo = new PageInfo<Shenqing>(list);
        return Result.success(pageInfo);
    }
    
    //添加申请信息
    @ResponseBody
    @RequestMapping("shenqingAdd")
    public Result shenqingAdd(Shenqing shenqing ,HttpServletRequest request) {
        Xinxi xinxi = xinxiDao.findById(shenqing.getXinxiid());
        HashMap map = new HashMap();
        map.put("xinxiid", shenqing.getXinxiid());
        map.put("memberid", shenqing.getMemberid());
        map.put("status", "待审核");
        List<Shenqing> list = shenqingDao.selectAll(map);
        if(list.size()!=0){
            throw new CustomException("你已申请该公寓，不能重复申请");
        }
        CheckCode che = new CheckCode();
        shenqing.setBhno(che.getCheckCode());
        shenqing.setPrice(xinxi.getPrice());
        shenqing.setTotal(xinxi.getPrice()*shenqing.getScnum());
        shenqingDao.add(shenqing);
        return Result.success();
    }


    //编辑申请信息
    @ResponseBody
    @RequestMapping("shenqingEdit")
    public Result shenqingEdit(Shenqing shenqing ,HttpServletRequest request) {
        Shenqing shenqing1 = shenqingDao.findById(shenqing.getId());
        shenqing.setTotal(shenqing1.getPrice()*shenqing.getScnum());
        shenqingDao.update(shenqing);
        return Result.success();
    }

    //付款
    @ResponseBody
    @RequestMapping("shenqingShedit")
    public Result shenqingShedit(Shenqing shenqing ,HttpServletRequest request) {
        shenqingDao.update(shenqing);
        return Result.success();
    }

    //审核申请信息
    @ResponseBody
    @RequestMapping("shenqingSh")
    public Result shenqingSh(Shenqing shenqing ,HttpServletRequest request) {
        Shenqing shenqing1 = shenqingDao.findById(shenqing.getId());
        HashMap map = new HashMap();
        map.put("xinxiid", shenqing1.getXinxiid());
        map.put("key1", "待审核");
        map.put("key2", "已付款");
        if(shenqing.getStatus().equals("通过")){
            List<Shenqing> list = shenqingDao.selectAll(map);
            for (Shenqing shenqing2 : list) {
                shenqing2.setStatus("未通过");
                shenqingDao.update(shenqing2);
            }
            Xinxi xinxi = xinxiDao.findById(shenqing1.getXinxiid());
            xinxi.setStatus("已出租");
            xinxiDao.update(xinxi);
        }
        shenqingDao.update(shenqing);
        return Result.success();
    }
    //退房
    @ResponseBody
    @RequestMapping("shenqingTf")
    public Result shenqingTf(Shenqing shenqing ,HttpServletRequest request) {
        Shenqing shenqing1 = shenqingDao.findById(shenqing.getId());
        Xinxi xinxi = xinxiDao.findById(shenqing1.getXinxiid());
        xinxi.setStatus("空闲");
        xinxiDao.update(xinxi);
        shenqingDao.update(shenqing);
        return Result.success();
    }
    
    //删除信息
    @ResponseBody
    @RequestMapping("shenqingDel")
    public Result shenqingDel(int id,HttpServletRequest request) {
        shenqingDao.delete(id);
        return Result.success();
    }
    
    //批量删除信息
    @ResponseBody
    @RequestMapping("shenqingDeleteAll")
    public Result typeDeleteAll(HttpServletRequest request) {
        String vals = request.getParameter("vals");
        String[] val = vals.split(",");
        for(int i=0;i<val.length;i++){
            shenqingDao.delete(Integer.parseInt(val[i]));
        }
        return Result.success();
    }

}

