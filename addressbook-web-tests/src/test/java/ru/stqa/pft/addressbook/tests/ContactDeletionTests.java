package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion() throws Exception {

    app.goTo().groupPage();
    if (!app.group().IsThereAGroup()) {
      app.group().create(new GroupData().whithName("test1"));
    }
    app.goTo().gotoHomePage();
    if (!app.getContactHelper().IsThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Petr", "Petrov", "+79000000000",
              "test@tests.com", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
