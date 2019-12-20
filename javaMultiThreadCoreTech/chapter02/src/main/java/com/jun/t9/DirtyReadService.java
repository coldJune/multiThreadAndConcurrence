package com.jun.t9;

public class DirtyReadService {
    public MyOneList addServiceMethod(MyOneList list,String data){
        try {
//            if(list.getSize()<1){
//                Thread.sleep(2000);//模拟获取数据
//                list.add(data);
//            }
            synchronized (list){//因为list是单例，所以需要做同步处理
                if(list.getSize()<1){
                    Thread.sleep(2000);//模拟获取数据
                    list.add(data);
                }
            }
        }catch (InterruptedException ie){

        }
        return list;
    }
}

class ThreadA extends Thread{
    private MyOneList list;
    public ThreadA(MyOneList list){
        super();
        this.list = list;
    }

    @Override
    public void run() {
        DirtyReadService service = new DirtyReadService();
        service.addServiceMethod(list, "a");
    }
}


class ThreadB extends Thread{
    private MyOneList list;
    public ThreadB(MyOneList list){
        super();
        this.list = list;
    }

    @Override
    public void run() {
        DirtyReadService service = new DirtyReadService();
        service.addServiceMethod(list, "B");
    }
}