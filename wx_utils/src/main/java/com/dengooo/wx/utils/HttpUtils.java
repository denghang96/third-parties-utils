package com.dengooo.wx.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.dengooo.wx.sdk.WXPayConstants.USER_AGENT;

public class HttpUtils {

    /**
     * 发送请求一个post请求，数据为XML格式
     */
    public static String sendPostXml(String url, String xml) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(xml, "utf-8");
            httpPost.setHeader("Content-Type", "application/xml");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity, "UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送一个待证书的请求
     * @param url
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @param useCert
     * @return
     * @throws Exception
     */
    public static String sendPostXmlWithCert(final String url, String data, int connectTimeoutMs, int readTimeoutMs, boolean useCert,String certPassword, String certFilePath) throws Exception {
        BasicHttpClientConnectionManager connManager;
        if (useCert) {
            // 证书
            char[] password = certPassword.toCharArray();
            InputStream certStream = new FileInputStream(certFilePath);
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslConnectionSocketFactory)
                            .build(),
                    null,
                    null,
                    null
            );
        }
        else {
            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build(),
                    null,
                    null,
                    null
            );
        }

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", USER_AGENT + " " + certPassword);
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }

    /**
     * get请求
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String onlyGet(String url, Map<String, String> params) {
        //创建HttpClinet
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //拼接访问url 进行
            URI uri = new URI(url);
            //拼接搜索内容 ?wd=httpclinet
            URIBuilder uriBuilder = new URIBuilder(uri);
            Set<String> paramsKey = params.keySet();
            Iterator<String> iterator = paramsKey.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                uriBuilder.setParameter(key, params.get(key));
            }
            URI uriParma = uriBuilder.build();
            //添加HTTP GET请求 访问百度搜索httpclient相关信息
            HttpGet httpGet = new HttpGet(uriParma);
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpGet);
            int satausCode = response.getStatusLine().getStatusCode();
            if (satausCode == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return content;
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
