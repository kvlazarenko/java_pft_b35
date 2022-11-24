package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    xStream.allowTypes(new Class[]{ContactData.class});
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider = "validContactsFromXml")

  public void testContactCreation(ContactData contact) throws Exception {
//    app.goTo().groupPage();
//    if (app.group().all().size() == 0) {
//      app.group().create(new GroupData().whithName("test1"));
//    }
//    File photo = new File("src/test/resources/ru.png");
//    ContactData contact = new ContactData()
//            .withFirstname("Ivan").withLastname("Ivanov").withHomePhone("+79000000000").withEmailAddress("test@tests.com").withPhoto(photo);
    app.contact().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().Count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}


