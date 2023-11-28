import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Trie {
   private class Node {
      private String data;
      private Node parent;
      private boolean isWord;
      private ArrayList<Node> children;

      public Node(final String d) {
         data = d;
         isWord = false;
         children = new ArrayList<Node>();
      }

      public void addChild(final Node c) {
         children.add(c);
         c.setParent(this);
      }

      public String getData() {
         return data;
      }

      public boolean isWord() {
         return isWord;
      }

      private void setParent(final Node p) {
         parent = p;
      }

      public void setIsWord(final boolean siw) {
         isWord = siw;
      }

      public boolean equals(final Node n) {
         return data.equals(n.getData());
      }
   }

   private Node head;

   public Trie(final String wordList) throws FileNotFoundException {
      final File words = new File(wordList);
      final Scanner scn = new Scanner(words, "US-ASCII");
      head = new Node("");
      while (scn.hasNextLine()) {
         final String w = scn.nextLine();
         if (w.length() < 3 || w.length() > 16) {
            continue;
         }
         trieBuilder(w, head);
      }
   }

   private void trieBuilder(final String toAdd, final Node parentNode) {
      if (toAdd.length() == 0) {
         parentNode.setIsWord(true);
         return;
      }
      Node child = new Node(toAdd.substring(0,1));
      final int ind = parentNode.children.indexOf(child);
      if (ind == -1) {
         parentNode.addChild(child);
      } else {
         child = parentNode.children.get(ind);
      }
      trieBuilder(toAdd.substring(1), child);
   }

   public boolean search(final String query) {
      return searchRecur(query, head);
   }

   private boolean searchRecur(final String q, Node c) {
      if (q.length() == 0 && c.isWord()) {
         return true;
      }
      final int ind = c.children.indexOf(new Node(q.substring(0,1)));
      if (ind != -1) {
         return searchRecur(q.substring(1), c.children.get(ind));
      }
      return false;
   }
}