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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

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
    contactCash = null;
  }

  public void selectContact(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public ContactData infoFormEditForm(ContactData contact) {

    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmailAddress(email)
            .withEmail2Address(email2).withEmail3Address(email3);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    //    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
//    WebElement row = checkbox.findElement(By.xpath(".//..//.."));
//    List<WebElement> cells = row.findElements(By.tagName("td"));
//    cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//@input[value='%s']]//td[8]/a", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public int Count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public boolean IsThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData data) {
    initContactCreation();
    fillContactForm(data, true);
    submitContactCreation();
    contactCash = null;
    homePage();
  }

  public void modify(ContactData contact) {
    selectContact(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCash = null;
    homePage();
  }

  private Contacts contactCash = null;

  public Contacts all() {
    if (contactCash != null) {
      return new Contacts(contactCash);
    }
    contactCash = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));

      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();

      contactCash.add(new ContactData()
              .withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address)
              .withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return contactCash;

  }


}
