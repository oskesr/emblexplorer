package emblexplorer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class storing the dna sequence as a List of characters.
 * Also contains method for searching a sequence passed as a parameter.
 * 
 * @author Om Kulkarni ID 110378732
 */
public class Sequence implements CharSequence {

    private List<Character> sequence = new ArrayList<Character>();

    /**
     * Get the value of sequence.
     *
     * @return the value of sequence
     */

    public List<Character> getSequence() {
        return sequence;
    }

    /**
     * Set the value of sequence.
     *
     * @param sequence new value of sequence
     */

    public void setSequence(List<Character> sequence) {
        this.sequence = sequence;
    }

    
    /**
     * Return length of sequence.
     *
     * @return int
     */

    @Override
    public int length() {
        return sequence.size();
    }

    /**
     * Get the character at particular index.
     *
     * @param index
     * @return char
     */

    @Override
    public char charAt(int index) {
        return sequence.get(index);
    }
   
    /**
     * Return a subsequence of the original sequence according the given indices.
     * start is first index, end is the last index
     *
     * @param start
     * @param end
     * @return CharSequence
     */

    @Override
    public CharSequence subSequence(int start, int end) {
        return (CharSequence) sequence.subList(start, end);
    }

    /**
     * Search the sequence for a particular query sequence and displays if
     * the query sequence is present or absent.
     *
     * @param searchSequence
     */

    public void searchSequence(String searchSequence) {

        // Convert the List to a string

        StringBuilder makeString = new StringBuilder();
        for (char c : sequence) {
            makeString.append(c);
        }

        String seqString = new String(makeString);

        // Check if sequence is present

        if (seqString.contains(searchSequence)) {
            System.out.println("The sequence " + searchSequence + " found");
        } else {
            System.out.println("The sequence " + searchSequence + " not found");
        }
    }
}



