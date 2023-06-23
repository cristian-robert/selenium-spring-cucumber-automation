package com.spring.testautomation.page.demoqa.mainPage.components;

import com.spring.testautomation.driver.annotations.PageFragment;
import com.spring.testautomation.page.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author cristian_iosef
 */
@PageFragment
public class CheckboxSection extends Base {

        @FindBy(xpath = "//span[@class='text-success']")
        private List<WebElement> successMessage;

        @FindBy(xpath = "//button[@title='Toggle']")
        private List<WebElement> allToggleButtons;

        @Override
        public boolean isAt() {
            return this.wait.until(d -> this.allToggleButtons.size() > 0);
        }

        public boolean checkAllSectionsCollapsed(){
                return this.allToggleButtons.size() == 1;
        }


        public void clickCheckbox(String checkboxName){
                driver.findElement(By.xpath("//label[@for='tree-node-" + checkboxName + "']")).click();
        }

        public void clickDropdown(String dropdownName){
                driver.findElement(By.xpath("//input[@id='tree-node-" + dropdownName + "']/ancestor::label/preceding-sibling::button"))
                        .click();
        }

        public boolean checkSuccessMessage(String message){
                return this.successMessage.stream().anyMatch(e -> e.getText().equals(message));
        }
}
