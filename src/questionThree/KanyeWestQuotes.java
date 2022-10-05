package questionThree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class KanyeWestQuotes {
    private static final Scanner input = new Scanner(System.in);
    private static boolean status = true;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://api.kanye.rest/";
    private static final Set<String> cache = new HashSet<>();

    public static void runKanyeWestQuoteApp(){
        welcomeMessage();

        next();

        while(status) {
            menu();
            sortResponse();
        }
    }

    private static void welcomeMessage() {
        System.out.println("Welcome to Kanye West Pearl of Wisdom...");
        alignText();
    }

    private static void menu(){
        System.out.println("""
                Press 1 for Next
                Press 2 to Quit
                """);
    }
    private static void alignText(){
        System.out.println("\n");
    }

    private static void sortResponse(){
        String response = input.nextLine();
        switch (response) {
            case "1" -> next();
            case "2" -> {
                System.out.println("Exiting Kanye West Quotes App....");
                status = false;
            }
            default -> {
                System.out.println("You have entered an invalid option. Please enter either 1 or 2");
                menu();
                sortResponse();
            }
        }
    }

    private static void next() {

        try {
            HttpURLConnection connection = initiateConnection();
            StringBuilder response = buildResponseBody(connection);

            verifyIfAlreadyDisplayed(response);
            memorizeQuote(response);
            System.out.println(response);
            alignText();
        }
        catch (IOException ex) {
            System.out.println("Error message: "+ex.getMessage()+". Check internet Connection");
            System.exit(0);
        }

    }

    private static void verifyIfAlreadyDisplayed(StringBuilder response) {
        if (cache.contains(response.toString())) {
            next();
        }
    }

    private static void memorizeQuote(StringBuilder response) {
        cache.add(response.toString());
    }

    private static StringBuilder buildResponseBody(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new StringBuilder(response.substring(9, (response.length() - 1)));
    }

    private static HttpURLConnection initiateConnection() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        return connection;
    }


}
