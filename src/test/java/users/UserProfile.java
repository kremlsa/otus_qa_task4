package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;

public class UserProfile {

    String fname;
    String fnameLatin;
    String lname;
    String lnameLatin;
    String blogName;
    String company;
    String work;
    String country;
    String city;
    String english;
    String contact1;
    String contact2;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFnameLatin() {
        return fnameLatin;
    }

    public void setFnameLatin(String fnameLatin) {
        this.fnameLatin = fnameLatin;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLnameLatin() {
        return lnameLatin;
    }

    public void setLnameLatin(String lnameLatin) {
        this.lnameLatin = lnameLatin;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public static UserProfile loadFromJson(String fileName) {
        if (!new File(fileName).exists()) return null;
        ObjectMapper objectMapper = new JsonMapper();
        UserProfile user = null;
        try {
            user = objectMapper
                    .readValue(new File(fileName), UserProfile.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}



