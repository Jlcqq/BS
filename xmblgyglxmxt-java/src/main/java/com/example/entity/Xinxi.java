package com.example.entity;

import java.io.Serializable;

/**
 * 公寓信息(Xinxi)实体类
 *
 */
public class Xinxi implements Serializable {
    private static final long serialVersionUID = -74680422293751736L;
/**
     * ID主键
     */
    private Integer id;
/**
     * 图片
     */
    private String filename;
/**
     * 名称
     */
    private String name;
/**
     * 分类ID
     */
    private Integer typeid;
/**
     * 价格
     */
    private Double price;
/**
     * 楼层
     */
    private String lcnum;
/**
     * 朝向
     */
    private String cxname;
/**
     * 地址
     */
    private String addr;
/**
     * 介绍
     */
    private String content;
/**
     * 创建日期
     */
    private String savetime;
    private String mjnum;
    private String status;
    private String delstatus;
    private String typename;

    public String getMjnum() {
        return mjnum;
    }

    public void setMjnum(String mjnum) {
        this.mjnum = mjnum;
    }

    public String getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLcnum() {
        return lcnum;
    }

    public void setLcnum(String lcnum) {
        this.lcnum = lcnum;
    }

    public String getCxname() {
        return cxname;
    }

    public void setCxname(String cxname) {
        this.cxname = cxname;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSavetime() {
        return savetime;
    }

    public void setSavetime(String savetime) {
        this.savetime = savetime;
    }

}

