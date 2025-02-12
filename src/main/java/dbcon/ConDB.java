package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConDB {

    public Connection getCon() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection con = DriverManager.getConnection("jdbc:derby:C:\\Users\\suhas\\PMS;create=true;upgrade=true");
        return con;
    }

    // Method to create Customer_Registration table
    public void createTable() {
        String sql = "CREATE TABLE Customer_Registration (" +
                     "Username VARCHAR(20), " +
                     "CustomerName VARCHAR(20), " +
                     "Email VARCHAR(30), " +
                     "Code VARCHAR(10), " +
                     "Mobno VARCHAR(15), " +
                     "Address VARCHAR(100), " +
                     "UserId VARCHAR(20), " +
                     "Password VARCHAR(255), " +  // Increased length for hashed passwords
                     "Preferance VARCHAR(50))";

        try (Connection con = getCon(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table Customer_Registration created successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            // If table already exists, ignore error
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
    public void createParcelBookingTable() {
        String sql = "CREATE TABLE Parcel_Booking (" +
                     "booking_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                     "sender_name VARCHAR(100) NOT NULL, " +
                     "sender_address VARCHAR(255) NOT NULL, " +
                     "sender_contact VARCHAR(15) NOT NULL, " +
                     "receiver_name VARCHAR(100) NOT NULL, " +
                     "receiver_address VARCHAR(255) NOT NULL, " +
                     "receiver_pin_code CHAR(6) NOT NULL, " +
                     "receiver_contact VARCHAR(15) NOT NULL, " +
                     "size_weight DECIMAL(10,2) NOT NULL, " +
                     "contents_description VARCHAR(255) NOT NULL, " +
                     "delivery_speed VARCHAR(20) NOT NULL, " +
                     "packaging VARCHAR(20) NOT NULL, " +
                     "preferred_date DATE NOT NULL, " +
                     "preferred_time TIME NOT NULL, " +
                     "total_cost DECIMAL(10,2) NOT NULL DEFAULT 20.00, " +
                     "payment_method VARCHAR(20) NOT NULL, " +
                     "insurance BOOLEAN DEFAULT FALSE, " +
                     "tracking BOOLEAN DEFAULT FALSE, " +
                     "booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection con = getCon(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table Parcel_Booking created successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table Parcel_Booking already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }


    public void alterParcelBookingTable() throws ClassNotFoundException {
        try (Connection con = getCon();
             Statement stmt = con.createStatement()) {

            // Drop each column in separate statements for Apache Derby
            stmt.executeUpdate("ALTER TABLE Parcel_Booking DROP COLUMN packaging");
            stmt.executeUpdate("ALTER TABLE Parcel_Booking DROP COLUMN insurance");
            stmt.executeUpdate("ALTER TABLE Parcel_Booking DROP COLUMN tracking");

            System.out.println("Columns removed successfully from Parcel_Booking table.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    public void createPaymentTable() {
        String sql = "CREATE TABLE Payment (" +
                     "payment_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                     "booking_id INT, " +  // Foreign key to Parcel_Booking
                     "payment_method VARCHAR(50), " +
                     "card_number VARCHAR(20), " +
                     "card_holder VARCHAR(100), " +
                     "expiry_date VARCHAR(10), " +
                     "cvv VARCHAR(4), " +
                     "amount DECIMAL(10,2), " +
                     "status VARCHAR(10) DEFAULT 'Pending', " +
                     "FOREIGN KEY (booking_id) REFERENCES Parcel_Booking(booking_id))";

        try (Connection con = getCon(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table Payment created successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            // If table already exists, ignore error
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
    public void createPaymentDetailsTable() {
        String sql = "CREATE TABLE Payment_Details (" +
                     "payment_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                     "username VARCHAR(50) NOT NULL, " +
                     "booking_id INT NOT NULL, " +
                     "payment_method VARCHAR(20) NOT NULL, " +
                     "payment_status VARCHAR(20) DEFAULT 'Pending', " +
                     "transaction_id VARCHAR(50), " +
                     "FOREIGN KEY (booking_id) REFERENCES Parcel_Booking(booking_id))";

        try (Connection con = getCon(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table Payment_Details created successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table Payment_Details already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    
    
    
    public void createOfficersTable() {
        String sql = "CREATE TABLE officers (" +
                     "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "username VARCHAR(50) NOT NULL UNIQUE, " +
                     "password VARCHAR(255) NOT NULL)";  // Increased length for hashed passwords

        try (Connection con = getCon(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table officers created successfully!");
            
            // Insert admin credentials
            String insertSql = "INSERT INTO officers (username, password) VALUES ('admin', 'admin')";
            stmt.executeUpdate(insertSql);
            System.out.println("Admin user added successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            // If table already exists, ignore error
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    
    public void createParcelStatusTable2() {
        String sql = "CREATE TABLE Parcel_Status (" +
                     "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "booking_id INT UNIQUE, " +
                     "status VARCHAR(50) DEFAULT 'Delivered', " +
                     "FOREIGN KEY (booking_id) REFERENCES Parcel_Booking(booking_id) ON DELETE CASCADE)";

        try (Connection con = getCon(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table Parcel_Status created successfully!");
            
            // Insert default status for existing bookings
            String insertSql = "INSERT INTO Parcel_Status (booking_id, status) " +
                               "SELECT booking_id, 'Delivered' FROM Parcel_Booking";
            stmt.executeUpdate(insertSql);
            System.out.println("Default status inserted for existing bookings!");
        } catch (SQLException | ClassNotFoundException e) {
            // If table already exists, ignore error
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }
    


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConDB db = new ConDB();
        db.createParcelStatusTable2(); // Call the method to create the table
//        Connection con=db.getCon();
//        if(con!=null) {
//        	System.out.println("Connected");
//        }else {
//        	System.out.println("failed");
//        }
    }
}
