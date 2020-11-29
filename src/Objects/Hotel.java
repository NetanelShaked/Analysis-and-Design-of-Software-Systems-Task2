package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Hotel implements ITestable {
    private String name;
    private HashMap<Client, ReservationSet> allReservation;
    private HashMap<Service, HotelService> services;
    private HashMap<Integer, Room> rooms;
    private String city;
    private Group group;
    private int rate;


    public Hotel(String city, String name, int rate) {
        this.city = city;
        this.name = name;
        this.rate = rate;
        rooms = new HashMap<Integer, Room>();
        allReservation = new HashMap<Client, ReservationSet>();
        services = new HashMap<Service, HotelService>();

    }

    public void addReservationSet(Client client, ReservationSet reservationSet) {
        allReservation.put(client, reservationSet);
    }

    public void addService(Service service, HotelService hotelService) {
        services.put(service, hotelService);
    }

    public void addRoom(int roomNumber, Room room) {
        rooms.put(roomNumber, room);
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public HashMap<Client, ReservationSet> getAllReservation() {
        return allReservation;
    }

    public HashMap<Service, HotelService> getServices() {
        return services;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public boolean checkConstraints() {
        return this.constrain7() && constraint2() && constrain11() && constrain12() && constrain_10() && constrain_6();
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        for (Hotel hotel : model.HotelAllInstances()) {
            if (!hotel.checkConstraints()) {
                return false;
            }
        }
        return true;
    }

    public boolean constrain11() {
        for (HotelService hotelService1 : this.services.values()) {
            for (HotelService hotelService2 : this.services.values()) {
                if (hotelService1 != hotelService2) {
                    if (hotelService1.getService().getServiceName() == null || hotelService2.getService().getServiceName() == null) {
                        continue;
                    }
                    if (hotelService1.getService().getServiceName().equals(hotelService2.getService().getServiceName())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean constraint2() {
        for (ReservationSet reservationSet : this.allReservation.values()) {
            if (reservationSet.getReservations().size() >= 5) {
                boolean exist = false;
                for (Reservation reservation : reservationSet.getReservations()) {
                    if (reservation.getRoomCategory().getType() == RoomCategory.RoomType.VIP)
                        exist = true;
                }
                if (!exist) return false;
            }
        }
        return true;
    }

    public boolean constrain12() {
        Set<Integer> years = new HashSet<>();
        for (HotelService hotelService : this.services.values()) {
            for (Booking booking : hotelService.getGivenServices()
            ) {
                if (booking.getDate() != null) {
                    years.add(booking.getDate().getYear());
                }
            }
        }
        for (Integer year : years) {
            if (sum_Array_list(help_function_section_12_get_hotelService_by_year(year)) < sum_Array_list(help_function_section_12_get_hotelService_by_year(year - 1))) {
                if (help_function_section_12_get_hotelService_by_year(year-1).size() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<HotelService> help_function_section_12_get_hotelService_by_year(int year) {
        ArrayList<HotelService> result = new ArrayList<>();
        for (HotelService hotelService : this.services.values()) {
            for (Booking booking : hotelService.getGivenServices()) {
                if (booking.getDate().getYear() == year)
                    for (HotelService hotelService1 : booking.getServices()
                    ) {
                        result.add(hotelService1);
                    }
            }
        }
        return result;
    }

    private float sum_Array_list(ArrayList<HotelService> to_sum) {
        float result = 0;
        for (HotelService hotelService : to_sum
        ) {
            result += hotelService.getPrice();
        }
        return result;
    }

    public boolean constrain7() {
        if (this.city.toLowerCase().equals("las vegas")) {
            if (this.allReservation.size() == 0)
                return true;
            for (Client c1 : this.allReservation.keySet()) {
                if (c1 == null)
                    return true;
                if (!(c1.getAge() >= 21))
                    return false;
            }
        }
        return true;
    }

    public boolean constrain_10() {
        float sum = 0;
        int counter = 0;
        if (this.rate == 5) {
            if(this.allReservation.size() == 0)
                return true;
            for (ReservationSet rs : this.allReservation.values()) {
                if(rs.getReservations().size() == 0)
                    continue;
                for (Reservation r : rs.getReservations()) {
                    if (r.getBookings() != null && r.getBookings().getReview() != null) {
                        sum += r.getBookings().getReview().getRank();
                        counter++;
                    }
                }
            }
            if (counter > 0)
                return (sum / counter) > 7.5;
        }
        return true;
    }

    public boolean constrain_6() {
        if (this.rooms.isEmpty()){
            return true;
        }
        float roomCount = this.rooms.size();

        if (roomCount == 0)
            return true;
        float vipRoomsCount = 0;
        for (Room room:this.rooms.values()
        ) {
            if (room.getRoomCategory().getType() == RoomCategory.RoomType.VIP)
                vipRoomsCount += 1;


        }
        float check = (vipRoomsCount/roomCount);
        if (0.1 > (check)){
            return false;}
        return true;

    }
}
