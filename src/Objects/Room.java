package Objects;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Room implements ITestable {
    private RoomCategory roomCategory;
    private HashMap<Date, Booking> bookings;
    private int number;
    private Hotel hotel;


    public Room(int number) {
        this.number = number;
        bookings = new HashMap<Date, Booking>();
    }

    public void setHotel(Hotel h) {
        hotel = h;
    }

    public void asignRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking booking, Date date) {
        bookings.put(date, booking);
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public HashMap<Date, Booking> getBookings() {
        return bookings;
    }

    public int getNumber() {
        return number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public boolean checkConstraints() {
        return constains4_8();
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        for (Room room : model.RoomAllInstances()) {
            if (!room.checkConstraints()) {
                return false;
            }
        }
        return Room.constraint_5(model);
    }

    public boolean constains4_8() {
        Collection<Booking> allBookings = bookings.values();
        for (Booking booking : allBookings) {
            Hotel bookedHotel = booking.getReservation().getReservationSet().getHotel();
            RoomCategory.RoomType reservedType = booking.getReservation().getRoomCategory().getType();
            RoomCategory.RoomType bookedType = roomCategory.getType();
            if (!bookingIsValid(reservedType, bookedType) || hotel != bookedHotel)
                return false;
        }
        return true;
    }

    private boolean bookingIsValid(RoomCategory.RoomType reservedType, RoomCategory.RoomType bookedType) {
        return (!(bookedType != reservedType || (bookedType.toString().equals("BASIC") && reservedType.toString().equals("SUITE")) ||
                (bookedType.toString().equals("BASIC") && reservedType.toString().equals("VIP")) ||
                (bookedType.toString().equals("SUITE") && reservedType.toString().equals("VIP"))));
    }

    public static boolean constraint_5(Model model) {
        // constraint 5
        HashSet<RoomCategory> roomCategories = model.RoomCategoryAllInstances();
        for (RoomCategory r : roomCategories
        ) {
            if (r.getType() == RoomCategory.RoomType.VIP) {
                for (Reservation res : r.reservations
                ) {
                    for (HotelService hotelService : res.getBookings().getServices()
                    ) {
                        if (!hotelService.getService().getClass().getCanonicalName().equals("VipService"))
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
