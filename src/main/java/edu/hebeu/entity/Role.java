package edu.hebeu.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import edu.hebeu.util.DateUtil;
@TableName("sys_role")
public class Role extends Model<Role> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7318666331517640866L;
	private Long id; // '编号',
	private String name; // '角色名称',
	private String enname; // '英文名称',
	private String roleType; // '角色类型',
	private String dataScope; // '数据范围',
	private String isSys; // '是否系统数据',
	private String useable; // '是否可用',
	private String createBy; // '创建者',
	private Date createDate; // '创建时间',
	private String updateBy; // '更新者',
	private Date updateDate; // '更新时间',
	private String remarks; // '备注信息',
	private String delFlag; // '删除标记',
	
	@TableField(exist=false)
	private String createDateStr;
	@TableField(exist=false)
	private String updateDateStr;
	@TableField(exist=false)
	private String menuIds;// 角色对应菜单
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getDataScope() {
		return dataScope;
	}
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	public String getIsSys() {
		return isSys;
	}
	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public String getCreateDateStr() {
		return DateUtil.formatDate(getCreateDate(), DateUtil.FMT);
	}
	public String getUpdateDateStr() {
		return DateUtil.formatDate(getUpdateDate(), DateUtil.FMT);
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
}
