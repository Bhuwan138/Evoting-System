package com.evoting.evotingsystem.Entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Voting {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voting_sequence_generator")
  @SequenceGenerator(name = "voting_sequence_generator", sequenceName = "votingSequence", allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @ManyToOne
  @JoinColumn(name = "ctzn_no")
  private UserDetails userDetails;

  public Voting() {
  }

  public Voting(Candidate candidate, UserDetails userDetails) {
    this.candidate = candidate;
    this.userDetails = userDetails;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetails userDetails) {
    this.userDetails = userDetails;
  }

  @Override
  public String toString() {
    return "Voting{" + "id=" + id + ", candidate=" + candidate + ", userDetails=" + userDetails + '}';
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + Objects.hashCode(this.id);
    hash = 67 * hash + Objects.hashCode(this.candidate);
    hash = 67 * hash + Objects.hashCode(this.userDetails);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Voting other = (Voting) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.candidate, other.candidate)) {
      return false;
    }
    return Objects.equals(this.userDetails, other.userDetails);
  }

}
