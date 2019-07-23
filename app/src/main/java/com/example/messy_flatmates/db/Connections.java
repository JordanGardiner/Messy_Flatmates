package com.example.messy_flatmates.db;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class Connections {

    private String url = "http://121.99.223.175";
    private String port = ":3009";
    private String responseCode = "408";
    private String responseBody = "";
    private Boolean failedConnection = true;

    public Connections(){

    }

    public JSONObject SendGetRequest(final String requestString){
        final StringBuffer response = new StringBuffer();
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        URL obj = new URL(url + port + requestString);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setConnectTimeout(1500);
                        con.setRequestMethod("GET");
                        setResponseCode(Integer.toString(con.getResponseCode()));
                        System.out.println("\nSending 'GET' request to URL : " + url);
                        System.out.println("Response Code : " + responseCode);

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine;

                        while ((inputLine = reader.readLine()) != null) {
                            response.append(inputLine);
                        }

                        reader.close();

                        //print result
                        System.out.println(response.toString());
                        failedConnection = false;
                        con.disconnect();

                    } catch (MalformedURLException e) {
                        System.out.println("MalformedURLException");
                        System.out.println(e.getMessage());


                    } catch (SocketTimeoutException e){
                        System.out.println("SocketTimeoutException");
                        System.out.println(e.getMessage());

                    } catch (IOException e) {
                        System.out.println("IOException");
                        System.out.println(e.getMessage());

                    }

                }
            });
            thread.start();
            thread.join();

        } catch (InterruptedException e){
            System.out.println("InterruptedException");
            System.out.println(e.getMessage());
        }

        System.out.println(response.toString());
        JSONObject responseObject = new JSONObject();
        try{
            if (failedConnection == false) {
                responseObject.put("responseCode", responseCode);
                responseObject.put("responseBody", response);
            } else {
                responseObject.put("responseCode", "408");
                responseObject.put("responseBody", "Request Timeout");
            }
        } catch (JSONException e){
            System.out.println(e.getMessage());
        }

        return responseObject;

    }


    public JSONObject SendPostRequest(final String requestString, final JSONObject jsonBody){
        final StringBuffer response = new StringBuffer();

        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        URL obj = new URL(url + port + requestString);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setConnectTimeout(1500);
                        //add request header

                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);

                        String jsonString = jsonBody.toString();

                        try(OutputStream os = con.getOutputStream()) {
                            byte[] input = jsonString.getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }
                        setResponseCode(Integer.toString(con.getResponseCode()));
                        InputStream error = con.getErrorStream();

                        if(error == null){
                            try(BufferedReader br = new BufferedReader(
                                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                                StringBuilder response = new StringBuilder();
                                String responseLine = null;
                                while ((responseLine = br.readLine()) != null) {
                                    response.append(responseLine.trim());
                                }
                                setResponseBody(response.toString());
                                System.out.println(responseBody);
                                System.out.println("res Code!!");
                            }
                        } else {
                            try(BufferedReader br = new BufferedReader(
                                    new InputStreamReader(con.getErrorStream(), "utf-8"))) {
                                StringBuilder response = new StringBuilder();
                                String responseLine = null;
                                while ((responseLine = br.readLine()) != null) {
                                    response.append(responseLine.trim());
                                }
                                setResponseBody(response.toString());
                                System.out.println(responseBody);
                                System.out.println("res Code!!");
                            }
                        }

                        failedConnection = false;


                    } catch (MalformedURLException e) {
                        System.out.println("MalformedURLException");
                        System.out.println(e.getMessage());


                    } catch (SocketTimeoutException e){
                        System.out.println("SocketTimeoutException");
                        System.out.println(e.getMessage());

                    } catch (IOException e) {

                        setResponseBody(response.toString());
                        System.out.println("IOException");
                        System.out.println(e.getMessage());

                    }

                }
            });
            thread.start();
            thread.join();

        } catch (InterruptedException e){
            System.out.println("InterruptedException");
            System.out.println(e.getMessage());
        }

        JSONObject responseJSON = new JSONObject();
        try{
            responseJSON.put("responseCode", responseCode);
            responseJSON.put("responseBody", responseBody);
        } catch (JSONException e){
            System.out.println(e.getMessage());
        }

        System.out.println(response.toString());


        return responseJSON;

    }


    private void setResponseCode(String code){
        responseCode = code;
    }
    private void setResponseBody(String responseString){
        responseBody = responseString;
    }

}
