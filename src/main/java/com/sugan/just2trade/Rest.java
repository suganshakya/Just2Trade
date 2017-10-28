package com.sugan.just2trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Rest {
    private RestTemplate restTemplate;
    private String baseUrl;
    private String accessToken;

    public Rest(String baseUrl) {
        this.baseUrl = baseUrl;
        restTemplate = new RestTemplate();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public <T> T get(String url, Class<T> clazz){
        ResponseEntity<T> responseEntity = restTemplate.exchange(baseUrl + url, HttpMethod.GET, getHttpEntity(), clazz);
        return responseEntity.getBody();
    }

    public <T> T post(String url, Object object, Class<T> clazz){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        if(accessToken != null)
            headers.add("Authorization", "Bearer " + accessToken);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<Object> request = new HttpEntity<Object>(object, headers);

        return restTemplate.postForObject(baseUrl + url, request, clazz);
    }


    public static String getJsonString(Object object)  {
        if(object == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseJson(JsonNode node, Class<T> clazz){
        if(node == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<String> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(accessToken != null)
            headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);
        return httpEntity;
    }


}
