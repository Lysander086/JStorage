package com.example;

import com.example.entity.PairDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
public class WebControllerTest {

    private TestRestTemplate template = new TestRestTemplate();


    List<PairDto> testList;
    long testNum = 10;
    Random random = new Random();


    String postUrl = "http://localhost:9000";

    @Before
    public void setUp() {
        PairDto tem;
        for (int i = 0; i < testNum; i++) {
            tem = new PairDto(String.valueOf(i), String.valueOf(i), random.nextInt(9000) + 1000);
            testList.add(tem);
        }
    }


    @Test
    public void test_set() {
        System.out.println("hi");
//        for (PairDto item: testList) {
//            try{
//                Object res = template.postForObject(postUrl + "/set", item, String.class);
//                System.out.println(res);
//            }catch (Exception e){
//                System.out.println(e.toString());
//            }
//        }

    }

    @Test
    public void get() {
    }

    @Test
    public void all() {
    }
}