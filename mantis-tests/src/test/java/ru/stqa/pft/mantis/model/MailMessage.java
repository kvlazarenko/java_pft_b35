package ru.stqa.pft.mantis.model;

public class MailMessage {

  private  String to;
  public String text;

  public MailMessage (String to, String text) {
    this.to = to;
    this.text = text;
  }
}
