package com.jun.synBlockString;

public class Service {
    private String usernameParam;
    private String passwordParam;
//    private String anyString = new String();
    public void setUsernamePassword(String username, String password){
        try{
            String anyString = new String();
            synchronized (anyString){
                System.out.println("threadName="+Thread.currentThread().getName()+"at "+System.currentTimeMillis()+" In");
                usernameParam = username;
                Thread.sleep(2000);
                passwordParam = password;
                System.out.println("threadName="+Thread.currentThread().getName()+"at "+System.currentTimeMillis()+" Out");
            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadA extends Thread{
    private Service service;
    public ThreadA(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.setUsernamePassword("a","aa");
    }
}

class ThreadB extends Thread{
    private Service service;
    public ThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.setUsernamePassword("b","bb");
    }
}