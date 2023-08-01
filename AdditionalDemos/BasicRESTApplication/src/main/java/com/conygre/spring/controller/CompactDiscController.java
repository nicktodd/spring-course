package com.conygre.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("library")
public class CompactDiscController  {

	private static Map<Integer, CompactDisc> library;
	
	static {
		library = new HashMap<Integer, CompactDisc>();
		library.put(1, new CompactDisc("Gold", 12.99, "Abba", 12, 1));
		library.put(2, new CompactDisc("Spice World", 4.99, "Spice Girls", 9, 2));
		library.put(3, new CompactDisc("Money for Nothing", 7.99, "Dire Straits", 13, 3));
		library.put(4, new CompactDisc("True", 5.99, "Spandau Ballet", 10, 4));
		library.put(5, new CompactDisc("Justin", 4.99, "Justin Bieber", 10, 5));
	}


	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<CompactDisc> getLibrary() {
		return library.values();
	}

	

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public CompactDisc getCompactDiscById(@PathVariable("id") int id) {
		return library.get(id);
	}



	@RequestMapping(value="/with404/{id}", method=RequestMethod.GET)
	public ResponseEntity<CompactDisc> getCompactDiscByIdHandling404(@PathVariable("id") int id) {
		CompactDisc compactDiscToReturn = library.get(id);

		if (compactDiscToReturn ==null) {
			return new ResponseEntity<CompactDisc>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<CompactDisc>(compactDiscToReturn, HttpStatus.OK);
		}
	}



	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deleteCompactDiscById(@PathVariable("id") int id) {
		library.remove(id);
	}


	@RequestMapping(method=RequestMethod.POST,
			consumes="application/json")
	public void addCompactDisc(@RequestBody CompactDisc p) {
		library.put(p.getId(), p);
	}

	
	@RequestMapping(method=RequestMethod.PUT,
			consumes="application/json")
	public void updateCompactDisc(@RequestBody CompactDisc p) {
		if (library.containsKey(p.getId())) {
			library.put(p.getId(), p);
		}

	}


}
class CompactDisc implements Serializable {


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + tracks;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompactDisc other = (CompactDisc) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (tracks != other.tracks)
			return false;
		return true;
	}

	private int id;
	private String title;
	private String artist;
	
	private double price;
	private int tracks;

	public CompactDisc() {}

	public CompactDisc(String t, double p,String a, int tr, int id){
	    title=t;
	    price=p;
	    artist=a;
	    tracks=tr;
        this.id = id;
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


