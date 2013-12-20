package vacation.DAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vacation.DAL.DBSqlScript;
import vacation.model.*;

public class StaticDataHandler {

	/**
	 * Get all the countries from the DB
	 * @return All the countries
	 */
	public List<Country> GetAllCountries()
	{
		List<Country> countries = new ArrayList<Country>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM countries";
		
		try (java.sql.Statement st = conn.createStatement()) {
			ResultSet resultSet = st.executeQuery(sql);

			while (resultSet.next()) {
								
				Country currentCountry = new Country(resultSet.getInt("ID"), resultSet.getString("Name"));
				countries.add(currentCountry);
			}
			
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return countries;
	}
	
	/**
	 * Get all the airports from the DB
	 * @return All the airports
	 */
	public List<Airport> GetAllAirports()
	{
		List<Airport> airports = new ArrayList<Airport>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM airports";
		
		try (java.sql.Statement st = conn.createStatement()) {
			ResultSet resultSet = st.executeQuery(sql);

			while (resultSet.next()) {
								
				Airport currentAirport = new Airport(resultSet.getInt("ID"),
						resultSet.getInt("City_ID"), resultSet.getString("Name"));
				airports.add(currentAirport);
			}
			
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return airports;
	}
	
	/**
	 * Get all the cities from the DB
	 * @return All the cities
	 */
	public List<City> GetAllCities()
	{
		List<City> cities = new ArrayList<City>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM cities";
		
		try (java.sql.Statement st = conn.createStatement()) {
			ResultSet resultSet = st.executeQuery(sql);

			while (resultSet.next()) {
								
				City currentCity = new City(resultSet.getInt("ID"),
						resultSet.getInt("Country_ID"), resultSet.getString("Name"));
				cities.add(currentCity);
			}
			
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return cities;
	}
	
	public static int GetNextID(String tableName)
	{
		Connection conn = DBConn.getConnection();
		String sql = "SELECT Auto_increment FROM information_schema.tables WHERE table_name=?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setString(1, tableName);
			ResultSet rs = st.executeQuery();
			rs.next();
			Object o = rs.getObject(1);
			return Integer.parseInt(o.toString());
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		
		return -1;
	}
	
	public static void BuildDB()
	{
		Connection conn = DBConn.getConnection();
		String script = 
				"/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */; \r\n" +
				"/*!40101 SET NAMES utf8 */; \r\n" +
				"/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */; \r\n" +
				"/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */; \r\n" +
				
				"-- Dumping database structure for nofson \r\n" +
				"DROP DATABASE IF EXISTS `nofson`; \r\n" +
				"CREATE DATABASE IF NOT EXISTS `nofson` /*!40100 DEFAULT CHARACTER SET hebrew */; \r\n" +
				"USE `nofson`; \r\n" +
				
				
				"-- Dumping structure for table nofson.airlines \r\n" +
				"DROP TABLE IF EXISTS `airlines`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `airlines` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  " PRIMARY KEY (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.airports \r\n" +
				"DROP TABLE IF EXISTS `airports`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `airports` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `City_ID` int(50) DEFAULT NULL, \r\n" +
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`), \r\n" +
				  "KEY `FK_airports_cities` (`City_ID`), \r\n" +
				  "CONSTRAINT `FK_airports_cities` FOREIGN KEY (`City_ID`) REFERENCES `cities` (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.bookings \r\n" +
				"DROP TABLE IF EXISTS `bookings`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `bookings` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `Order_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Depart_Flight_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Return_Flight_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Hotel_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Number_Of_Nights` int(11) DEFAULT NULL, \r\n" +
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  " `Passport_ID` int(11) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`), \r\n" +
				  "KEY `FK_Bookings_Hotels` (`Hotel_ID`), \r\n" +
				  "KEY `FK_Bookings_Flights_Depart` (`Depart_Flight_ID`), \r\n" +
				  "KEY `FK_Bookings_Flight_Return` (`Return_Flight_ID`), \r\n" +
				  "KEY `FK_bookings_orders` (`Order_ID`), \r\n" +
				  "CONSTRAINT `FK_Bookings_Hotels` FOREIGN KEY (`Hotel_ID`) REFERENCES `hotels` (`ID`), \r\n" +
				  "CONSTRAINT `FK_Bookings_Flights_Depart` FOREIGN KEY (`Depart_Flight_ID`) REFERENCES `flights` (`ID`), \r\n" +
				  "CONSTRAINT `FK_Bookings_Flight_Return` FOREIGN KEY (`Return_Flight_ID`) REFERENCES `flights` (`ID`), \r\n" +
				  "CONSTRAINT `FK_bookings_orders` FOREIGN KEY (`Order_ID`) REFERENCES `orders` (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.cities \r\n" +
				"DROP TABLE IF EXISTS `cities`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `cities` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `Country_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`), \r\n" +
				  "KEY `FK_cities_countries` (`Country_ID`), \r\n" +
				  "CONSTRAINT `FK_cities_countries` FOREIGN KEY (`Country_ID`) REFERENCES `countries` (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				 
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.countries \r\n" +
				"DROP TABLE IF EXISTS `countries`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `countries` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" + 
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.flights \r\n" +
				"DROP TABLE IF EXISTS `flights`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `flights` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `Departure_Time` datetime DEFAULT NULL, \r\n" +
				  " `Arrival_Time` datetime DEFAULT NULL, \r\n" +
				  " `From_Airport` int(11) DEFAULT NULL, \r\n" +
				  " `To_Airport` int(11) DEFAULT NULL, \r\n" +
				  " `Aireline_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Cost` int(11) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`), \r\n" +
				  "KEY `FK_Flights_Airlines` (`Aireline_ID`), \r\n" +
				  "KEY `FK_flights_airports_From` (`From_Airport`), \r\n" +
				  "KEY `FK_flights_airports_to` (`To_Airport`), \r\n" +
				  "CONSTRAINT `FK_Flights_Airlines` FOREIGN KEY (`Aireline_ID`) REFERENCES `airlines` (`ID`), \r\n" +
				  "CONSTRAINT `FK_flights_airports_From` FOREIGN KEY (`From_Airport`) REFERENCES `airports` (`ID`), \r\n" +
				  "CONSTRAINT `FK_flights_airports_to` FOREIGN KEY (`To_Airport`) REFERENCES `airports` (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.hotels \r\n" +
				"DROP TABLE IF EXISTS `hotels`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `hotels` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  " `City_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Address` varchar(50) DEFAULT NULL, \r\n" +
				  " `Phone` varchar(20) DEFAULT NULL, \r\n" +
				  " `Cost` int(11) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.orders \r\n" +
				"DROP TABLE IF EXISTS `orders`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `orders` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `User_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Method_ID` int(11) DEFAULT NULL, \r\n" + 
				  " `Order_Time` datetime DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`), \r\n" +
				  "KEY `FK_orders_users` (`User_ID`), \r\n" +
				  "KEY `FK_orders_payment_methods` (`Method_ID`), \r\n" +
				  "CONSTRAINT `FK_orders_users` FOREIGN KEY (`User_ID`) REFERENCES `users` (`ID`), \r\n" +
				  "CONSTRAINT `FK_orders_payment_methods` FOREIGN KEY (`Method_ID`) REFERENCES `payment_methods` (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.payment_methods \r\n" +
				"DROP TABLE IF EXISTS `payment_methods`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `payment_methods` ( \r\n" +
				  " `ID` int(11) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `Name` varchar(50) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				
				
				"-- Dumping structure for table nofson.users \r\n" +
				"DROP TABLE IF EXISTS `users`; \r\n" +
				"CREATE TABLE IF NOT EXISTS `users` ( \r\n" +
				  " `ID` int(50) NOT NULL AUTO_INCREMENT, \r\n" +
				  " `UserName` varchar(50) DEFAULT NULL, \r\n" +
				  " `Password` varchar(50) DEFAULT NULL, \r\n" +
				  " `Registration_Date` date DEFAULT NULL, \r\n" +
				  " `First_Name` varchar(50) DEFAULT NULL, \r\n" +
				  " `Last_Name` varchar(50) DEFAULT NULL, \r\n" +
				  " `Address` varchar(50) DEFAULT NULL, \r\n" +
				  " `Email` varchar(50) DEFAULT NULL, \r\n" +
				  " `Phone` varchar(50) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`) \r\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=hebrew; \r\n" +
				
				"-- Data exporting was unselected. \r\n" +
				"/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */; \r\n" +
				"/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */; \r\n" +
				"/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;";
		
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public static void InitUsers()
	{
		Connection conn = DBConn.getConnection();
		String script = "#Users \r\n" +
						"#====== \r\n" +
						"insert into users values (1, 'Lior1989', '123456', CURRENT_DATE(), 'Lior', 'Yaffe', 'Bialik 96 Ramat-Gan', 'lieo@walla.co.il', '0501234567'); \r\n" +
						"insert into users values (2, 'LiorMa', 'Aa123456', CURRENT_DATE(), 'Lior', 'Matityahu', 'Petach-Tikva', 'lala@gmail.com', '05098721234');";
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public static void InitPaymentMethods()
	{
		Connection conn = DBConn.getConnection();
		String script = "#Payment_Methods \r\n" +
					"#============ \r\n" +
				"insert into payment_methods value (1, 'Credit card'); \r\n" +
				"insert into countries values(1,'USA'); \r\n" +
				"insert into payment_methods value (2, 'Cash'); \r\n" +
				"insert into payment_methods value (3, 'Paypal');";
		
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public static void InitCountries()
	{
		Connection conn = DBConn.getConnection();
		String script = "#Countries \r\n" +
				"#============ \r\n" +
				"insert into countries values(1,'USA'); \r\n" +
				"insert into countries values(2,'Israel'); \r\n" +
				"insert into countries values(3,'Italy'); \r\n" +
				"insert into countries values(4,'Germany'); \r\n" +
				"insert into countries values(5,'Turkey'); \r\n" +
				"insert into countries values(6,'Greece'); \r\n" +
				"insert into countries values(7,'France'); \r\n" +
				"insert into countries values(8,'Spain'); \r\n" +
				"insert into countries values(9,'Mexico'); \r\n" +
				"insert into countries values(10,'Japan');";
		
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public static void InitCities()
	{
		Connection conn = DBConn.getConnection();
		String script = 	"#Cities \r\n" +
				"#=========== \r\n" +
				"insert into cities values(1,1,'New-York'); \r\n" +
				"insert into cities values(2,1,'Boston'); \r\n" +
				"insert into cities values(3,2,'Tel-Aviv'); \r\n" +
				"insert into cities values(4,3,'Rome'); \r\n" +
				"insert into cities values(5,4,'Munich'); \r\n" + 
				"insert into cities values(6,4,'Berlin'); \r\n" +
				"insert into cities values(7,5,'Istanbul'); \r\n" +
				"insert into cities values(8,6,'Athens'); \r\n" +
				"insert into cities values(9,7,'Paris'); \r\n" +
				"insert into cities values(10,8,'Madrid'); \r\n" +
				"insert into cities values(11,8,'Barcelona'); \r\n" + 
				"insert into cities values(12,9,'Mexico City'); \r\n" +
				"insert into cities values(13,10,'Tokyo');";
		
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public static void InitHotels()
	{
		Connection conn = DBConn.getConnection();
		String script = 	"#Hotels \r\n" +
				"#============ \r\n" +
				"insert into hotels values (1,'Holiday Inn', 3, 'Hayarkon 92', '03-5812342',300); \r\n" +
				"insert into hotels values (2,'Sheraton', 3, 'Tel-Aviv', '03-23024245', 370); \r\n" +
				"insert into hotels values (3, 'Crowne Plaza Berlin',6,'Berlin', '329342842', 450);";
		
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	
	public static void InitData()
	{
		String script = "#Users \r\n" +
						"#====== \r\n" +
						"insert into users values (1, 'Lior1989', '123456', CURRENT_DATE(), 'Lior', 'Yaffe', 'Bialik 96 Ramat-Gan', 'lieo@walla.co.il', '0501234567'); \r\n" +
						"insert into users values (2, 'LiorMa', 'Aa123456', CURRENT_DATE(), 'Lior', 'Matityahu', 'Petach-Tikva', 'lala@gmail.com', '05098721234'); \r\n" +
						
						"#Payment_Methods \r\n" +
						"#============ \r\n" +
						"insert into payment_methods value (1, 'Credit card'); \r\n" +
						"insert into countries values(1,'USA'); \r\n" +
						"insert into payment_methods value (2, 'Cash'); \r\n" +
						"insert into payment_methods value (3, 'Paypal'); \r\n" +
						
						"#Countries \r\n" +
						"#============ \r\n" +
						"insert into countries values(1,'USA'); \r\n" +
						"insert into countries values(2,'Israel'); \r\n" +
						"insert into countries values(3,'Italy'); \r\n" +
						"insert into countries values(4,'Germany'); \r\n" +
						"insert into countries values(5,'Turkey'); \r\n" +
						"insert into countries values(6,'Greece'); \r\n" +
						"insert into countries values(7,'France'); \r\n" +
						"insert into countries values(8,'Spain'); \r\n" +
						"insert into countries values(9,'Mexico'); \r\n" +
						"insert into countries values(10,'Japan'); \r\n" +
						
						"#Cities \r\n" +
						"#=========== \r\n" +
						"insert into cities values(1,1,'New-York'); \r\n" +
						"insert into cities values(2,1,'Boston'); \r\n" +
						"insert into cities values(3,2,'Tel-Aviv'); \r\n" +
						"insert into cities values(4,3,'Rome'); \r\n" +
						"insert into cities values(5,4,'Munich'); \r\n" + 
						"insert into cities values(6,4,'Berlin'); \r\n" +
						"insert into cities values(7,5,'Istanbul'); \r\n" +
						"insert into cities values(8,6,'Athens'); \r\n" +
						"insert into cities values(9,7,'Paris'); \r\n" +
						"insert into cities values(10,8,'Madrid'); \r\n" +
						"insert into cities values(11,8,'Barcelona'); \r\n" + 
						"insert into cities values(12,9,'Mexico City'); \r\n" +
						"insert into cities values(13,10,'Tokyo'); \r\n" +
						
						"#Hotels \r\n" +
						"#============ \r\n" +
						"insert into hotels values (1,'Holiday Inn', 3, 'Hayarkon 92', '03-5812342',300); \r\n" +
						"insert into hotels values (2,'Sheraton', 3, 'Tel-Aviv', '03-23024245', 370); \r\n" +
						"insert into hotels values (3, 'Crowne Plaza Berlin',6,'Berlin', '329342842', 450); \r\n" +
						
						
						"#Airports \r\n" +
						"#=========== \r\n" +
						"insert into airports values(1,3,'Ben-Gurion airport'); \r\n" +
						"insert into airports values(2,1,'JFK airport'); \r\n" +
						
						"#Airlines \r\n" +
						"#============ \r\n" +
						"insert into airlines values (1,'El-AL'); \r\n" +
						"insert into airlines values (2,'Delta'); \r\n" + 
						"insert into airlines values (3,'Alitalia'); \r\n" +
						"insert into airlines values (4,'Air France'); \r\n" +
						
						"#Flights \r\n" +
						"#============ \r\n" +
						"insert into flights values(1,STR_TO_DATE('2-12-2013', '%d-%m-%Y'), STR_TO_DATE('3-12-2013', '%d-%m-%Y'),1,2,1,950); \r\n" +
						"insert into flights values(2,STR_TO_DATE('9-12-2013', '%d-%m-%Y'), STR_TO_DATE('10-12-2013', '%d-%m-%Y'),2,1,1,950);  \r\n" +
						"insert into flights values(3,STR_TO_DATE('4-12-2013', '%d-%m-%Y'), STR_TO_DATE('4-12-2013', '%d-%m-%Y'),1,2,2,800); \r\n" + 
						"insert into flights values(4,STR_TO_DATE('10-12-2013', '%d-%m-%Y'), STR_TO_DATE('11-12-2013', '%d-%m-%Y'),2,1,2,800); \r\n" +
						
						"#Orders \r\n" +
						"#============= \r\n" +
						"insert into orders values(1,1,3,STR_TO_DATE('30-11-2013', '%d-%m-%Y')); \r\n" +
						"insert into orders values(2,2,1,STR_TO_DATE('3-12-2013', '%d-%m-%Y')); \r\n" +
						
						"#Bookings \r\n" +
						"#======= \r\n" +
						"insert into bookings values(1,1,1,2,1,7,'Lior Yaffe',123456789); \r\n" +
						"insert into bookings values(2,1,1,2,1,7,'Ploni Almoni',3493432492);";
		
		Connection conn = DBConn.getConnection();
		DBSqlScript dbs = new DBSqlScript(conn, false, true);
		try {
			dbs.runScript(new BufferedReader(new StringReader(script)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
}
