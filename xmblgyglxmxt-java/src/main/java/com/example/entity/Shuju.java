package com.example.entity;

import java.io.Serializable;

/**
 * 违规记录(Shuju)实体类
 *
 */
public class Shuju implements Serializable {
    private static final long serialVersionUID = 659341574251779571L;
/**
     * 主键ID
     */
    private Integer id;
/**
     * 公寓ID
     */
    private Integer xinxiid;
/**
     * 申请用户ID
     */
    private Integer memberid;
/**
     * 申请单号
     */
    private String bhno;
/**
     * 违规类型
     */
    private String typename;
/**
     * 详细内容
     */
    private String content;
/**
     * 创建日期
     */
    private String savetime;
    private int shenqingid;
    private String xinxiname;
    private String membername;

    public int getShenqingid() {
        return shenqingid;
    }

    public void setShenqingid(int shenqingid) {
        this.shenqingid = shenqingid;
    }

    public String getXinxiname() {
        return xinxiname;
    }

    public void setXinxiname(String xinxiname) {
        this.xinxiname = xinxiname;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getXinxiid() {
        return xinxiid;
    }

    public void setXinxiid(Integer xinxiid) {
        this.xinxiid = xinxiid;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getBhno() {
        return bhno;
    }

    public void setBhno(String bhno) {
        this.bhno = bhno;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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

