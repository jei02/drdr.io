package com.example.pdoran;

import android.widget.EditText;

public class MemberDB {


    private String name;
    private String phoneNumber;
    private String birthDay;
    private String address;
    private String photoUri;


    public MemberDB(String name,String phoneNumber,String birthDay,String address){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.birthDay=birthDay;
        this.address=address;
    }

 //오버로드
    public MemberDB(String name,String phoneNumber,String birthDay,String address,String photoUri){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.birthDay=birthDay;
        this.address=address;
        this.photoUri=photoUri;
    }

    //이름
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    //폰번호
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    //생일
    public String getBirthDay(){
        return this.birthDay;
    }
    public void setBirthDay(String birthDay){
        this.birthDay=birthDay;
    }

    //주소
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    //프로필사진
    public String getphotoUri() {
        return this.photoUri;
    }
    public void setphotoUri(String photoUri){
        this.photoUri=photoUri;
    }

}
