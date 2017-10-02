package mvc.view;

import java.awt.event.ActionListener;


public interface iView extends ActionListener
{
	void setGUI();

    void exerciseId();

    void btnSave();

    void btnNext();

    void btnPrevious();

    void blocksId();

    void btnCheckAnswer();

}
