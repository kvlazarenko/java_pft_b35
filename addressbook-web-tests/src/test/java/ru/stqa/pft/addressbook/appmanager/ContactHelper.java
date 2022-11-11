package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());


    if (creation) {
      List<WebElement> searchgroup = wd.findElements(By.name("new_group"));
      Select s = new Select(wd.findElement(By.name("new_group")));
      for (WebElement element : searchgroup) {
        List<WebElement> option = element.findElements(By.tagName("option"));
        for (int i = 0; i < option.size(); i++) {
          if (option.get(i).getText().equals(contactData.getGroup())) {
            s.selectByVisibleText((contactData.getGroup()));
            return;
          }
        }
        s.selectByIndex(0);
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
// if(creation)
//  {
//    new Select(wd.findElement(By.name("new_group")))
//            .selectByVisibleText((contactData.getGroup()));
//  } else
//    Assert.assertFalse(isElementPresent(By.name("new_group")));
//  }


  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean IsThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData data) {
    initContactCreation();
    fillContactForm(data, true);
    submitContactCreation();
    returnToHomePage();
  }

  public List<ContactData> getContactList() {

    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));

      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String f = cells.get(1).getText();
      String l = cells.get(2).getText();

      ContactData contact = new ContactData(id, l, f, null, null, null);
      contacts.add(contact);
    }
    return contacts;

  }
}
