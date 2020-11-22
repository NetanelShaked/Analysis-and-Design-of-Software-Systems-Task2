package Objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Service implements ITestable{

    protected HashMap<Hotel, HotelService> serviceAtHotels;
    protected  String serviceName;

    public Service(String serviceName){
        serviceAtHotels = new HashMap<Hotel, HotelService>();
        this.serviceName = serviceName;
    }

    public void addHotel(Hotel h, HotelService hs){
        serviceAtHotels.put(h,hs);
    }


    public HashMap<Hotel, HotelService> getServiceAtHotels() {
        return serviceAtHotels;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public boolean checkConstraints() {
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        Set<Service> services=new HashSet<>();
        for (HotelService hotelService:model.HotelServiceAllInstances()
             ) {
            if(hotelService.getService()!=null) {
                services.add(hotelService.getService());
            }
        }
        for (Service service:services
             ) {
            if(!service.checkConstraints()){
                return false;
            }
        }
        return true;
    }
}
