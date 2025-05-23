package com.example.dao;

import com.example.entity.Shenqing;

import java.util.HashMap;
import java.util.List;

/**
 * 公寓申请(Shenqing)表数据库访问层
 *
 */
public interface ShenqingDao {
       Shenqing findById(int id);
       List<Shenqing> selectAll(HashMap map);
       List<Shenqing> selectTJ(HashMap map);
       void add(Shenqing shenqing);
       void update(Shenqing shenqing);
       void delete(int id);
       
}

