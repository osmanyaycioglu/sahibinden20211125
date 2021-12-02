package com.training.spring.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class ClientConfig {


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplateBuilder rt = this.restTemplateBuilder();
        RestTemplate restTemplateLoc = rt.build();
        List<ClientHttpRequestInterceptor> interceptorsLoc = restTemplateLoc.getInterceptors();
        if (interceptorsLoc == null) {
            interceptorsLoc = new ArrayList<>();
        }
        interceptorsLoc.add(new MyInterceptor());
        restTemplateLoc.setInterceptors(interceptorsLoc);
        return restTemplateLoc;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder builderLoc = new RestTemplateBuilder();
        builderLoc.requestFactory(this::httpComponentsClientHttpRequestFactory);
        builderLoc.errorHandler(new MyResponseErrorHandler());
        builderLoc.interceptors(new MyInterceptor());
        return builderLoc;
    }

    public static class MyInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(final HttpRequest requestParam,
                                            final byte[] bodyParam,
                                            final ClientHttpRequestExecution executionParam) throws IOException {
            long delta = System.nanoTime();
            HttpHeaders headersLoc = requestParam.getHeaders();
            headersLoc.add("X-Origin",
                           "restaurant.subdomain.order.order");
            ClientHttpResponse responseLoc = executionParam.execute(requestParam,
                                                                    bodyParam);
            System.out.println("Delta : " + (System.nanoTime() - delta));
            return responseLoc;
        }

    }

    public static class MyResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(final ClientHttpResponse responseParam) throws IOException {
            return true;
        }

        @Override
        public void handleError(final ClientHttpResponse responseParam) throws IOException {
            System.out.println("Error handler error e baktı");
        }

    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpReFactory = new HttpComponentsClientHttpRequestFactory();
        httpReFactory.setHttpClient(this.closeableHttpClient());
        return httpReFactory;
    }

    @Bean
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom()
                          .setConnectionManager(this.poolingHttpClientConnectionManager())
                          .setDefaultRequestConfig(RequestConfig.custom()
                                                                .setConnectTimeout(1000)
                                                                .setConnectionRequestTimeout(1000)
                                                                .setSocketTimeout(5000)
                                                                .build())
                          .setKeepAliveStrategy(this.connectionKeepAliveStrategy())
                          .build();
    }


    @Scheduled(fixedDelay = 60000)
    public void closeConnections() {
        PoolingHttpClientConnectionManager p = this.poolingHttpClientConnectionManager();
        p.closeExpiredConnections();
        p.closeIdleConnections(60,
                               TimeUnit.SECONDS);
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return ClientConfig::getKeepAliveDuration;
    }

    public static long getKeepAliveDuration(final HttpResponse r,
                                            final HttpContext c) {
        Args.notNull(r,
                     "HTTP response");
        final HeaderElementIterator it = new BasicHeaderElementIterator(r.headerIterator(HTTP.CONN_KEEP_ALIVE));
        while (it.hasNext()) {
            final HeaderElement he = it.nextElement();
            final String param = he.getName();
            final String value = he.getValue();
            if ((value != null) && param.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(value) * 1000;
                } catch (final NumberFormatException ignore) {
                }
            }
        }
        return 60_000;
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager();
        pccm.setMaxTotal(300);
        pccm.setDefaultMaxPerRoute(50);
        HttpRoute httpRouteLoc = new HttpRoute(HttpHost.create("http://127.0.0.1:8080"));
        pccm.setMaxPerRoute(httpRouteLoc,
                            100);
        pccm.setDefaultSocketConfig(SocketConfig.custom()
                                                .setSoKeepAlive(true)
                                                .setSoTimeout(5000)
                                                .setTcpNoDelay(true)
                                                .build());
        return pccm;
    }


}
