package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test

  public void testContactCreation() throws Exception {
    app.getNaigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().IsThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    ContactData contact = new ContactData("Ivan", "Ivanov", "+79000000000",
            "test@tests.com", "test1");
    app.getContactHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(contact);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}


