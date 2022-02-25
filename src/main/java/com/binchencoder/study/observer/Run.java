package com.binchencoder.study.observer;

public class Run {

  public static void main(String[] args) {
    Person person = new Person("fsx");

    // 来10只猫 观察这个人
    for (int i = 0; i < 10; i++) {
      person.addObserver(new Cat("cat" + i));
    }

    //开始放fish，这时候观察的猫就应该都过来了
    person.giveFish("草鱼");
  }

}
