package com.spring.testautomation.driver.service;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
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
@Service
public class ScreenshotService {

    @Autowired
    private ApplicationContext ctx;

    @Value("${application.screenshot.path}")
    private Path screenshotPath;

    public void takeScreenshot(){
        File srcFile = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'", Locale.getDefault()).format(new Date());

        try {
            FileCopyUtils.copy(srcFile, new File(screenshotPath + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getScreenshot(){
        return this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
    }
}
