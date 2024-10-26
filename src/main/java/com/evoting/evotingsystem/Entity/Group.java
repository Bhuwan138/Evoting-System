package com.evoting.evotingsystem.Entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence_generator")
  @SequenceGenerator(name = "group_sequence_generator", sequenceName = "groupSequence", allocationSize = 1)
  @Column(name = "group_id")
  private Long groupId;
  @Column(name = "group_name")
  private String groupName;
  @OneToMany(mappedBy = "group")
  private List<Position> positions;

  public Group() {
  }

  public Group(Long groupId, String groupName, List<Position> positions) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.positions = positions;
  }

  public Group(String groupName) {
    this.groupName = groupName;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public List<Position> getPositions() {
    return positions;
  }

  public void setPositions(List<Position> positions) {
    this.positions = positions;
  }

}
