package com.example.md2html;

public class MarkdownToHtmlConverter {
     public String convert(String inputFile) {
          StringBuilder sb = new StringBuilder();

          sb.append("<html>");
          sb.append("  <body>");
          sb.append("    <h1>Hello, World!");

          sb.append("  </body>");
          sb.append("</html>");

          return sb.toString();
     }
}
