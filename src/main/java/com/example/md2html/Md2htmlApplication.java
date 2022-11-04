package com.example.md2html;


import java.util.Arrays;

public class Md2htmlApplication {
     public static void main(String[] args) {
          if (args.length != 1) {
               System.out.println("Usage: APP INPUT_FILE.md");
               return;
          }

          // first argument is always the Markdown input file
          String inputFile = args[0];

          MarkdownToHtmlConverter converter = new MarkdownToHtmlConverter();

          String html = converter.convert(inputFile);

          System.out.println(html);
     }
}
