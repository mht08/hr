package edu.hebeu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_employee_menu")
public class EmployeeMenu extends Model<EmployeeMenu> implements Serializable{
	
	private static final long serialVersionUID = -5703611223185251501L;
	private Long employeeId; // '用户编号',
	private Long menuId; // '菜单编号',
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	@Override
	protected Serializable pkVal() {
		return this.employeeId;
	}
	

}
