import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        BookingRepository repo = new FakeBookingRepo();
        RoomPricing roomPrice = new StandardRoomPricing();
        AddOnPricing addOnPrice = new StandardAddOnPricing();

        HostelFeeCalculator calc = new HostelFeeCalculator(repo, roomPrice , addOnPrice);
        calc.process(req);
    }
}
