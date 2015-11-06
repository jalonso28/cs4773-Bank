package com.notit.bank.bankapp;

import android.content.Context;

import com.notit.bank.bankapp.User.*;

/**
 * Created by Beaster on 11/5/2015.
 */
public class DBController {

    private static DBController ourInstance;
    private static User user;


    public static DBController getInstance(Context context) {
        if (ourInstance != null)
            return ourInstance;
        else {
            ourInstance = new DBController(context);
            return ourInstance;
        }
    }

    private DBController(Context context) {
        /*
        TODO: remove these arbitrary entries and implement database operations here
         */
        // HR user for managing employees
        user = new User();
        user.setEmail("fake@email.com");
        user.setFirst("This");
        user.setLast("Person");
        user.setId(1);
        user.setPassword("1234heythere");
        user.setRoleCode(0);
        user.setRoleTitle("HR");

        // Typical employee User

    }

    public User getUser(String email) {
        return this.user;
    }

    // Get a single employee by their id
    public User getUser(long id) {
        return this.user;
    }



}
