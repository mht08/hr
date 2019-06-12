package edu.hebeu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import edu.hebeu.entity.Menu;
import edu.hebeu.mapper.MenuMapper;
import edu.hebeu.service.MenuService;

@Service("MenuService")
public class MenuSeviceImpl extends ServiceImpl<MenuMapper, Menu> implements
		MenuService {
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> selectMenuAll() {
		// TODO Auto-generated method stub
		return menuMapper.selectMenuAll();
	}

	@Override
	public Menu selectMenuById(Long id) {
		// TODO Auto-generated method stub
		return menuMapper.selectMenuById(id);
	}

	@Override
	public int insertMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuMapper.insertMenu(menu);
	}

	@Override
	public int deletemenuByids(String[] selectIds) {
		int num = 0;
		List<Menu> menu = menuMapper.getMenuByIds(selectIds);
		for (Menu menu2 : menu) {
			String parentIds = menu2.getParentIds() + menu2.getId() + ",";
			num += menuMapper.deleteMenuByParentIds(parentIds, menu2.getId());
		}
		return num;

	}

	@Override
	public Integer updateMenuById(Menu menu) {
		// TODO Auto-generated method stub
		return menuMapper.updateById(menu);
	}

}
