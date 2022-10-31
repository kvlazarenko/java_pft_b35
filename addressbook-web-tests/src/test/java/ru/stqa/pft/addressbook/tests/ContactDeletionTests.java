package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {

    app.getNaigationHelper().gotoHomePage();
    if (!app.getContactHelper().IsThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Petr", "Petrov", "+79000000000",
              "test@tests.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().switchToAlertAccept();
  }

}
