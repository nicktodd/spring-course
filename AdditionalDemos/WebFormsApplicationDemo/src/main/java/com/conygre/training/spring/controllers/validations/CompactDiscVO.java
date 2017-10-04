// This entity class uses annotations instead of the mapping XML file

package com.conygre.training.spring.controllers.validations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.Range;


public class CompactDiscVO implements Serializable {

	private int id;

	@NotNull
	@Size(min=1, max=40)
	private String title;

	@NotNull
	@Size(min=1, max=40)
	private String artist;

	@Range(min=0, max=100)
	private double price;

	@Range(min=0, max=50)
	private int tracks;

	public CompactDiscVO() {
	}

	public CompactDiscVO(String t, double p, String a, int tr) {
		title = t;
		price = p;
		artist = a;
		tracks = tr;

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

	public void setId(int id) {
		this.id = id;
	}

}
