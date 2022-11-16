package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void homePage() {
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

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void delete(ContactData contact) {
    selectContact(contact.getId());
    deleteSelectedContact();
  }

  public void selectContact(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModification(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']"))
            .findElement(By.xpath("../..//img[@alt='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean IsThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData data) {
    initContactCreation();
    fillContactForm(data, true);
    submitContactCreation();
    homePage();
  }

  public void modify(ContactData contact) {
    selectContact(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    homePage();
  }


  public Contacts all() {

    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));

      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();

      contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname));
    }
    return contacts;

  }
}
