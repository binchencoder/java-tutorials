package com.binchencoder.study.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * Created by chenbin on 18-8-23.
 */
public class RegexTest {

  @Test
  public void testRegexFailed() {
//    String email ="dnjnfslkffkjkjkslioeo9edkdjfks";

    List<String> emails = new ArrayList();
    emails.add("user@domain.com");
    emails.add("user@domain.co.in");
    emails.add("user.name@domain.com");
    emails.add("user_name@domain.com");
    emails.add("username@yahoo.corporate.in");
    emails.add("113423reuteutr@1.cn");

    // Invalid emails
    emails.add(".username@yahoo.com");
    emails.add("username@yahoo.com.");
    emails.add("username@yahoo..com");
    emails.add("username@yahoo.c");
    emails.add("username@yahoo.corporate");

    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    Pattern pattern = Pattern.compile(regex);
    for (String email : emails) {
      Matcher matcher = pattern.matcher(email);
      System.out.println(email + " : " + matcher.matches());
    }
  }
}