package com.error.rest.error;

public class MyFeignClientException extends RuntimeException {

    private static final long serialVersionUID = 6335671059259121916L;
    private ErrorObj          errorObj;

    public MyFeignClientException(final ErrorObj errorObjParam) {
        super();
        this.setErrorObj(errorObjParam);
    }


    public MyFeignClientException() {
    }


    public ErrorObj getErrorObj() {
        return this.errorObj;
    }


    public void setErrorObj(final ErrorObj errorObjParam) {
        this.errorObj = errorObjParam;
    }
}
