package com.conygre.training.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by nicktodd on 09/09/2014.
 */
@XmlRootElement // this is required to enable JSON serialization
public class CompactDiscList implements Serializable {

    public Collection<CompactDisc> getDiscCollection() {
        return discCollection;
    }

    public void setDiscCollection(Collection<CompactDisc> discCollection) {
        this.discCollection = discCollection;
    }


    private Collection<CompactDisc> discCollection;




}
