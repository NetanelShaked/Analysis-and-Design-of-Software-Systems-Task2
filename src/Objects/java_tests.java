package Objects;
import java.util.Date;

public class java_tests {

    public static String HotelTest_case0() {
        boolean expectedOutput = false;
        Model m1 = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("Haifa", "Dan Panorama", 4);
        Hotel h2 = new Hotel("Haifa", "Dan Carmel", 4);
        m1.addObjectToModel(g);
        m1.addObjectToModel(h1);
        m1.addObjectToModel(h2);
        m1.create_link_group_hotel(h1, g);
        m1.create_link_group_hotel(h2, g);
        boolean studentOutput = m1.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }


    public static String HotelTest_case1() {
        boolean expectedOutput = true;
        Model m1 = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("Haifa", "Leonardo", 5);
        Hotel h2 = new Hotel("Haifa", "Dan Carmel", 4);
        m1.addObjectToModel(g);
        m1.addObjectToModel(h1);
        m1.addObjectToModel(h2);
        m1.create_link_group_hotel(h1, g);
        boolean studentOutput = m1.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }

    public static String HotelTest_case2() {
        boolean expectedOutput = true;
        Model m1 = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("Las Vegas", "Venice", 5);
        Service s1 = new CommunityService("Casino");
        HotelService hs1 = new HotelService(0, 10);
        m1.addObjectToModel(g);
        m1.addObjectToModel(h1);
        m1.addObjectToModel(s1);
        m1.addObjectToModel(hs1);
        m1.create_link_group_hotel(h1, g);
        m1.create_link_hotel_service_hotelService(h1, s1, hs1);
        boolean studentOutput = m1.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }

    public static String HotelTest_case3() {
        boolean expectedOutput = false;
        Model m1 = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("Haifa", "Dan Panorama", 4);
        Hotel h2 = new Hotel("Haifa", "Dan Carmel", 4);
        m1.addObjectToModel(g);
        m1.addObjectToModel(h1);
        m1.addObjectToModel(h2);
        m1.create_link_group_hotel(h1, g);
        m1.create_link_group_hotel(h2, g);
        boolean studentOutput = m1.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }


    public static String ClientTest_case0() {
        boolean expectedOutput = false; // the client is under 21 and made a reservation to hotel in Las Vegas
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Model m = new Model();

        Client client1 = new Client(1, 20, "Dolev", "Tel Aviv");
        Hotel hotel1 = new Hotel("Las Vegas", "Paris", 4);
        Room room1 = new Room(404);
        ReservationSet reservationSet1 = new ReservationSet();
        Reservation reservation1 = new Reservation(orDate, reqDate, 100);
        RoomCategory roomCategory1 = new RoomCategory(200, RoomCategory.RoomType.BASIC);
        Service s1 = new CommunityService("Casino");
        HotelService hs1 = new HotelService(0, 10);


        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(room1);
        m.addObjectToModel(reservationSet1);
        m.addObjectToModel(reservation1);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs1);
        m.addObjectToModel(roomCategory1);

        m.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet1);
        m.create_link_reservationSet_reservation(reservationSet1, reservation1);
        m.create_link_reservation_roomCategory(reservation1, roomCategory1);
        m.create_link_hotel_room(room1, hotel1);
        m.create_link_room_roomCategory(room1, roomCategory1);
        m.create_link_hotel_service_hotelService(hotel1, s1, hs1);

        boolean studentOutput = m.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }

    public static String ClientTest_case1() {
        boolean expectedOutput = false; // community service instead vip service
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Model m = new Model();


        Client client1 = new Client(1, 23, "Dolev", "Tel Aviv");
        Hotel hotel1 = new Hotel("London", "BPS", 4);
        Room room1 = new Room(404);
        ReservationSet reservationSet1 = new ReservationSet();
        Reservation reservation1 = new Reservation(orDate, reqDate, 100);
        RoomCategory roomCategory1 = new RoomCategory(200, RoomCategory.RoomType.VIP);
        Booking booking = new Booking(reqDate, room1);
        Service s1 = new CommunityService("wifi"); // this is the reason for false
        HotelService hs1 = new HotelService(12, 10);
        Review review = new Review(9, "I love this place!", Model.getDateFromString("02-01-2020"));


        m.addObjectToModel(booking);
        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(reservationSet1);
        m.addObjectToModel(reservation1);
        m.addObjectToModel(room1);
        m.addObjectToModel(review);
        m.addObjectToModel(roomCategory1);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs1);

        m.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet1);
        m.create_link_reservationSet_reservation(reservationSet1, reservation1);
        m.create_link_reservation_roomCategory(reservation1, roomCategory1);
        m.create_link_hotel_room(room1, hotel1);
        m.create_link_room_roomCategory(room1, roomCategory1);
        m.create_link_hotel_service_hotelService(hotel1, s1, hs1);
        m.create_link_reservation_booking(booking, reservation1);
        m.create_link_room_Booking(room1, booking);
        m.create_link_booking_review(booking, review);
        boolean studentOutput = m.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }


    public static String ClientTest_case2() {
        boolean expectedOutput = false;// vip room - no review
        Model m = new Model();
        Client client1 = new Client(1, 23, "Dolev", "Tel Aviv");
        Hotel hotel1 = new Hotel("London", "BPS", 4);
        Room room1 = new Room(404);
        ReservationSet reservationSet1 = new ReservationSet();
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Reservation reservation1 = new Reservation(orDate, reqDate, 100);
        RoomCategory roomCategory1 = new RoomCategory(200, RoomCategory.RoomType.VIP);
        Booking booking = new Booking(reqDate, room1);
        Service s1 = new VipService("Butler Service");
        HotelService hs1 = new HotelService(56, 10);


        m.addObjectToModel(booking);
        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(reservationSet1);
        m.addObjectToModel(reservation1);
        m.addObjectToModel(room1);
        m.addObjectToModel(roomCategory1);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs1);


        m.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet1);
        m.create_link_reservationSet_reservation(reservationSet1, reservation1);
        m.create_link_reservation_roomCategory(reservation1, roomCategory1);
        m.create_link_hotel_room(room1, hotel1);
        m.create_link_room_roomCategory(room1, roomCategory1);
        m.create_link_hotel_service_hotelService(hotel1, s1, hs1);
        m.create_link_reservation_booking(booking, reservation1);
        m.create_link_room_Booking(room1, booking);
        boolean studentOutput = m.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }


    public static String bookinTest_case0() {
        boolean expectedOutput = true;
        Model m = new Model();
        Client client1 = new Client(1, 23, "Dolev", "Tel Aviv");
        Hotel hotel1 = new Hotel("London", "BPS", 4);
        Room room1 = new Room(404);
        ReservationSet reservationSet1 = new ReservationSet();
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Reservation reservation1 = new Reservation(orDate, reqDate, 100);
        RoomCategory roomCategory1 = new RoomCategory(200, RoomCategory.RoomType.BASIC);
        Booking booking = new Booking(reqDate, room1);
        Service s1 = new VipService("Butler Service");
        HotelService hs1 = new HotelService(56, 10);


        m.addObjectToModel(booking);
        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(reservationSet1);
        m.addObjectToModel(reservation1);
        m.addObjectToModel(room1);
        m.addObjectToModel(roomCategory1);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs1);


        m.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet1);
        m.create_link_reservationSet_reservation(reservationSet1, reservation1);
        m.create_link_reservation_roomCategory(reservation1, roomCategory1);
        m.create_link_hotel_room(room1, hotel1);
        m.create_link_room_roomCategory(room1, roomCategory1);
        m.create_link_hotel_service_hotelService(hotel1, s1, hs1);
        m.create_link_reservation_booking(booking, reservation1);
        m.create_link_room_Booking(room1, booking);
        boolean studentOutput = m.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }


    public static String bookinTest_case1() {
        boolean expectedOutput = false; // the client asked for vip room and got basic type
        Model m = new Model();
        Client client1 = new Client(1, 23, "Dolev", "Tel Aviv");
        Hotel hotel1 = new Hotel("London", "BPS", 4);
        Room room1 = new Room(404);
        ReservationSet reservationSet1 = new ReservationSet();
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Reservation reservation1 = new Reservation(orDate, reqDate, 100);
        RoomCategory roomCategory1 = new RoomCategory(800, RoomCategory.RoomType.VIP);
        RoomCategory roomCategory2 = new RoomCategory(200, RoomCategory.RoomType.BASIC);
        Booking booking = new Booking(reqDate, room1);
        Service s1 = new VipService("Butler Service");
        HotelService hs1 = new HotelService(56, 10);


        m.addObjectToModel(booking);
        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(reservationSet1);
        m.addObjectToModel(reservation1);
        m.addObjectToModel(room1);
        m.addObjectToModel(roomCategory1);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs1);


        m.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet1);
        m.create_link_reservationSet_reservation(reservationSet1, reservation1);
        m.create_link_reservation_roomCategory(reservation1, roomCategory1);
        m.create_link_hotel_room(room1, hotel1);
        m.create_link_room_roomCategory(room1, roomCategory2);
        m.create_link_hotel_service_hotelService(hotel1, s1, hs1);
        m.create_link_reservation_booking(booking, reservation1);
        m.create_link_room_Booking(room1, booking);
        boolean studentOutput = m.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }


    public static String bookinTest_case2() {
        boolean expectedOutput = true;
        Model m = new Model();
        Client client1 = new Client(1, 23, "Dolev", "Tel Aviv");
        Hotel hotel1 = new Hotel("London", "BPS", 4);
        Room room1 = new Room(404);
        Room room12 = new Room(405);
        Room room13 = new Room(406);
        Room room14 = new Room(407);
        Room room15 = new Room(408);
        Room room16 = new Room(409);
        Room room17 = new Room(410);
        Room room18 = new Room(411);
        Room room19 = new Room(412);
        Room room20 = new Room(413);
        ReservationSet reservationSet1 = new ReservationSet();
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Reservation reservation1 = new Reservation(orDate, reqDate, 100);
        RoomCategory roomCategory1 = new RoomCategory(200, RoomCategory.RoomType.BASIC);
        RoomCategory roomCategory2 = new RoomCategory(800, RoomCategory.RoomType.VIP);
        Booking booking = new Booking(reqDate, room1);
        Service s1 = new VipService("Butler Service");
        HotelService hs1 = new HotelService(56, 10);


        m.addObjectToModel(booking);
        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(reservationSet1);
        m.addObjectToModel(reservation1);
        m.addObjectToModel(room1);
        m.addObjectToModel(roomCategory1);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs1);
        m.addObjectToModel(room12);
        m.addObjectToModel(room13);
        m.addObjectToModel(room14);
        m.addObjectToModel(room15);
        m.addObjectToModel(room16);
        m.addObjectToModel(room17);
        m.addObjectToModel(room18);
        m.addObjectToModel(room19);
        m.addObjectToModel(room20);

        m.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet1);
        m.create_link_reservationSet_reservation(reservationSet1, reservation1);
        m.create_link_reservation_roomCategory(reservation1, roomCategory1);
        m.create_link_hotel_room(room1, hotel1);
        m.create_link_hotel_room(room12, hotel1);
        m.create_link_hotel_room(room13, hotel1);
        m.create_link_hotel_room(room14, hotel1);
        m.create_link_hotel_room(room15, hotel1);
        m.create_link_hotel_room(room16, hotel1);
        m.create_link_hotel_room(room17, hotel1);
        m.create_link_hotel_room(room18, hotel1);
        m.create_link_hotel_room(room19, hotel1);
        m.create_link_hotel_room(room20, hotel1);
        m.create_link_room_roomCategory(room1, roomCategory2);
        m.create_link_room_roomCategory(room12, roomCategory1);
        m.create_link_room_roomCategory(room13, roomCategory1);
        m.create_link_room_roomCategory(room14, roomCategory1);
        m.create_link_room_roomCategory(room15, roomCategory1);
        m.create_link_room_roomCategory(room16, roomCategory1);
        m.create_link_room_roomCategory(room17, roomCategory1);
        m.create_link_room_roomCategory(room18, roomCategory1);
        m.create_link_room_roomCategory(room19, roomCategory1);
        m.create_link_room_roomCategory(room20, roomCategory1);
        m.create_link_hotel_service_hotelService(hotel1, s1, hs1);
        m.create_link_reservation_booking(booking, reservation1);
        m.create_link_room_Booking(room1, booking);
        boolean studentOutput = m.checkModelConstraints();
        return (expectedOutput == studentOutput) ? "PASS" : "FAIL - Output: " + studentOutput;
    }

}