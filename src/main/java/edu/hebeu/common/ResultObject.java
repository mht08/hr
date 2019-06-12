package edu.hebeu.common;

public class ResultObject<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultObject getSuccess(Object data) {
        ResultObject result = new ResultObject(MessageCode.CODE_SUCCESS);
        result.setData(data);
        return result;
    }

    public ResultObject(MessageCode code) {
        this.code = code.getCode();
        this.message = Util.getMessage(code.name());
    }

    public ResultObject(MessageCode code, T data, String...argv) {
        this.code = code.getCode();
        this.message = Util.getMessage(code.name(), argv);
        this.data = data;
    }
}
