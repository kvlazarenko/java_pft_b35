package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {

    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().whithName("test1"));
    }
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Petr").withLastname("Petrov").withPhone("+79000000000").withEmail("test@tests.com").withGroup("test1"));
    }
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size() - 1));

    assertThat(after, equalTo(before.without(deletedContact)));

  }

}
