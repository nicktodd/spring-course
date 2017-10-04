// this is a regular JavaBean that instances of will be passed
// back to the web application clients
// the alternative is to pass entity bean references back for the CDs,
// but this would mean a substantial overhead in remote reference calls as all the data
// is accessed by the pages

package com.conygre.training.entities;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

//Adding caching
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity @Table(name="tracks")
public class Track implements Serializable {

  //Instance variables
  @Column(name="title") private String title;

  @Column(name="cd_id", insertable=false, updatable=false)
  private int cdId;

  @Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	@Column(name="id")
	private int id;

  //Methods
  public int getId(){
    return id;
  }

  public void setId(int s){
    id = s;
  }

  public void setTitle(String s){
    title = s;
  }


  public String getTitle(){
    return title;
  }

  public int getCdId(){
    return cdId;
  }

  public void setCdId(int id)
  {
	  cdId = id;
  }

  // bidirectional
  @JoinColumn (name="cd_id", referencedColumnName="id")
  @ManyToOne
  private CompactDisc disc;
  

  public CompactDisc getDisc() {
	return disc;
}

public void setDisc(CompactDisc disc) {
	this.disc = disc;
}

//constructors
  public Track(){}

  public Track(int id, int cdId, String title){
    this.title=title;
    this.id = id;
    this.cdId = cdId;
  }
  
  
  public Track(String title) {
	  this.title = title;
  }
}