// This entity class uses annotations instead of the mapping XML file

package com.conygre.training.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;


// add an annotations specifying the table that this will map to
@Entity @Table(name="compact_discs")

// Adding caching
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

// ensure that the class implements Serializable

@NamedQueries(
		{
			@NamedQuery(name="compactdisc.getAll", query="from CompactDisc where price > :price")
		})
public class CompactDisc implements Serializable {

	
	
	
	

	// add an attribute specifying a column for the id property
	// add attributes to ensure that the id column is automantically generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	// add attributes for all the remaining properties
	@Column(name="title") private String title;
	@Column(name="artist") private String artist;
	@Column(name="price") private double price;
	@Column(name="tracks") private int tracks;

	public CompactDisc() {}

	public CompactDisc(String t, double p,String a, int tr){
	    title=t;
	    price=p;
	    artist=a;
	    tracks=tr;
	    
	  }

	public void setId(int id) {
		this.id = id;
	}
	
	public void addTrack(Track t) {
		t.setDisc(this);
		trackTitles.add(t);
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTracks() {
		return tracks;
	}

	public void setTracks(int tracks) {
		this.tracks = tracks;
	}

	public int getId() {
		return id;
	}

	// Adding relationships 
	@OneToMany(mappedBy="cdId", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Track> trackTitles = new HashSet<Track>();

	public Set<Track> getTrackTitles() {
		return trackTitles;
	}

	public void setTrackTitles(Set<Track> trackTitles) {
		this.trackTitles = trackTitles;
	}



}
