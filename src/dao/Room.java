package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Vector;

import view.LoginHMS;

public class Room {
    private static Connection c = null;
    private static String[] strings = { "Room Number (*)", "Category", "Occupancy",
            "Availability" };
    public static Vector<String> COLUMNS = new Vector<String>(Arrays.asList(strings));

    private static String[] GRP_HOTELS = { "Room Count ", "Hotel Name", "Availability" };
    public static Vector<String> GRP_HOTELS_COLUMNS = new Vector<String>(Arrays.asList(GRP_HOTELS));

    private static String[] GRP_RTYPE = { "Room Count ", "Room Category", "Availability" };
    public static Vector<String> GRP_RTYPE_COLUMNS = new Vector<String>(Arrays.asList(GRP_RTYPE));

    private static String[] GRP_RCITY = { "Room Count ", "Address/City", "Availability" };
    public static Vector<String> GRP_RCITY_COLUMNS = new Vector<String>(Arrays.asList(GRP_RCITY));

    private static String[] GRP_RDATES = { "Room Count ", "Hotel Name", "Availability" };
    public static Vector<String> GRP_RDATES_COLUMNS = new Vector<String>(Arrays.asList(GRP_RDATES));

    private static String[] TOT_OCCUP = { "Room Count ", "Availability" };
    public static Vector<String> TOT_OCCUP_COLUMNS = new Vector<String>(Arrays.asList(TOT_OCCUP));

    private static String[] PER_OCCUP = { "Hotel Name", "Occupancy Percentage" };
    public static Vector<String> PER_OCCUP_COLUMNS = new Vector<String>(Arrays.asList(PER_OCCUP));

    public static void setConnnection(Connection conn) {
        c = conn;
    }

    public boolean createRoom(int room_num, int hotel_id, String room_category, int occupancy,
            String availability) {

        try {
            PreparedStatement exe = c.prepareStatement(
                    "insert into room(room_num, hotel_id,room_category,occupancy,availability) values(?, ?,?,?,?)");
            exe.setInt(1, room_num);
            exe.setInt(2, hotel_id);
            exe.setString(3, room_category);
            exe.setInt(4, occupancy);
            exe.setString(5, availability);

            exe.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Vector<Vector<Object>> getRoomDetails() {
        Vector<Vector<Object>> data = null;
        try {

            PreparedStatement exe = c.prepareStatement(
                    "Select room_num, room_category,occupancy,availability from room");
            ResultSet result = exe.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Data of the table
            data = new Vector<Vector<Object>>();
            while (result.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(result.getObject(i));
                }
                data.add(vector);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return data;
    }

    public Vector<Vector<Object>> getOccupancyStats(String type, String city) {
        Vector<Vector<Object>> data = null;
        try {

            PreparedStatement exe = null;
            switch (type) {
            case "Occupancy group by all hotels":
                exe = c.prepareStatement(
                        " select count(hotel_id), hotel_name, availability from hotel natural join room where availability = 'available' group by hotel_id");
                break;
            case "Occupancy by room type":
                exe = c.prepareStatement(
                        " select count(hotel_id), room_category, availability from hotel natural join room where availability = 'available' and hotel_id=? group by room_category");
                exe.setInt(1, LoginHMS.hid);
                break;
            case "Occupancy by city":
                exe = c.prepareStatement(
                        "select count(*), hotel_address, availability from hotel nautral join room where availability = 'available' and hotel_address like ? group by hotel_address like ?");
                exe.setString(1, "%" + city + "%");
                exe.setString(2, "%" + city + "%");
                break;
            case "Occupancy by dates":
                exe = c.prepareStatement(
                        " select count(hotel_id), hotel_name, availability from hotel natural join room where availability = 'available' group by hotel_id");
                break;
            case "Total Occupancy":
                exe = c.prepareStatement(
                        "select count(hotel_id), availability from hotel natural join room where hotel_id = ? group by availability");
                exe.setInt(1, LoginHMS.hid);
                break;
            case "% of rooms occupied":
                exe = c.prepareStatement(
                        "select hotel_name, (count(*) * 100/(select count(*) from room where hotel_id = 1)) as percentage from room natural join hotel where hotel_id=1 and availability = 'unavailable'");
                break;
            }

            ResultSet result = exe.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Data of the table
            data = new Vector<Vector<Object>>();
            while (result.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(result.getObject(i));
                }
                data.add(vector);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return data;
    }
}