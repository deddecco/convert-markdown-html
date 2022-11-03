package com.example.md2htrml;
import java.util.StringTokenizer;


public class TokenizerTest {
     public static void main(String[] args) {
          String test = "###this is a test";
          StringTokenizer st = new StringTokenizer(test, "\s\r\t");

          String firstToken = st.nextToken();
          System.out.println(firstToken);

          int countHashtags = countHashtags(firstToken);

          System.out.println("Heading level: " + countHashtags);
          boolean isBold = recognizeBold(firstToken);

     }

     private static boolean recognizeBold(String firstToken) {
          boolean isBold = false;
          if (firstToken.charAt(0) == '*' && firstToken.charAt(1) != '*') {
               isBold = true;
               System.out.println("This text is bold");
          }
          return isBold;
     }

     private static boolean recognizeItalic(String firstToken) {
          boolean isItalic = false;
          if (firstToken.charAt(0) == '*' && firstToken.charAt(1) == '*') {
               isItalic = true;
               System.out.println("This text is italic");
          }
          return isItalic;
     }

     private static int countHashtags(String firstToken) {
          int hashtagCount = 0;
          for (int i = 0; i < firstToken.length(); i++) {
               if (firstToken.charAt(i) == '#') {
                    hashtagCount++;
               }
          }
          return hashtagCount;
     }


     public static String convertHeading(String firstToken) {
          GenerateHTML.Heading heading = new GenerateHTML.Heading();

          return "Hello, World!";
     }
}
