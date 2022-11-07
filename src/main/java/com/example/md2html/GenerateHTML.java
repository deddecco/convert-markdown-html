package com.example.md2html;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GenerateHTML {

     // generate the opening doctype tag
     static class BeginTag {
          public void printBeginTag() {
               System.out.println("""
                         <!DOCTYPE html>
                         <html lang="en">""");
          }
     }

     // generate bold tags
     static class BoldTag {

          // surround given text by bold tags
          public String makeTextBold(String text) {
               return getBoldBegin() + text + getBoldEnd();
          }

          // open bold tag
          public String getBoldBegin() {
               return "<b>";
          }

          // close bold tag
          public String getBoldEnd() {
               return "</b>";
          }
     }

     // generate italic tags
     static class ItalicTag {

          // surround given text by italic tags
          public String makeTextItalic(String text) {
               return getItalicBegin() + text + getItalicEnd();
          }

          //open italic tag
          public String getItalicBegin() {
               return "<i>";
          }

          // close italic tag
          public String getItalicEnd() {
               return "</i>";
          }
     }

     // generate strong tags
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

     // generate body tag
     // document body, including headings, paragraphs, figures, etc.
     static class BodyTag {
          public void printBodyBeginTag() {
               System.out.println("<body>");
          }

          public void printBodyEndTag() {
               System.out.println("</body>");
          }

     }

     // generate main tah
     // unique, main content of an HTML document
     static class MainTag {
          public void printMainBeginTag() {
               System.out.println("<main>");
          }

          public void printMainEndTag() {
               System.out.println("</main>");
          }
     }

     // generate unordered list
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

          public String addToUL(String ULItem) {
               String openTag = "<li>";
               String closeTag = "</li>";
               String element = openTag + "\n" + ULItem + "\n" + closeTag;
               int indexToAdd = UL.indexOf(ULOpenTag) + 1;

               UL.add(indexToAdd, element);

               return element;
          }
     }

     // generate ordered list
     static class OrderedList {
          ArrayList<String> OL = new ArrayList<>();
          String OLOpenTag = "<ol>";
          String OLCloseTag = "</ol>";

          // create a list that only contains open and close tags
          public void createUL() {
               OL.add(OLOpenTag);
               OL.add(OLCloseTag);
          }

          // element by element, print the list
          public void printOL() {
               for (String ulElem : OL) {
                    System.out.println(ulElem);
               }
          }

          // add new element immediately prior to closing tag
          public void addToUL(String OLItem) {
               String openTag = "<li>";
               String closeTag = "</li>";
               String element = openTag + "\n" + OLItem + "\n" + closeTag;
               int indexToAdd = OL.indexOf(OLCloseTag) - 1;

               OL.add(indexToAdd, element);
          }
     }

     // generate figure tag
     static class Figure {
          // generate image tag
          public void printImgTag(String src, String alt, String title, int width, int height) {
               System.out.println("<img src = " + src + " alt= " + alt + " title=" + title + "width= " + width + "height = " + height + ">");
          }

          public void printBeginFigureTag() {
               System.out.println("<figure>");
          }

          public void printEndFigureTag() {
               System.out.println("</figure");
          }


          // surround caption with caption tag
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

          // print location, alt text, title, dimensions, and captions all surrounded by figure tags
          public void printAllInfo(String src, String alt, String title, int width, int height, String caption) {
               printBeginFigureTag();
               printImgTag(src, alt, title, width, height);
               printBeginFigCaption();
               printCaptionInfo(caption);
               printEndFigCaption();
               printEndFigureTag();
          }
     }

     // generate anchor tags for links
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
     // generate paragraph tags
     // paragraph tags go inside bodies, lists, etc.
     static class ParagraphTag {

          String beginParaTag = "<p>";
          String endParaTag = "</p>";

          // surround given text by paragraph tags
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
     // generate headings
     static class Heading {

          // surround text by heading tag given by parameter
          public String printHeadingText(int headingLevel, String headingText) {
               // handles strong tags
               if (headingLevel != 0) {
                    return "<h" + headingLevel + ">" + headingText + "</h" + headingLevel + ">";
               } else { // handles numbered headings
                    return "<strong>" + headingText + "</strong>";
               }
          }

          // print beginning heading tag
          public void printHeadingBeginTag(int headingLevel) {
               if (headingLevel >= 1 && headingLevel <= 6) {
                    System.out.println("<h" + headingLevel + ">");
               } else {
                    System.out.println("<strong>");
               }
          }

          // print ending heading tag
          public void printHeadingEndTag(int headingLevel) {
               if (headingLevel >= 1 && headingLevel <= 6) {
                    System.out.println("</h" + headingLevel + ">");
               } else {
                    System.out.println("</strong>");
               }
          }
     }
     // generate break tag
     // tag does not close
     static class Break {
          public void printBreak() {
               System.out.println("<br>");
          }
     }
     // generate horizontal rule tag
     // tag does not close
     static class HorizontalRule {
          public void printHorizontalRule() {
               System.out.println("<hr>");
          }
     }
     // generate table
     static class Table {

          // generate begin tag for table
          public void printTableBeginTag() {
               System.out.println("<table>");
          }


          // generate end tag for table
          public void printTableEndTag() {
               System.out.println("</table>");
          }

          // generate begin tag for row within table
          public String printTableRowBeginTag() {
               return "<tr>";
          }

          // generate end tag for row within table
          public String printTableRowEndTag() {
               return "</tr>";
          }

          // surround table info by begin and end tags
          public void printTableRow(String word, int frequency) {
               System.out.println(printTableDataBeginTag() + word + printTableDataEndTag() + printTableDataBeginTag() + frequency + printTableDataEndTag());
          }

          // table data begin tag
          public String printTableDataBeginTag() {
               return "<td>";
          }


          // table data end tag
          public String printTableDataEndTag() {
               return "</td>";
          }

          // table header begin tag
          public String getTableHeaderBeginTag() {
               return "<th>";
          }

          // table header end tag
          public String getTableHeaderEndTag() {
               return "</th>";
          }


          // surround dataElem by begin and end tags
          public void printTableDataRow(String dataElem) {
               System.out.println(printTableDataBeginTag() + dataElem + printTableDataEndTag());
          }

          // surround data element by formatting tags, and then surround that by data row tags
          public void printTableDataRow(String dataElem, String dataFormatTag) {
               System.out.print(printTableDataBeginTag() + "<" + dataFormatTag + ">" + dataElem + "</" + dataFormatTag + ">" + printTableDataEndTag());
          }


          // print table header
          public void printTableHeader(String headingText) {
               System.out.println(getTableHeaderBeginTag() + headingText + getTableHeaderEndTag());
          }
     }
     public static void main(String[] args) throws IOException {


          // file path from which HTML will be generated
          String filePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m10\\BibleFrequencyAnalysis.txt";
          BufferedReader reader = new BufferedReader(new FileReader(filePath));
          // read the first row in the file into an array-- don't need it now, may want it later
          String[] tableHeader = reader.readLine().split("\t");

          // read the rest of the file into an array with 2 columns
          ArrayCreator arrayCreator = new ArrayCreator(reader).invoke();
          // number of words to be used in the word cloud
          int numberOfWords = arrayCreator.getNumberOfWords();
          // read in that 2 column array
          String[][] rows = arrayCreator.getRows();
          // reorder that array so that it puts the element with the highest frequency in the middle
          // and then other words fan out from the center (like the way justices sit at the bench at SCOTUS
          // fourth_associate  next_most_senior chief most_senior_associate third_associate
          String[][] reordered = getReordered(rows);
          // middle index gets  "0" (strong), divide rest into 12ths
          // nearest 2 12ths (immediate left and right) get 1
          // next 12ths get 2
          // next 12ths get 3 ...
          // these will be heading numbers
          int[] headingsAssigned = populateHeadingsAssigned(numberOfWords, reordered);
          // create new list
          // add 0 to that list
          // add every index where the heading number at that index is different from its neighbor
          // add last index to the list
          List<Integer> boundaryList = populateBoundaryList(headingsAssigned);

          printHeadings(reordered, headingsAssigned, boundaryList);
     }
     // print out the words with appropriate opening and closing tags given the boundary list info
     private static void printHeadings(String[][] reordered, int[] headingsAssigned, List<Integer> boundaryList) {
          Heading heading = new Heading();
          heading.printHeadingBeginTag(6);
          for (int i = 0; i < reordered.length - 1; i++) {
               // boundary list case
               if (boundaryList.contains(i) && boundaryList.contains(i + 1)) {
                    System.out.println(reordered[i][0]);
                    heading.printHeadingEndTag(headingsAssigned[i]);
                    heading.printHeadingBeginTag(headingsAssigned[i + 1]);
               } // non-boundary case
               else {
                    System.out.println(reordered[i][0]);
               }
          }
          heading.printHeadingEndTag(6);
     }
     // apply reordering to String[][] rows, return the reordered [][]
     private static String[][] getReordered(String[][] rows) {

          // comparator looks at frequency, ignores word
          Comparator<String[]> comparator = Comparator.comparingInt(o -> Integer.parseInt(o[1]));
          // sort 2d array with comparator
          Arrays.sort(rows, comparator);
          // reverse
          Collections.reverse(Arrays.asList(rows));
          SupremeCourtOrdering sco = new SupremeCourtOrdering();
          // rearrange
          return sco.reorder(rows);
     }
     private static int @NotNull [] populateHeadingsAssigned(int numberOfWords, String[][] reordered) {
          // 2 symmetric halves of 6 headings each = each part = 1/(6*2) of the whole
          int gapSize = reordered.length / 12;

          int[] headingsAssigned = new int[numberOfWords];
          // middle = strong = "heading 0"
          // other indices get numerical values exactly = the headings they are assigned
          headingsAssigned[reordered.length / 2 - 1] = 0;

          for (int idx = 0; idx < reordered.length; idx++) {

               int hdgUpperBound;
               int hdgLowerBound;


               // deal with each half
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
               // headings 6 and 0 are special cases:
               // 6: first and last segments

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
          // this is just the int[]
          return headingsAssigned;

     }

     // whenever two adjacent cells in the heading array are different,
     // put both of their indices (and 0 and the last index)
     // in the list
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

               // read the first 150 non-heading rows in the file into the [][]
               while (((currLine = reader.readLine()) != null) && i < numberOfWords) {
                    String[] dataRow = currLine.split("\t");
                    rows[i] = dataRow;
                    i++;
               }
               return this;
          }
     }
}