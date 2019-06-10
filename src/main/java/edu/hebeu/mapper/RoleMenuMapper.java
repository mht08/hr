package edu.hebeu.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import edu.hebeu.entity.RoleMenu;

public interface RoleMenuMapper extends BaseMapper<RoleMenu>{
	/**
	 * 查询所有
	 * @return
	 */
	public List<RoleMenu> selectRoleMenuAll();
	
	/**
	 * 通过ID查询一条数据
	 * @param id
	 * @return
	 */
	public RoleMenu selectRoleMenuById(Long id);
	
	/**
	 * 修改数据
	 * @param menu
	 * @return
	 */
	public int updateRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 添加数据
	 * @param menu
	 * @return
	 */
	public int  insertRoleMenu(RoleMenu RoleMenu);
	
	/**
	 * 删除数据
	 * @param menu
	 * @return
	 */
	public int  delRoleMenu(Long id);

}
