package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPropertiesTests extends TestBase {

  public void preconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Petr").withLastname("Petrov").withAddress("г.Москва ул.Замоскворецкая").withHomePhone("+(790)")
              .withMobilePhone("22-22").withWorkPhone("33 33 33").witHomePhone2("44-44 44").withEmailAddress("test@tests.com")
              .withEmail2Address("test2@tests.com").withEmail3Address("test3@tests.com").withGroup("test1"));
    }
  }

  @Test

  public void testContactPhones() {

    preconditions();
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));
  }

  @Test
  public void testContactEmails() {

    preconditions();
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditForm)));
  }

  @Test
  public void testContactAddress() {
    
    preconditions();
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone2())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPropertiesTests::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
