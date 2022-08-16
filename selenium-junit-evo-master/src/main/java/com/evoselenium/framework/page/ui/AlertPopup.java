package com.evoselenium.framework.page.ui;

import com.evoselenium.framework.page.AbstractPageComponent;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AlertPopup extends AbstractPageComponent {

    private static final By ALERT_POPUP_ROOT = By.id("alert_dv");

    private static final By CLOSE = By.cssSelector(".alert_head_right");

    private static final By MESSAGE = By.id("alert_msg");

    private static final By OK = By.id("alert_ok");

    public AlertPopup(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        waitForPageComponentElementVisible(ALERT_POPUP_ROOT);
    }

    private WebElement getRootElement() {
        return getElement(ALERT_POPUP_ROOT);
    }

    public void clickClose() {
        click(getRootElement(), CLOSE);
    }

    private String getAlertMessage() {
        return getRootElement().findElement(MESSAGE).getText();
    }

    public void verifyAlertMessage(String message) {
        assertThat("Alert message does not match expected", getAlertMessage(), is(message));
    }

    public ActionPromise clickOk() {
        click(getRootElement(), OK);
        return new ActionPromise(getContext());
    }
}
