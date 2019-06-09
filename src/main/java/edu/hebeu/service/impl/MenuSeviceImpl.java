package edu.hebeu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hebeu.entity.Menu;
import edu.hebeu.mapper.MenuMapper;
import edu.hebeu.service.MenuService;
@Service("MenuService")
public class MenuSeviceImpl implements MenuService {
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

}
