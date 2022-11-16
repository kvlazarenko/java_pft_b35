package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {

    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().whithName("test1"));
    }
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact()
              .create(new ContactData()
                      .withFirstname("Petr").withLastname("Petrov").withPhone("+79000000000").withEmail("test@tests.com").withGroup("test1"));
    }
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId()).withFirstname("Sidr").withLastname("Sidorov").withPhone("+78000000000").withEmail("www@www.com");

    app.contact().modify(contact);

    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size()));

    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));

  }
}

