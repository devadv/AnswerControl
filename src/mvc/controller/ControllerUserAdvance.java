
package mvc.controller;

import mvc.model.Model;
import mvc.view.ViewUserAdvance;


public class ControllerUserAdvance implements iCRUD
{
    private Model model;
    
    
    public ControllerUserAdvance(Model model)
    {
        this.model = model;
        createDBConnection();
        ViewUserAdvance view = new ViewUserAdvance(model, this);
        model.addObserver(view);
       System.out.println(model.allAnswersFilled("1B", "ben"));
    }
    
    @Override
    public void createDBConnection() 
    {
        model.createDBConnection();
    }

    @Override
    public void create() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retrieve() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserName() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
