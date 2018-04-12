package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Vector;

public class Service {
    private static Connection c = null;
    private static String[] strings = { "Service Number (*)", "Service Type" };
    public static Vector<String> COLUMNS = new Vector<String>(Arrays.asList(strings));

    public static void setConnnection(Connection conn) {
        c = conn;
    }

    public boolean addService(int service_num, int hotel_id, String type) {

        try {
            PreparedStatement exe = c.prepareStatement(
                    "insert into service(service_num, hotel_id,type) values(?, ?,?)");
            exe.setInt(1, service_num);
            exe.setInt(2, hotel_id);
            exe.setString(3, type);

            exe.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Vector<Vector<Object>> getServiceDetails(int hid) {
        Vector<Vector<Object>> data = null;
        try {

            PreparedStatement exe = c.prepareStatement(
                    "Select service_num, type from service where hotel_id = ?");
            exe.setInt(1, hid);
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

    public boolean deleteService(int service_num, int hid) {
        try {
            PreparedStatement exe = c.prepareStatement(
                    " Delete from service where service_num = ? and hotel_id=?");
            exe.setInt(1, service_num);
            exe.setInt(2, hid);
            exe.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}
