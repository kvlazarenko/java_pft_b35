package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NaigationHelper naigationHelper;
  private GroupHelper groupHelper;
  private Browser browser;


  public ApplicationManager(Browser browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    System.setProperty("webdriver.gecko.driver", "C:\\Windows\\System32\\geckodriver.exe");
    if (browser.equals(Browser.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setBinary("pathFirefox"));
    } else if (browser.equals(Browser.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(Browser.IE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    groupHelper = new GroupHelper(wd);
    naigationHelper = new NaigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),
            properties.getProperty("web.adminPassword"), properties.getProperty("web.baseUrl"));
  }


  public void stop() {
    wd.quit();
  }

  public boolean isElementPresent(By by) {
    try {
      groupHelper.wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  public boolean isAlertPresent() {
    try {
      groupHelper.wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NaigationHelper goTo() {
    return naigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

}
