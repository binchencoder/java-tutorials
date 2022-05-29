package com.binchencoder.study.tree;

import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 20-12-1.
 */
public class BinaryTreeTest {

  static BinaryTree btree;

  @BeforeClass
  public static void init() {
    btree = new BinaryTree();
    btree.insert(7); // Insert root node
    btree.insert(2);
    btree.insert(3);
    btree.insert(4);
    btree.insert(5);
    btree.insert(8);
  }

  @Test
  public void find() throws Exception {
  }

  @Test
  public void insert() throws Exception {
    Node node = btree.find(6);
    assertNull(node);
  }

  @Test
  public void delete() throws Exception {
  }

  @Test
  public void testInfixOrder() {
    Node node = btree.find(7);
    btree.infixOrder(node);
  }

  @Test
  public void testPreOrder() {
    Node node = btree.find(7);
    btree.preOrder(node);
  }

  @Test
  public void testPostOrder() {
    Node node = btree.find(7);
    btree.postOrder(node);
  }
}