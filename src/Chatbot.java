import java.util.Scanner;

public abstract class Chatbot {
    /** Parent class for all chatbots
     * Includes methods that are present in all holiday chatbots
    * */

    public abstract String getGreeting(); // default greeting -- different for every holiday

    public void chatLoop(String statement)
    {
        Scanner in = new Scanner (System.in);
        System.out.println (getGreeting());


        while (!statement.equals("Goodbye."))
        {
            statement = in.nextLine();
            //getResponse handles the user reply
            System.out.println(getResponse(statement));

        }
    }

    public abstract String getResponse(String statement); // response method, different for every chatbot
}
