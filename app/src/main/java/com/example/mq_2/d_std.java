package com.example.mq_2;

public class d_std {
    private String Regno;
    private String Name;
    private String Email;

    private String Mobile;

    public d_std() {
        // Required empty constructor for Firebase
    }

    public d_std(String rollNo, String name, String email, String mobile) {
        this.Regno = rollNo;
        this.Name = name;
        this.Email = email;
        this.Mobile = mobile;
    }

    public String getRegno() {
        return Regno;
    }

    public void setRegno(String rollNo) {
        this.Regno = rollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }


}
