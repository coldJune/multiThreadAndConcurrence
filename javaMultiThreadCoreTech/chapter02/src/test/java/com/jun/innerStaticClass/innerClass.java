package com.jun.innerStaticClass;


import org.junit.Test;

public class innerClass {
    @Test
    public void test(){
       PublicClass publicClass = new PublicClass();
       publicClass.setUserName("publicUser");
       publicClass.setPassword("publicPass");

        System.out.println(publicClass.getUserName()+" "+ publicClass.getPassword());
        PublicClass.PrivateClass privateClass = new PublicClass.PrivateClass();
        privateClass.setAge("ageValue");
        privateClass.setAddress("addressValue");
        System.out.println(privateClass.getAge()+" "+ privateClass.getAddress());
    }
}
