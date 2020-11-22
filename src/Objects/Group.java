package Objects;

import java.util.HashSet;
import java.util.Set;

public class Group implements ITestable {
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id){
        hotels = new HashSet<Hotel>();
        groupId = id;
    }



    public void addHotelToGroup(Hotel hotel){
        hotels.add(hotel);
    }

    //getters

    public int getGroupId() {
        return groupId;
    }

    public HashSet<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public boolean checkConstraints() {
        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }

    private boolean constrain1() {
        for (Hotel hotel1 : hotels) {
            for (Hotel hotel2 : hotels) {
                if (hotel1.getCity() == hotel2.getCity()) {
                    if (hotel1 != hotel2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean constrain3(){
        Set<Service> allServices = hotels.iterator().next().getServices().keySet();
        for (Hotel hotel: hotels){
            if (!(hotel.getServices().keySet().equals(allServices)))
                return false;
        }
        return true;}

}
