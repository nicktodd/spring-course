package com.conygre.spring.beans;

public class Family {

    private Person father;
    private Person mother;
    private Person son;
    private Person daughter;

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getSon() {
        return son;
    }

    public void setSon(Person son) {
        this.son = son;
    }

    public Person getDaughter() {
        return daughter;
    }

    public void setDaughter(Person daughter) {
        this.daughter = daughter;
    }
}
