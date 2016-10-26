
package mvc.controller;

import mvc.model.Model;
import mvc.view.ViewInputAnswerUser;

public class ControllerInputUserAnswer implements iCRUD
{
    private Model model;
    private ViewInputAnswerUser view;
    private String userName;
    
    public ControllerInputUserAnswer(Model model, String name)
    {
        this.model = model;
        createDBConnection();
        userName = name;
        view = new ViewInputAnswerUser(model, this) {};
        model.addObserver(view);
        
        if(!model.userNameExist(name))
        {
            model.saveUserName(name);
        } 
    }
    
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void createDBConnection() 
    {
        model.createDBConnection();
    }

    @Override
    public void create() 
    {
        model.createUserAnswer(view.getExcercise(), view.getUserAnswer(), userName);
    }

    @Override
    public void retrieve() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() 
    {
        model.updateUserAnswer(view.getExcercise(), view.getUserAnswer(), userName);
    }

    @Override
    public void delete() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
