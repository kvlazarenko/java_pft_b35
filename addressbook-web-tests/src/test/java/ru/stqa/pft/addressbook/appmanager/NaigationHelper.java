package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NaigationHelper {
  private WebDriver wd;


  public NaigationHelper(WebDriver wd) {
    this.wd = wd;

  }

  public void gotoGroupPage() {
    wd.findElement(By.xpath("//*/text()[normalize-space(.)='']/parent::*")).click();
  }
}
