package com.example.messy_flatmates.db;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

/**
 * @version 1.0
 * Sub class of Connections.
 * Holds all get specific requests to the server.
 * @author Jordan Gardiner
 */
public class Post_requests extends Connections {

    /**
     * Takes information to create a user
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param dob
     * @return JSONObject with response from the server
     */
    public JSONObject Create_user(String firstName, String lastName, String email, String password, String dob) {
        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("dob", dob);
        } catch (JSONException e) {

        }

        String requestString = "/api/user";
        return SendPostRequest(requestString, jsonBody, null);


    }

    /**
     * Logs a user in to the database and returns response from the server
     * @param email
     * @param password
     * @return JSONObject
     */
    public JSONObject Login(String email, String password) {
        JSONObject loginJSON = new JSONObject();
        try {
            loginJSON.put("email", email);
            loginJSON.put("password", password);

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return SendPostRequest("/api/user/login", loginJSON, null);
    }

    /**
     *
     * @param taskName
     * @param taskDescription
     * @param dueDate
     * @param points
     * @param token
     * @return JSONObject with response from the server
     */
    public JSONObject Create_task(String taskName, String taskDescription, String dueDate, String points, String token) {

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("taskName", taskName);
            jsonBody.put("taskDescription", taskDescription);
            jsonBody.put("dueDate", dueDate);
            jsonBody.put("taskPoints", Integer.parseInt(points));

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        String requestString = "/api/tasks";
        return SendPostRequest(requestString, jsonBody, token);

    }

    /**
     * Creates a flat
     * @param addr
     * @param token
     * @return
     */
    public JSONObject Create_flat(String addr, String token) {
        String requestString = "/api/flat";
        JSONObject body = new JSONObject();
        try {
            body.put("address", addr);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return SendPostRequest(requestString, body, token);
    }

    public JSONObject New_flatty(String inviteCode, String token) {
        String request = "/api/flat/newFlatty";
        JSONObject body = new JSONObject();
        try {
            body.put("invite_token", inviteCode);

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return SendPostRequest(request, body, token);
    }

    public JSONObject Leave_flat(String token){
        String request = "/api/flat/leave";
        JSONObject j = new JSONObject();
        //return SendPostRequest(request, )
        return j;
    }
}