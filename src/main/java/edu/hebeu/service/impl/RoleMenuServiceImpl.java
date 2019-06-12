package edu.hebeu.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import edu.hebeu.entity.RoleMenu;
import edu.hebeu.mapper.RoleMenuMapper;
import edu.hebeu.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService{

	@Override
	public List<Long> getListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return baseMapper.getListByRoleId(roleId);
	}

}
