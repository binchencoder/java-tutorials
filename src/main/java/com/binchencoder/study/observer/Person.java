package com.binchencoder.study.observer;

import java.util.Observable;

public class Person extends Observable {

  public String name;

  public Person(String name) {
    this.name = name;
  }

  public void giveFish(String fishName) {
    setChanged();
    notifyObservers(fishName);
  }
}
