package com.notit.bank.bankapp;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Toast;

import com.notit.bank.bankapp.User.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DBController extends Thread {

    private static DBController ourInstance;
    private static User user;
    private static Context context;
    private static String jsonString;
    String progress;


    public static DBController getInstance(Context context) {
        if (ourInstance != null)
            return ourInstance;
        else {
            ourInstance = new DBController(context);
            return ourInstance;
        }

    }

    private DBController(Context context) {
        this.context = context;
        user = new User();

        this.user.setFirst("Some");
        this.user.setLast("Person");
        this.user.setRoleTitle("User");
        this.user.setEmail("some@person.com");
        this.user.setPassword("someotherguy");
        this.user.setRoleCode(1);
    }

    public User getUser(String email) {
        return this.user;
    }

    // Get a single employee by their id
    public User getUser(int id) {
        return this.user;
        //return getUserByID(id);
    }

    public void getToken(String email, String password) {
        String address = "http://159.203.140.203/account/delete/initiate/" + email + "?password=" +
                password;

        final String url = address;

        /*
        TODO: {"meta":{"code":404,"cause":"account exists"}} is error
        200 is success
         */

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(String email, String password, String token) {
        String address = "http://159.203.140.203/account/delete/confirm/" + email + "?password=" +
                password + "&token=" + token;

        final String url = address;

          /*
        TODO: {"meta":{"code":404,"cause":"account exists"}} is error
        200 is success
         */

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
    }

    public Boolean updatePassword(String email, String oPassword, String nPassword) {
        String address = "http://159.203.140.203/account/password/" + email + "?old_password=" +
                oPassword + "&new_password=" + nPassword;
        final String url = address;

        /*
        TODO: {"meta":{"code":404,"cause":"account exists"}} is error
        200 is success
         */

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(jsonString.contains("login invalid"))
            return false;
        else
            Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
        return true;
    }

    public void updateEmail(String oEmail, String nEmail, String password) {
        String address = "http://159.203.140.203/account/email/" + oEmail + "?password=" +
                password + "&new_email=" + nEmail;
        final String url = address;

        /*
        TODO: {"meta":{"code":404,"cause":"account exists"}} is error
        200 is success
         */

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();

    }

    public void insertUser(User newUser) {
        jsonString = "";
        String address = "http://159.203.140.203/account/create/" + Integer.toString(newUser.getId())
                + "?ssn=" + Integer.toString(newUser.getSSN()) + "&name=" + newUser.getFirst() + "+"
                + newUser.getLast() + "&email=" + newUser.getEmail();
        final String url = address;

        /*
        TODO: {"meta":{"code":404,"cause":"account exists"}} is error
        200 is success
         */

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
    }


    public String getJsonFromServer(String url) throws IOException {

        BufferedReader inputStream = null;
        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();
       // dc.setConnectTimeout(5000);
        //dc.setReadTimeout(5000);
        inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));
        // read the JSON results into a string
        String jsonResult = inputStream.readLine();
        return jsonResult;
    }


}
