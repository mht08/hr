package edu.hebeu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;


import edu.hebeu.entity.Role;
import edu.hebeu.mapper.RoleMapper;
import edu.hebeu.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{
	@Autowired
	private RoleMapper roleMapper;

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
		return roleMapper.insertRole(role);
	}

	@Override
	public int delRole(Long id) {
		Role selectById = roleMapper.selectById(id);
		roleMapper.delRole(id);;
		return 0;
	}

}
