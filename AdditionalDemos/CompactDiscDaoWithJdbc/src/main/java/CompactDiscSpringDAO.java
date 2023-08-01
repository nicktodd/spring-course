import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CompactDiscSpringDAO extends JdbcDaoSupport {
	
	public CompactDisc getCdById(int id) {
		
		CompactDisc disc =  getJdbcTemplate().
				queryForObject("select title, artist, id from compact_discs where id = ?", 
						new Object[] {id}, 
						new CompactDiscRowmapper() );
		return disc;
		
	}
	
	private class CompactDiscRowmapper implements RowMapper<CompactDisc>  {

		public CompactDisc mapRow(ResultSet results, int rowNumber) throws SQLException {
			CompactDisc disc = new CompactDisc();
			disc.setArtist(results.getString("artist"));
			disc.setTitle(results.getString("title"));
			disc.setId(results.getInt("id"));
			return disc;
		}
		
	}
	
	

}
