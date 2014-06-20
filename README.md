LanguageDetectionHadoop
=======================

Language Detection of Large Corpuses using Langdetect and Hadoop


USAGE(Single Node) -

First of all you need to have a TSV file with atleast two columns - an ID and the Message.
Use the JAR in the dist folder as

java -jar LanguageDetector.jar INPUT_PATH.tsv OUTPUT_PATH LANGDETECT_PROFILES_PATH IDFIELDNUMBER MESSAGEFIELDNUMBER

INPUT_PATH.tsv - See Sample.tsv
LANGDETECT_PROFILES_PATH - Its the path of langdetect/profiles directory
IDFIELDNUMBER - 0 in the Sample.tsv
MESSAGEFIELDNUMBER - 1 in the Sample.tsv
The Output will appear in the Output Path.


USAGE(Cluster) -

Copy the input file and langdetect/profiles in the HDFS.
You have to pass the external Libraries of the project through -libjars method
You will have to pass the libraries of lib folder.

Read some details from - http://grepalex.com/2013/02/25/hadoop-libjars/

Run the command from hadoop bin folder -
./hadoop jar LanguageDetector.jar [PARAMETERS] -libjars PATH_OF_JAR
