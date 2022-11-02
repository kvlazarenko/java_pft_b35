package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactCreationTests extends TestBase {


  @Test

  public void testContactCreation() throws Exception {
    app.getNaigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().IsThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getContactHelper().createContact(new ContactData("Petr", "Petrov", "+79000000000",
            "test@tests.com", "test1"));
  }
}


