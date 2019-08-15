package com.example.messy_flatmates.db;

import android.content.Context;

import com.example.messy_flatmates.MainActivity;

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

/**
 * @TODO: (before deploying) have error information stored in a log file, not System.out.println
 * @version 1.0
 * Connections is a superclass that holds the restful http methods (get, post, put, update, delete)
 * each function simply sends the request, use the specific sub classes (Get_requests, Post_requests)
 * for specific requests.
 * @author Jordan Gardiner
 */
public class Connections {

    private String url = "http://192.168.1.11"; //URL of server
    private String port = ":3009"; //Port server is listening on
    private String responseCode = "408";
    private String responseBody = "";

//    /**
//     * Creates a thread and sends an http get request to the web server
//     * @param requestString the route to the server i.e /api/Status
//     * @param token if no token make null
//     * @return returns the servers response in json format e.g
//     */
//    public JSONObject SendGetRequest(final String requestString, final String token){
//        try {
//            //create a new thread for the connection
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//
//                        URL obj = new URL(url + port + requestString);
//                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//                        con.setConnectTimeout(1500);
//                        con.setRequestMethod("GET");
//                        if(token != null){
//                            con.setRequestProperty("token", token);
//                        }

//                        setResponseCode(Integer.toString(con.getResponseCode()));
//                        InputStream error = con.getErrorStream();
//                        if(error == null){
//                            try(BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
//                                StringBuilder response = new StringBuilder();
//                                String responseLine;
//                                while ((responseLine = br.readLine()) != null) {
//                                    response.append(responseLine.trim());
//                                }
//                                setResponseBody(response.toString());
//                            }
//                        } else {
//                            try(BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(con.getErrorStream(), "utf-8"))) {
//                                StringBuilder response = new StringBuilder();
//                                String responseLine;
//                                while ((responseLine = br.readLine()) != null) {
//                                    response.append(responseLine.trim());
//                                }
//                                setResponseBody(response.toString());
//                            }
//                        }
//                        con.disconnect();
//
//                    } catch (MalformedURLException e) {
//                        System.out.println("MalformedURLException");
//                        System.out.println(e.getMessage());
//
//
//                    } catch (SocketTimeoutException e){
//                        System.out.println("SocketTimeoutException");
//                        System.out.println(e.getMessage());
//
//                    } catch (IOException e) {
//                        System.out.println("IOException");
//                        System.out.println(e.getMessage());
//
//                    }
//
//                }
//            });
//            thread.start();
//            thread.join();
//
//        } catch (InterruptedException e){
//            System.out.println("InterruptedException");
//            System.out.println(e.getMessage());
//        }
//
//        JSONObject responseJSON = new JSONObject();
//        try{
//            responseJSON = new JSONObject(responseBody);
//            responseJSON.put("responseCode", responseCode);
//
//        } catch (JSONException e){
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Attempting to connect: " + requestString);
//        System.out.println("Response code " + responseCode);
//        System.out.println("Response Body " + responseBody);
//        return responseJSON;
//    }
//
//    /**
//     * Creates a thread and sends the http post request through a stream writer
//     * @param requestString the route to the server i.e /api/user
//     * @param jsonBody the json body to send to the server
//     * @param token the users current token, if not needed set to null
//     * @return returns the json object sent back from the server
//     */
//    public JSONObject SendPostRequest(final String requestString, final JSONObject jsonBody, final String token){
//        final StringBuffer response = new StringBuffer();
//
//        try {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    try {
//
//                        URL obj = new URL(url + port + requestString);
//                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//                        con.setRequestMethod("POST");
//                        con.setConnectTimeout(1500);
//
//                        //request headers
//                        con.setRequestProperty("Content-Type", "application/json");
//                        con.setRequestProperty("Accept", "application/json");
//                        if(token != null){
//                            con.setRequestProperty("token", token);
//                        }
//
//                        con.setDoOutput(true);
//
//                        String jsonString = jsonBody.toString();
//
//                        try(OutputStream os = con.getOutputStream()) {
//                            byte[] input = jsonString.getBytes("utf-8");
//                            os.write(input, 0, input.length);
//                        }
//                        setResponseCode(Integer.toString(con.getResponseCode()));
//                        InputStream error = con.getErrorStream();
//
//                        if(error == null){
//                            try(BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
//                                StringBuilder response = new StringBuilder();
//                                String responseLine;
//                                while ((responseLine = br.readLine()) != null) {
//                                    response.append(responseLine.trim());
//                                }
//                                setResponseBody(response.toString());
//                            }
//                        } else {
//                            try(BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(con.getErrorStream(), "utf-8"))) {
//                                StringBuilder response = new StringBuilder();
//                                String responseLine;
//                                while ((responseLine = br.readLine()) != null) {
//                                    response.append(responseLine.trim());
//                                }
//                                setResponseBody(response.toString());
//                            }
//                        }
//
//                    } catch (MalformedURLException e) {
//                        System.out.println("MalformedURLException");
//                        System.out.println(e.getMessage());
//
//
//                    } catch (SocketTimeoutException e){
//                        System.out.println("SocketTimeoutException");
//                        System.out.println(e.getMessage());
//
//                    } catch (IOException e) {
//
//                        setResponseBody(response.toString());
//                        System.out.println("IOException");
//                        System.out.println(e.getMessage());
//
//                    }
//
//                }
//            });
//            thread.start();
//            thread.join();
//
//        } catch (InterruptedException e){
//            System.out.println("InterruptedException");
//            System.out.println(e.getMessage());
//        }
//
//
//        JSONObject responseJSON = new JSONObject();
//        try{
//            responseJSON = new JSONObject(responseBody);
//            responseJSON.put("responseCode", responseCode);
//
//        } catch (JSONException e){
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Attempting to connect: " + requestString);
//        System.out.println("Response code " + responseCode);
//        System.out.println("Response Body " + responseBody);
//        return responseJSON;
//    }
//

    /**
     * Creates a thread and sends the http post request through a stream writer
     * @param requestString the route to the server i.e /api/user
     * @param jsonBody the json body to send to the server
     * @param token the users current token, if not needed set to null
     * @return returns the json object sent back from the server
     */
    public JSONObject SendRequest(final String requestString, final JSONObject jsonBody, final String token,final String requestType){
        final StringBuffer response = new StringBuffer();

        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        URL obj = new URL(url + port + requestString);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod(requestType);
                        con.setConnectTimeout(1500);

                        //request headers

                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Accept", "application/json");


                        if(token != null){
                            con.setRequestProperty("token", token);
                        }

                        String jsonString = "{}";

                        if (jsonBody != null){
                            jsonString = jsonBody.toString();
                        }


                        if(!requestType.equals("GET")){
                            con.setDoOutput(true);
                            try(OutputStream os = con.getOutputStream()) {
                                byte[] input = jsonString.getBytes("utf-8");
                                os.write(input, 0, input.length);
                            }
                        }

                        setResponseCode(Integer.toString(con.getResponseCode()));
                        InputStream error = con.getErrorStream();

                        if(error == null){
                            try(BufferedReader br = new BufferedReader(
                                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                                StringBuilder response = new StringBuilder();
                                String responseLine;
                                while ((responseLine = br.readLine()) != null) {
                                    response.append(responseLine.trim());
                                }
                                setResponseBody(response.toString());
                            }
                        } else {
                            try(BufferedReader br = new BufferedReader(
                                    new InputStreamReader(con.getErrorStream(), "utf-8"))) {
                                StringBuilder response = new StringBuilder();
                                String responseLine;
                                while ((responseLine = br.readLine()) != null) {
                                    response.append(responseLine.trim());
                                }
                                setResponseBody(response.toString());
                            }
                        }
                        con.disconnect();

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
            responseJSON = new JSONObject(responseBody);
            responseJSON.put("responseCode", responseCode);

        } catch (JSONException e){
            System.out.println(e.getMessage());
        }


        System.out.println("Attempting to connect: " + requestString);
        System.out.println("Response code " + responseCode);
        System.out.println("Response Body " + responseBody); //@todo error with a JSONArray being made as a JSONObject. 
        return responseJSON;
    }


    private void setResponseCode(String code){
        responseCode = code;
    }
    private void setResponseBody(String responseString){
        responseBody = responseString;
    }

}
