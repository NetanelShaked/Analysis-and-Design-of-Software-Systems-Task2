package Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking implements ITestable {
    private Date date;
    private Room room;
    private ArrayList<HotelService> services;
    private Reservation reservation;
    private Review review;


    public Booking(Date a_date, Room a_room) {
        date = a_date;
        room = a_room;
        services = new ArrayList<HotelService>();
    }

    public void addService(HotelService s) {
        services.add(s);
    }

    public void addReview(Review a_review) {
        review = a_review;
    }

    public void addReservation(Reservation r) {
        reservation = r;
    }

    public void assignRoom(Room room) {
        this.room = room;
    }


    // getters

    public Date getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Review getReview() {
        return review;
    }


    @Override
    public boolean checkConstraints() {
        return constrain_9() && constraint_13();
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        for (Hotel hotel :
                model.HotelAllInstances()) {
            if (!hotel.checkConstraints()) {
                return false;
            }
        }
        return true;
    }

    public boolean constrain_9() {
        if (services.size() == 0)
            return true;
        for (HotelService hs : services) {
            if (hs == null)
                continue;
            if (hs.getService() instanceof VipService)
                return this.review != null;
        }
        return true;
    }

    public boolean constraint_13() {
        if (getServices().size() == 0)
            return true;
        if (getServices() == null)
            return true;
        for (HotelService hs : getServices()
        ) {
            if (hs.getHotel().getName() != getReservation().getReservationSet().getHotel().getName())
                return false;


        }
        return true;
    }
}
