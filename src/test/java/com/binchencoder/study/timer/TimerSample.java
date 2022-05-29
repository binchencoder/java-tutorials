package com.binchencoder.study.timer;

import java.util.Timer;
import java.util.TimerTask;
import org.junit.jupiter.api.Test;

public class TimerSample {

  @Test
  public void timerTest() {
    Timer timer = new Timer(false);
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        System.out.println("Come in: " + System.currentTimeMillis());
      }
    }, 0, 5 * 1000);

//    while (true) {
//      timer.scheduleAtFixedRate(new TimerTask() {
//        @Override
//        public void run() {
//          System.out.println("Come in: " + System.currentTimeMillis());
//        }
//      }, 0, 5 * 1000);
//    }

    timer.cancel();
  }
}
