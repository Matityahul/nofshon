package vacation.DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vacation.model.Country;

public class CountriesHandler {
	
	/**
	 * Get all the countries from the DB
	 * @return All the countries
	 */
	public List<Country> GetAllCountries()
	{
		List<Country> Countries = new ArrayList<Country>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM countriies";
		
		try (java.sql.Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			Countries = extractCountriesFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return Countries;
	}

	private static List<Country> extractCountriesFromRS(ResultSet resultSet) throws SQLException {
		List<Country> countries = new ArrayList<Country>();
		
		while (resultSet.next()) {
			int country_id=resultSet.getInt("ID");
			String country_name =resultSet.getString("NAME");
			
			Country country = new Country(country_id, country_name);
			
			countries.add(country);
		}
		
		return countries;
	}
}
