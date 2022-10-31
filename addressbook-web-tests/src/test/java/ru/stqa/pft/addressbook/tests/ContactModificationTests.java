package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {

    app.getNaigationHelper().gotoHomePage();
    if (!app.getContactHelper().IsThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Petr", "Petrov", "+79000000000",
              "test@tests.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov",
            "+79000000000", "test@tests.com", "null"), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
