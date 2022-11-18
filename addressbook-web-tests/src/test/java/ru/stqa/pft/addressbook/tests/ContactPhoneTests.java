package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Test
public class ContactPhoneTests extends TestBase {

  public void testContactPhones() {

    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Petr").withLastname("Petrov").withHomePhone("+(790)").withMobilePhone("22-22").withWorkPhone("33 33 33")
              .withEmail("test@tests.com").withGroup("test1"));
    }
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

      assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFormEditForm.getHomePhone())));
      assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFormEditForm.getMobilePhone())));
      assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFormEditForm.getWorkPhone())));
    }
    public String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
