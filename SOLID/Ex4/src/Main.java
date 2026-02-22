import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        List<AddOnRule> rules = List.of(
            new MessAddOn(),
            new LaundryAddOn(),
            new GymAddOn()
        );
        
        RoomType doubleRoom = new DoubleRoom();

        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), rules);
        calc.process(req, doubleRoom);
    }
}