import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the holiday chatbot. Which holiday bot would you like to speak to? Press 1 for Valentine's Day, 2 for Halloween, and 3 for Thanksgiving.");
		int holiday=in.nextInt();
		Chatbot chatbot; // creates a placeholder chatbot before choosing which holiday
		if (holiday==1) {
			chatbot = new ValentineDay();}
		else if (holiday==2) {
			chatbot = new Halloween();}
		else {
			chatbot= new Thanksgiving();}

		String statement = in.nextLine();


		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation
			chatbot.chatLoop(statement);
			statement = in.nextLine();
		}
	}

}
