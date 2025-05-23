package com.example.controller;


import com.example.common.CustomException;
import com.example.common.Result;
import com.example.dao.MemberDao;
import com.example.entity.Member;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 普通用户信息表(Member)表控制层
 *
 */
@CrossOrigin//解决跨域问题注解
@RestController
public class MemberController {
    @Resource
    MemberDao memberDao;

    
    //用户登录
    @ResponseBody
    @RequestMapping("memberLogin")
    public Result Login(Member member, HttpServletRequest request, HashMap map) {
        map.put("username", member.getUsername());
        map.put("password", member.getPassword());
        List<Member> list = memberDao.selectOne(map);
        if(list.size()==0){
            throw new CustomException("用户名或密码错误或账号已冻结");
        }else{
            request.getSession().setAttribute("member", list.get(0));
        }
        //获取当前登录用户信息
        Member membeOne = (Member) request.getSession().getAttribute("member");
        return Result.success(membeOne);
    }

    //用户注册
    @ResponseBody
    @RequestMapping("memberReg")
    public Result memberReg(Member member ,HttpServletRequest request,HashMap map) {
        map.put("username",member.getUsername());
        List<Member> list = memberDao.selectAll(map);
        //判断用户名的唯一性
        if (list.size()>0){
            throw new CustomException("已存在的用户名，请更换");
        }
        memberDao.add(member);
        return Result.success();
    }

    //信息列表
    @ResponseBody
    @RequestMapping("memberList")
    public Result  memberList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize, HttpServletRequest request){
        String key = request.getParameter("key");
        String key1 = request.getParameter("key1");
        //HashMap<String,Object> res = new HashMap<String,Object>();
        HashMap map = new HashMap();
        map.put("key", key);
        map.put("key1", key1);
        PageHelper.startPage(pageNum, pageSize);
        List<Member> list = memberDao.selectAll(map);
        PageInfo<Member> pageInfo = new PageInfo<Member>(list);
        return Result.success(pageInfo);
    }

    //添加或编辑信息
    @ResponseBody
    @RequestMapping("memberAdd")
    public Result memberAdd(Member member ,HttpServletRequest request) {
        Member user = (Member) request.getSession().getAttribute("member");
        if(member.getId()==null){
            HashMap map = new HashMap();
            map.put("username",member.getUsername());
            List<Member> list = memberDao.selectAll(map);
            //判断用户名的唯一性
            if (list.size()>0){
                throw new CustomException("该用户名以存在，请更换");
            }
            memberDao.add(member);
        }else{
            memberDao.update(member);
        }
        return Result.success();
    }

    //账户冻结及解冻
    @ResponseBody
    @RequestMapping("memberEdit")
    public Result memberEdit(Member member ,HttpServletRequest request) {
        memberDao.update(member);
        Member member1 = memberDao.findById(member.getId());
        return Result.success(member1);
    }

    //个人密码编辑
    @ResponseBody
    @RequestMapping("memberPass")
    public Result memberPass(Member member ,HttpServletRequest request) {
        String onlpassword = request.getParameter("onlpassword");
        String confirmPasword = request.getParameter("confirmPasword");
        if(!onlpassword.equals(member.getPassword())){
            throw new CustomException("原密码错误");
        }
        member.setPassword(confirmPasword);
        memberDao.update(member);
        return Result.success();
    }



    //删除信息
    @ResponseBody
    @RequestMapping("memberDel")
    public Result memberDel(int id,HttpServletRequest request) {
        memberDao.delete(id);
        return Result.success();
    }

    //批量删除信息
    @ResponseBody
    @RequestMapping("memberDeleteAll")
    public Result typeDeleteAll(HttpServletRequest request) {
        String vals = request.getParameter("vals");
        String[] val = vals.split(",");
        for(int i=0;i<val.length;i++){
            memberDao.delete(Integer.parseInt(val[i]));
        }
        return Result.success();
    }


}

