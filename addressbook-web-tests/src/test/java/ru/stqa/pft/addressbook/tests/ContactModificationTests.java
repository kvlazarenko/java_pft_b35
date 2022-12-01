package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().whithName("test1"));
    }

    if (app.db().contacts().size()== 0) {
      app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov")
              .withAddress("London").withEmailAddress("london@london.com")
              .withHomePhone("111111").withMobilePhone("222222")
              .withWorkPhone("333333").witHomePhone2("444444").withEmail2Address("london2@london.com")
              .withEmail3Address("london3@london.com"));
    }
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstname("Petr").withLastname("Petrov")
            .withAddress("Paris").withEmailAddress("paris@paris.com")
            .withHomePhone("999999").witHomePhone2("666666").withMobilePhone("888888")
            .withWorkPhone("777777").withEmail2Address("paris2@paris.com")
            .withEmail3Address("paris3@paris.com");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().Count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));

  }
}

