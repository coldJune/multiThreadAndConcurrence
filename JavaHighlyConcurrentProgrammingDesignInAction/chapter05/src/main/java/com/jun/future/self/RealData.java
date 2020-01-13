package com.jun.future.self;

public class RealData implements Data {
    protected final String result;

    public RealData(String result) {//使用sleep模拟数据生成
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            sb.append(result);
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ie){

        }
        System.out.println("构造realdata完成");
        this.result = sb.toString();
    }

    public String getResult() {
        return result;
    }
}
