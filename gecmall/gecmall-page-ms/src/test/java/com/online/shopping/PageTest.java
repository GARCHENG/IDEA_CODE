package com.online.shopping;

import com.online.shopping.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = PageMsApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PageTest {

    @Autowired
    private PageService pageService;

    @Test
    public void fun1(){
        pageService.getItemHtml("149187842867982");

    }
}
