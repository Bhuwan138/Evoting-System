package com.evoting.evotingsystem.Entity;

import java.util.List;

public class GroupOrPosition {

  private String type;
  private String groupName;
  private List<String> positions;

  // Constructors, getters, and setters
  public GroupOrPosition(String type, String groupName, List<String> positions) {
    this.type = type;
    this.groupName = groupName;
    this.positions = positions;
  }

  // Getters and setters for fields
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public List<String> getPositions() {
    return positions;
  }

  public void setPositions(List<String> positions) {
    this.positions = positions;
  }
}
