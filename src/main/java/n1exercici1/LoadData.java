package n1exercici1;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println("Stock successfully saved!");
        }
        catch (IOException ex){
            System.err.println("Problem saving stock data");
        }
    }
    public static void saveTickets(TicketHistory ticketHistory){
        List<Ticket> listTickets = ticketHistory.getTicketList();
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(ticketFile, listTickets);
            System.out.println("Tickets successfully saved!");
        }
        catch (IOException ex){
            System.err.println("Problem saving ticket data");
        }
    }
    public static Map<Product, Integer> loadStock(){
        System.out.println("Loading previous stock...");
        try{
            TypeReference<HashMap<Product, Integer>> typeReference = new TypeReference<HashMap<Product, Integer>>(){};
            Map<Product, Integer> stock = mapper.readValue(stockFile,typeReference);
            System.out.println("Stock successfully loaded");
            return stock;
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.err.println("Problem loading stock data, starting with empty stock");
            return new HashMap<Product, Integer>();
        }
    }
    public static TicketHistory loadTickets(){
        System.out.println("Loading previous tickets...");
        try{
            TypeReference<List<Ticket>> typeReference = new TypeReference<List<Ticket>>(){};
            List<Ticket> ticketList = mapper.readValue(ticketFile, typeReference);
            TicketHistory ticketHistory = new TicketHistory(ticketList);
            System.out.println("Previous tickets successfully loaded");
            return ticketHistory;
        }
        catch (com.fasterxml.jackson.databind.exc.MismatchedInputException ex){
            System.out.println("Empty tickets file, starting with no previous tickets");
            return new TicketHistory();
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.err.println("Problem loading ticket data, starting with no previous tickets");
            return new TicketHistory();
        }
    }
}
