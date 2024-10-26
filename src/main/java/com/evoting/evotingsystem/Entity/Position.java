package com.evoting.evotingsystem.Entity;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_sequence_generator")
  @SequenceGenerator(name = "position_sequence_generator", sequenceName = "positionSequence", allocationSize = 1)

  @Column(name = "position_id")
  private Long positionId;
  @Column(name = "position_name")
  private String positionName;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group group;

  public Long getPositionId() {
    return positionId;
  }

  public void setPositionId(Long positionId) {
    this.positionId = positionId;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

}
