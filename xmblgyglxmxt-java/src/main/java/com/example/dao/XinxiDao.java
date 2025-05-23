package com.example.dao;

import com.example.entity.Xinxi;

import java.util.HashMap;
import java.util.List;

/**
 * 公寓信息(Xinxi)表数据库访问层
 *
 */
public interface XinxiDao {
   Xinxi findById(int id);
   List<Xinxi> selectAll(HashMap map);
   List<Xinxi> selectPh(HashMap map);
   //List<Xinxi> selectbie(HashMap map);
   void add(Xinxi xinxi);
   void update(Xinxi xinxi);
   void delete(int id);


}

