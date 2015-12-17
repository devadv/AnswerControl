package mvc.controller;

public interface iController {
	
	void createDBConnection();
	
	void createQuestion();
	
	void getQuestion();
	
	void updateQuestion();
	
	void deleteQuestion();
	
	//tools Question
	
	boolean isQuestion();
	
	
	//CRUD Answer
	void createAnswer();
	
	void getAnswer();
	
	void updateAnswer();
	
	void deleteAnswer();
	
	//tools Answer
	
	boolean isAnswer();

}
