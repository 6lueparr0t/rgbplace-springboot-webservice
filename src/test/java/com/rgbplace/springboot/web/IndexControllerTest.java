package com.rgbplace.springboot.web;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스 Ver.2");
    }

    @Test
    public void Json타입_리턴() throws Exception {
        String transId = "\"vnblqoeir1020301294010\"";

        try {
            JSONObject jsonObject = new JSONObject("{\"transId\":"+transId+"}");
            log.info("test : {}", jsonObject);
        }catch (JSONException err){
            log.info("Error : {}", err.toString());
        }
    }
}
