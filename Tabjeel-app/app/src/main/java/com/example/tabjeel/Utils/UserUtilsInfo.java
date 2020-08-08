package com.example.tabjeel.Utils;


import java.util.Random;

public class UserUtilsInfo {

    public String ID;
    public String FullName;
    public String Phone;
    public String Email;
    public String Password ;
    public String Terms;


    public UserUtilsInfo(){

    }

    public String getTerms() {
        return Terms;
    }

    public void setTerms(String terms) {
        Terms = terms;
    }

    public UserUtilsInfo(String  id , String fullName , String phone, String email , String password){
        this.ID = id;
        this.FullName = fullName;
        this.Phone = phone;
        this.Email = email;
        this.Password = password ;
    }


    public String UsernameGenerator(String name){
        String username = name.replace(" ","_").toLowerCase();
        String Signature = "@watch.com";
        this.Email = username;
        return  username + Signature;
    }

    public String PasswordGenerator(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        this.Password = buffer.toString();
        return buffer.toString();
    }
}
