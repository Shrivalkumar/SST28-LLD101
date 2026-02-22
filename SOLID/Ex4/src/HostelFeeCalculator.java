import java.util.*;

public class HostelFeeCalculator {
    private final BookingRepository repo;
    private final RoomPricing roomPrice;
    private final AddOnPricing addOnPrice;

    public HostelFeeCalculator(BookingRepository repo, RoomPricing roomPrice, AddOnPricing addOnPrice){ 
        this.repo = repo;
        this.roomPrice= roomPrice;
        this.addOnPrice = addOnPrice;
    }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = roomPrice.roomPrice(req.roomType);
        for(AddOn a : req.addOns){
            total.plus(addOnPrice.addOnPrice(a));
        }
        return total;
    }
}
