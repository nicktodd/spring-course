// this is a regular JavaBean that instances of will be passed
// back to the web application clients
// the alternative is to pass entity bean references back for the CDs,
// but this would mean a substantial overhead in remote reference calls as all the data
// is accessed by the pages

package com.conygre.spring.boot.entities;
import javax.persistence.*;


import java.io.Serializable;

//Adding caching
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity @Table(name="tracks")
public class Track implements Serializable {

  //Instance variables
  @Column(name="title") private String title;


  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  public int getCdId() {
    return cdId;
  }

  public void setCdId(int cdId) {
    this.cdId = cdId;
  }

  @Column(name="cd_id")
  private int cdId;

  //Methods
  public Integer getId(){
    return id;
  }

  public void setId(Integer s){
    id = s;
  }

  public void setTitle(String s){
    title = s;
  }


  public String getTitle(){
    return title;
  }



  //constructors
  public Track(){}

  public Track(int id,  String title, int cdId){
    this.title=title;
    this.id = id;
    this.cdId = cdId;

  }


  public Track(String title) {
    this.title = title;
  }
}