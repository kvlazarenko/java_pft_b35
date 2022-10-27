package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NaigationHelper extends HelperBase {


  public NaigationHelper(WebDriver wd) {

    super(wd);

  }

  public void gotoGroupPage() {
        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
  }

  public void gotoContactPage() {
    click(By.linkText("home"));
  }
}
