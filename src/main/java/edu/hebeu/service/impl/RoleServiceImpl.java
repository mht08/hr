package edu.hebeu.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import edu.hebeu.entity.Role;
import edu.hebeu.entity.RoleMenu;
import edu.hebeu.mapper.RoleMapper;
import edu.hebeu.mapper.RoleMenuMapper;
import edu.hebeu.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public List<Role> selectRoleAll() {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleAll();
	}

	@Override
	public Role selectRoleById(Long id) {
		Role employee = baseMapper.selectById(id);
	   return employee;
	}

	@Override
	public int insertRole(Role role) {
		// TODO Auto-generated method stub
		int count = 0;
		// ID不存在为新增,ID存在为修改
		if(role.getId() == null) {
			// 插入角色
			count = roleMapper.insertRole(role);
		} else {
			// 修改角色
			count = roleMapper.updateById(role);
			// 删除角色与菜单关系
			roleMenuMapper.delByRole(role.getId());
		}
		
		// 判断是否有新的菜单
		if(StringUtils.isNotBlank(role.getMenuIds())) {
			String[] menuIdStr = role.getMenuIds().split(",");
			List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
			for (String string : menuIdStr) {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(role.getId());
				roleMenu.setMenuId(Long.valueOf(string));
				roleMenuList.add(roleMenu);
			}
			// 插入角色与菜单关系
			roleMenuMapper.insertRoleAndMenu(roleMenuList);
		}
		return count;
	}

	@Override
	public int delRole(Long id) {
		Role selectById = roleMapper.selectById(id);
		roleMapper.delRole(id);;
		return 0;
	}

}
