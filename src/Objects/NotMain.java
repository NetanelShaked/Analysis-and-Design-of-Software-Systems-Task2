package Objects;

import java.util.Date;

public class NotMain {

    private static void test_constrain1(){
        Model model = new Model();
        Hotel h1 = new Hotel("BeerSheva","Isrotal",5);
        Group g1 = new Group(1);
        model.create_link_group_hotel(h1,g1);
        model.addObjectToModel(h1);
        model.addObjectToModel(g1);
        if(!model.checkModelConstraints()) System.out.println("Failed! Linked h1<->g1");
        Hotel h2 = new Hotel("TelAviv","Isrotal",4);
        model.create_link_group_hotel(h2,g1);
        model.addObjectToModel(h2);
        if(!model.checkModelConstraints()) System.out.println("Failed! Linked h2<->g1");
        Hotel h3 = new Hotel("BeerSheva","Isrotal-club",4);
        model.create_link_group_hotel(h3,g1);
        model.addObjectToModel(h3);
        if(model.checkModelConstraints()) System.out.println("Failed! h1&h3 from the same city linked to g1 And no Error detected");
        else System.out.println("Passed!");
    }

    private static void test_constraint2(){
        Model model = new Model();
        Hotel h1 = new Hotel("BeerSheva","Isrotal",5);
        model.addObjectToModel(h1);
        Client c1 = new Client(123,26,"tzah","BeerSheva");
        model.addObjectToModel(c1);
        ReservationSet r1 = new ReservationSet();
        model.addObjectToModel(r1);
        model.create_link_client_hotel_reservationSet(c1,h1,r1);
        Reservation res1 = new Reservation(new Date(),new Date(),1);
        Reservation res2 = new Reservation(new Date(),new Date(),2);
        Reservation res3 = new Reservation(new Date(),new Date(),3);
        Reservation res4 = new Reservation(new Date(),new Date(),4);
        model.addObjectToModel(res1);
        model.addObjectToModel(res2);
        model.addObjectToModel(res3);
        model.addObjectToModel(res4);
        model.create_link_reservationSet_reservation(r1,res1);
        model.create_link_reservationSet_reservation(r1,res2);
        model.create_link_reservationSet_reservation(r1,res3);
        model.create_link_reservationSet_reservation(r1,res4);
        RoomCategory rc1 = new RoomCategory(100, RoomCategory.RoomType.BASIC);
        RoomCategory rc2 = new RoomCategory(100, RoomCategory.RoomType.BASIC);
        RoomCategory rc3 = new RoomCategory(100, RoomCategory.RoomType.BASIC);
        RoomCategory rc4 = new RoomCategory(100, RoomCategory.RoomType.BASIC);
        model.addObjectToModel(rc1);
        model.addObjectToModel(rc2);
        model.addObjectToModel(rc3);
        model.addObjectToModel(rc4);
        model.create_link_reservation_roomCategory(res1,rc1);
        model.create_link_reservation_roomCategory(res2,rc2);
        model.create_link_reservation_roomCategory(res3,rc3);
        model.create_link_reservation_roomCategory(res4,rc4);
        if(!model.checkModelConstraints()){
            System.out.println("Failed, no 5 reservations in ReservationSet - should passed.");
        }
        Reservation res5 = new Reservation(new Date(),new Date(),5);
        model.addObjectToModel(res5);
        model.create_link_reservationSet_reservation(r1,res5);
        RoomCategory rc5 = new RoomCategory(100, RoomCategory.RoomType.BASIC);
        model.addObjectToModel(rc5);
        model.create_link_reservation_roomCategory(res5,rc5);
        if(model.checkModelConstraints()){
            System.out.println("Failed, ReservationSet have more than 5 Reservation and all the types is Basic!");
        }
        Reservation res6 = new Reservation(new Date(),new Date(),6);
        model.addObjectToModel(res6);
        model.create_link_reservationSet_reservation(r1,res6);
        RoomCategory rc6 = new RoomCategory(100, RoomCategory.RoomType.VIP);
        model.addObjectToModel(rc6);
        model.create_link_reservation_roomCategory(res6,rc6);
        if(!model.checkModelConstraints()){
            System.out.println("Failed, ReservationSet has more than 5 Reservation and has roomType VIP");
        }
        else{
            System.out.println("Passed!");
        }

    }

    public static void main(String[] args)
    {
	// write your code here
//        test_constraint2();
//        test_constrain1();
//        test_constrain11();
        test_constrain12();
//        System.out.println(new Date(2020,5,1).getYear());
    }

    private static void test_constrain12() {
        Model model=new Model();

        Hotel h1=new Hotel("Netanya","TzahTheMan",4);
        HotelService hotelService1=new HotelService(10,5);
        HotelService hotelService2=new HotelService(10,5);
        HotelService hotelService3=new HotelService(10,5);
        Service service1=new VipService("1");
        Service service2=new VipService("2");
        Service service3=new VipService("3");
        h1.addService(service1,hotelService1);
        h1.addService(service2,hotelService2);
        h1.addService(service3,hotelService3);
        hotelService1.setHotel(h1);
        hotelService2.setHotel(h1);
        hotelService3.setHotel(h1);
        hotelService1.assignService(service1);
        hotelService2.assignService(service2);
        hotelService3.assignService(service3);
        service1.addHotel(h1,hotelService1);
        service2.addHotel(h1,hotelService2);
        service3.addHotel(h1,hotelService3);
        Room room=new Room(5);
        room.setHotel(h1);
        h1.addRoom(5,room);
        Booking booking1=new Booking(new Date(2020,5,1),room);
        Booking booking2=new Booking(new Date(2020,5,1),room);
        Booking booking3=new Booking(new Date(2020,5,1),room);

        hotelService1.addBooking(booking1);
        hotelService2.addBooking(booking2);
        hotelService2.addBooking(booking1);
        hotelService3.addBooking(booking1);
        hotelService3.addBooking(booking2);
        hotelService3.addBooking(booking3);
        booking1.addService(hotelService1);
        booking2.addService(hotelService2);
        booking2.addService(hotelService1);
        booking3.addService(hotelService1);
        booking3.addService(hotelService2);
        booking3.addService(hotelService3);

        model.addObjectToModel(h1);
        model.addObjectToModel(room);
        model.addObjectToModel(hotelService1);
        model.addObjectToModel(hotelService2);
        model.addObjectToModel(hotelService3);
        model.addObjectToModel(booking1);
        model.addObjectToModel(booking2);
        model.addObjectToModel(booking3);
        model.addObjectToModel(service1);
        model.addObjectToModel(service2);
        model.addObjectToModel(service3);
        System.out.println(h1.constrain12());
        System.out.println(h1.constrain11());
        System.out.println(h1.checkConstraints());
        service3.serviceName="2";
        System.out.println(h1.constrain11());


    }


    private static void test_constrain11() {
    }
}
