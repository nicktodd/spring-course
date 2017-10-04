import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conygre", "root", "c0nygre");
			PreparedStatement stmt = conn.prepareStatement("select title, artist, id from compact_discs where id = ?");
			stmt.setInt(1, 14);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				CompactDisc disc = new CompactDisc();
				disc.setArtist(results.getString("artist"));
				disc.setTitle(results.getString("title"));
				disc.setId(results.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
