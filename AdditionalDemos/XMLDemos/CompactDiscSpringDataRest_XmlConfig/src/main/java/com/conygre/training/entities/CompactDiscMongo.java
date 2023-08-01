// This entity class uses annotations instead of the mapping XML file

package com.conygre.training.entities;

import java.io.Serializable;

import javax.persistence.Id;


public class CompactDiscMongo implements Serializable {

	
	@Id
	private String id;

	// add attributes for all the remaining properties
	private String title;
	private String artist;
	private Double price;
	private Integer tracks;

	public CompactDiscMongo() {}

	public CompactDiscMongo(String t, double p,String a, int tr){
	    title=t;
	    price=p;
	    artist=a;
	    tracks=tr;
	    
	  }

	public void setId(String id) {
		this.id = id;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTracks() {
		return tracks;
	}

	public void setTracks(Integer tracks) {
		this.tracks = tracks;
	}

	public String getId() {
		return id;
	}
}
