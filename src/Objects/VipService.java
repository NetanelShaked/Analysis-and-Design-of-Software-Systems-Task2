package Objects;

import java.util.HashSet;
import java.util.Set;

public class VipService extends Service{

    public VipService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        Set<Service> services=new HashSet<>();
        for (HotelService hotelService:model.HotelServiceAllInstances()
        ) {
            if(hotelService.getService()!=null && hotelService.getService() instanceof VipService) {
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
