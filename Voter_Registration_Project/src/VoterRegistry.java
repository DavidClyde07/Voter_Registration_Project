import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class VoterRegistry {
	
	
	static String[]id = new String[211];
	static String[]name = new String[211];
	//create a new hash table with keys of type Long and values of type String 
	static Hashtable<Long,String> Registry;

	
	/**
	 * This is a method to read in the Voter Registration 
	 * information from a csv file and store it in the
	 * respective arrays. 
	 */    
	    
	public static void setVoterData() {
		
        String filename = "Voter_Information.csv";
        
        int count = 0;
        BufferedReader bufferIn = null;
        try {
            
        	bufferIn = new BufferedReader(new FileReader(filename));
            String read = null;
            
            
            /*
             * While loop to read file line by line the loop
             * will terminate as soon as a null line is 
             * encountered. This will happen when there are 
             * no more lines to read.
             */
            
            while ((read = bufferIn.readLine()) != null) {
            	/*Each line of information contains ID numbers
                 *and Names these need to be separated and stored 
                 *in the respective arrays 
                 */
            	
            	String[] s = read.split(";");
                id[count] = (s[0]);
                name[count] = s[1];
                count++;
            }
        } 
        catch (IOException e) {}

        finally {
            try {
                bufferIn.close();
            }
            catch (Exception e) {}
        }		
		
	}
		
	

/*
* hash function to hash keys to indexes
*@param:
*/

    public static void myHashFunction(){

        Registry = new Hashtable();
        for (int i=0;i<avoidNullValues(id);i++)
        {
        	
        	/*
        	 * put() function takes in 2 parameter, the key and the
        	 * respective value that the key maps to.
        	 * 
        	 * put() returns: the previous value if an existing key
        	 * is passed but if a new key value pair is entered it
        	 * will return NULL 
        	 * If an existing key is passed then the previous
        	 * value gets returned. If a new pair is passed, 
        	 * then NULL is returned.
        	 * 
        	 */
            Registry.put(Long.parseLong(id[i]),name[i]);
        }

    }
/*
*method to stop adding null values to the hash table
*@param: String[] key_Array
*@return : null_check
*/

    public static int avoidNullValues(String[] key_Array){

        int null_check = 0;
        for (int i=0; i<key_Array.length; i++ )
        {
        	//key_array.length is 211 because it is given the id array but it only contains 100 elements
        	
            if(key_Array[i] != null)
            {
                null_check++ ;
            }
        }
        return null_check;
    }
	
	
	public static void main(String[] args) throws Exception {
		//Invoke function to read in data from csv.
		setVoterData();
		//Invoke hash function to populate the hash table
		myHashFunction();
		
		//Text base interface which gets user input using the scanner class
        Scanner scanIn = new Scanner(System.in);
        int input;
        System.out.println("Welcome to to voter verification");
      
        do
        {
        System.out.println("To verify you registration, enter 1, or to exit, press 0");
            input = scanIn.nextInt();
            
          //if 1 then the users is going to enter a ID number to verify
            if(input==1){
            	
                System.out.println("Enter your ID: ");
              //ID num are 13 digits which is bigger than int will allow, so read it in as a Long
                long IDinput = scanIn.nextLong();
              
                //look up ID number in the registry, if there proceed.
                if(Registry.containsKey(IDinput)){
                    System.out.println("Verification of "+Registry.get(IDinput)+" successful!");
                    System.out.println("  ");
                }
                else{
                    System.out.println("Verification Failed.\n");
                    System.out.println("  ");
                }
            }
        }
        while(input!=0);
        System.out.println("Goodbye.\n");
        scanIn.close();
    }

}
