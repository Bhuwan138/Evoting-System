package com.evoting.evotingsystem.Entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Candidate {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_sequence_generator")
  @SequenceGenerator(name = "candidate_sequence_generator", sequenceName = "candidateSequence", allocationSize = 1)
  @Column(name="candidateId")
  private int candidateId;
  private String party;
  private String symbol;
  private String city;
  private String candidateGroup;
  private String position;
  private int isCandidate = 0;
  @OneToOne
  @JoinColumn(name = "ctznNo")
  private UserDetails userDetails;

  public Candidate() {
  }

  public Candidate(int candidateId, String party, String symbol, String city, String candidateGroup, String position, UserDetails userDetails) {
    this.candidateId = candidateId;
    this.party = party;
    this.symbol = symbol;
    this.city = city;
    this.candidateGroup = candidateGroup;
    this.position = position;
    this.userDetails = userDetails;
  }

  

  public int getCandidateId() {
    return candidateId;
  }

  public void setCandidateId(int candidateId) {
    this.candidateId = candidateId;
  }

  public String getParty() {
    return party;
  }

  public void setParty(String party) {
    this.party = party;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCandidateGroup() {
    return candidateGroup;
  }

  public void setCandidateGroup(String candidateGroup) {
    this.candidateGroup = candidateGroup;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public int isIsCandidate() {
    return isCandidate;
  }

  public void setIsCandidate(int isCandidate) {
    this.isCandidate = isCandidate;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetails userDetails) {
    this.userDetails = userDetails;
  }

  @Override
  public String toString() {
    return "Candidate{" + "candidateId=" + candidateId + ", party=" + party + ", symbol=" + symbol + ", city=" + city + ", candidateGroup=" + candidateGroup + ", position=" + position + ", isCandidate=" + isCandidate + ", userDetails=" + userDetails + '}';
  }

 
 

  

}
