package com.jun.future.self;

public class Client {
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }
}
