import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

    public class ProcessClaim {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
      
// PART I

        // File CREATION - we created files for input and output insurance database
        File inputCICSData = new File("/Users/Faisal/Desktop/Assignment2/inputCICSData.txt");
        File inputClaims = new File("/Users/Faisal/Desktop/Assignment2/inputClaims.txt");
        File outputCICSDatabase = new File("/Users/Faisal/Desktop/Assignment2/outputCICSDatabase.txt");
        File outputClaimInvoices = new File("/Users/Faisal/Desktop/Assignment2/outputClaimInvoices.txt");
                
        // File CHECK-UP - we check if  both input files are there or not !
        if ( !inputCICSData.exists() && !inputClaims.exists()  )
                {
                    System.out.print("The INPUT FILE DOESN'T EXIST !" );
                    System.exit(0);
                }
        
        // File Scanning - We initiate a scanner for both input files ( to read data ) 
        Scanner readdataFROMCICSData = new Scanner(inputCICSData);
        Scanner readdataFROMClaims = new Scanner(inputClaims);
        
// PART II
// ------> USED TO SCAN AND SAVE DATA GOTTEN FROM CICSData FILE || This part solves CICSData <------


        // Creation of Arrays - These arrays are used for the CICSData (car,owner,insuranceCoverage) classses
        Car[] cars = new Car[readdataFROMCICSData.nextInt()];
        Owner[] owners = new Owner[readdataFROMCICSData.nextInt() + 10];
        InsuranceCoverage[] insCoverage = new InsuranceCoverage[readdataFROMCICSData.nextInt()];
        int Claim_users_age[] = new int[owners.length];
        String[] users_date = new String[owners.length];
                
        
        // Initalising counters for arrays initialised above and used below                   
        int AddCar_position = 0,AddOwner_position = 0,AddInsCov_position = 0, COUNTER_CLAIM_OWNER_ID = 0;

        // We initialise any string, to start proccess of scanning in the do-while loop
        String Control = " ";

                do {
                    
                    // Reads data from CICSData
                    Control = readdataFROMCICSData.next();
                    
                    // If the command ( ADDCAR ) is scanned then enter 
                    if ( Control.equalsIgnoreCase("AddCar") ) {
                        
                        // Initalising variables for CAR class used in this command 
                        String Brand,CarColor,CarModel,CarPlate,CarType;
                        int BuiltYear;
                        
                        // Continues, reading and initialsing data from file and inserting it to file
                        CarPlate = readdataFROMCICSData.next();
                        CarType = readdataFROMCICSData.next();
                        Brand = readdataFROMCICSData.next();
                        CarModel = readdataFROMCICSData.next();
                        CarColor = readdataFROMCICSData.next();
                        BuiltYear = readdataFROMCICSData.nextInt();
                        
                        // The variables is used to store in array, and sends data to Car Class with its marked args
                        cars[AddCar_position] = new Car(CarPlate,CarType,Brand,CarModel,CarColor,BuiltYear);
                        
                        // For the next ADDCAR command readen, we ++ the counter to insert new slot in the array
                        AddCar_position++;
                        
                        }
                    
                    // ELSE If the command ( AddOwner ) is scanned then enter 
                    else if( Control.equalsIgnoreCase("AddOwner") ) {
                        
                        // Initalising variables for Owner class used in this command 
                        String First_name,Last_name,nationalID, YEARdate, MONTHdate, DATEdate;
                        
                        // Continues, reading and initialsing data from file and inserting it to file
                        First_name = readdataFROMCICSData.next();
                        Last_name = readdataFROMCICSData.next();
                        nationalID = readdataFROMCICSData.next();
                        
                        // used for creating DATE class..
                        YEARdate = readdataFROMCICSData.next();
                        MONTHdate = readdataFROMCICSData.next();
                        DATEdate = readdataFROMCICSData.next();
                      
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
                        String sDate1 = YEARdate+"-"+MONTHdate+"-"+DATEdate; 
                     
                        Date dob = formatter.parse(sDate1);
                        
                        // The variables is used to store in array, and sends data to Car Class with its marked args
                        owners[AddOwner_position] = new Owner(First_name,Last_name,nationalID,dob);
                        
                        // owner age ( used in penalty ) 
                        users_date[AddOwner_position] = YEARdate;
                        
                        // For the next ADDCAR command readen, we ++ the counter to insert new slot in the array
                        AddOwner_position++;
                    }
                    
                    // ELSE If the command ( AddInsuranceCoverage ) is scanned then enter 
                    else if( Control.equalsIgnoreCase("AddInsuranceCoverage") ) {
                                     
                        // Initalising variables for Owner class used in this command 
                        double amount;String description;int insuranceCoverageCode;
                        
                        // Continues, reading and initialsing data from file and inserting it to file
                        insuranceCoverageCode = readdataFROMCICSData.nextInt();
                        description = readdataFROMCICSData.next();
                        amount = readdataFROMCICSData.nextDouble();
            
                        // The variables is used to store in array, and sends data to Car Class with its marked args
                        insCoverage[AddInsCov_position] = new InsuranceCoverage(insuranceCoverageCode,description,amount);
                        
                        // For the next AddInsuranceCoverage command readen, we ++ the counter to insert new slot in the array
                        AddInsCov_position++;
                        
                    }
                 // If command QUIT is set, then leave the DO-WHILE loop ..
                }while( !Control.equalsIgnoreCase("Quit") );

                
                // Used to store all owners
                for ( int i = 10 ; i < owners.length ; i++ )
                {
                    owners[i] = new Owner();
                }
// PART III      
// ------> USED TO SCAN AND SAVE DATA GOTTEN FROM inputClaims FILE || This part solves Claim Proccess <------


          // Creation of Arrays - These arrays are used for the inputClaims (Claim) class
                Claim[] claims = new Claim[readdataFROMClaims.nextInt()];
                        
                long[] CLAIM_array_copy = new long[claims.length];
                long[] CLAIM_Number = new long[claims.length];
                int[] CLAIM_insurance_coverage = new int[claims.length];
                int[] CLAIM_CAR_NumberPlate = new int[claims.length];
                int[] CLAIM_OWNER_ID = new int[claims.length];
                int[] CLAIM_penalty_amount = new int[claims.length];
                int[] CLAIM_user_date = new int[claims.length];
                        
                // We initialise any string, to start proccess of scanning in the do-while loop
                String Controlz = " ";
   
        
                // Initalising counters for arrays initialised above and used below                   
                int ProccessCLAIM_position = 0, COUNTER_CLAIM_insurance_coverage = 0, COUNTER_CLAIM_CAR_NumberPlate= 0;
                
                do {
                    
                                        
                    // Reads data from inputClaims
                    Controlz = readdataFROMClaims.next();
                    
                                       
                    // If the command ( ProcessClaim ) is scanned then enter 
                    if ( Controlz.equalsIgnoreCase("ProcessClaim") ) {
                                
                        // Initalising variables for ProcessClaim class used in this command 
                        int ClaimNo;String Location, NumPlate, ownerID ;Date ClaimDate;Boolean hasPremium;Boolean hasSpecialOffer;
                        int YEARdate, MONTHdate, DAYEdate;

                        // the insurance coverage ( find coverage object associated )
                        ClaimNo = readdataFROMClaims.nextInt();
                               
                        for ( int i = 0 ; i < insCoverage.length ; i++ )
                        {
                            if ( ClaimNo == (insCoverage[i].getInsuranceCoverageCode()) )
                            {
                                CLAIM_insurance_coverage[COUNTER_CLAIM_insurance_coverage] = i;
                            }
                        }
                        
                        // the Number plate ( car object associated with the given number plate )
                        NumPlate = readdataFROMClaims.next();
                        for ( int i = 0 ; i < cars.length ; i++ )
                        {
                            if ( NumPlate.equals(cars[i].getCarPlate()) )
                            {
                                CLAIM_CAR_NumberPlate[COUNTER_CLAIM_CAR_NumberPlate] = i;
                            }
                        }
                                                
                        // Owner’s National ID ( search for the owner object associated with the given national ID )
                        ownerID = readdataFROMClaims.next();
                        for ( int i = 0, j = 0 ; i < owners.length ; i++ )
                        {
                            if ( ownerID.equals(owners[i].getNationalID() ) )
                            {
                                CLAIM_OWNER_ID[COUNTER_CLAIM_OWNER_ID] = i;
                                CLAIM_user_date[COUNTER_CLAIM_OWNER_ID] = Integer.parseInt(users_date[i]);
                                owners[i].INCREMENTnumberOFclaims();
                            }
                        }
                        
                                                
                        // Continues, reading and initialsing data from file and inserting it to file

                        Location = readdataFROMClaims.next();
                                                
                        YEARdate = readdataFROMClaims.nextInt();
                        MONTHdate = readdataFROMClaims.nextInt();
                        DAYEdate = readdataFROMClaims.nextInt();
                        Date dob = new Date(YEARdate,MONTHdate,DAYEdate);
                        
                        hasPremium = readdataFROMClaims.nextBoolean();
                        hasSpecialOffer = readdataFROMClaims.nextBoolean();

                        // The variables is used to store in array, and sends data to claim Class with its marked args
                        claims[ProccessCLAIM_position] = new Claim(ClaimNo,Location,dob,hasPremium,hasSpecialOffer);
                        
                        // For the next ProcessClaim command readen, we ++ the counter to insert new slot in the array
                        ProccessCLAIM_position++;
                        
                        // generate a unique 13-digit time stamped invoice number
                        for ( int i = 0 ; i < CLAIM_Number.length ; i++ )
                        {
                            CLAIM_Number[i] = System.currentTimeMillis();
                        }
                    }
                                            
                   // For the next ProcessClaim command readen, we ++ the counter to insert new slot in the array
                    COUNTER_CLAIM_insurance_coverage++; COUNTER_CLAIM_CAR_NumberPlate++;COUNTER_CLAIM_OWNER_ID++;
                }                 
                // If command QUIT is set, then leave the DO-WHILE loop ..
                while( !Controlz.equalsIgnoreCase("Quit") );
                
                

                // In this stage, we used Date and both boolean ( hasPremium & hasSpecialOffer ), 
                // to find owner's age AND to calculate insurance penalty amount..
                int age;
                LocalDate today = LocalDate.now();
                for ( int i = 0 ; i < CLAIM_penalty_amount.length ; i++ )
                        {
                            if ( claims[i].getHasPremium() )
                            {
                                CLAIM_penalty_amount[i] += 200;
                            }
                            if ( claims[i].getHasSpecialOffer() ){
                                CLAIM_penalty_amount[i] += 100;
                            }  
                            
                            age = Math.abs(today.getYear()) - ((CLAIM_user_date[i]));

                            if ( age >= 60 )
                            {
                                CLAIM_penalty_amount[i] += 50;
                            }
                            
                       }
                
                        
                  
                        
                // print CICSDatabase.txt in bith files
                PrintWriter writeDataCIS = new PrintWriter(outputCICSDatabase);
                PrintWriter writeDataCLAIM = new PrintWriter(outputClaimInvoices);

// PART IV
// ------> USED TO PRINT DATA GOTTEN FROM all classes || This part prints data as refered in both OUTPUT files <------
                
                writeDataCIS.println("--------------- Welcome to CICS  Database ---------------\n\n\n");
                
                for ( int i = 0 ; i < cars.length ; i++ )
                {
                    writeDataCIS.println("	"+cars[i].toString());
                    writeDataCIS.println("------------------------------------------------------\n");
                }
                
                for ( int i = 0 ; i < insCoverage.length ; i++ )
                {
                    writeDataCIS.println(insCoverage[i].toString());
                    writeDataCIS.println("------------------------------------------------------\n");
                }
                
                for ( int i = 0 ; i < owners.length - 10 ; i++ )
                {
                    writeDataCIS.println(owners[i].toString());
                    
                    if ( i < owners.length - 9 )
                        writeDataCIS.println("------------------------------------------------------\n");
                    else 
                        continue;
                    
                }
                    
                writeDataCLAIM.println("--------------- Welcome to Traffic Claim System ---------------\n\n");
                
                for ( int i = 0 ; i < claims.length ; i++ )
                {
                    writeDataCLAIM.println("Invoice No. "+System.currentTimeMillis()+"\n");
                    
                    writeDataCLAIM.println("Insurance Coverage Details");
                    writeDataCLAIM.println("	Insurance Coverage Code: "+insCoverage[CLAIM_insurance_coverage[i]].getInsuranceCoverageCode());
                    writeDataCLAIM.println("	Insurance Coverage Description: "+insCoverage[CLAIM_insurance_coverage[i]].getDescription().replaceAll("_", " ")); 
                    writeDataCLAIM.println("	Insurance Coverage Penalty: "+insCoverage[CLAIM_insurance_coverage[i]].getAmount()+"\n");         

                    writeDataCLAIM.println("Car Details");
                    writeDataCLAIM.println("	Number Plate: "+cars[CLAIM_CAR_NumberPlate[i]].getCarPlate());
                    writeDataCLAIM.println("	Type: "+cars[CLAIM_CAR_NumberPlate[i]].getCarType()); 
                    writeDataCLAIM.println("	Brand: "+cars[CLAIM_CAR_NumberPlate[i]].getBrand());  
                    writeDataCLAIM.println("	Model: "+cars[CLAIM_CAR_NumberPlate[i]].getCarModel());  
                    writeDataCLAIM.println("	Color: "+cars[CLAIM_CAR_NumberPlate[i]].getCarColor());  
                    writeDataCLAIM.println("	Built Year: "+cars[CLAIM_CAR_NumberPlate[i]].getBuiltYear()+"\n");
                    
                   
                    writeDataCLAIM.println("Owner Details");
                    writeDataCLAIM.println("	National ID: "+owners[CLAIM_OWNER_ID[i]].getNationalID());
                    writeDataCLAIM.println("	Full Name: "+owners[CLAIM_OWNER_ID[i]].getFirst_name()+" "+owners[CLAIM_OWNER_ID[i]].getLast_name()+"\n");
                    
                    writeDataCLAIM.println("Claim Details");
                    // period 
                    writeDataCLAIM.println("	Date: "+claims[i].getClaimDate().getYear()+"-"+claims[i].getClaimDate().getDate()+"-"+claims[i].getClaimDate().getMonth());
                    writeDataCLAIM.println("	Location: "+claims[i].getLocation()+"\n");
                    
                                        
                    writeDataCLAIM.println("Total Amount: "+( CLAIM_penalty_amount[i] + insCoverage[CLAIM_insurance_coverage[i]].getAmount() ) );
                    writeDataCLAIM.println("───────────────────────────────────────────────────────");


                }
                                        
                writeDataCLAIM.println("--------Total claim(s) by owner--------\n");
        writeDataCLAIM.printf("%-21s", "Owner ID");
        writeDataCLAIM.printf("%-22s", "Owner Name");
        writeDataCLAIM.printf("%s%n%n", "Total Claim(s)");
        
        for (int z = 0 ; z < owners.length - 10 ; z++ ) {

            writeDataCLAIM.print(owners[z].getNationalID());
            writeDataCLAIM.printf("%21s", owners[z].getFirst_name()+" "+owners[z].getLast_name());
            writeDataCLAIM.printf("%26d%n", owners[z].getNumberOFclaims());
                }    
                
                readdataFROMCICSData.close();
                readdataFROMClaims.close();
                writeDataCIS.close();
                writeDataCLAIM.close();
    }
}

// THE EasyRent INSURANCE SOFTWARE IS DONE !
