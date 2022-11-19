package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @Test
  public void testContactAddress() {

    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Petr").withLastname("Petrov").withAddress("г.Москва ул.Замоскворецкая").withHomePhone("+(790)")
              .withMobilePhone("22-22").withWorkPhone("33 33 33").withEmailAddress("test@tests.com")
              .withGroup("test1").withEmailAddress("test@tests.com").withEmail2Address("test2@tests.com")
              .withEmail3Address("test3@tests.com"));
    }
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
  }
}
