/*
* Author: C.A.M.E.R.O.N.
* Course: CSE 2010
* Section: 02
* Description: given a dictionary and a Boggle board, finds the 20 highest scoring words
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BogglePlayer {

   public BogglePlayer (final String wordFile) throws FileNotFoundException {
      final File words = new File(wordFile);
      final Scanner scn = new Scanner(words, "US-ASCII");
   }

   public Word[] getWords(final char[][] board) {
      return new Word[1];  // placeholder so class can compile
   }
}
