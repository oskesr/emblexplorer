package emblexplorer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class defines the main method which doesthe actual parsing of the 
 * EMBl file and allocates to an object of Embl class.
 * File name is passed as a command line parameter.
 * Also contains the test method for charAt from the Sequence class
 * Also tests the searchSequence method for a particular sequence 'aggaggu'
 *
 * @author Om Kulkarni ID 110378732
 */

public class EmblParser {

    public static void main(String[] args) {
        try {

            //Scanner fileInput = new Scanner(System.in);
            //File source = new File(fileInput.next());

            File source = new File(args[0]);

            Embl record = new Embl();
            EmblParser parser = new EmblParser();

            // Parse the embl record file and allocate fields in embl class

            record.setId(parser.parseID(source));
            record.setSpecies(parser.parseSpecies(source));
            record.getDnaSeq().setSequence(parser.parseSequence(source));

            System.out.println("Parsed the EMBL record and allocated information to an EMBL object ");

            // Display the EMBL record and test the search sequence method

            parser.showRecord(record);
            parser.testSearchSequence(record);

            // Test the charAt method

            parser.testCharAt(record);

        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file name, aborting");
        }

    }

    /**
     * Display the contents of the EMBL record.
     *
     * @param record
     */

    public void showRecord(Embl record) {

        System.out.println("The contents of the EMBL record are : ");
        System.out.println("ID is : " + record.getId());
        System.out.println("List of taxonomic classifications is: " + record.getSpecies());
        System.out.println("DNA sequence is : " + record.getDnaSeq().getSequence());
    }

    /**
     * Tests the charAt method of the Sequence class.
     *
     * @param record
     */

    public void testCharAt(Embl record) {

        // Get length from embl object
        int last = record.getDnaSeq().length();

        System.out.println("Testing the charAt method, input the position of the character (Between 1 and " + last + ")");

        // Get required position from user
        Scanner scanner = new Scanner(System.in);
        int position = scanner.nextInt();

        if (position > 0 && position <= last) {
            System.out.println("Character is " + record.getDnaSeq().charAt(position - 1));
        } else {
            System.out.println("Invalid index");
        }

    }

    /**
     * Test the searchSequence method for the Shine Dalgarno sequence.
     * 
     * @param record
     */

    public void testSearchSequence(Embl record) {

        System.out.println("Searching for the Shine Dalgarno Sequence (AGGAGGU) ");
        
        record.getDnaSeq().searchSequence("aggaggu");

    }

    /**
     * Parses the ID of the record from the file.
     *
     * @param source
     * @return String
     * @throws FileNotFoundException
     */

    public String parseID(File source) throws FileNotFoundException {

        // Declare scanner and use it to read the second token of the file ,
        // which is the primary accession number

        Scanner file = new Scanner(source);
        String idLine = file.next();
        idLine = file.next();

        // Remove ';' in the end of the token

        idLine=idLine.substring(0, idLine.length()-1);
        return idLine;

    }

    /**
     * Parses the taxonomic classifications of the embl record from the file.
     * Returns an ArrayList of strings.
     *
     * @param source
     * @return ArrayList<String>
     * @throws FileNotFoundException
     */

    public ArrayList<String> parseSpecies(File source) throws FileNotFoundException {

        // Declare scanner and use it to read the first line of the file

        Scanner file = new Scanner(source);
        String line = file.nextLine();

        ArrayList<String> species = new ArrayList<String>();
        ArrayList<String> speciesTemp = new ArrayList<String>();

        // While the EMBL file has not been read fully i.e. the last line
        // of the record starts with "//", run till it is not "//"


        while (!(line.charAt(0) == '/' && line.charAt(1) == '/')) {

            // Check if the line starts with "OC", whic hstores the taxonomy
            // information. If so, add the line to a array list sp

            if (file.hasNext("OC")) {
                line = file.nextLine();
                speciesTemp.add(line);
            } else {
                line = file.nextLine();
            }

        }

        // Split the line , one at a time

        for (String lineSplit : speciesTemp) {

            // Remove first four characters of the line, as they are spaces

            lineSplit = lineSplit.substring(4);

            //  Split line at each ";", the standard delimiter for an EMBL record

            String[] taxon = lineSplit.split(";");

            // Add all split strings to the final arraylist

            species.addAll(Arrays.asList(taxon));
        }

        return species;
    }

    /**
     * Parses the sequence of the embl record from the file.
     * Returns an ArrayList of characters.
     *
     * @param source
     * @return List<Character>
     * @throws FileNotFoundException
     */
    public List<Character> parseSequence(File source) throws FileNotFoundException {

        //Declare scanner and use it to read the first line of the file

        Scanner file = new Scanner(source);
        String line = file.nextLine();
        String token = new String();

        ArrayList<String> sequenceTemp = new ArrayList<String>();

        // Flag indicates if sequence line has been found.
        // Till it is found , goto next line.

        int flag = 0;
        while (flag == 0) {

            // If line contains sequence, make flag = 1

            if (file.hasNext("SQ")) {
                flag = 1;
                line = file.nextLine();
                token = file.next();

                // Till the end of sequence is found , i.e. "//"
                // add tokens to the list of strings

                while (!"//".equals(token)) {

                    // If an int is found as the next token , skip 
                    // adding it to the list

                    if (file.hasNextInt()) {
                        sequenceTemp.add(token);
                        token = file.next();
                        token = file.next();
                    } else {
                        sequenceTemp.add(token);
                        token = file.next();
                    }
                }

                line = file.nextLine();

            } else {
                line = file.nextLine();
            }
        }


        // Convert the list of strings to a single string

        StringBuilder sb = new StringBuilder();
        for (String s : sequenceTemp) {
            sb.append(s);
        }

        // Convert string to a List of characters

        String str = sb.toString();
        List<Character> sequence = new ArrayList<Character>();

        for (char b : str.toCharArray()) {
            sequence.add(b);
        }

        return sequence;
    }
}
