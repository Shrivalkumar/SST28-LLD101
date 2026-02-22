public class StandardRoomPricing implements RoomPricing{
    @Override
    public Money roomPrice(int roomType){
        double cost = 0.0;
        if(roomType == 1){
            cost = 14000.0;
        }else if(roomType ==2){
            cost =15000.0;
        }else if(roomType == 3){
            cost = 12000.0;
        }
        return new Money(cost);
    }
}
