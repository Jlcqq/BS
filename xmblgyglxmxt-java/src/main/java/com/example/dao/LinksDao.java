package com.example.dao;

import com.example.entity.Links;

import java.util.HashMap;
import java.util.List;

/**
 * 友情链接(Links)表数据库访问层
 *
 */
public interface LinksDao {
   Links findById(int id);
       List<Links> selectAll(HashMap map);
       void add(Links links);
       void update(Links links);
       void delete(int id);
   

}

