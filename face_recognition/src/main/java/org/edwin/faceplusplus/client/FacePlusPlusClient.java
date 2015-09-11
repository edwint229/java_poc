package org.edwin.faceplusplus.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Client for call API from faceplusplus.com
 * 
 * @author edwin
 */
@Component
public class FacePlusPlusClient {

    private static final Logger logger = LoggerFactory.getLogger(FacePlusPlusClient.class);

    @Value("#{props['api_host']}")
    private String apiHost;

    @Value("#{props['api_key']}")
    private String apiKey;

    @Value("#{props['api_secret']}")
    private String apiSecret;

    /**
     * Call Face Plus Plus APIs
     * 
     * @param api
     * @return @
     */
    public String request(String api) {
        return request(api, PostParamBuilder.create());
    }

    /**
     * Call Face Plus Plus APIs
     * 
     * @param api
     * @param params
     * @return
     */
    public String request(String api, PostParamBuilder params) {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(apiHost + api);
            MultipartEntityBuilder builder = params.getMultiPartBuilder();
            builder.addTextBody("api_key", apiKey);
            builder.addTextBody("api_secret", apiSecret);
            httpPost.setEntity(builder.build());

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);

            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode() || json.contains("error")) {
                throw new RuntimeException(json);
            }

            return json;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
}
