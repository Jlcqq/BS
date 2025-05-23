package com.example.controller;

import com.example.common.Result;
import com.example.dao.MemberDao;
import com.example.dao.ShenqingDao;
import com.example.dao.ShujuDao;
import com.example.dao.XinxiDao;
import com.example.entity.Member;
import com.example.entity.Shenqing;
import com.example.entity.Shuju;
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
 * 违规记录信息(Shuju)表控制层
 *
 */
@RestController
public class ShujuController {
    @Resource
    ShujuDao shujuDao;
    @Resource
    XinxiDao xinxiDao;
    @Resource
    MemberDao memberDao;
    @Resource
    ShenqingDao shenqingDao;

    //信息列表
    @ResponseBody
    @RequestMapping("shujuList")
    public Result shujuList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize, HttpServletRequest request){
        String key = request.getParameter("key");
        HashMap map = new HashMap();
        map.put("key", key);
        PageHelper.startPage(pageNum, pageSize);
        List<Shuju> list = shujuDao.selectAll(map);
        for (Shuju shuju : list) {
            Xinxi xinxi = xinxiDao.findById(shuju.getXinxiid());
            shuju.setXinxiname(xinxi.getName());
            Member member =memberDao.findById(shuju.getMemberid());
            shuju.setMembername(member.getName());
        }
        PageInfo<Shuju> pageInfo = new PageInfo<Shuju>(list);
        return Result.success(pageInfo);
    }
    //添加或编辑信息
    @ResponseBody
    @RequestMapping("shujuAdd")
    public Result shujuAdd(Shuju shuju ,HttpServletRequest request) {
        if(shuju.getId()==null){
            Shenqing shenqing = shenqingDao.findById(shuju.getShenqingid());
           shuju.setXinxiid(shenqing.getXinxiid());
           shuju.setMemberid(shenqing.getMemberid());
           shuju.setBhno(shenqing.getBhno());
            shujuDao.add(shuju);
        }else{
            shujuDao.update(shuju);
        }
        return Result.success();
    }
    
    //删除信息
    @ResponseBody
    @RequestMapping("shujuDel")
    public Result shujuDel(int id, HttpServletRequest request) {
        shujuDao.delete(id);
        return Result.success();
    }
    
    //批量删除信息
    @ResponseBody
    @RequestMapping("shujuDeleteAll")
    public Result typeDeleteAll(HttpServletRequest request) {
        String vals = request.getParameter("vals");
        String[] val = vals.split(",");
        for(int i=0;i<val.length;i++){
            shujuDao.delete(Integer.parseInt(val[i]));
        }
        return Result.success();
    }
}

