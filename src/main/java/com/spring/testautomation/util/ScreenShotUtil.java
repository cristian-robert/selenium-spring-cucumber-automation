package com.spring.testautomation.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author cristian_iosef
 */
@Lazy
@Component
public class ScreenShotUtil {

    @Autowired
    private TakesScreenshot driver;

    @Value("${application.screenshot.path}")
    private Path screenshotPath;

    public void takeScreenShot(){
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'", Locale.getDefault()).format(new Date());

        try {
            FileCopyUtils.copy(srcFile, new File(screenshotPath + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
