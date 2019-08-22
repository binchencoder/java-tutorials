package com.binchencoder.study.locks.synchronize;

public class SleepThread implements Runnable {

  private Service service;

  public SleepThread(Service service) {
    this.service = service;
  }

  @Override
  public void run() {
    service.mSleep();
  }
}
