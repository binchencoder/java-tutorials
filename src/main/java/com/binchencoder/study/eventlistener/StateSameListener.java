package com.binchencoder.study.eventlistener;

import java.util.EventListener;

// 状态没改变事件
public class StateSameListener implements EventListener {

  public void handleEvent(MyEvent event) {
    System.out.println(event.getSource() + " 的状态没有任何变化~");
  }

}
