package com.jun.setNewPropertiesLockOne;

import java.io.Serializable;

public class UserInfo {
    private String username;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class  Service {
    public void serviceMethodA(UserInfo userInfo){
        synchronized (userInfo){
            try {
                System.out.println(Thread.currentThread().getName());
                userInfo.setUsername("aaaaaa");
                Thread.sleep(2000);
                System.out.println("end! time="+System.currentTimeMillis());
            }catch (InterruptedException i){

            }
        }
    }
}

class ThreadA extends Thread{
    private Service service;
    private UserInfo userInfo;
    public ThreadA(Service service, UserInfo userInfo){
        this.service = service;
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        service.serviceMethodA(userInfo);
    }
}

class ThreadB extends Thread{
    private Service service;
    private UserInfo userInfo;
    public ThreadB(Service service, UserInfo userInfo){
        this.service = service;
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        service.serviceMethodA(userInfo);
    }
}