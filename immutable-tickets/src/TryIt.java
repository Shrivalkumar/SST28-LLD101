import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.List;

public class TryIt {
    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Creation
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing");
        System.out.println("Initial: " + t1);

        // 2. "Updates" (creating new objects)
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("Final state (t3): " + t3);
        System.out.println("Original state (t1) unchanged: " + t1);

        // 3. Defensive Copy Check
        try {
            t3.getTags().add("HACK"); // This will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccess: Internal list is protected from mutation!");
        }
    }
}