package edu.hebeu.util;

public enum MessageCode {
	/** 成功*/
	CODE_SUCCESS("0000"),
	/** 参数错误*/
	CODE_PARAMETER_ERROR("9000"),
	/** 用户名或密码错误*/
	CODE_LOGIN_ERROR("9001"),
	/** 状态错误*/
	CODE_STATE_ERROR("9002"),
	/** 请先登录*/
	CODE_NEED_LOGIN("9003"),
	/** 原密码错误*/
	CODE_PASSWORD_ERROR("9004"),
	/** {1}*/
	CODE_MSG_EXCEPTION("9998"),
	/** 系统异常*/
	CODE_EXCEPTION("9999");

	private String code;
	MessageCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
