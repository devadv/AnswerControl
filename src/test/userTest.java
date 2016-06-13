
package test;

import mvc.model.Model;

public class userTest 
{
    public static void main(String[] args) 
    {
       String name =  System.getProperty("user.name");   
       System.out.println("username: " + name);
       Model model = new Model();
       model.createDBConnection();
       model.saveUserName(name);       
       model.createUserAnswer("abcdefgh", "1.1", name);
       
       
    }
    
    
}
