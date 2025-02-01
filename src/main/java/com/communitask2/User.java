package com.communitask2;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String firstName;
    private String middleName;
    private String surname;
    private String email;
    private String password;
    private int age;
    private String maritalStatus;
    private String birthday;
    private String barangay;
    private String contactNumber;

    public User(String firstName, String middleName, String surname, String email, String password, 
                int age, String maritalStatus, String birthday, String barangay, String contactNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.birthday = birthday;
        this.barangay = barangay;
        this.contactNumber = contactNumber;
    }

    public String getHashedPassword() {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getMaritalStatus() { return maritalStatus; }
    public String getBirthday() { return birthday; }
    public String getBarangay() { return barangay; }
    public String getContactNumber() { return contactNumber; }
    
}
