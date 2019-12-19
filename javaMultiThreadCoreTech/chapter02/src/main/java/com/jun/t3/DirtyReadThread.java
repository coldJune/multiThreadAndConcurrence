package com.jun.t3;

public class DirtyReadThread extends Thread {
    private Publicvar publicvar;
    public DirtyReadThread(Publicvar publicvar){
        super();
        this.publicvar = publicvar;
    }

    @Override
    public void run() {
        super.run();
        publicvar.setVale("B","BB");
    }
}
