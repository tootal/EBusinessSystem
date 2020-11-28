package com.demo.dao;

import java.util.List;

import com.demo.model.Statistics;


/**
 * dao层接口- 对虚拟表 统计报表 操作
 * @author MouseHappy
 */
public interface StatisticsDao {
	/**
	 * 获取统计报表
	 * @return List<Statistics>
	 */
	List<Statistics> getStatistics();
}
