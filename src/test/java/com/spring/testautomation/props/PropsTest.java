package com.spring.testautomation.props;

import com.spring.testautomation.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author cristian_iosef
 */
public class PropsTest extends SpringBaseTestNGTest {

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void propsTest() throws IOException {
        PropertiesLoaderUtils.loadProperties(resourceLoader.getResource("classpath:my.properties"))
                .forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
