package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {

  private int id = Integer.MAX_VALUE;;
  private  String name;
  private  String header;
  private  String footer;


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }


  public GroupData whithId(int id) {
    this.id = id;
    return this;
  }
  public GroupData whithName(String name) {
    this.name = name;
    return this;
  }

  public GroupData whithHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData whithFooter(String footer) {
    this.footer = footer;
    return this;
  }


  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

}