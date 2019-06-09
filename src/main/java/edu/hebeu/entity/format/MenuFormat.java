package edu.hebeu.entity.format;

import edu.hebeu.entity.Menu;
import edu.hebeu.util.DateUtil;

public class MenuFormat extends Menu{

	private static final long serialVersionUID = 6309412439282098525L;
	private String parentIdStr;
	private String parentIdsStr;
	@SuppressWarnings("unused")
	private String createDateStr;
	@SuppressWarnings("unused")
	private String updateDateStr;
	
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
		return  getCreateDate() != null ? DateUtil.formatDate(getCreateDate(), DateUtil.FMT) : null;
	}
	public String getUpdateDateStr() {
		return getUpdateDate() != null ? DateUtil.formatDate(getUpdateDate(), DateUtil.FMT) : null;
	}
}
