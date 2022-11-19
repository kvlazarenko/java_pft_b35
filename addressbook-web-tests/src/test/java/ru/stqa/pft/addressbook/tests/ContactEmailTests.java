package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @Test
  public void testContactEmails() {

    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Petr").withLastname("Petrov").withHomePhone("+(790)").withMobilePhone("22-22").withWorkPhone("33 33 33")
              .withEmailAddress("test@tests.com").withGroup("test1").withEmailAddress("test@tests.com")
              .withEmail2Address("test2@tests.com").withEmail3Address("test3@tests.com"));
    }
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
