package com.example.goldendreamsbowling.JavaFile;

import android.widget.TextView;

public class User {
    public String fullname,phoneNum,Email,Password;
    public User(TextView fullname, TextView mail, TextView pass, TextView phone)
    {

    }
    public User(String fullname,String phoneNum,String Email,String Password)
    {
        this.fullname = fullname;
        this.phoneNum = phoneNum;
        this.Email = Email;
        this.Password = Password;
    }
}
