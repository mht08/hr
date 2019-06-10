package edu.hebeu.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import edu.hebeu.entity.EmployeeMenu;
import edu.hebeu.entity.History;


public interface EmployeeMenuMapper extends BaseMapper<EmployeeMenu>{
	/**
	 * 查询所有
	 * @return
	 */
	public List<EmployeeMenu> selectEmployeeMenuAll();
	
	/**
	 * 通过ID查询一条数据
	 * @param id
	 * @return
	 */
	public EmployeeMenu selectEmployeeMenuById(Long id);
	
	/**
	 * 修改数据
	 * @param menu
	 * @return
	 */
	public int updateEmployeeMenu(EmployeeMenu employeeMenu);
	
	/**
	 * 添加数据
	 * @param menu
	 * @return
	 */
	public int  insertEmployeeMenu(EmployeeMenu employeeMenu);
	
	/**
	 * 删除数据
	 * @param menu
	 * @return
	 */
	public int  delEmployeeMenu(Long id);

}
