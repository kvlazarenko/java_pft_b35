package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test

  public void testContactCreation() throws Exception {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().whithName("test1"));
    }
    ContactData contact = new ContactData()
            .withFirstname("Ivan").withLastname("Ivanov").withHomePhone("+79000000000").withEmail("test@tests.com").withGroup("test2");
    app.contact().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().Count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}


