package edu.hebeu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("sys_role_menu")
public class RoleMenu extends Model<RoleMenu> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 892377112834316191L;
	private Long roleId; // '角色编号',
	private Long menuId; // '菜单编号',
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.roleId;
	}
	

}
