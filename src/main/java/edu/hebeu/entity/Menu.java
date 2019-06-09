package edu.hebeu.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import edu.hebeu.util.DateUtil;

@TableName("sys_menu")
public class Menu extends Model<Leave> implements Serializable{
	private static final long serialVersionUID = 4124436894266362736L;
	private Long id; // '编号',
	private Long parentId; // '父级编号',
	private String parentIds; // '所有父级编号',
	private String name; // '名称',
	private String sort; // '排序',
	private String href; // '链接',
	private String hrefType; // '链接类型',
	private String target; // '目标',
	private String icon; // '图标',
	private String isShow;// '是否在菜单中显示',
	private String permission; // '权限标识',
	private String createBy; // '创建者',
	private Date createDate; // '创建时间',
	private String updateBy; // '更新者',
	private Date updateDate; // '更新时间',
	private String remarks; // '备注信息',
	private String delFlag; // '删除标记',
	private int homeShow; // '是否首页显示 0:NO 1:YES ',
	private String HomeIcon; // '关联图片存储表id',
	private int treeShow; // '是否树形菜单显示 0:NO 1:YES ',
	private String isReportPutaway; // '是否上架',
	private String isReport; // '是否报表',
	private String isShare; // '是否分享 （1：是；0：不是）',
	private String isNewIframe; // '是否打开新页面 （1：是；0：否）',
	private String isAppView; // '是否AP显示 （1：是；0：否）',
	private String filePath; // '文件地址',
	private String parentIdStr;
	private String parentIdsStr;
	@SuppressWarnings("unused")
	private String createDateStr;
	@SuppressWarnings("unused")
	private String updateDateStr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
	return  parentId == null ? 0L : parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return StringUtils.isBlank(parentIds) ? "0," : parentIds.trim();
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getHrefType() {
		return hrefType;
	}
	public void setHrefType(String hrefType) {
		this.hrefType = hrefType;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
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
	public int getHomeShow() {
		return homeShow;
	}
	public void setHomeShow(int homeShow) {
		this.homeShow = homeShow;
	}
	public String getHomeIcon() {
		return HomeIcon;
	}
	public void setHomeIcon(String homeIcon) {
		HomeIcon = homeIcon;
	}
	public int getTreeShow() {
		return treeShow;
	}
	public void setTreeShow(int treeShow) {
		this.treeShow = treeShow;
	}
	public String getIsReportPutaway() {
		return isReportPutaway;
	}
	public void setIsReportPutaway(String isReportPutaway) {
		this.isReportPutaway = isReportPutaway;
	}
	public String getIsReport() {
		return isReport;
	}
	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public String getIsNewIframe() {
		return isNewIframe;
	}
	public void setIsNewIframe(String isNewIframe) {
		this.isNewIframe = isNewIframe;
	}
	public String getIsAppView() {
		return isAppView;
	}
	public void setIsAppView(String isAppView) {
		this.isAppView = isAppView;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getParentIdStr() {
		return parentIdStr;
	}
	public void setParentIdStr(String parentIdStr) {
		this.parentIdStr = parentIdStr;
	}
	public String getParentIdsStr() {
		return parentIdsStr;
	}
	public void setParentIdsStr(String parentIdsStr) {
		this.parentIdsStr = parentIdsStr;
	}
	public String getCreateDateStr() {
		return DateUtil.formatDate(getCreateDate(), DateUtil.FMT);
	}
	public String getUpdateDateStr() {
		return DateUtil.formatDate(getUpdateDate(), DateUtil.FMT);
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
}
