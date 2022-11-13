package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().List().size() == 0) {
      app.group().create(new GroupData().whithName("tet1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    List<GroupData> before = app.group().List();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().List();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);

    Assert.assertEquals(before, after);

  }

}
