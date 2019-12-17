package com.jun.t4_threadsafe;

public class LoginServletWithSafe {
    private static String userNameRef;
    private static String passwordRef;
    synchronized public static void doPost(String userName, String password){
        try {
            userNameRef = userName;
            passwordRef = password;
            if("a".equals(userName)){
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username="+userNameRef+"; password="+passwordRef);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
