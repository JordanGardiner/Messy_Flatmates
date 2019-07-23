package com.example.messy_flatmates.db;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connections {

    private String url = "http://121.99.223.175";
    private String port = ":3009";
    private String responseCode = "";
    private String responseBody = "";
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

                    } catch (MalformedURLException e) {
                        System.out.println("MalformedURLException");
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
        responseObject.put("responseCode", responseCode);
        responseObject.put("responseData", response);
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

                        //add request header

                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);

                        String jsonString = jsonBody.toString();

                        try(OutputStream os = con.getOutputStream()) {
                            byte[] input = jsonString.getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }

                        try(BufferedReader br = new BufferedReader(
                                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                            StringBuilder response = new StringBuilder();
                            String responseLine = null;
                            while ((responseLine = br.readLine()) != null) {
                                response.append(responseLine.trim());
                            }
                            System.out.println(response);
                            setResponseBody(response.toString());

                        }
                        setResponseCode(Integer.toString(con.getResponseCode()));

                    } catch (MalformedURLException e) {
                        System.out.println("MalformedURLException");
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

        JSONObject responseJSON = new JSONObject();
        try{
            responseJSON.put("responseCode", responseCode);
            responseJSON.put("responseBody", responseBody);
        } catch (JSONException e){
            System.out.println("definitely here");
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
