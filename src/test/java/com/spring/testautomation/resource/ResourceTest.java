package com.spring.testautomation.resource;

import com.spring.testautomation.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author cristian_iosef
 */
public class ResourceTest extends SpringBaseTestNGTest {

    @Value("classpath:data/testData.csv")
    private Resource resource;

    @Value("${application.screenshot.path}")
    private Path path;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test(dataProvider = "getData")
    public void resourceTest(String url, String saveAs) throws IOException {
//        Files.readAllLines(resource.getFile().toPath())
//                .forEach(System.out::println);
//        FileCopyUtils.copy(resource.getInputStream(), Files.newOutputStream(path));
        FileCopyUtils.copy(
                resourceLoader.getResource(url).getInputStream(),
                Files.newOutputStream(path.resolve(saveAs)));

    }

    @DataProvider
    public Object [][] getData() throws IOException {
        return Files.readAllLines(resource.getFile().toPath())
                .stream()
                .map(line -> line.split(","))
                .toArray(Object[][]::new);
    }
}
