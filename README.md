# emblexplorer

Reads an EMBL record and extracts the ID, taxonomic classifications, DNA seqeunce.
Searches for presence of the Shine Dalgarno seqeunce 'aggaggu'

Command line usage from one directory level above src folder

Compile using:

`javac emblexplorer/*.java`

For execution, provide EMBL record file name as the input.

`java emblexplorer.EmblParser test_record.txt`

Also asks for position along the DNA seqeunce, and returns the base at that particular postion.
