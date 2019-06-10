package edu.hebeu.mapper;

import java.util.List;





import com.baomidou.mybatisplus.mapper.BaseMapper;

import edu.hebeu.entity.Log;


public interface LogMapper extends BaseMapper<Log>{
	/**
	 * 查询所有
	 * @return
	 */
	public List<Log> selectLogAll();
	
	/**
	 * 通过ID查询一条数据
	 * @param id
	 * @return
	 */
	public Log selectLogById(Long id);
	
	/**
	 * 修改数据
	 * @param menu
	 * @return
	 */
	public int updateLog(Log log);
	
	/**
	 * 添加数据
	 * @param menu
	 * @return
	 */
	public int  insertLog(Log log);
	
	/**
	 * 删除数据
	 * @param menu
	 * @return
	 */
	public int  delLog(Long id);
}
