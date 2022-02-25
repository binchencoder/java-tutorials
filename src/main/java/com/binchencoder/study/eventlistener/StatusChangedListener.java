package com.binchencoder.study.eventlistener;

import java.util.EventListener;

// 状态改变事件
public class StatusChangedListener implements EventListener {

  public void handleEvent(MyEvent event) {
    System.out.println(event.getSource() + " 的状态改变啦~");
  }

}
