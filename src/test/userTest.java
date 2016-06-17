
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
//       model.createUserAnswer("abcdefgh", "1.1", name);
//       model.createUserAnswer("abcdefgh", "1.3", name);
//       model.createUserAnswer("abcdefgh", "1.4", name);
//       model.createUserAnswer("abcdefgh", "1.5", name);
//       model.createUserAnswer("abcdefgh", "1.6", name);
       model.updateUserAnswer("hello world", "1.1", name);
       model.updateUserAnswer("hello world", "1.2", name);
       model.updateUserAnswer("hello world", "1.3", name);
       model.updateUserAnswer("hello world", "1.4", name);
          
       
    }
    
    
}
