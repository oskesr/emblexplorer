package emblexplorer;

import java.util.ArrayList;

/**
 * This class stores the  contents of an EMBL record,namely
 * the ID, the taxonomoic classifications and the sequence.
 *
 * @author Om Kulkarni ID 110378732
 */

public class Embl {

    private String id;

    /**
     * Get the value of id.
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id.
     *
     * @param id new value of id
     */

    public void setId(String id) {
        this.id = id;
    }
    private ArrayList<String> species = new ArrayList<String>();

    /**
     * Get the value of species.
     *
     * @return the value of species
     */

    public ArrayList<String> getSpecies() {
        return species;
    }

    /**
     * Set the value of species.
     *
     * @param species new value of species
     */

    public void setSpecies(ArrayList<String> species) {
        this.species = species;
    }
    private Sequence dnaSeq = new Sequence();

    /**
     * Get the dna sequence.
     *
     * @return the value of dnaSeq
     */

    public Sequence getDnaSeq() {
        return dnaSeq;
    }

    /**
     * Set the value of dnaSeq.
     *
     * @param dnaSeq new value of dnaSeq
     */

    public void setDnaSeq(Sequence dnaSeq) {
        this.dnaSeq = dnaSeq;
    }
}
