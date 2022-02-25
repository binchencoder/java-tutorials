package com.binchencoder.study.eventlistener;

import java.util.EventObject;

public class MyEvent extends EventObject {

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public MyEvent(Object source) {
    super(source);
  }
}
