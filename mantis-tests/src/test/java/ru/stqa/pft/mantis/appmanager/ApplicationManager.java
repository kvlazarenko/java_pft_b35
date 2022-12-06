package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;


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
      wd = new FirefoxDriver(new FirefoxOptions().setBinary(properties.getProperty("pathFirefox")));
    } else if (browser.equals(Browser.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(Browser.IE)) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }
  public void stop() {
    wd.quit();
  }
}
