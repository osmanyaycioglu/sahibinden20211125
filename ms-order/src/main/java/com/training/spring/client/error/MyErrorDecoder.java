package com.training.spring.client.error;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.error.rest.error.ErrorObj;
import com.error.rest.error.MyFeignClientException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

@Component
public class MyErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(final String methodKeyParam,
                            final Response responseParam) {
        try {
            if (responseParam.status() == 500) {
                return new RetryableException(responseParam.status(),
                                              "Internal server error",
                                              null,
                                              null,
                                              responseParam.request());
            }
            InputStream asInputStreamLoc = responseParam.body()
                                                        .asInputStream();
            ObjectMapper mapperLoc = new ObjectMapper();
            ErrorObj errorObjLoc = mapperLoc.readValue(asInputStreamLoc,
                                                       ErrorObj.class);
            return new MyFeignClientException(errorObjLoc);
        } catch (Exception eLoc) {
            eLoc.printStackTrace();
        }
        return this.defaultDecoder.decode(methodKeyParam,
                                          responseParam);
    }

}
