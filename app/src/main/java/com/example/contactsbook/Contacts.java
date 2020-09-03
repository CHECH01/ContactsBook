package com.example.contactsbook;

public class Contacts{

    private String name,phone,email,birthday,description;
    public Contacts(String name, String phone,String email, String birthday, String description){
        this.name        = name;
        this.phone       = phone;
        this.email       = email;
        this.birthday    = birthday;
        this.description = description;
    }
    public String getName()        { return name;        }
    public String getPhone()       { return phone;       }
    public String getEmail()       { return email;       }
    public String getBirthday()    { return birthday;    }
    public String getDescription() { return description; }
}
