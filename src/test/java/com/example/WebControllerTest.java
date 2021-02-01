package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
public class WebControllerTest {

    @Resource
    WebController controller;

    private MockHttpSession session;

    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        this.session = new MockHttpSession();
        response = new MockHttpServletResponse();
    }


    @Test
    public void test_set() {
//        request.set
    }

    @Test
    public void get() {
    }

    @Test
    public void all() {
    }
}