package com.binchencoder.study.eventlistener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class MySource {

  private int status;
  List<EventListener> eventListeners = new ArrayList<>();

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void addListener(EventListener listener) {
    eventListeners.add(listener);
  }

  // 调用所有的合适的监听器
  public void notifyListeners(int oldStatus, int newStatus) {
    eventListeners.forEach(l -> {
      if (oldStatus == newStatus) {
        // doSamething
      } else {
        // doSamething
      }
    });
  }
}
