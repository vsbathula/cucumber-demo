package com.prasaddys.pageobjects.basepage;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

    private final Logger log = LogManager.getLogger(BasePage.class);

    // Click a button or link
    public void click(SelenideElement element) {
        element.scrollIntoView(true);
        element.click();
        log.info("Element Property to be clicked: {}", element.getAttribute("outerHTML"));
    }

    // Type text into an input field
    public void type(SelenideElement element, String text) {
        element.scrollIntoView(true);
        element.clear();
        element.type(text);
        log.info("Element Property text input: {}", element.getAttribute("outerHTML"));
    }

}
