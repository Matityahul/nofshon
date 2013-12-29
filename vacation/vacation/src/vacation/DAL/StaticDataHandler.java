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
	
	public static Airport GetAirportByID(int id)
	{
		Airport airport = null;
		
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM airports where id = ?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			rs.next();
			airport =new Airport(rs.getInt("ID"),
					rs.getInt("City_ID"), rs.getString("Name"));
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return airport;
	}
	
	/**
	 * Get all the airports from the DB
	 * @return All the airports
	 */
	public static List<Airport> GetAllAirports()
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
	
	public static String GetAirportNameByID(int id)
	{
		List<Airport> airports = GetAllAirports();
		for (int i = 0; i < airports.size(); i++)
		{
			if (airports.get(i).get_id() == id)
				return airports.get(i).getName();
		}
		
		return "";
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
				  " `Airline_ID` int(11) DEFAULT NULL, \r\n" +
				  " `Cost` int(11) DEFAULT NULL, \r\n" +
				  "PRIMARY KEY (`ID`), \r\n" +
				  "KEY `FK_Flights_Airlines` (`Airline_ID`), \r\n" +
				  "KEY `FK_flights_airports_From` (`From_Airport`), \r\n" +
				  "KEY `FK_flights_airports_to` (`To_Airport`), \r\n" +
				  "CONSTRAINT `FK_Flights_Airlines` FOREIGN KEY (`Airline_ID`) REFERENCES `airlines` (`ID`), \r\n" +
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
				"insert into hotels values (3, 'Crowne Plaza Berlin',6,'Berlin', '329342842', 450); \r\n" +
				"insert into hotels values (4, 'New York Skyline',1,'725 10th Avenue', '2125863400', 700);";
		
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
		String script = 
						"insert into users values (1, 'Lior1989', '123456', CURRENT_DATE(), 'Lior', 'Yaffe', 'Bialik 96 Ramat-Gan', 'lieo@walla.co.il', '0501234567'); \r\n" +
						"insert into users values (2, 'LiorMa', 'Aa123456', CURRENT_DATE(), 'Lior', 'Matityahu', 'Petach-Tikva', 'lala@gmail.com', '05098721234'); \r\n" +
						
						"insert into payment_methods value (1, 'Credit card'); \r\n" +
						"insert into payment_methods value (2, 'Cash'); \r\n" +
						"insert into payment_methods value (3, 'Paypal'); \r\n" +
						
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

						"insert into hotels values (1,'Holiday Inn', 3, 'Hayarkon 92', '03-5812342',300); \r\n" +
						"insert into hotels values (2,'Sheraton', 3, 'Tel-Aviv', '03-23024245', 370); \r\n" +
						"insert into hotels values (3, 'Crowne Plaza Berlin',6,'Berlin', '329342842', 450); \r\n" +
						"insert into hotels values (4, 'New York Skyline',1,'725 10th Avenue', '2125863400', 700); \r\n" +
					
						"insert into airports values(1,3,'Ben-Gurion airport'); \r\n" +
						"insert into airports values(2,1,'JFK airport'); \r\n" +
						"insert into airports values(3,6,'Schonefeld Airport'); \r\n" +
						
						"insert into airlines values (1,'El-AL'); \r\n" +
						"insert into airlines values (2,'Delta'); \r\n" + 
						"insert into airlines values (3,'Alitalia'); \r\n" +
						"insert into airlines values (4,'Air France'); \r\n" +
						
"insert into flights values(1,STR_TO_DATE('1-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(2,STR_TO_DATE('1-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(3,STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(4,STR_TO_DATE('2-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(5,STR_TO_DATE('2-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" + 

"insert into flights values(6,STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(7,STR_TO_DATE('3-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(8,STR_TO_DATE('3-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(9,STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(10,STR_TO_DATE('4-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(11,STR_TO_DATE('4-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(12,STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(13,STR_TO_DATE('5-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(14,STR_TO_DATE('5-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(15,STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(16,STR_TO_DATE('6-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" + 

"insert into flights values(17,STR_TO_DATE('6-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(18,STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(19,STR_TO_DATE('7-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(20,STR_TO_DATE('7-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(21,STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(22,STR_TO_DATE('8-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(23,STR_TO_DATE('8-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(24,STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(25,STR_TO_DATE('9-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(26,STR_TO_DATE('9-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(27,STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(28,STR_TO_DATE('10-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(29,STR_TO_DATE('10-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(30,STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(31,STR_TO_DATE('11-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(32,STR_TO_DATE('11-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(33,STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(34,STR_TO_DATE('12-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(35,STR_TO_DATE('12-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(36,STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(37,STR_TO_DATE('13-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(38,STR_TO_DATE('13-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(39,STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(40,STR_TO_DATE('14-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(41,STR_TO_DATE('14-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(42,STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(43,STR_TO_DATE('15-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(44,STR_TO_DATE('15-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(45,STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(46,STR_TO_DATE('16-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(47,STR_TO_DATE('16-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(48,STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(49,STR_TO_DATE('17-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(50,STR_TO_DATE('17-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(51,STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(52,STR_TO_DATE('18-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(53,STR_TO_DATE('18-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(54,STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(55,STR_TO_DATE('19-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(56,STR_TO_DATE('19-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(57,STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(58,STR_TO_DATE('20-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(59,STR_TO_DATE('20-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(60,STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(61,STR_TO_DATE('21-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(62,STR_TO_DATE('21-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(63,STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(64,STR_TO_DATE('22-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(65,STR_TO_DATE('22-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(66,STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(67,STR_TO_DATE('23-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(68,STR_TO_DATE('23-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(69,STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(70,STR_TO_DATE('24-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(71,STR_TO_DATE('24-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(72,STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(73,STR_TO_DATE('25-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(74,STR_TO_DATE('25-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(75,STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(76,STR_TO_DATE('26-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(77,STR_TO_DATE('26-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(78,STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(79,STR_TO_DATE('27-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(80,STR_TO_DATE('27-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(81,STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(82,STR_TO_DATE('28-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(83,STR_TO_DATE('28-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(84,STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(85,STR_TO_DATE('29-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950); \r\n" +

"insert into flights values(86,STR_TO_DATE('29-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000); \r\n" +

"insert into flights values(87,STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800); \r\n" +

"insert into flights values(88,STR_TO_DATE('1-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(89,STR_TO_DATE('1-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(90,STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(91,STR_TO_DATE('2-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(92,STR_TO_DATE('2-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(93,STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(94,STR_TO_DATE('3-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(95,STR_TO_DATE('3-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(96,STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(97,STR_TO_DATE('4-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(98,STR_TO_DATE('4-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(99,STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(100,STR_TO_DATE('5-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(101,STR_TO_DATE('5-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(102,STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(103,STR_TO_DATE('6-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(104,STR_TO_DATE('6-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(105,STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(106,STR_TO_DATE('7-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(107,STR_TO_DATE('7-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(108,STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(109,STR_TO_DATE('8-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(110,STR_TO_DATE('8-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(111,STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(112,STR_TO_DATE('9-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(113,STR_TO_DATE('9-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(114,STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(115,STR_TO_DATE('10-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(116,STR_TO_DATE('10-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(117,STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(118,STR_TO_DATE('11-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(119,STR_TO_DATE('11-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(120,STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(121,STR_TO_DATE('12-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(122,STR_TO_DATE('12-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(123,STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(124,STR_TO_DATE('13-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(125,STR_TO_DATE('13-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(126,STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(127,STR_TO_DATE('14-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(128,STR_TO_DATE('14-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(129,STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(130,STR_TO_DATE('15-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(131,STR_TO_DATE('15-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(132,STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(133,STR_TO_DATE('16-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(134,STR_TO_DATE('16-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(135,STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(136,STR_TO_DATE('17-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(137,STR_TO_DATE('17-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(138,STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(139,STR_TO_DATE('18-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(140,STR_TO_DATE('18-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(141,STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(142,STR_TO_DATE('19-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(143,STR_TO_DATE('19-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(144,STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(145,STR_TO_DATE('20-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(146,STR_TO_DATE('20-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(147,STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(148,STR_TO_DATE('21-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(149,STR_TO_DATE('21-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(150,STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(151,STR_TO_DATE('22-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(152,STR_TO_DATE('22-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(153,STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(154,STR_TO_DATE('23-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(155,STR_TO_DATE('23-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(156,STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(157,STR_TO_DATE('24-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(158,STR_TO_DATE('24-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(159,STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(160,STR_TO_DATE('25-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(161,STR_TO_DATE('25-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(162,STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(163,STR_TO_DATE('26-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(164,STR_TO_DATE('26-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(165,STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(166,STR_TO_DATE('27-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(167,STR_TO_DATE('27-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(168,STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(169,STR_TO_DATE('28-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(170,STR_TO_DATE('28-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(171,STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(172,STR_TO_DATE('29-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950); \r\n" +

"insert into flights values(173,STR_TO_DATE('29-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000); \r\n" +

"insert into flights values(174,STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800); \r\n" +

"insert into flights values(175,STR_TO_DATE('1-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(176,STR_TO_DATE('1-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(177,STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(178,STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(179,STR_TO_DATE('2-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(180,STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(181,STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(182,STR_TO_DATE('3-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(183,STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(184,STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(185,STR_TO_DATE('4-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(186,STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(187,STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(188,STR_TO_DATE('5-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(189,STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(190,STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(191,STR_TO_DATE('6-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(192,STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(193,STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(194,STR_TO_DATE('7-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(195,STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(196,STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(197,STR_TO_DATE('8-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(198,STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(199,STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(200,STR_TO_DATE('9-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(201,STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(202,STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(203,STR_TO_DATE('10-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(204,STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(205,STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(206,STR_TO_DATE('11-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(207,STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(208,STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(209,STR_TO_DATE('12-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(210,STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(211,STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(212,STR_TO_DATE('13-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(213,STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(214,STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(215,STR_TO_DATE('14-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(216,STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(217,STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(218,STR_TO_DATE('15-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(219,STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(220,STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(221,STR_TO_DATE('16-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(222,STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(223,STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(224,STR_TO_DATE('17-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(225,STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(226,STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(227,STR_TO_DATE('18-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(228,STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(229,STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(230,STR_TO_DATE('19-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(231,STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(232,STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(233,STR_TO_DATE('20-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(234,STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(235,STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(236,STR_TO_DATE('21-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(237,STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(238,STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(239,STR_TO_DATE('22-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(240,STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(241,STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(242,STR_TO_DATE('23-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(243,STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(244,STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(245,STR_TO_DATE('24-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(246,STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(247,STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(248,STR_TO_DATE('25-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(249,STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(250,STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(251,STR_TO_DATE('26-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(252,STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(253,STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(254,STR_TO_DATE('27-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(255,STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(256,STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(257,STR_TO_DATE('28-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(258,STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(259,STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(260,STR_TO_DATE('29-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(261,STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +

"insert into flights values(262,STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350); \r\n" +

"insert into flights values(263,STR_TO_DATE('30-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450); \r\n" +

"insert into flights values(264,STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300); \r\n" +


"insert into flights values(265,STR_TO_DATE('1-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(266,STR_TO_DATE('1-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(267,STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(268,STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(269,STR_TO_DATE('2-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(270,STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(271,STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(272,STR_TO_DATE('3-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(273,STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(274,STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(275,STR_TO_DATE('4-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(276,STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(277,STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(278,STR_TO_DATE('5-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(279,STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(280,STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(281,STR_TO_DATE('6-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(282,STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(283,STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(284,STR_TO_DATE('7-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(285,STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(286,STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(287,STR_TO_DATE('8-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(288,STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(289,STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(290,STR_TO_DATE('9-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(291,STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(292,STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(293,STR_TO_DATE('10-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(294,STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(295,STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(296,STR_TO_DATE('11-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(297,STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(298,STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(299,STR_TO_DATE('12-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(300,STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(301,STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(302,STR_TO_DATE('13-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(303,STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(304,STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(305,STR_TO_DATE('14-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(306,STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(307,STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(308,STR_TO_DATE('15-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(309,STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(310,STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(311,STR_TO_DATE('16-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(312,STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(313,STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(314,STR_TO_DATE('17-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(315,STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(316,STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(317,STR_TO_DATE('18-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(318,STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(319,STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(320,STR_TO_DATE('19-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(321,STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(322,STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(323,STR_TO_DATE('20-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(324,STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(325,STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(326,STR_TO_DATE('21-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(327,STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(328,STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(329,STR_TO_DATE('22-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(330,STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(331,STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(332,STR_TO_DATE('23-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(333,STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(334,STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(335,STR_TO_DATE('24-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(336,STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(337,STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(338,STR_TO_DATE('25-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(339,STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(340,STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(341,STR_TO_DATE('26-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(342,STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(343,STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(344,STR_TO_DATE('27-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(345,STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(346,STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(347,STR_TO_DATE('28-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(348,STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(349,STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(350,STR_TO_DATE('29-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(351,STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

"insert into flights values(352,STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350); \r\n" +

"insert into flights values(353,STR_TO_DATE('30-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450); \r\n" +

"insert into flights values(354,STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300); \r\n" +

						

						"insert into orders values(1,1,3,STR_TO_DATE('30-11-2013', '%d-%m-%Y')); \r\n" +
						"insert into orders values(2,2,1,STR_TO_DATE('3-12-2013', '%d-%m-%Y')); \r\n" +
						
						"insert into bookings values(1,1,1,2,1,7,'Lior Yaffe',12345); \r\n" +
						"insert into bookings values(2,1,1,2,1,7,'Ploni Almoni',34934);";
		
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
