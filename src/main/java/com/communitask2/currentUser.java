/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.communitask2;

public class currentUser {
    public static currentUser instance;
    private int userId;
    private String email;
    
    private currentUser(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public static void setInstance(int userId, String email) {
        instance = new currentUser(userId, email);
    }

    public static currentUser getInstance() {
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public static void clearSession() {
        instance = null;
    }
   }

