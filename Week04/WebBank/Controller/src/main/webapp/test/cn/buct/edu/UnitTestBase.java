package cn.buct.edu;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring-controller.xml","classpath:spring-dao.xml","classpath:spring-service.xml"} )
abstract class UnitTestBase extends AbstractTransactionalJUnit4SpringContextTests {
}
