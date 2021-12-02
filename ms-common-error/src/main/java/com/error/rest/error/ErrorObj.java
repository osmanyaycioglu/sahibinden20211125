package com.error.rest.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorObj {

    private List<ErrorObj> subErrorObjs;
    private String         domain;
    private String         boundedContext;
    private String         microservice;
    private String         message;
    private Integer        cause;

    public ErrorObj addSubError(final ErrorObj errorObjParam) {
        if (this.subErrorObjs == null) {
            this.subErrorObjs = new ArrayList<>();
        }
        this.subErrorObjs.add(errorObjParam);
        return this;
    }

    public List<ErrorObj> getSubErrorObjs() {
        return this.subErrorObjs;
    }

    public ErrorObj setSubErrorObjs(final List<ErrorObj> subErrorObjsParam) {
        this.subErrorObjs = subErrorObjsParam;
        return this;
    }

    public String getDomain() {
        return this.domain;
    }

    public ErrorObj setDomain(final String domainParam) {
        this.domain = domainParam;
        return this;
    }

    public String getBoundedContext() {
        return this.boundedContext;
    }

    public ErrorObj setBoundedContext(final String boundedContextParam) {
        this.boundedContext = boundedContextParam;
        return this;
    }

    public String getMicroservice() {
        return this.microservice;
    }

    public ErrorObj setMicroservice(final String microserviceParam) {
        this.microservice = microserviceParam;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorObj setMessage(final String messageParam) {
        this.message = messageParam;
        return this;
    }

    public Integer getCause() {
        return this.cause;
    }

    public ErrorObj setCause(final Integer causeParam) {
        this.cause = causeParam;
        return this;
    }

}
