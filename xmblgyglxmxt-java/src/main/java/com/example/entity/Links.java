package com.example.entity;

import java.io.Serializable;

/**
 * 友情链接(Links)实体类
 *
 */
public class Links implements Serializable {
    private static final long serialVersionUID = -25074873999610539L;
/**
     * 主键ID
     */
    private Integer id;
/**
     * 名称
     */
    private String name;
/**
     * 跳转链接
     */
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

