package edu.hebeu.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import edu.hebeu.entity.Role;


public interface RoleMapper extends BaseMapper<Role>{
	/**
	 * 查询所有
	 * @return
	 */
	public List<Role> selectRoleAll();
	
	/**
	 * 通过ID查询一条数据
	 * @param id
	 * @return
	 */
	public Role selectRoleById(Long id);
	
	/**
	 * 修改数据
	 * @param menu
	 * @return
	 */
	public int updateRole(Role role);
	
	/**
	 * 添加数据
	 * @param menu
	 * @return
	 */
	public int  insertRole(Role role);
	
	/**
	 * 删除数据
	 * @param menu
	 * @return
	 */
	public void  delRole(Long id);

}
