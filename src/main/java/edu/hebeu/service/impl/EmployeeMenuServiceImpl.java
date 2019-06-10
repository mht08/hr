package edu.hebeu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import edu.hebeu.entity.Attendance;
import edu.hebeu.entity.EmployeeMenu;
import edu.hebeu.mapper.AttendanceMapper;
import edu.hebeu.mapper.EmployeeMenuMapper;
import edu.hebeu.service.EmployeeMenuService;
@Service("EmployeeMenuService")
public class EmployeeMenuServiceImpl  extends ServiceImpl<EmployeeMenuMapper, EmployeeMenu>  implements EmployeeMenuService{
	@Autowired
	private EmployeeMenuMapper employeeMenuMapper;

	@Override
	public List<EmployeeMenu> selectEmployeeMenuAll() {
		// TODO Auto-generated method stub
		return employeeMenuMapper.selectEmployeeMenuAll();
	}

	@Override
	public EmployeeMenu selectEmployeeMenuById(Long id) {
		// TODO Auto-generated method stub
		return employeeMenuMapper.selectEmployeeMenuById(id);
	}

	@Override
	public int insertEmployeeMenu(EmployeeMenu employeeMenu) {
		// TODO Auto-generated method stub
		return employeeMenuMapper.insertEmployeeMenu(employeeMenu);
	}

}
