package n1exercici1;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;

public class LoadData <T>{
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String currDir = System.getProperty("user.dir");
    private static final String stockPath = currDir + "/src/main/resources/Stock.json";
    private static final File stockFile = new File(stockPath);
    private static final String ticketPath = currDir + "/src/main/resources/Tickets.json";
    private static final File ticketFile = new File(ticketPath);

    public static void saveStock(Map<Product, Integer> stock){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(stockFile, stock);
        }
        catch (IOException ex){
            System.err.println("Problem saving stock data");
        }
    }

    public static void saveTicket(Ticket ticket){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(ticketFile, ticket);
        }
        catch (IOException ex){
            System.err.println("Problem saving ticket data");
        }
    }

    public static void loadStock(){
        try{
            Map<Product, Integer> stock = mapper.readValue(stockFile, Map.class);
        }
        catch (IOException ex){
            System.err.println("Problem loading stock data");
        }
    }
    public static void loadTickets(){
        try{
            Ticket ticket = mapper.readValue(ticketFile, Ticket.class);
        }
        catch (IOException ex){
            System.err.println("Problem loading stock data");
        }
    }
}
