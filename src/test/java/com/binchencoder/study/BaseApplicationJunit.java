package com.binchencoder.study;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试基类
 *
 * @author: chenbin
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ServerApplication.class})
@ActiveProfiles(profiles = "dev")
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Rollback(false)
public class BaseApplicationJunit {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseApplicationJunit.class);

    // 在所有测试方法前执行一次，一般在其中写上整体初始化的代码
    @BeforeClass
    public static void before() {
        LOGGER.info("================before do something=============");
    }

    // 在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码 -8558317122796788040
    @AfterClass
    public static void after() {
        LOGGER.info("================After do something=============");
    }

    public BaseApplicationJunit() {
    }

}
