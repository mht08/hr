package edu.hebeu.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("sys_log")
public class Log  extends Model<Log> implements Serializable{
	private Long id; // '编号',
	private String type; // '日志类型',
	private String title; // '日志标题',
	private String createBy; // '创建者',
	private Date createDate; // '创建时间',
	private String remoteAddr; // '操作IP地址',
	private String employeeAgent; // '用户代理',
	private String requestUri; // '请求URI',
	private String method; // '操作方式',
	private String params; // '操作提交的数据',
	private String exception; // '异常信息',
	private Long menuId; // '菜单ID',
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreate_by(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getEmployeeAgent() {
		return employeeAgent;
	}
	public void setEmployeeAgent(String employeeAgent) {
		this.employeeAgent = employeeAgent;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
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
		return this.id;
	}
	
	
}
