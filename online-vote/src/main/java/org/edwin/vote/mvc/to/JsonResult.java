package org.edwin.vote.mvc.to;

public class JsonResult {

    private Integer code;

    private Object result;

    public static JsonResult fail(String message) {
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setResult(message);

        return result;
    }

    public static JsonResult success(String message) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setResult(message);

        return result;
    }

    public static JsonResult success(Object returnData) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setResult(returnData);

        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
