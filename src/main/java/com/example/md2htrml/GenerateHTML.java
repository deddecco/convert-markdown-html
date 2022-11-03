package com.example.md2htrml;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class GenerateHTML {

     static class BeginTag {
          public void printBeginTag() {
               System.out.println("""
                         <!DOCTYPE html>
                         <html lang="en">""");
          }
     }

     static class BoldTag {
          public void makeTextBold(String text) {
               System.out.println(getBoldBegin() + text + getBoldEnd());
          }

          public String getBoldBegin() {
               return "<b>";
          }

          public String getBoldEnd() {
               return "</b>";
          }
     }

     static class ItalicTag {
          public void makeTextItalic(String text) {
               System.out.println(getItalicBegin() + text + getItalicEnd());
          }

          public String getItalicBegin() {
               return "<i>";
          }

          public String getItalicEnd() {
               return "</i>";
          }
     }

     static class StrongTag {
          public void makeTextStrong(String text) {
               System.out.println(getStrongBegin() + text + getStrongEnd());
          }

          public String getStrongBegin() {
               return "<strong>";
          }

          public String getStrongEnd() {
               return "</strong>";
          }
     }

     static class BodyTag {
          public void printBodyBeginTag() {
               System.out.println("<body>");
          }

          public void printBodyEndTag() {
               System.out.println("</body>");
          }

     }

     static class MainTag {
          public void printMainBeginTag() {
               System.out.println("<main>");
          }

          public void printMainEndTag() {
               System.out.println("</main>");
          }
     }

     static class UnorderedList {
          ArrayList<String> UL = new ArrayList<>();
          String ULOpenTag = "<ul>";
          String ULCloseTag = "</ul>";

          public void createUL() {
               UL.add(ULOpenTag);
               UL.add(ULCloseTag);
          }

          public void printUL() {
               for (String ulElem : UL) {
                    System.out.println(ulElem);
               }
          }

          public void addToUL(String ULItem) {
               String openTag = "<li>";
               String closeTag = "</li>";
               String element = openTag + "\n" + ULItem + "\n" + closeTag;
               int indexToAdd = UL.indexOf(ULOpenTag) + 1;

               UL.add(indexToAdd, element);
          }
     }

     static class OrderedList {
          ArrayList<String> OL = new ArrayList<>();
          String OLOpenTag = "<ol>";
          String OLCloseTag = "</ol>";

          public void createUL() {
               OL.add(OLOpenTag);
               OL.add(OLCloseTag);
          }

          public void printOL() {
               for (String ulElem : OL) {
                    System.out.println(ulElem);
               }
          }

          public void addToUL(String OLItem) {
               String openTag = "<li>";
               String closeTag = "</li>";
               String element = openTag + "\n" + OLItem + "\n" + closeTag;
               int indexToAdd = OL.indexOf(OLCloseTag) - 1;

               OL.add(indexToAdd, element);
          }
     }

     static class Figure {
          public void printImgTag(String src, String alt, String title, int width, int height) {
               System.out.println("<img src = " + src + " alt= " + alt + " title=" + title + "width= " + width + "height = " + height + ">");
          }

          public void printBeginFigureTag() {
               System.out.println("<figure>");
          }

          public void printEndFigureTag() {
               System.out.println("</figure");
          }

          public void printCaptionInfo(String caption) {
               printBeginFigCaption();
               System.out.println(caption);
               printEndFigCaption();
          }

          public void printBeginFigCaption() {
               System.out.println("<figure>");
          }

          public void printEndFigCaption() {
               System.out.println("</figure>");
          }

          public void printAllInfo(String src, String alt, String title, int width, int height, String caption) {
               printBeginFigureTag();
               printImgTag(src, alt, title, width, height);
               printBeginFigCaption();
               printCaptionInfo(caption);
               printEndFigCaption();
               printEndFigureTag();
          }
     }

     static class AnchorTag {
          public String printBeginAnchor() {
               String beginAnchor = "<a href =";
               System.out.print(beginAnchor);
               return beginAnchor;
          }

          public String printEndAnchor() {
               String endAnchor = "</a> ";
               System.out.print(endAnchor);
               return endAnchor;
          }

          public void printAnchorPhone(int phoneNum, String formattedPhone) {
               System.out.println(printBeginAnchor() + "tel:" + phoneNum + ">" + formattedPhone + printEndAnchor());
          }

          public void printAnchorSection(String sectionName, String formattedSection) {
               System.out.println(printBeginAnchor() + "#" + sectionName + "> " + formattedSection + printEndAnchor());
          }

          public void printAnchorEmail(String emailAddress, String emailCoverMessage) {
               System.out.println(printBeginAnchor() + "mailto:" + emailAddress + "> " + emailCoverMessage + printEndAnchor());
          }
     }

     static class ParagraphTag {

          String beginParaTag = "<p>";
          String endParaTag = "</p>";

          public void printPara(String text) {
               String paragraph = getBeginPara() + text + getEndPara();
               System.out.println(paragraph);
          }

          public String getBeginPara() {
               return beginParaTag;
          }

          public String getEndPara() {
               return endParaTag;
          }
     }

     static class Heading {
          public void printHeadingText(int headingLevel, String headingText) {
               if (headingLevel != 0) {
                    System.out.println("<h" + headingLevel + ">" + headingText + "</h" + headingLevel + ">");
               } else {
                    System.out.println("<strong>" + headingText + "</strong>");
               }
          }

          public void printHeadingBeginTag(int headingLevel) {
               if (headingLevel >= 1 && headingLevel <= 6) {
                    System.out.println("<h" + headingLevel + ">");
               } else {
                    System.out.println("<strong>");
               }
          }

          public void printHeadingEndTag(int headingLevel) {
               if (headingLevel >= 1 && headingLevel <= 6) {
                    System.out.println("</h" + headingLevel + ">");
               } else {
                    System.out.println("</strong>");
               }
          }
     }

     static class Break {
          public void printBreak() {
               System.out.println("<br>");
          }
     }

     static class HorizontalRule {
          public void printHorizontalRule() {
               System.out.println("<hr>");
          }
     }

     static class Table {
          public void printTableBeginTag() {
               System.out.println("<table>");
          }

          public void printTableEndTag() {
               System.out.println("</table>");
          }

          public String printTableRowBeginTag() {
               return "<tr>";
          }

          public String printTableRowEndTag() {
               return "</tr>";
          }

          public void printTableRow(String word, int frequency) {
               System.out.println(printTableDataBeginTag() + word + printTableDataEndTag() + printTableDataBeginTag() + frequency + printTableDataEndTag());
          }

          public String printTableDataBeginTag() {
               return "<td>";
          }

          public String printTableDataEndTag() {
               return "</td>";
          }

          public String getTableHeaderBeginTag() {
               return "<th>";
          }

          public String getTableHeaderEndTag() {
               return "</th>";
          }

          public void printTableDataRow(String dataElem) {
               System.out.println(printTableDataBeginTag() + dataElem + printTableDataEndTag());
          }

          public void printTableDataRow(String dataElem, String dataFormatTag) {
               System.out.print(printTableDataBeginTag() + "<" + dataFormatTag + ">" + dataElem + "</" + dataFormatTag + ">" + printTableDataEndTag());
          }

          public void printTableHeader(String headingText) {
               System.out.println(getTableHeaderBeginTag() + headingText + getTableHeaderEndTag());
          }
     }

     public static void main(String[] args) throws IOException {

          String filePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m10\\BibleFrequencyAnalysis.txt";
          BufferedReader reader = new BufferedReader(new FileReader(filePath));
          String[] tableHeader = reader.readLine().split("\t");
          Table table = new Table();

          ArrayCreator arrayCreator = new ArrayCreator(reader).invoke();
          int numberOfWords = arrayCreator.getNumberOfWords();
          String[][] rows = arrayCreator.getRows();
          String[][] reordered = getReordered(rows);
          int[] headingsAssigned = populateHeadingsAssigned(numberOfWords, reordered);
          List<Integer> boundaryList = populateBoundaryList(headingsAssigned);

          printHeadings(reordered, headingsAssigned, boundaryList);
     }

     private static void printHeadings(String[][] reordered, int[] headingsAssigned, List<Integer> boundaryList) {
          Heading heading = new Heading();
          heading.printHeadingBeginTag(6);
          for (int i = 0; i < reordered.length - 1; i++) {
               if (boundaryList.contains(i) && boundaryList.contains(i + 1)) {
                    System.out.println(reordered[i][0]);
                    heading.printHeadingEndTag(headingsAssigned[i]);
                    heading.printHeadingBeginTag(headingsAssigned[i + 1]);
               } else {
                    System.out.println(reordered[i][0]);
               }
          }
          heading.printHeadingEndTag(6);
     }

     private static String[][] getReordered(String[][] rows) {
          Comparator<String[]> comparator = Comparator.comparingInt(o -> Integer.parseInt(o[1]));
          Arrays.sort(rows, comparator);
          Collections.reverse(Arrays.asList(rows));
          SupremeCourtOrdering sco = new SupremeCourtOrdering();
          return sco.reorder(rows);
     }

     @NotNull
     private static int[] populateHeadingsAssigned(int numberOfWords, String[][] reordered) {
          int gapSize = reordered.length / 12;

          int[] headingsAssigned = new int[numberOfWords];
          headingsAssigned[reordered.length / 2 - 1] = 0;

          for (int idx = 0; idx < reordered.length; idx++) {

               int hdgUpperBound;
               int hdgLowerBound;

               for (int j = 0; j < 5; j++) {
                    hdgUpperBound = reordered.length / 2 - 1 - (gapSize * j);
                    hdgLowerBound = reordered.length / 2 - 1 - (gapSize * (j + 1));
                    if ((idx <= hdgUpperBound) && (idx >= hdgLowerBound)) {
                         headingsAssigned[idx] = j + 1;
                    }
               }
               for (int j = 0; j < 5; j++) {
                    hdgLowerBound = reordered.length / 2 + (gapSize * j);
                    hdgUpperBound = reordered.length / 2 - 1 + (gapSize * (j + 1));
                    if ((idx >= hdgLowerBound) && (idx <= hdgUpperBound)) {
                         headingsAssigned[idx] = j + 1;
                    }
               }

               if ((idx >= reordered.length / 2 + 5 * gapSize) && (idx <= reordered.length - 1)) {
                    headingsAssigned[idx] = 6;
               }
               if (idx >= 0 && idx <= gapSize + 1) {
                    headingsAssigned[idx] = 6;
               }
               if (idx == reordered.length / 2 - 1) {
                    headingsAssigned[idx] = 0;
               }
          }
          return headingsAssigned;
     }

     @NotNull
     private static List<Integer> populateBoundaryList(int[] headingsAssigned) {
          List<Integer> boundaryList = new ArrayList<>();
          boundaryList.add(0);
          for (int i = 1; i < headingsAssigned.length; i++) {
               if (headingsAssigned[i] != headingsAssigned[i - 1]) {
                    boundaryList.add(i - 1);
                    boundaryList.add(i);
               }
          }
          boundaryList.add(headingsAssigned.length - 1);
          return boundaryList;
     }

     private static class ArrayCreator {
          private final BufferedReader reader;
          private int numberOfWords;
          private String[][] rows;

          public ArrayCreator(BufferedReader reader) {
               this.reader = reader;
          }

          public int getNumberOfWords() {
               return numberOfWords;
          }

          public String[][] getRows() {
               return rows;
          }

          public ArrayCreator invoke() throws IOException {
               String currLine;
               int i = 0;
               numberOfWords = 150;

               rows = new String[numberOfWords][2];

               while (((currLine = reader.readLine()) != null) && i < numberOfWords) {

                    String[] dataRow = currLine.split("\t");
                    rows[i] = dataRow;
                    i++;
               }
               return this;
          }
     }
}