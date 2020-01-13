package com.jun.future.self;

public class SelfFutureDemo {
    /**
     * RealData是最真实数据，FutureData是立即返回的用于提取RealData的订单
     * Client返回FutureData，并且开启构造RealData的线程
     * Client返回的futureData是没有真实数据的，需要线程设置数据
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        System.out.println("数据="+data.getResult());
    }
}
