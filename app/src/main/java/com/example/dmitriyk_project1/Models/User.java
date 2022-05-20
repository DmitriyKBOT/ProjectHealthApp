package com.example.dmitriyk_project1.Models;

import com.example.dmitriyk_project1.Models.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    //занесение данных в базу данных
    public String id ,Name, SecName,AGE, VES, Email;

    public ArrayList<Task>tasks;

    public User(String id, String name, String secName, String AGE, String VES, String email, ArrayList<Task> tasks) {
        this.id = id;
        Name = name;
        SecName = secName;
        this.AGE = AGE;
        this.VES = VES;
        Email = email;
        this.tasks = tasks;
    }

    public User() {
    }
}

