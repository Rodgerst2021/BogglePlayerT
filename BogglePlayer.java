/*
* Author: C.A.M.E.R.O.N.
* Course: CSE 2010
* Section: 02
* Description: given a dictionary and a Boggle board, finds the 20 highest scoring words
 */

import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class BogglePlayer {
   // class to hold a list of children, and the row and column of the parent letter
   private class Entry implements Comparable<Entry> {
      private ArrayList<String> key;
      private int row;
      private int col;
      public Entry (final ArrayList<String> k, final int r, final int c) {
         key = k;
         row = r;
         col = c;
      }

      public ArrayList<String> key() {
         return key;
      }

      public int row() {
         return row;
      }

      public int col() {
         return col;
      }

      // uses size of list for compareTo, therefore priority queue will key based on list size
      public int compareTo(final Entry e) {
         return key.size() - e.key().size();
      }
   }

   final Trie dictionary;
   public BogglePlayer (final String wordFile) throws FileNotFoundException {
      dictionary = new Trie(wordFile);
   }

   public Word[] getWords(final char[][] board) {
      return new Word[1];  // placeholder so class can compile
   }

   /**
    * creates a priority queue of all the letters in the board, keying based on length of list, creating a heatmap of
    * the letters with the most children near it
    * @param b 2d array representing boggle board
    * @return heatmap of which letters have children near them
    */
   private PriorityQueue<Entry> childScan(final char[][] b) {
      // priority queue that will key based on # of children
      final PriorityQueue<Entry> hm = new PriorityQueue<Entry>();

      // loops through all letters on board
      for (int r = 0; r < b.length; r++) {
         for (int c = 0; c < b[r].length; c++) {
            // prepares an ArrayList to hold the children of that letter in the trie
            final ArrayList<String> children = dictionary.getChildren(String.valueOf(b[r][c]));
            // Plan moving forward: loop through children, remove all that are not adjacent to the current letter
            for (int i = children.size() - 1; i >= 0; i--) {

            }
         }
      }
      return hm;
   }


}
