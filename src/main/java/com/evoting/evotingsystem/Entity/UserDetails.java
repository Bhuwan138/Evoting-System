
package com.evoting.evotingsystem.Entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {
    @Id
    private String ctznNo;
    private String password;
    private String userName;
    private String address;
    private String city;
    private String email;
    private String mobileNo;
    private String userType;

  public UserDetails() {
  }

  public UserDetails(String ctznNo, String password) {
    this.ctznNo = ctznNo;
    this.password = password;
  }
  
  

  public UserDetails(String ctznNo, String password, String userName, String address, String city, String email, String mobileNo, String userType) {
    this.ctznNo = ctznNo;
    this.password = password;
    this.userName = userName;
    this.address = address;
    this.city = city;
    this.email = email;
    this.mobileNo = mobileNo;
    this.userType = userType;
  }

  public String getCtznNo() {
    return ctznNo;
  }

  public void setCtznNo(String ctznNo) {
    this.ctznNo = ctznNo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Override
  public String toString() {
    return "UserDetails{" + "ctznNo=" + ctznNo + ", password=" + password + ", userName=" + userName + ", address=" + address + ", city=" + city + ", email=" + email + ", mobileNo=" + mobileNo + ", userType=" + userType + '}';
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 71 * hash + Objects.hashCode(this.ctznNo);
    hash = 71 * hash + Objects.hashCode(this.password);
    hash = 71 * hash + Objects.hashCode(this.userName);
    hash = 71 * hash + Objects.hashCode(this.address);
    hash = 71 * hash + Objects.hashCode(this.city);
    hash = 71 * hash + Objects.hashCode(this.email);
    hash = 71 * hash + Objects.hashCode(this.mobileNo);
    hash = 71 * hash + Objects.hashCode(this.userType);
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
    final UserDetails other = (UserDetails) obj;
    if (!Objects.equals(this.ctznNo, other.ctznNo)) {
      return false;
    }
    if (!Objects.equals(this.password, other.password)) {
      return false;
    }
    if (!Objects.equals(this.userName, other.userName)) {
      return false;
    }
    if (!Objects.equals(this.address, other.address)) {
      return false;
    }
    if (!Objects.equals(this.city, other.city)) {
      return false;
    }
    if (!Objects.equals(this.email, other.email)) {
      return false;
    }
    if (!Objects.equals(this.mobileNo, other.mobileNo)) {
      return false;
    }
    return Objects.equals(this.userType, other.userType);
  }
    
    
    
}
