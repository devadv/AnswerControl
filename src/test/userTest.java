package test;

import mvc.model.Model;

public class userTest {
	public static void main(String[] args) {
		
		Model model = new Model();
		model.createDBConnection();
		model.delTable("user_answer");
		model.delTable("user");

		for (int i = 0; i < 3; i++) {
			String name = System.getProperty("user.name");
			

			
			if (model.userNameExist(name)) {

				name = "badev";
				

				if (model.userNameExist(name)) {
					name = "jpietersen";
					
				}
			}
			
			System.out.println("Username : "  + name);
			model.saveUserName(name);
			model.createUserAnswer("abcdefgh", "1.1", name);
			model.createUserAnswer("abcdefgh", "1.3", name);
			model.createUserAnswer("abcdefgh", "1.4", name);
			model.createUserAnswer("abcdefgh", "1.5", name);
			model.createUserAnswer("abcdefgh", "1.6", name);
			model.updateUserAnswer("hello world", "1.3", name);
		}
		/*
		 * model.updateUserAnswer("hello world", "1.1", name);
		 * model.updateUserAnswer("hello world", "1.2", name);
		 * model.updateUserAnswer("hello world", "1.3", name);
		 * model.updateUserAnswer("hello world", "1.4", name);
		 */

	}

}
