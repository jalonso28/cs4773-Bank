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
import java.util.Date;

public class DBController extends Thread {

    private static DBController ourInstance;
    private static User user;
    private static Context context;
    private static String jsonString;
    private static String host = "http://159.203.136.85/BankingSystem/";
    //private static String host = "http://159.203.140.203/";


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
    }

    public User getUser(String email) {
        return this.user;
    }

    // Get a single employee by their id
    public User getUser(int id) {
        return this.user;
    }

    public boolean checkLogin(String email, String password) {
        String address = host + "login?email=" + email + "&password=" +
                password;

        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(jsonString.contains("Log in successful")) {
            this.user.setEmail(email);
            this.user.setPassword(password);
            return true;
        }
        else

            Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
        return false;
    }

    public void logout(String email, String password) {
        String address = host + "logout?email=" + email + "&password=" +
                password;

        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
    }


    public void getToken(String email, String password) {
        String address = host + "account/delete/initiate/" + email + "?password=" +
                password;

        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);
                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(String email, String password, String token) {
        String address = host + "account/delete/confirm/" + email + "?password=" +
                password + "&token=" + token;

        final String url = address;


        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
    }

    public boolean updatePassword(String email, String oPassword, String nPassword) {
        String address = host + "account/password/" + email + "?old_password=" +
                oPassword + "&new_password=" + nPassword;
        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(jsonString.contains("{\"code\":200}"))
            return true;
        else
            Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();

        return false;
    }

    public void updateEmail(String oEmail, String nEmail, String password) {
        String address = host + "account/email/" + oEmail + "?password=" +
                password + "&new_email=" + nEmail;
        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();

    }

    public void insertUser(User newUser) {
        jsonString = "";
        String address = host + "account/create/" + Integer.toString(newUser.getId())
                + "?ssn=" + Integer.toString(newUser.getSSN()) + "&name=" + newUser.getFirst() + "+"
                + newUser.getLast() + "&email=" + newUser.getEmail();
        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
    }


    public String getJsonFromServer(String url) throws IOException {

        BufferedReader inputStream = null;
        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();
        //dc.setConnectTimeout(2000);
        //dc.setReadTimeout(2000);
        inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));
        // read the JSON results into a string
        String jsonResult = inputStream.readLine();
        return jsonResult;
    }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }


    public void updateGps(double latitude, double longitude, double altitude, Date date) {
        jsonString = "";
        String dateTime = date.getYear() + "-" + date.getMonth() + "-" + date.getDay() +
                date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        String address = host + "gps/add?email=" + user.getEmail()
                + "&password=" + user.getPassword() + "&profileID=" + 8 +
                "&latitude=" + latitude  + "&longitude=" + longitude +
                "&altitude=" + 1 + "&dateAndTime=" + dateTime;

        final String url = address;

        AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                jsonString = getUrlContents(url);

                /*try {
                    jsonString = getJsonFromServer(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };
        mTask.execute();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonString, Toast.LENGTH_LONG).show();
    }
}
