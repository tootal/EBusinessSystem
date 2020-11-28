package com.demo.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.demo.utils.JdbcUtils;
import com.demo.dao.StatisticsDao;
import com.demo.model.Statistics;


public class StatisticsDaoImpl implements StatisticsDao {
	
	/** (non-Javadoc)
	 * @see com.demo.dao.StatisticsDao#getStatistics(java.lang.String)
	 */
	@Override
	public List<Statistics> getStatistics() {
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "select name,sum(quantity) as squantity,sum(orderitem.price) as sprice from orderitem,clothes where clothes.id=orderitem.clothes_id group by clothes_id";// SQL语句
			List<Statistics> statistics = (List<Statistics>) runner.query(sql, null, new BeanListHandler(Statistics.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
			
			return statistics;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
