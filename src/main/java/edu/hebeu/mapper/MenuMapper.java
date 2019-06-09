package edu.hebeu.mapper;

import java.util.List;

import edu.hebeu.entity.Menu;

public interface  MenuMapper {
	/**
	 * 查询所有
	 * @return
	 */
	public List<Menu> selectMenuAll();
	
	/**
	 * 通过ID查询一条数据
	 * @param id
	 * @return
	 */
	public Menu selectMenuById(Long id);
	
	/**
	 * 修改数据
	 * @param menu
	 * @return
	 */
	public int updateMenu(Menu menu);
	
	/**
	 * 添加数据
	 * @param menu
	 * @return
	 */
	public int  insertMenu(Menu menu);
	
	/**
	 * 删除数据
	 * @param menu
	 * @return
	 */
	public int  delMenu(Long id);
	

}
