package com.binchencoder.study.eventlistener;

public class Run {

  public static void main(String[] args) {
    MySource mySource = new MySource();
    mySource.addListener(new StatusChangedListener());
    mySource.addListener(new StateSameListener());

    int oldStatus = mySource.getStatus();
    mySource.setStatus(1);
    int newStatus = mySource.getStatus();

    // 触发所有的监听者们
    mySource.notifyListeners(oldStatus, newStatus);
  }

}
