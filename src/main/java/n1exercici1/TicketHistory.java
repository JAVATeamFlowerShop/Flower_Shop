package n1exercici1;

import java.util.ArrayList;
import java.util.List;

public class TicketHistory {
    private List<Ticket> ticketHistory = new ArrayList<>();

    private void addTicket(Ticket ticket){

    }

    public float getTotalSalesAmount(){
        return (float) ticketHistory.stream().mapToDouble(ticket -> ticket.getAmount()).sum();
    }

}
