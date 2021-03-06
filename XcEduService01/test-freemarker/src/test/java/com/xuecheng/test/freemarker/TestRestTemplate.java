package com.xuecheng.test.freemarker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test1(){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/page/getModel/5a754adf6abb500ad05688d9", Map.class);
        System.out.println(forEntity);
    }
}
