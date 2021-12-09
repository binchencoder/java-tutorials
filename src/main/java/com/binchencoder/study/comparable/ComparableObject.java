package com.binchencoder.study.comparable;

/**
 * @author: chenbin
 * @date: 2021/12/8 下午12:27
 */
public class ComparableObject implements Comparable<ComparableObject> {

  private Float score;

  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }

  public ComparableObject(Float score) {
    this.score = score;
  }

  @Override
  public int compareTo(ComparableObject o) {
    if (this.getScore() > o.getScore()) {
      return 1;
    } else if (this.getScore() < o.getScore()) {
      return -1;
    }
    return 0;
  }
}
