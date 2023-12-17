package n1exercici1;

import java.util.*;

public class TicketHistory {
    private static List<Ticket> ticketList;

    private static float totalSalesAmount;
    public TicketHistory() {
        ticketList = new ArrayList<>();
        totalSalesAmount = 0.0f;
    }
    public TicketHistory(List<Ticket> jsonTicketList){
        ticketList = jsonTicketList;
        updateTotalSalesAmount();
    }
    public static float getTotalSalesAmount() {
        return totalSalesAmount;
    }
    public static void setTotalSalesAmount(float amount){
        totalSalesAmount = amount;
    }

    public List<Ticket> getTicketList(){
        return ticketList;
    }
    public void addTicket(Ticket ticket){
        ticketList.add(ticket);
        updateTotalSalesAmount();
    }

    private static float calcTotalSalesAmount(){
        return (float) ticketList.stream().mapToDouble(Ticket::getAmount).sum();
    }

    private static void updateTotalSalesAmount(){
        setTotalSalesAmount(calcTotalSalesAmount());
    }
    public String toPrettyString(){
        StringBuilder allTickets = new StringBuilder();
        ticketList.forEach(t-> allTickets.append(t.toPrettyString()));
        return allTickets.toString();
    }

}
