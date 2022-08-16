package com.evoselenium.framework.page;

import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbstractComponentBase {

    private final TestContext context;

    AbstractComponentBase(TestContext context) {
        this.context = context;
    }

    protected TestContext getContext() {
        return context;
    }

    protected boolean waitForPageComponentElementVisible(By by) {
        context.getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        return true;
    }

    protected WebElement getElement(By by) {
        return context.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void scrollIntoView(WebElement webElement) {
        ((JavascriptExecutor) context.getDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void click(WebElement webElement) {
        WebElement element = context.getWait().until(ExpectedConditions.elementToBeClickable(webElement));
        scrollIntoView(webElement);
        element.click();
    }

    protected void click(WebElement parent, By by) {
        click(context.getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, by)));
    }
}
