package n1exercici1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketHistory {
    private final String currDir = System.getProperty("user.dir");
    private final String ticketHistoryPath = currDir + "/src/main/sellHistory.txt";
    private File ticketHistoryFile;
    private Scanner readTickets;
    private FileWriter writeTickets;
    private List<Ticket> ticketHistory;

    {
        ticketHistoryFile = new File(ticketHistoryPath);
        try {
            this.readTickets = new Scanner(ticketHistoryFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Problem reading ticket history");
        }
        try {
            this.writeTickets = new FileWriter(ticketHistoryFile, true);
        } catch (IOException e) {
            System.out.println("Problem finding ticket history");
        }
    }
    public TicketHistory() {
        this.ticketHistory = new ArrayList<>();
    }

    public void addTicket(Ticket ticket){
        ticketHistory.add(ticket);
        try {
            writeTickets.write(ticket.toString());
        } catch (IOException e) {
            System.out.println("Problem writing ticket in txt file");
        }
    }

    public float getTotalSalesAmount(){
        return (float) ticketHistory.stream().mapToDouble(Ticket::getAmount).sum();
    }

    @Override
    public String toString(){
        StringBuilder allTickets = new StringBuilder();
        ticketHistory.forEach(allTickets::append);
        return allTickets.toString();
    }

}
