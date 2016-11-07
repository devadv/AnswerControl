package test;

import mvc.model.Model;

public class userTest {
	public static void main(String[] args) {
		
		Model model = new Model();
		model.createDBConnection();
		
        //System.out.println("" + model.allAnswersFilled(1, "hintveld"));
       
        System.out.println("allAnswers filled: " + model.allAnswersFilled("1A", "badev"));
		/*String[] blocks = model.getBlockList();
		
		for ( int i = 0;i<blocks.length; i++){
			System.out.println(blocks[i]);
		}*/
		//System.out.println(model.getBlockName("1.2"));
//		String[] lijst =  model.getExerciseList(1);
//		for (int i =0; i< lijst.length;i ++){
//			System.out.println(lijst[i]);
//		}
		
		
		
//		model.delTable("user_answer");
//		model.delTable("user");
//
//		for (int i = 0; i < 3; i++) 
//        {
//			String name = System.getProperty("user.name");
//			
//			
//			if (model.userNameExist(name)) {
//
//				name = "badev";
//				
//
//				if (model.userNameExist(name)) {
//					name = "jpietersen";
//					
//				}
//			}
//			
//			System.out.println("Username : "  + name);
//			model.saveUserName(name);
//			model.createUserAnswer("abcdefgh", "1.1", name);
//			model.createUserAnswer("abcdefgh", "1.3", name);
//			model.createUserAnswer("abcdefgh", "1.4", name);
//			model.createUserAnswer("abcdefgh", "1.5", name);
//			model.createUserAnswer("abcdefgh", "1.6", name);
//			model.updateUserAnswer("hello world", "1.3", name);
//		}
		
//
//        
//        System.out.println("xx" + model.getExerciseList(2));
//        for(String s : model.getExerciseList(2))
//        {
//            System.out.println("yy" + s);
//        }
	}

}
