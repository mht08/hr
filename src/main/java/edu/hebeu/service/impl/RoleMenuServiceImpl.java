package edu.hebeu.service.impl;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import edu.hebeu.entity.RoleMenu;
import edu.hebeu.mapper.RoleMenuMapper;
import edu.hebeu.service.RoleMenuService;

public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService{

	@Override
	public boolean insert(RoleMenu entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAllColumn(RoleMenu entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<RoleMenu> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<RoleMenu> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<RoleMenu> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<RoleMenu> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateById(RoleMenu entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnById(RoleMenu entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RoleMenu entity, Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<RoleMenu> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<RoleMenu> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdate(RoleMenu entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RoleMenu selectOne(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectMap(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectObj(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoleMenu> selectList(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RoleMenu> selectPage(Page<RoleMenu> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> selectObjs(Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> selectMapsPage(Page page,
			Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RoleMenu> selectPage(Page<RoleMenu> page,
			Wrapper<RoleMenu> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<RoleMenu> selectRoleMenuAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleMenu selectRoleMenuById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRoleMenu(RoleMenu RoleMenu) {
		// TODO Auto-generated method stub
		return 0;
	}

}
