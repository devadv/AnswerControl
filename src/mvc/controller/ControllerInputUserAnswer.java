
package mvc.controller;

import mvc.model.Model;
import mvc.view.ViewInputAnswerUser;

public class ControllerInputUserAnswer implements iCRUD
{
    private Model model;
    private ViewInputAnswerUser view;
    
    public ControllerInputUserAnswer(Model model)
    {
        this.model = model;
        createDBConnection();
        this.view = new ViewInputAnswerUser(model, this);
        
    }

    @Override
    public void createDBConnection() 
    {
        model.createDBConnection();
    }

    @Override
    public void create() 
    {
        model.createUserAnswer(view.getExcercise(), view.getUserAnswer(), view.getUserName());
    }

    @Override
    public void retrieve() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() 
    {
        model.updateUserAnswer(view.getExcercise(), view.getUserAnswer(), view.getUserName());
    }

    @Override
    public void delete() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
