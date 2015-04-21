package com.noriental.teaching.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.noriental.teaching.domain.Chapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/applicationContext*.xml" })
public class ChapterServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ChapterServiceTest.class);
    @Autowired
    private ChapterService chapterService;

    @Before
    public void before() throws Exception {
    }

    @Test
    public void testGetSetString() {
        Chapter c = chapterService.findById(2l);

        Chapter cc = chapterService.findParentWithReturn(c);
        System.out.println(JSONObject.toJSONString(cc));
        logger.info("abc");

    }
}
