
package test;

import mvc.model.Model;

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
       
       model.createUserAnswer("", "1.1", name);
       
    }
    
    
}
