package ru.stqa.pft.mantis.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() throws IOException, MessagingException {
    Users users = app.db().users();
    if (users.size() == 1) {
      long now = System.currentTimeMillis();
      String userReg = String.format("user%s", now);
      String passwordReg = "password";
      String emailReg = String.format("user%s@localhost", now);
      app.james().createUser(userReg, passwordReg);
      app.registration().start(userReg, emailReg);
      List<MailMessage> mailMessages = app.james().waitForMail(userReg, passwordReg, 120000);
      String confirmationLink = findConfirmationLinkCreateUser(mailMessages, emailReg);
      app.registration().finish(confirmationLink, passwordReg);
    }
  }

  @Test
  public void testChangeUserPassword() throws IOException, MessagingException {

    Users users = app.db().users();
    UserData user = users.stream().filter(usr -> usr.getId() != 1).findFirst().get();
    String username = user.getUsername();
    String email = user.getEmail();
    String password = "password";

    app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.navigationHelper().goToManageUsersPage();
    app.navigationHelper().selectUser(username);
    app.navigationHelper().resetPassword();

    List<MailMessage> mailMessagesResetPassword = app.james().waitForMailMoreOne(username, password, 120000);
    String confirmationLinkResetPassword = findConfirmationLink(mailMessagesResetPassword, email);
    String newPassword = "pass";
    app.registration().finish(confirmationLinkResetPassword, newPassword);
    app.session().login(username, newPassword);
    assertTrue(app.newSession().login(username, newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)
            && m.text.contains("Someone (presumably you) requested a password change through e-mail")).iterator().next();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private String findConfirmationLinkCreateUser(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}

