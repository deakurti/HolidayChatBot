import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class Halloween extends Chatbot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	/**
	 * Get a default greeting
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "It is the time of season for dressing up. Are you excited?";
	}

	/**
	 * Gives a response to a user statement related to halloween
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";

		if (statement.length() == 0)
		{
			response = "Hey. Say something.";
		}

		else if (findKeyword(statement, "costume") >= 0)
		{
			response = "Do you still need to buy a costume?";
			emotion++;
		}

		else if (findKeyword(statement, "pumpkin") >= 0)
		{
			response = "I could really use some pumpkin pie.";
			emotion++;
		}
		else if (findKeyword(statement, "scary") >= 0)
		{
			response = "If you get scared easily, you won't like the Haunted House.";
			emotion--;
		}
		else if (findKeyword(statement, "haunted") >= 0)
		{
			response = "Don't be nervous!!";
			emotion--;
		}
		else if (findKeyword(statement, "candy")>=0)
		{
			response= "The best part of Halloween is getting free candy.";
			emotion++;
		}

		// Response transforming I need to buy statement
		else if (findKeyword(statement, "I need to buy", 0) >= 0)
		{
			response = transformINeedToBuyStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		else if (findKeyword(statement, "I love", 0)>=0)
		{
			response=transfromCostumeStatement(statement);
		}
		else if (findKeyword(statement, "I", 0) >= 0 && findKeyword(statement, "you", 2)>=0) {
			response=transformIYouStatement(statement);
		}
		else
		{
			response = getRandomResponse();
		}

		return response;
	}

	/**
	 * Take a statement with "I need to buy <something>." and transform it into
	 * "I love <something>."
	 * @param statement the user statement, assumed to contain "I need to buy"
	 * @return the transformed statement
	 */
	private String transformINeedToBuyStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want ", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "I love" + restOfStatement + ".";
	}

	/**
	 * transsforms "costume" to "What kind do you want?"
	 * @param statement is the user's statement
	 * @return the transformed statement
	 */
	private String transfromCostumeStatement(String statement){
		statement=statement.trim();
		String lastChar = statement.substring(statement.length()-1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn= findKeyword(statement, "costume", 0);
		String restOfStatement = statement.substring(psn+6).trim();
		return "What kind do you want " + restOfStatement + "?";
	}
	/**
	 * Take a statement with "I want <something>." and transform it into
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "I <something> you" and transform it into
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);

		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}




	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
							int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
					// letter
					&& ((after.compareTo("a") < 0) || (after
					.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}



	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{
			return randomSadResponses [r.nextInt(randomSadResponses.length)];
		}
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}

	private String [] randomNeutralResponses = {"It is the time of season for dressing up. Are you excited?",
			"My mom is dressing up as a vampire. What is your costume?",
			"Will you be my valentine?",
			"I love the fall leaves falling",
			"What's your favorite kind of flower?",
			"Can chatbots fall in love?"
	};
	private String [] randomSadResponses = {":(", "Stop being sad. Halloween is the time of tricks and treats.", "Trick or Treat?", "You look like a scary Jack-o-lantern."};
	private String [] randomHappyResponses = {"Ah. To be happy on Halloween.", "Sometimes I wish I worked in a haunted house.", "have fun this Halloween."};

}
