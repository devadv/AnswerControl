/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import mvc.model.Model;

/**
 *
 * @author hintveld
 */
public class userTest 
{
    public static void main(String[] args) 
    {
        

       String name =  System.getProperty("user.name");
            
       System.out.println("name: " + name);
       
       Model model = new Model();
       model.createDBConnection();
       model.saveUserName(name);
       
       
        System.out.println(model.userNameExist(name));
    }
    
    
}
