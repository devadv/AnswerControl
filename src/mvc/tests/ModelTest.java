package mvc.tests;

import mvc.model.Model;


public class ModelTest {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.createDBConnection();
		model.createQuestion("1.1", "Dit is de vraag", 1);
		model.retrieveQuestion("1.1");
		model.updateQuestion("1.1" , "Dit de vraag updated", 1);
		model.retrieveQuestion("1.1");
		model.questionExist("1.1");
		model.questionExist("1.45");
		
		

	}

}
