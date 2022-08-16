package com.evoselenium.framework.page;

import com.evoselenium.framework.selenium.TestContext;

public abstract class AbstractPageComponent extends AbstractComponentBase implements IPageComponent {

    public AbstractPageComponent(TestContext context) {
        super(context);
        verify();
    }
}
