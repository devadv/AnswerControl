package mvc.model;

public interface iModel {
	
	
	//db connection
	void createDBConnection();
		
	//CRUD Question
	void createQuestion(String exercise_nr, String question, int block_id);
	
	String retrieveQuestion(String exercise_nr);
	
	void updateQuestion(String exercise_nr, String question, int block_id);
	
	void deleteQuestion();
	
	//tools Question
	
	boolean isQuestion(String exercise_nr);
	
	
	//CRUD Answer
	void createAnswer(String exercise_nr, String answer, int block_id);
	
	String retrieveAnswer(String exercise_nr);
	
	void updateAnswer(String exercise_nr, String answer, int block_id);
	
	void deleteAnswer();
	
	//tools Answer
	
	boolean isAnswer(String exercise_nr);

	
	

	
	
}
