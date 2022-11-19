package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().whithName("test1");
    app.group().create(group);
    assertThat(app.group().Count(), equalTo(before.size()+1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.whithId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test
  public void testGroupBadCreation() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().whithName("test2'");
    app.group().create(group);
    assertThat(app.group().Count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
