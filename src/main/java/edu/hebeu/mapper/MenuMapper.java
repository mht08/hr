package edu.hebeu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;

import edu.hebeu.entity.Menu;

public interface  MenuMapper extends BaseMapper<Menu>{
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
	
	public int deleteMenuByParentIds(@Param("parentIds") String parentIds,
			@Param("id") Long id);
	
	
	public List<Menu> getMenuByIds(@Param("ids") String[] ids);
	
	public Integer updateById(Menu menu);
	

}
