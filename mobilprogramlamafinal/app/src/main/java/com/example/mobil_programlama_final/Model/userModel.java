package com.example.mobil_programlama_final.Model;

public class userModel {
   String FirstName;
   String LastName;
   String Email;
   String Id;

    public userModel(String firstName, String lastName, String email, String id) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
