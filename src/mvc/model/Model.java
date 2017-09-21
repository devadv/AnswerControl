package mvc.model;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


public class Model extends Observable implements iModel
{
    private Statement statement;
	private ResultSet resultSet;

	/*
	   SET FOREIGN_KEY_CHECKS=0;
	   TRUNCATE correct_answer;
       SET FOREIGN_KEY_CHECKS=1;
	 */

    private Connection connection;

	@Override
	public void createDBConnection()
    {
        String DATABASEURL = "jdbc:mysql://sql.zz/badev_bluej_exercises_test";
        String username = "badev_hintveld";
        String password = "V99r9R9qwMmYPcqU";
		/*String DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
         String username = "ben";
         String password = "12345";
         System.out.println( "Location: Home" );*/

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
            connection = DriverManager.getConnection( DATABASEURL, username, password );
            statement = connection.createStatement();
        }
        catch ( Exception connectException )
        {
            JOptionPane.showMessageDialog(null, "No connection to database", "Error", JOptionPane.WARNING_MESSAGE);
            connectException.printStackTrace();
            System.out.println("No connection");
        }

	}


    public String[][] getUserProgress()
    {
        ArrayList<String> arrayList = new ArrayList();
        String[] userNames = getUserNames();
        String[] blockList = getBlockList();
        String[][] answers = new String[userNames.length][blockList.length + 1];
        int j = 0;

        for(int i = 0; i < userNames.length; i++)
        {
            answers[i][j] = userNames[i];

            for( j = 1; j < blockList.length + 1; j++)
            {
                answers[i][j] = String.valueOf(allAnswersFilled(blockList[j - 1], userNames[i]));
            }

            j = 0;
        }


        return answers;
    }

    public String[] getUserNames()
    {
        ArrayList<String> arrayList = new ArrayList<>();

        try
        {
            PreparedStatement retrieveUsername = connection.prepareStatement
            ( "SELECT username "
            + "FROM user "
            );

            retrieveUsername.executeQuery();
            resultSet = retrieveUsername.getResultSet();

            while(resultSet.next())
            {
                arrayList.add(resultSet.getString("username"));
            }

        }
        catch (Exception e)
        {
        }

        String names[] = arrayList.toArray(new String[arrayList.size()]);
        return names;
    }


    public String[] getColumnNames()
    {
        String[] blocks = getBlockList();
        String[] columnNames = new String[blocks.length + 1];
        columnNames[0] = "Gebruikers naam";

        for(int i = 1; i < blocks.length + 1; i++)
        {
            columnNames[i] = blocks[i - 1];
        }
        return columnNames;
    }

    /**
     * @param blockName
     * @param userName
     * @return false if an answerfield is not filled
    */

    public boolean blockNameExist(String blockName, String userName)
    {
        try
        {
            PreparedStatement exist = connection.prepareStatement
            ( "SELECT * "
            + "FROM correct_answer "
            + "INNER JOIN user_answer "
            + "ON correct_answer.idcorrect_answer = user_answer.correct_answerid "
            + "INNER JOIN user "
            + "ON user_answer.userid = user.iduser "
            + "INNER JOIN block "
            + "ON correct_answer.block_id = block.idblock "
            + "WHERE  username = ? "
            + "HAVING blockname = ? "
            );

            exist.setString(1, userName);
            exist.setString(2, blockName);
            exist.executeQuery();

            resultSet = exist.getResultSet();

            if(resultSet.next())
            {

            }

        }
        catch (Exception e)
        {
        }

        return false;
    }


    public int allAnswersFilled(String blockName, String userName)
    {
        int nr= 0;

        try
        {
            PreparedStatement answer = connection.prepareStatement
            ( "SELECT COUNT(*) "
            + "FROM correct_answer "
            + "INNER JOIN user_answer "
            + "ON correct_answer.idcorrect_answer = user_answer.correct_answerid "
            + "INNER JOIN user "
            + "ON user_answer.userid = user.iduser "
            + "INNER JOIN block "
            + "ON correct_answer.block_id = block.idblock "
            + "WHERE username = ? "
            + "AND blockname = ? "
            + "AND user_answer.answer != '' "
            + "OR user_answer.answer != NULL "
         /*   + "AND EXISTS ( SELECT correct_answer.exercise_nr "
            + "FROM correct_answer "
            + "INNER JOIN user_answer "
            + "ON correct_answer.idcorrect_answer = user_answer.correct_answerid "
            + "WHERE blockname = ? ) "*/
            );

            answer.setString(1, userName);
            answer.setString(2, blockName);
            answer.executeQuery();
            resultSet = answer.getResultSet();

            if(resultSet.next())
            {
                nr = resultSet.getInt(1);
            }

        }
        catch(Exception e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Exception in Model.allAnswersFilled");
        }

        return nr;
    }// end method allAnswersFilled

    public void saveUserName(String name)
    {
        System.out.println("Model.saveUserName: " + name);
        if(!userNameExist(name))
        {
            try
            {
                PreparedStatement saveUserName = connection.prepareStatement
                ( "INSERT INTO user "
                + "(username) "
                + "VALUES( ? )"
                );

                saveUserName.setString(1, name);
                saveUserName.execute();
            }
            catch (Exception e)
            {
            }
        }
        setChanged();
		notifyObservers();
    }

    public boolean userNameExist(String name)
    {
        try
        {
            PreparedStatement userNameExist = connection.prepareStatement
            ( "SELECT username "
            + "FROM user "
            + "WHERE username = ? "
            );

            userNameExist.setString(1, name);
            userNameExist.executeQuery();
            resultSet = userNameExist.getResultSet();

            if(resultSet.next())
            {
            	return true;
            }
        }
        catch (Exception e)
        {
        }
        return false;
    }

    public void createUserAnswer(String answer, String exerciseNr, String userName)
    {
        int correctAnswerId = getIdCorrectAnswer(exerciseNr);
        int userId = getUserId(userName);

        System.out.println("createUserAnswer correctAnswerId: " + correctAnswerId + " userId: " + " nuserName: " + userName);

        try
        {
            PreparedStatement create = connection.prepareStatement
                ( "INSERT INTO user_answer "
                + "(correct_answerid "
                + ",answer "
                + ",userid) "
                + "VALUES( ?, ?, ? )"
                );

            create.setInt(1, correctAnswerId);
            create.setString(2, answer);
            create.setInt(3, userId);
            create.execute();
        }
        catch (Exception e)
        {
        }

        setChanged();
		notifyObservers();

    }

    public void updateUserAnswer(String answer, String exerciseNr, String name)
    {
        int correctAnswerId = getIdCorrectAnswer(exerciseNr);
        int userId = getUserId(name);

        System.out.println("updateUserAnswer correctAnswerId: " + correctAnswerId + " userId: " + userId + " name: " + name);

        try
        {
            PreparedStatement update = connection.prepareStatement
                ( "UPDATE user_answer "
                + "SET "
                + "answer = ? "
                + "WHERE correct_answerid = ? "
                + "AND userid = ? "
                );

            update.setString(1, answer);
            update.setInt(2, correctAnswerId);
            update.setInt(3, userId);
            update.executeUpdate();
        }
        catch (Exception e)
        {
        }
        setChanged();
		notifyObservers();
    }

    public String getBlockName(int blockNr)
    {
        String blockName = "";

        try
        {
            PreparedStatement block = connection.prepareStatement
            ( "SELECT blockname "
            + "FROM block "
            + "WHERE block.idblock = ? "
            );

            block.setInt(1, blockNr);
            block.executeQuery();
            resultSet = block.getResultSet();

            resultSet.next();
            blockName = resultSet.getString("blockname");

        }
        catch (Exception e)
        {
        }

        return blockName;
    }


    public String[] getExerciseList(int blockNr)
    {
        ArrayList<String> arrayList = new ArrayList<>();

        try
        {
            PreparedStatement exerciseNr = connection.prepareStatement
            ( "SELECT exercise_nr "
            + "FROM correct_answer "
            + "WHERE block_id = ? "
            );

            exerciseNr.setInt(1, blockNr);
            exerciseNr.executeQuery();
            resultSet = exerciseNr.getResultSet();

            while (resultSet.next())
            {
                arrayList.add(resultSet.getString("exercise_nr"));
            }

        }
        catch (Exception e)
        {
        }

        String[] exercise = arrayList.toArray(new String[arrayList.size()]);

        //Arrays.sort(exercise);
        String[] list = new String[exercise.length];
        int j = 0;

        for(int i = 0; i < exercise.length; i++)
        {
            if(exercise[i].length() == 3)
            {
                list[j++] = exercise[i];
            }
        }

        for(int i = 0; i < exercise.length; i++)
        {
            if(exercise[i].length() == 4)
            {
                list[j++] = exercise[i];
            }
        }

        for(int i = 0; i < exercise.length; i++)
        {
            if(exercise[i].length() == 5)
            {
                list[j++] = exercise[i];
            }
        }

        return list;
    }

    public int getIdCorrectAnswer(String exerciseNr)
    {
        int idCorrectAnswer = 0;

        try
        {
            PreparedStatement retrieveID = connection.prepareStatement
            ( "SELECT idcorrect_answer "
            + "FROM correct_answer "
            + "WHERE exercise_nr = ? "
            );

            retrieveID.setString(1, exerciseNr);
            retrieveID.executeQuery();
            resultSet = retrieveID.getResultSet();

            resultSet.next();
            idCorrectAnswer = resultSet.getInt("idcorrect_answer");
        }
        catch (Exception e)
        {
        }

        return idCorrectAnswer;
    }

    public int getUserId(String userName)
    {
        int idUser = 0;

        try
        {
            PreparedStatement getUserId = connection.prepareStatement
                ( "SELECT iduser "
                + "FROM user "
                + "WHERE username = ? "
                );

            getUserId.setString(1, userName);
            getUserId.executeQuery();
            resultSet = getUserId.getResultSet();

            resultSet.next();
            idUser = resultSet.getInt("iduser");
        }
        catch (Exception e)
        {
        }

        return idUser;
    }


	@Override
	public void createQuestion(String exercise_id, String question, int block_id) {

//		String sql = "INSERT INTO correct_answer (exercise_nr, question, block_id) VALUES ('"
//				+ exercise_id + "','" + question + "','" + block_id + "')";

		try
        {
            PreparedStatement create = connection.prepareStatement
                ( "INSERT INTO correct_answer "
                + "(exercise_nr "
                + ", question "
                + ", block_id) "
                + "VALUES( ?, ?, ? )"
                );

            create.setString(1, exercise_id);
            create.setString(2, question);
            create.setInt(3, block_id);
            create.execute();
		}
        catch (SQLException e)
        {

		}

		setChanged();
		notifyObservers();

	}
//questions
	@Override
	public String retrieveQuestion(String exercise_nr)
    {
        String question = "";

        if(questionExist(exercise_nr))
        {
            try
            {
                PreparedStatement retrieve = connection.prepareStatement
                    ( "SELECT question "
                    + "FROM correct_answer "
                    + "WHERE exercise_nr = ? "
                    );

                retrieve.setString(1, exercise_nr);
                retrieve.executeQuery();
                resultSet = retrieve.getResultSet();

                while(resultSet.next())
                {
                    question = resultSet.getString("question");
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }

            return question;
        }

        return "";
	}

    public String[] getBlockList()
    {
        ArrayList<String> arrayList = new ArrayList<>();

        try
        {
            PreparedStatement retrieve = connection.prepareStatement
                    ( "SELECT blockname "
                    + "FROM block "
                    );

            retrieve.execute();
            resultSet = retrieve.getResultSet();

            while (resultSet.next())
            {
                arrayList.add(resultSet.getString(1));
            }

        }
        catch ( SQLException ex )
        {
        }

        String[] blocks = arrayList.toArray( new String[arrayList.size() ] );

        return blocks;
    }

    public String retrieveAnswerUser(String exercise_nr, String userName)
    {
        int userId = getUserId(userName);
        int correctAnswerId = getIdCorrectAnswer(exercise_nr);
        String answerUser = "";

        try
        {
            PreparedStatement retrieve = connection.prepareStatement
                ( "SELECT answer "
                + "FROM user_answer "
                + "WHERE userid = ? "
                + "AND correct_answerid = ? "
                );

            retrieve.setInt(1, userId);
            retrieve.setInt(2, correctAnswerId);
            retrieve.execute();
            resultSet = retrieve.getResultSet();

            resultSet.next();
            answerUser = resultSet.getString("answer");
        }
        catch(SQLException ex)
        {
        }

        return answerUser;
    }// end method retrieveAnswerUser

    public boolean userAnswerExist(String exerciseNr, String userName)
    {
        int userId = getUserId(userName);
        int correctAnswerId = getIdCorrectAnswer(exerciseNr);

        try
        {
            PreparedStatement isUserAnswer = connection.prepareStatement
            ( "SELECT answer "
            + "FROM user_answer "
            + "WHERE userid = ? "
            + "AND correct_answerid = ? "
            );

            isUserAnswer.setInt(1, userId);
            isUserAnswer.setInt(2, correctAnswerId);
            isUserAnswer.executeQuery();
            resultSet = isUserAnswer.getResultSet();

            if(resultSet.next())
            {
                return true;
            }

        }
        catch(SQLException ex)
        {
        }

        return false;
    }

	@Override
	public void updateQuestion(String exercise_nr, String question, int block_id) {
		/*
        String sql = "UPDATE correct_answer SET question = '" + question + "',"
                + "creation_date = CURRENT_TIMESTAMP WHERE exercise_nr = '" + exercise_nr + "'";
        */

		try
        {
             PreparedStatement update = connection.prepareStatement
                 ( "UPDATE correct_answer "
                 + "SET creation_date = CURRENT_TIMESTAMP "
                 + ",question = ? "
                 + "WHERE exercise_nr = ? "
                 );

             update.setString(1, question);
             update.setString(2, exercise_nr);
             update.executeUpdate();

			//statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setChanged();
		notifyObservers();

	}

    @Override
	public void updateAnswer(String exercise_nr, String answer, int block_id)
    {
        try
        {
             PreparedStatement update = connection.prepareStatement
                 ( "UPDATE correct_answer "
                 + "SET creation_date = CURRENT_TIMESTAMP "
                 + ",answer = ? "
                 + "WHERE exercise_nr = ? "
                 );

             update.setString(1, answer);
             update.setString(2, exercise_nr);
             update.executeUpdate();

		} catch (SQLException e)
        {
		}

		setChanged();
		notifyObservers();
	}

	@Override
	public void deactivateQuestion(String exercise_nr)
    {
        try
        {
            PreparedStatement deactivate = connection.prepareStatement
            ( "UPDATE correct_answer "
            + "SET deactivate_date = CURRENT_TIMESTAMP "
            + "WHERE exercise_nr = ? "
            );

            deactivate.setString(1, exercise_nr);
            deactivate.executeUpdate();
        }
        catch (SQLException e)
        {

        }
	}

    @Override
    public void resetDeactivateDate()
    {
        try
        {
            PreparedStatement reset = connection.prepareStatement
            ( "UPDATE correct_answer "
            + "SET deactivate_date = NULL "
            );

            reset.executeUpdate();
        }
        catch (SQLException e)
        {

        }
    }

	public boolean questionExist(String exercise_nr)
    {
		try
        {
            PreparedStatement isQuestion = connection.prepareStatement
            ( "SELECT exercise_nr "
            + "FROM correct_answer "
            + "WHERE exercise_nr = ? "
            );

            isQuestion.setString(1, exercise_nr);
            isQuestion.executeQuery();
            resultSet = isQuestion.getResultSet();

			if (resultSet.next())
            {
				return true;
			}

		}
        catch (SQLException e)
        {

		}

		return false;

	}


    @Override
	public boolean answerExist(String exercise_nr)
    {
        try
        {
            PreparedStatement answer = connection.prepareStatement
            ( "SELECT answer "
            + "FROM correct_answer "
            + "WHERE exercise_nr = ? "
            );

            answer.setString(1, exercise_nr);
            answer.executeQuery(); //returns the ResultSet object generated by the query.

            resultSet = answer.getResultSet();

            if(resultSet.next())
            {
                return true;
            }

        }
        catch(SQLException e)
        {

        }

		return false;
	}

	@Override
	public String retrieveAnswer(String exercise_nr)
    {
		String answer = "";

        if(questionExist(exercise_nr))
        {
            try
            {
                PreparedStatement retrieve = connection.prepareStatement
                    ( "SELECT answer "
                    + "FROM correct_answer "
                    + "WHERE exercise_nr = ? "
                    );

                retrieve.setString(1, exercise_nr);
                retrieve.executeQuery();
                resultSet = retrieve.getResultSet();

                while(resultSet.next())
                {
                    answer = resultSet.getString("answer");
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }

            return answer;
        }

        return "";

	}


	@Override
	public void deleteAnswer() {
		// TODO

	}


	public void delTable(String table)
    {
        String sql = "DELETE FROM " + table;

		try
        {
            PreparedStatement del = connection.prepareStatement
                (sql);

            del.executeUpdate();
            resultSet = del.getResultSet();

        }
        catch (SQLException ex)
        {

        }

		System.out.println("Table : " +table + " deleted!");

	}

}
