package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test

  public void testContactCreation() throws Exception {

    app.getContactHelper().createContact(new ContactData("Petr", "Petrov", "+79000000000",
            "test@tests.com", "test1"), true);
  }


}
