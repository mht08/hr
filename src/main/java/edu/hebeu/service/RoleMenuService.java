package edu.hebeu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import edu.hebeu.entity.RoleMenu;

public interface RoleMenuService extends IService<RoleMenu>{

	public List<Long> getListByRoleId(Long roleId);
}

