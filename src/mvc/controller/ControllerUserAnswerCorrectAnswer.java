
package mvc.controller;

import mvc.model.Model;
import mvc.view.ViewInputAnswerUser;
import mvc.view.ViewUserAnswerCorrectAnswer;

public class ControllerUserAnswerCorrectAnswer implements iCRUD
{
    private Model model;
    private ViewUserAnswerCorrectAnswer view;
    private String userName;
    private int blockid;

    public ControllerUserAnswerCorrectAnswer(Model model, String name, int blockid)
    {
        this.model = model;
        createDBConnection();
        userName = name;
        //view = new ViewUserAnswerCorrectAnswer(model, this,blockid);
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
