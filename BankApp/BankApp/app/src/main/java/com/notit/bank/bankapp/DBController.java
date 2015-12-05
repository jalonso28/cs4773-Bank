package com.notit.bank.bankapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.notit.bank.bankapp.User.*;
import com.notit.bank.bankapp.LoginActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Beaster on 11/5/2015.
 */
public class DBController {

    private static DBController ourInstance;
    private static User user;
    private static Context context;


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
        this.user.setId(0);

    }

    public User getUser(String email) {
        return this.user;
    }

    // Get a single employee by their id
    public User getUser(int id) {
        return this.user;
        //return getUserByID(id);
    }


    public void deleteEmployee(User user) {
        String address = "http://159.203.136.85/BankingSystem/account/delete/initiate/";
        address = address.concat(user.getEmail());
        address = address.concat("?password=");
        address = address.concat(user.getPassword());
        address = address.concat("/");
        /*
        TODO connect to endpoint to complete delete employee
         */

        Toast.makeText(context, address,
                 Toast.LENGTH_LONG).show();

        address = "http://159.203.140.203/BankingSystem/account/delete/confirm/";
        address.concat(user.getEmail());
        address = address.concat("?password=");
        address = address.concat(user.getPassword());
        address = address.concat("?token=");
        address = address.concat("TOKEN");

        Toast.makeText(context, address,
                Toast.LENGTH_LONG).show();


    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> u = new ArrayList<>();
        u.add(this.user);
        return u;
    }

    public void updateUser(User newUser, User user) {
        String address;
        if(!newUser.getPassword().equals(user.getPassword())) {
            address = "http://159.203.140.203/account/password/";
            address = address.concat(user.getEmail());
            address = address.concat("?password=");
            address = address.concat(user.getPassword());
            address = address.concat("&new_password=");
            address = address.concat(newUser.getPassword());
            address = address.concat("/");
            Toast.makeText(context, address,
                    Toast.LENGTH_LONG).show();
        }
        if(!newUser.getEmail().equals(user.getEmail())) {
            address = "http://159.203.136.85/account/email/";
            address = address.concat(user.getEmail());
            address = address.concat("?password=");
            address = address.concat(user.getPassword());
            address = address.concat("&new_email=");
            address = address.concat(newUser.getEmail());
            address = address.concat("/");

            Toast.makeText(context, address,
                    Toast.LENGTH_LONG).show();

        }
    }

    public void insertUser(User newUser) {
        String address = "http://159.203.140.203/account/create/";
        address = address.concat(Integer.toString(newUser.getId()));
        address = address.concat("?ssn=");
        address = address.concat(Integer.toString(newUser.getSSN()));
        address = address.concat("&name=");
        address = address.concat(newUser.getFirst());
        address = address.concat("+");
        address = address.concat(newUser.getLast());
        address = address.concat("&email=");
        address = address.concat(newUser.getEmail());
        address = address.concat("/");
        Toast.makeText(context, address,
                Toast.LENGTH_LONG).show();

        try {
            URL url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        try {
            request.setURI(new URI(address));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> s = new ArrayList<>();
        s.add("User");
        return s;
    }
/*
    private User getUserByID(int id) {
        String restURL = "http://159.203.136.85/BankingSystem/";

        return this.user;

    }

    public class RestOperation extends AsyncTask<String, Void, Void> {

        final HttpClient httpClient = new DefaultHttpClient();
        String content;
        String error;
        String data;
        int ID;

        RestOperation(int id){
            this.ID = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + Integer.toString(ID);
                Toast.makeText(context, data,
                        Toast.LENGTH_SHORT).show();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected Void doInBackground(String... params) {
            BufferedReader br = null;

            URL url;
            try {
                url = new URL("http://159.203.136.85/BankingSystem/");
              //  Toast.makeText(context, params[0],
             //           Toast.LENGTH_SHORT).show();

                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);

                OutputStreamWriter outputStreamWr = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWr.write(data);
                outputStreamWr.flush();

                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = br.readLine())!=null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }

                content = sb.toString();

            } catch (MalformedURLException e) {
                error = e.getMessage();
                e.printStackTrace();
            } catch (IOException e) {
                error = e.getMessage();
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(error!=null) {
                Toast.makeText(context, ("Error " + error),
                        Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(context, "Server Data Received",
                //        Toast.LENGTH_SHORT).show();
                JSONObject jsonResponse;

                try {
                    jsonResponse = new JSONObject(content);

                    JSONArray jsonArray = jsonResponse.optJSONArray("Android");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject child = jsonArray.getJSONObject(i);

                        String name = child.getString("name");
                        String id = child.getString("id");
                        String ssn = child.getString("ssn");

                        Toast.makeText(context, "id",
                                Toast.LENGTH_SHORT).show();
                        if(name == null)
                            Toast.makeText(context, "name",
                                    Toast.LENGTH_SHORT).show();
                        if(ssn == null)
                            Toast.makeText(context, "ssn",
                                    Toast.LENGTH_SHORT).show();

                        user = new User();
                        user.setId(Integer.parseInt(id));
                        user.setSSN(Integer.parseInt(ssn));
                        user.setFirst(name);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }


    }*/

}
