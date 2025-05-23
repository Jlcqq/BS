package com.example.entity;

import java.io.Serializable;

/**
 * 公寓申请信息(Shenqing)实体类
 *
 */
public class Shenqing implements Serializable {
    private static final long serialVersionUID = 910937447788455819L;
/**
     * 主键ID
     */
    private Integer id;
/**
     * 用户ID
     */
    private Integer memberid;
/**
     * 公寓ID
     */
    private Integer xinxiid;
/**
     * 申请单号
     */
    private String bhno;
    /**
     * 申请人姓名
     */
    private String name;
/**
     * 联系方式
     */
    private String tel;
/**
     * 申请入住时长
     */
    private Integer scnum;
/**
     * 单价（元/月）
     */
    private Double price;
/**
     * 合计金额
     */
    private Double total;
/**
     * 审核状态
     */
    private String status;
/**
     * 申请日期
     */
    private String savetime;
    private String fkstatus;
    private String membername;
    private String xinxiname;

    public String getFkstatus() {
        return fkstatus;
    }

    public void setFkstatus(String fkstatus) {
        this.fkstatus = fkstatus;
    }

    public String getBhno() {
        return bhno;
    }

    public void setBhno(String bhno) {
        this.bhno = bhno;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getXinxiname() {
        return xinxiname;
    }

    public void setXinxiname(String xinxiname) {
        this.xinxiname = xinxiname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getXinxiid() {
        return xinxiid;
    }

    public void setXinxiid(Integer xinxiid) {
        this.xinxiid = xinxiid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getScnum() {
        return scnum;
    }

    public void setScnum(Integer scnum) {
        this.scnum = scnum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSavetime() {
        return savetime;
    }

    public void setSavetime(String savetime) {
        this.savetime = savetime;
    }

}

