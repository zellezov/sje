package com.evoselenium.framework.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public final class TakeScreenshot {

    private static byte[] takeSchreenshotInBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void takeScreenshot(String fileName, WebDriver driver) {
        String pathRoot = "target\\surefire-reports\\";
        String filePath = pathRoot + "screenshot-" + fileName + ".png";
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), takeSchreenshotInBytes(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
