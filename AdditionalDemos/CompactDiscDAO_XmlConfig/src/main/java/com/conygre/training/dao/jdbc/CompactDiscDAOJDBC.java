package com.conygre.training.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;



import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.conygre.training.dao.CompactDiscDAO;
import com.conygre.training.entities.CompactDisc;

public class CompactDiscDAOJDBC extends JdbcDaoSupport implements CompactDiscDAO{

	static class CompactDiscRowMapper implements RowMapper
	{
		 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	            CompactDisc product = new CompactDisc();
	            product.setTracks(rs.getInt("tracks"));
	            product.setTitle(rs.getString("title"));
	            product.setArtist(rs.getString("artist"));
	            product.setId(rs.getInt("id"));
	            product.setPrice(rs.getDouble("price"));
	            return product;
	      }
	}

	private static String sqlUpdate = "insert into compact_discs (title, artist, price, tracks) values (?,?,?,?)";
	private static String sqlQuery = "select id, title, artist, tracks, price from compact_discs where id = ?";
	
	public void addCompactDisc(CompactDisc cd) {
		
		Object[] args = {cd.getArtist(), cd.getTitle(), cd.getPrice(), cd.getTracks()};
		getJdbcTemplate().update(sqlUpdate, args);
		
	}

	public CompactDisc getCDByID(int id) {

		System.out.println("Using JDBC to get the Data!");
		CompactDisc product = (CompactDisc) 
			getJdbcTemplate().queryForObject(sqlQuery ,
					new Object[]{id},new CompactDiscRowMapper());
		return product;
	}

	public Collection<CompactDisc> getAllDiscs() {
		System.out.println("Using JDBC to get the Data!");
		RowMapperResultSetExtractor extractor = 
					new RowMapperResultSetExtractor(new CompactDiscRowMapper());
		
		Object result = getJdbcTemplate().query("select * from compact_discs",
															new Object[]{}, extractor);
		List<CompactDisc> resultList = (List<CompactDisc>)result;
		return resultList;
	}

	public CompactDisc getCompactDiscByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<CompactDisc> getDiscsByArtist(String artist) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
