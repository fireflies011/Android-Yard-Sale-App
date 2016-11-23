package com.example.tate.loginfbla;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.content.Context;
import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedWriter;
import java.net.URLEncoder;
import java.io.InputStreamReader;

/**
 * Created by Tate on 11/22/2016.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    String register_url = ""; // Insert between quotation --> http://local host ip address/LoginFBLA/register.php
    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;
  public BackgroundTask(Context ctx)
  {
      this.ctx = ctx;
      activity = (Activity)ctx;
  }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
         progressDialog = new ProgressDialog(ctx);
         progressDialog.setTitle("Please Wait");
         progressDialog.setMessage("Connecting to server....");
         progressDialog.setIndeterminate(true);
         progressDialog.setCancelable(false);
         progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];

        if(method.equals("register"))
        {
            try {
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
               String name = params[1];
                String email = params[2];
                String password = params[3];
               String data = URLEncoder.encode("name","UTF-8")+"-"+URLEncoder.encode(name,"UTF-8")+"&"+
                       URLEncoder.encode("email","UTF-8")+"-"+URLEncoder.encode(email,"UTF-8")+"&"+
                       URLEncoder.encode("password","UTF-8")+"-"+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line=bufferedReader.readLine())!=null)
                {

                    stringBuilder.append(line+"\n");

                }
                 httpURLConnection.disconnect();
                Thread.sleep(5000);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {

        try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            if(code.equals("reg_true"))
            {
                showDialog("Registration Success",message,code);
            }
            else if(code.equals("reg_false"))
            {
                showDialog("Registration Failed",message,code);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void showDialog(String title,String message,String code)
    {
        builder.setTitle(title);
        if(code.equals("reg_true")||code.equals("reg_false"))
        {
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
                    activity.finish();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
