package ruleChecker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author PUSHKAR SINGH NEGI
 * KU ID: 2946319
 * Programming Project: Classification System : EECS 839 : SPRING 2018
 * Start Date:
 * Modified Date:
 */
public class test_msd {
	
	test_msd()
	{}
	
	public static double methodToRuleChecker(ArrayList<test1.dataFileDetails> listdataFileDetails, String strRuleFileName)
	{


		double doubErrorRate=0.0;
		ArrayList<rulesFileDetails> listRulesFileDetails=new ArrayList<rulesFileDetails>();
		// list of rulesFileDetails, that will contain all the condition in 1st LinkedHashMap and decision value pair in 2nd LinkedHashMap
		
		//ArrayList<dataFileDetails> listdataFileDetails=new ArrayList<dataFileDetails>();// one object will store individual row along with attribute-value pair in key value
		
		String line;

		try {
		/**********************START-- code to read the rule file**********************/
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			//String input = JOptionPane.showInputDialog("Please give number");
			
			/* RULE FILE NAME: INPUT FROM USER START*/
			
			boolean boolRuleFileError=false;
			
		/*	System.out.println("Please enter the name of the input rule file and press enter\n");
			
			String strRuleFileName = in.readLine();	
			
			if(strRuleFileName.trim().equalsIgnoreCase(""))
			{
				boolRuleFileError=true;
			}
			if(boolRuleFileError==false)
			{
			if(!strRuleFileName.trim().endsWith(".txt"))
			{
				boolRuleFileError=true;
			}
			}
			if(boolRuleFileError==false)
			{
			if(strRuleFileName.trim().endsWith(".txt"))
			{
				String[] strSplit=strRuleFileName.trim().split(".txt");
				
				if(strSplit[0].length()==0)
				{
					boolRuleFileError=true;
				}
			}
			}*/
			/*while (boolRuleFileError) {
				
				System.out.println("Please enter a valid rule file name");
				strRuleFileName = in.readLine();
				boolRuleFileError=false;
				
				if(strRuleFileName.trim().equalsIgnoreCase(""))
				{
					boolRuleFileError=true;
				}
				if(boolRuleFileError==false)
				{
				if(!strRuleFileName.trim().endsWith(".txt"))
				{
					boolRuleFileError=true;
				}
				}
				if(boolRuleFileError==false)
				{
				if(strRuleFileName.trim().endsWith(".txt"))
				{
					String[] strSplit=strRuleFileName.trim().split(".txt");
					
					if(strSplit.length==0)
					{
						boolRuleFileError=true;
					}
				}
				}
			    }*/
			//strRuleFileName="class_ex_rules.txt";
		//	strRuleFileName="austr-aca.r.txt";
		//	strRuleFileName="car.r.p.txt";
		//	strRuleFileName="car.r.c.txt";
		//	strRuleFileName="m-iris.r.txt";
			
			
			//System.out.println(" RULE FILE NAME value input by user is---"+strRuleFileName);
			
			/* RULE FILE NAME: INPUT FROM USER END*/
			
			/* DATA FILE NAME: INPUT FROM USER START*/
			boolean boolDataFileError=false;
			/*System.out.println("\nPlease enter the name of the input data file and press enter\n");
			 in = new BufferedReader(new InputStreamReader(System.in));
			String strDataFileName = in.readLine();
			
			if(strDataFileName.trim().equalsIgnoreCase(""))
			{
				boolDataFileError=true;
			}
			if(boolDataFileError==false)
			{
			if(!strDataFileName.trim().endsWith(".txt"))
			{
				boolDataFileError=true;
			}
			}
			if(boolDataFileError==false)
			{
			if(strDataFileName.trim().endsWith(".txt"))
			{
				String[] strSplit=strDataFileName.trim().split(".txt");
				
				if(strSplit[0].length()==0)
				{
					boolDataFileError=true;
				}
			}
			}*/
			/*while (boolDataFileError) {
				
				System.out.println("Please enter a valid Data file name");
				strDataFileName = in.readLine();
				boolDataFileError=false;
				
				if(strDataFileName.trim().equalsIgnoreCase(""))
				{
					boolDataFileError=true;
				}
				if(boolDataFileError==false)
				{
				if(!strDataFileName.trim().endsWith(".txt"))
				{
					boolDataFileError=true;
				}
				}
				if(boolDataFileError==false)
				{
				if(strDataFileName.trim().endsWith(".txt"))
				{
					String[] strSplit=strDataFileName.trim().split(".txt");
					
					if(strSplit.length==0)
					{
						boolDataFileError=true;
					}
				}
				}
			    }*/
			
			
					
			//System.out.println(" DATA  FILE NAME value input by user is---"+strDataFileName);
			
			/* DATA FILE NAME: INPUT FROM USER END*/
			
			/**********************START-- code to read the rule file**********************/
			
			//String strRuleFileName="class_ex_rules.txt";
			
			 InputStream fis = new FileInputStream(strRuleFileName);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			    
			    rulesFileDetails objRulesFileDetails=null;
			    int intTempRuleNumber=1;
			    while ((line = br.readLine()) != null) {
			        //System.out.println(line.trim());
			    	line=	line.trim().replaceAll("\\s+","");
			        if(line.trim().length()>0)  // check for non empty line
			        {
			        	
			        if(!line.trim().startsWith("!")) // if line starts with ! i.e. its comment so ignore it
			        {
			        	if(line.trim().matches("^[0-9].*$")) // to check that the line starts with number, for the three number check
			        	{
			        		String[] tempThreeFactors=line.trim().split(",");
			        		//System.out.println(tempThreeFactors.length);
			        		if(tempThreeFactors.length==3 && !tempThreeFactors[0].trim().equals("") && !tempThreeFactors[1].trim().equals("")
			        		&&		!tempThreeFactors[2].equals(""))
			        		{
			        			 objRulesFileDetails=new rulesFileDetails();
			        			objRulesFileDetails.setIntSpecificity(Integer.parseInt(tempThreeFactors[0].trim()));
			        			objRulesFileDetails.setIntStrength(Integer.parseInt(tempThreeFactors[1].trim()));
			        			objRulesFileDetails.setIntTotNoMatchTrainCases(Integer.parseInt(tempThreeFactors[2].trim()));
			        		}
			        		else
			        		{
			        			System.out.println("Error in Rule File, specifity, strength or Total cases missing ");
			        			System.exit(0);
			        		}
			        	} else if(line.trim().startsWith("("))  // checking for conditions and actions
			        	{
			        		
			        	String[] tempSplitRuleOnHash=line.trim().replaceAll("\\s+","").split("->"); // two parts, 1st part contains the conditions and 2nd part action(decision)
			        		
			        	String[] tempSplitCondAttrValuePair=tempSplitRuleOnHash[0].split("&"); // store individual conditions- example (Capacity,four) 
			        //	System.out.println(tempSplitRuleOnHash[1]);
			        	
			        	//	 System.out.println("decision value pair after removing -> and () is---"+objRulesFileDetails.getLhmDecision());
			        		
			        		for(String s:tempSplitCondAttrValuePair)
			        		{
			        			
			        			String[] strTempEachAttValuePair=s.trim().substring(s.indexOf("(")+1, s.indexOf(")")).split(",");
			        			//finding each individual rules conditions by trimming the '(' and ')' and splitting by ,
			        			
			        			objRulesFileDetails.getLhmConditions().put(strTempEachAttValuePair[0], strTempEachAttValuePair[1]);
			        			
			        			//System.out.println("tempSplitCondAttrValuePair**"+s);
			        		}
			        		
			        		//System.out.println("condition Attricbue value pair after removing , and () is---"+objRulesFileDetails.getLhmConditions());
			        		
			        		if(line.trim().contains("->"))
				        	{
				        	String[] strSplitDecisionValueComma=tempSplitRuleOnHash[1].substring(tempSplitRuleOnHash[1].indexOf("(")+1, tempSplitRuleOnHash[1].indexOf(")")).trim().split(",");
				   // by indexOf removing first'(' and last ')' and then split on basis of comma so that we get decision and decision value instrSplitDecisionValueComma[0] and [1]
				        	
				        	objRulesFileDetails.getLhmDecision().put(strSplitDecisionValueComma[0], strSplitDecisionValueComma[1]);
				        	//storing the decision and its value in the hashmap of the class object made
				        	
				        	objRulesFileDetails.setIntRuleNumber(intTempRuleNumber);
				        	
				        	
				        	listRulesFileDetails.add(objRulesFileDetails);
				        	intTempRuleNumber++;
				        	}
			        		
			        		
			        	} else if(line.trim().startsWith("->"))
			        	{

				        	String[] strSplitDecisionValueComma=line.trim().substring(line.trim().indexOf("(")+1, line.trim().indexOf(")")).trim().split(",");
				   // by indexOf removing first'(' and last ')' and then split on basis of comma so that we get decision and decision value instrSplitDecisionValueComma[0] and [1]
				        	
				        	objRulesFileDetails.getLhmDecision().put(strSplitDecisionValueComma[0], strSplitDecisionValueComma[1]);
				        	//storing the decision and its value in the hashmap of the class object made
				        	objRulesFileDetails.setIntRuleNumber(intTempRuleNumber);
				        	listRulesFileDetails.add(objRulesFileDetails);
				        	intTempRuleNumber++;
			        	}
			        	//System.out.println("kaam ka hai");
			        }
			     }
			    
	}
			// print for RULES    
		/*for(rulesFileDetails obj:listRulesFileDetails)
		{
			
			System.out.println("Rule number:- "+ obj.getIntRuleNumber());
			System.out.println("specificty----"+obj.getIntSpecificity());
			System.out.println("strnegth  ----"+obj.getIntStrength());
			System.out.println("Totao case  --"+obj.getIntTotNoMatchTrainCases());
			System.out.println("conditions hashmap---"+obj.getLhmConditions());
			System.out.println("Decision hashmap---"+obj.getLhmDecision());
			
			System.out.println();
			
		}*/
		/**********************END-- code to read the rule file**********************/
			
			/* 1. MATCHING(PARTIAL) factor: INPUT FROM USER START*/
			
			//System.out.println("Do you want to use the matching factor ?");
			 in = new BufferedReader(new InputStreamReader(System.in));
		/*	String strMatchingFactor = in.readLine();
			
			while (!(strMatchingFactor.trim().equalsIgnoreCase("y") || strMatchingFactor.trim().equalsIgnoreCase("n") || strMatchingFactor.trim().equalsIgnoreCase("")))
			{
				System.out.println("Please enter a valid response for the question !!!.");
				strMatchingFactor = in.readLine();
			
			}
			if(strMatchingFactor.trim().equalsIgnoreCase(""))
			{
				strMatchingFactor="n";
			}
			*/
			 
			 String strMatchingFactor = "y";
			//strDataFileName="class_ex.txt"; 
			//strDataFileName="austr-aca.txt";
			//strDataFileName="car.txt";
			//strDataFileName="m-iris.txt";
			//System.out.println("Matching factor---"+strMatchingFactor);System.out.println("\n");
			
			/* 1. MATCHING(PARTIAL) factor: INPUT FROM USER END*/
			
			/* 2. STRENGTH OR CONDITIONAL PROBABILITY factor: INPUT FROM USER START*/
			
			//System.out.println("Do you want to use the strength (s) or conditional probablity (p) as the strength factor ?");
			// in = new BufferedReader(new InputStreamReader(System.in));
			//String strStrengthfactor = in.readLine();
			
			 String strStrengthfactor = "s";
			/*while (!(strStrengthfactor.trim().equalsIgnoreCase("p") || strStrengthfactor.trim().equalsIgnoreCase("s")))
			{
				System.out.println("Please enter a valid response for the question !!!.");
				strStrengthfactor = in.readLine();
			}
			
			
			if(strStrengthfactor.trim().equalsIgnoreCase("p"))
			{
				for(rulesFileDetails obj:listRulesFileDetails)
				{
					if(obj.getIntTotNoMatchTrainCases()==0)
					{
						System.out.println("Since for one of the rules the total number of matching case is zero, conditional probability option cannot be executed;"
								+ " by default selecting STRENGTH as the strength factor");
						
						strStrengthfactor="s";
					}
					System.out.println();
					
				}
			}*/
			
			//System.out.println("Strength factor---"+strStrengthfactor);System.out.println("\n");
			
			/* 2. STRENGTH OR CONDITIONAL PROBABILITY factor: INPUT FROM USER END*/
			
			/* 3. SPECIFICTY factor not: INPUT FROM USER START*/
			
			/*System.out.println("Do you want to use the factor associated with specificty or not ?");
			 in = new BufferedReader(new InputStreamReader(System.in));
			String strSpecificityFactor = in.readLine();
			
			
			while (!(strSpecificityFactor.trim().equalsIgnoreCase("y") || strSpecificityFactor.trim().equalsIgnoreCase("n") || strSpecificityFactor.trim().equalsIgnoreCase("")))
			{
				System.out.println("Please enter a valid response for the question !!!.");
				strSpecificityFactor = in.readLine();
			
			}
			if(strSpecificityFactor.trim().equalsIgnoreCase(""))
			{
				strSpecificityFactor="n";
			}
			*/
			 String strSpecificityFactor="n";
			//System.out.println("Specificity factor---"+strSpecificityFactor);System.out.println("\n");
			
			/* 3. SPECIFICTY factor not: INPUT FROM USER END*/
			
			/* 4. SUPPORT OF OTHER RULES OR NOT factor : INPUT FROM USER START*/
			
			/*System.out.println("Do you want to use the support of other rules or not ?");
			 in = new BufferedReader(new InputStreamReader(System.in));
			String strSupportFactor = in.readLine();
			if(strSupportFactor.trim().equalsIgnoreCase(""))
			{
				strSupportFactor="n";
			}
			
			while (!(strSupportFactor.trim().equalsIgnoreCase("y") || strSupportFactor.trim().equalsIgnoreCase("n") || strSupportFactor.trim().equalsIgnoreCase("")))
			{
				System.out.println("Please enter a valid response for the question !!!.");
				strSupportFactor = in.readLine();
			
			}
			if(strSupportFactor.trim().equalsIgnoreCase(""))
			{
				strSupportFactor="n";
			}*/
			 String strSupportFactor = "y";
			//System.out.println("Specificity factor---"+strSupportFactor);System.out.println("\n");
			
			/* OUTPUT 2 : CONCEPT STATISTICS: START*/
			
			/*System.out.println("\nDo you want to know the concept statistics ?");
			 in = new BufferedReader(new InputStreamReader(System.in));
			String strConceptStats = in.readLine();
			
			
			while (!(strConceptStats.trim().equalsIgnoreCase("y") || strConceptStats.trim().equalsIgnoreCase("n") || strConceptStats.trim().equalsIgnoreCase("")))
			{
				System.out.println("Please enter a valid response for the question !!!.");
				strConceptStats = in.readLine();
			
			}
			if(strConceptStats.trim().equalsIgnoreCase(""))
			{
				strConceptStats="n";
			}*/
			
			//System.out.println("Concept Statistics---"+strConceptStats);System.out.println("\n");
			
			/* OUTPUT 2 : CONCEPT STATISTICS: END*/
			
			/* OUTPUT 3 : How cases associated with concepts were classified: START*/		
			
			/*System.out.println("Do you want to know how cases are associated with concepts are classified ?");
			 in = new BufferedReader(new InputStreamReader(System.in));
			String strCasesConceptThirdOutput = in.readLine();
			
			while (!(strCasesConceptThirdOutput.trim().equalsIgnoreCase("y") || strCasesConceptThirdOutput.trim().equalsIgnoreCase("n") ||
					strCasesConceptThirdOutput.trim().equalsIgnoreCase("")))
			{
				System.out.println("Please enter a valid response for the question !!!.");
				strCasesConceptThirdOutput = in.readLine();
			
			}
			if(strCasesConceptThirdOutput.trim().equalsIgnoreCase(""))
			{
				strCasesConceptThirdOutput="n";
			}
					
			//System.out.println("how cases are associated with concepts are classified---"+strCasesConceptThirdOutput);
			System.out.println("\n");*/
			/* OUTPUT 3 : How cases associated with concepts were classified: END*/
			
			
			
		
		/**********************START-- code to read the data file**********************/
		/*System.out.println("Please enter the name of the input data file and press enter\n");
		 in = new BufferedReader(new InputStreamReader(System.in));
		String strDataFileName = in.readLine();
		System.out.println("value input by user is for data file is---"+strRuleFileName);
		*/
//		String strDataFileName="class_ex.txt";
		
		/* InputStream fisDataFile = new FileInputStream(strDataFileName);
		    InputStreamReader isrDataFile = new InputStreamReader(fisDataFile, Charset.forName("UTF-8"));
		    BufferedReader brDataFile = new BufferedReader(isrDataFile);
		    String lineDataFile="";
		    dataFileDetails objdataFileDetails=null;
		    String[] strAttributeName= {""};
		    int intTempCaseNumber=1;
		    while ((lineDataFile = brDataFile.readLine()) != null) {
		        //System.out.println(line.trim());
		    	//System.out.println("before---"+lineDataFile.trim());
		    	lineDataFile=lineDataFile.replaceAll("^ +| +$| (?= )", "");
		    	//lineDataFile= 	lineDataFile.trim().replaceAll("[ ]{2,}", " ");
		    	//lineDataFile= filter(lineDataFile, " [\\s]+", " ");
		    	//lineDataFile= lineDataFile.trim();
		    	
		    	
		    	//System.out.println("after----"+lineDataFile.trim());
		    	
		    	if(lineDataFile.trim().length()>0)  // check for non empty line
		        {
		        	
		        if(!(lineDataFile.trim().startsWith("<") || lineDataFile.trim().endsWith(">")) ) // if line starts with ! i.e. its comment so ignore it
		        {
		        	if(lineDataFile.trim().startsWith("[") || lineDataFile.trim().endsWith("]")) // to check that the line starts with number, for the three number check
		        	{
		  String strAttributeNameSpaceSeparated=lineDataFile.trim().substring(lineDataFile.trim().indexOf("[")+1, lineDataFile.trim().indexOf("]"));
		  strAttributeName=	strAttributeNameSpaceSeparated.trim().split(" ");
		  
		//  for(String s:strAttributeName)
		  //System.out.println("strAttributeName--"+s);
		        	}
		        	
		        	else
			        {
		        		objdataFileDetails=new dataFileDetails();
		        		
		        		
			        	String[] strCases=lineDataFile.trim().split(" ");
			       // 	System.out.println("strCases size---"+strCases.length);
			        	
			        	if(strAttributeName.length==strCases.length)
			        	{	
			        		for(int j=0;j<strCases.length;j++)
			        	{
			        		
			        			objdataFileDetails.getLhmCases().put(strAttributeName[j], strCases[j]);
			        	}
			        	}
			        	objdataFileDetails.setIntCaseNumber(intTempCaseNumber);
			        	objdataFileDetails.setStrDecisionAttrName(strAttributeName[strAttributeName.length-1]);
			        	
			        	
			        	List<Entry<String,String>> entryList =
					    	    new ArrayList<Map.Entry<String, String>>(objdataFileDetails.getLhmCases().entrySet());
					    	Entry<String, String> lastEntry =
					    	    entryList.get(entryList.size()-1);
			        	
					    	objdataFileDetails.getLhmCaseDecisionValue().put(lastEntry.getKey(), lastEntry.getValue()); // to store the decision namd and value in separate LHM
			        	
					    	objdataFileDetails.getLhmCases().remove(objdataFileDetails.getStrDecisionAttrName());
					    	//to remove the last entry from the LinkedHashMap i.e. to remove the decision name and its value as it is stored in LhmCaseDecisionValue LinkedHasMap
					    	
					    	listdataFileDetails.add(objdataFileDetails);
			        	intTempCaseNumber++;
			        }
		        	
		        }
		        
		        }
		    }*/
		    // print for cases
		    /*System.out.println("\n******************************************************DATA FILE CONTENT ARE--********************************************---\n");
				
		    for(dataFileDetails obj:listdataFileDetails)
			{
				
				System.out.println("\nCase Number:- "+obj.getIntCaseNumber());
				System.out.println("attribute value pairs are---"+obj.getLhmCases());
				System.out.println("Decision value pairs are---"+obj.getLhmCaseDecisionValue());
				System.out.println("Decision Attribute Name:- "+obj.getStrDecisionAttrName());
				//System.out.println();
				
			}*/
			
			
	System.out.println("\n *************************************************START FOR CLASSIFICATION TEST************************************************** \n");	    
		    
			/*********************************START-- code to implement logic for *************************************************
			 * 1. not classified at all : with rule, neither LHS or RHS matches
			 * 2. Incorrectly classified: with rule, LHS matches
			 * 3. Correctly & incorrectly classified ( when we will introduce concept of strength or conditional probability then no case will exist in this scenario
			 * 4. correctly classfified : with rule complete LHS and RHS matches
			 * **********************/
			
		    // bascically this LinkedHashMap object is required for Correctly & incorrectly classified, to check and then to decide on the basis of strength or Cp
		LinkedHashMap<String, LinkedHashMap<Integer,Integer >> lhmClassificiationTable=new LinkedHashMap<String, LinkedHashMap<Integer,Integer >>();
		//     key= Concept1, LinkedHashMap<RuleNos,CaseNum >>
		//     key= Concept2, LinkedHashMap<RuleNum,CaseNum >>
		//     key= Concept3, LinkedHashMap<RuleNum,CaseNum >>
		
		ArrayList<Integer> listNotClassifiedAtAll=new ArrayList<Integer>(); // list to store the case numbers that are not classified at all 
		ArrayList<Boolean> boolListFlag=new ArrayList<Boolean>();
		
		ArrayList<conceptRuleCasesMapping> listConceptRuleCasesMapping=new ArrayList<conceptRuleCasesMapping>();
		
		conceptRuleCasesMapping objConceptRuleCasesMapping=null;
		
		ArrayList<Integer> listPartialMatchIncorrectlyClass=new ArrayList<Integer>(); // list to store the case numbers that are Partial match and incorrectly class 
		ArrayList<Integer> listPartialMatchCorrectlyClass=new ArrayList<Integer>(); // list to store the case numbers that are Partial match and correctly class 
		
		ArrayList<Integer> listCompleteMatchIncorrectlyClass=new ArrayList<Integer>(); // list to store the case numbers are Complete match and incorrectly class 
		ArrayList<Integer> listCompleteMatchCorrectlyClass=new ArrayList<Integer>(); // list to store the case numbers that are Complete match and correctly class 
		
		
		//looping for each cases in the decision table
		for(test1.dataFileDetails objData:listdataFileDetails) //parsing the decision table:
			//number of objects in this list is equal to the  number of cases we have in the decision table  
		{
			objConceptRuleCasesMapping=new conceptRuleCasesMapping(); 
			
			for(rulesFileDetails objRule:listRulesFileDetails)
			{
				
			//	for (Map.Entry<String, String> entryCase : objData.getLhmCases().entrySet()) { // looping through number of cases 
				    //System.out.println(entry.getKey()+" : "+entry.getValue());
				
				ArrayList<String> listKeysRuleAttribute = new ArrayList<>(objRule.getLhmConditions().keySet());// for each rule finding the keyset so that
				// after this we for each case X for each rule we can only fetch the key value pair for elements that are there in this list
				
				boolListFlag=new ArrayList<Boolean>();
				for( String temp:listKeysRuleAttribute) { //traversing through the LHS of rule, i.e. attribute name in LHS of conditions in Rule
					
					
					
					if(objData.getLhmCases().get(temp).trim().equals("*") || objData.getLhmCases().get(temp).trim().equals("-")  )//missing attribute handledd
					{
						boolListFlag.add(true); // no matter what the case be if respective attribute in rule has * or - in datatable then always its true
					} else if((objData.getLhmCases().get(temp).trim().equals("?")))
					{
						boolListFlag.add(false); 
					}
					else if((objRule.getLhmConditions().get(temp).contains("..") && !objData.getLhmCases().get(temp).contains(".."))){
					//	System.out.println("objRule.getLhmConditions().get(temp)--"+objRule.getLhmConditions().get(temp));
						
						String[] strRuleNumLowerUpperLimit=objRule.getLhmConditions().get(temp).split("\\.\\.");
						
				//	System.out.println("strRuleNumLowerUpperLimit size---"+strRuleNumLowerUpperLimit.length);
						// if (i >= minValueInclusive && i <= maxValueInclusive)
						
					//	System.out.println("strRuleNumLowerUpperLimit---"+strRuleNumLowerUpperLimit[0]);
					//	System.out.println("strRuleNumLowerUpperLimit---"+strRuleNumLowerUpperLimit[1]);
						if( (Double.valueOf(objData.getLhmCases().get(temp)) >= Double.valueOf( strRuleNumLowerUpperLimit[0])) &&
								( Double.valueOf(objData.getLhmCases().get(temp)) <= Double.valueOf( strRuleNumLowerUpperLimit[1]	)))
						{
							boolListFlag.add(true);
						}
						else
						{
							boolListFlag.add(false);
						}
						

						
					}else
					{
					if(objRule.getLhmConditions().get(temp).equalsIgnoreCase(objData.getLhmCases().get(temp))) // 
					{
						boolListFlag.add(true); // creating a bool list for each rule conditions will check respective attribute value pair and store true if matches else false
					}
					else
					{
						boolListFlag.add(false);
					}
					}
				
				}
			//	System.out.println("objData.getLhmCaseDecisionValue()--"+objData.getLhmCaseDecisionValue());
			//	System.out.println("objRule.getLhmDecision()-----------"+objRule.getLhmDecision());
				
				//boolean b = objData.getLhmCaseDecisionValue().entrySet().stream().filter(value -> objRule.getLhmDecision().entrySet().stream().anyMatch(value1 -> (value1.getKey() == value.getKey() && value1.getValue() == value.getValue()))).findAny().isPresent();
							
				if(objData.getLhmCaseDecisionValue().get(objData.getStrDecisionAttrName()).trim().equals(objRule.getLhmDecision().get(objData.getStrDecisionAttrName().trim())))
				{
					objConceptRuleCasesMapping.getLhmRuleAndCaseDecisionTrueOrFalseMap().put(objRule.getIntRuleNumber(),true );
				}// to store for each case, every rule(RHS) matches with the decision decision value or not
				//ex. Decision value in  table and in Rule(RHS)matche true/false---{1=true, 2=true, 3=false, 4=true}
				else
					{
					objConceptRuleCasesMapping.getLhmRuleAndCaseDecisionTrueOrFalseMap().put(objRule.getIntRuleNumber(),false );
					}
				
				objConceptRuleCasesMapping.setIntCaseNumber(objData.getIntCaseNumber());
				objConceptRuleCasesMapping.getLhmRuleMatching().put(objRule.getIntRuleNumber(), boolListFlag);
				if(boolListFlag.contains(false))
				{
				objConceptRuleCasesMapping.getLhmRuleTrueOrFalseMap().put(objRule.getIntRuleNumber(), false);
				}
				else
				{
					objConceptRuleCasesMapping.getLhmRuleTrueOrFalseMap().put(objRule.getIntRuleNumber(), true);	
				}
				
				if(boolListFlag.contains(false ) && boolListFlag.contains(true))
				{
					objConceptRuleCasesMapping.getLhmRuleTrueOrFalsePartialMatchingMap().put(objRule.getIntRuleNumber(), true);
				}
				else
				{
					objConceptRuleCasesMapping.getLhmRuleTrueOrFalsePartialMatchingMap().put(objRule.getIntRuleNumber(), false);
				}
				
				
				//System.out.println("For case---"+objData.getIntCaseNumber()+"---and RUle Number--"+objRule.getIntRuleNumber()+"---boolean flag list is--"+boolListFlag);
				/*if(boolListFlag.contains(false))
				{
					System.out.println("For case---"+objData.getIntCaseNumber()+"--- DOESNT MATCH---NOT Classified at all");
				}
				else
				{
					System.out.println("For case---"+objData.getIntCaseNumber()+"---MATCH--- Classified at all");
				}*/
			}

			listConceptRuleCasesMapping.add(objConceptRuleCasesMapping);
		}
		
		//ArrayList<Boolean> listRHSMatchesOrNot=new ArrayList<Boolean>();
		
		
			for(conceptRuleCasesMapping objTemp:listConceptRuleCasesMapping)
			{
				Boolean boolNotClassifiedAtAll=true;
				
			//	System.out.println("\n \n  For case Number---"+objTemp.getIntCaseNumber());
				
			//	System.out.println("Rule number and matching mapping is---"+objTemp.getLhmRuleMatching());
				
			//	System.out.println("Rule number and matching FINAL CONVERTED TO ONE mapping is (COMPLETE MATCHING)---"+objTemp.getLhmRuleTrueOrFalseMap());
				
			//	System.out.println("Rule number and matching final converted to one mapping (PARTIAL MATCHING)----"+objTemp.getLhmRuleTrueOrFalsePartialMatchingMap());
				
			//	System.out.println("Decision value in  table and in Rule(RHS)matche true/false---"+objTemp.getLhmRuleAndCaseDecisionTrueOrFalseMap());
				
				ArrayList<Integer> listRuleNumberTrueValue=new ArrayList<Integer>();
				//this list will store all the rule number for which our hashmap has true value, i.e. the list will contain all the rule numbers for which
				//the condition of the rule mmatches with the case conditions		
				
				ArrayList<Integer> listRuleNumberTrueValuePartialMatching=new ArrayList<Integer>();
				//this list will store all the rule number for which our hashmap has true value, i.e. the list will contain all the rule numbers for which
				//the condition of the rule partially mmatches with the case conditions	
				
				//complete matching
				for (Map.Entry<Integer, Boolean> entryTemp : objTemp.getLhmRuleTrueOrFalseMap().entrySet()) { 
				   			// ex. {1=false, 2=false, 3=false, 4=false} this is parsed 1,2,3,4 rules and for each case it matches or not
					if(entryTemp.getValue()==true) 
						{
						listRuleNumberTrueValue.add(entryTemp.getKey());
				// as soon the case is true for any of the rule, it shows that it n=is not in the NOT CLASSIFIED CATEGORY
				}
				}
				
				//partial matching
				if(listRuleNumberTrueValue.isEmpty())
				{
				for (Map.Entry<Integer, Boolean> entryTemp : objTemp.getLhmRuleTrueOrFalsePartialMatchingMap().entrySet()) { 
				   			// ex. {1=false, 2=false, 3=false, 4=false} this is parsed 1,2,3,4 rules and for each case it matches or not
					if(entryTemp.getValue()==true) 
						{
						listRuleNumberTrueValuePartialMatching.add(entryTemp.getKey());
				// as soon the case is true for any of the rule, it shows that it n=is not in the NOT CLASSIFIED CATEGORY
				}
				}
				}
				
				
			if(listRuleNumberTrueValue.size()==0 && listRuleNumberTrueValuePartialMatching.size()==0) 
				// this list contains the rule number for which LHS matches with the particular case so checking for partial and complete matching both
				//if both the list contains 0 size,i.e. none of them has any element then not classified at all, LHS doesnt matched with any conditions for this case
			{
				//System.out.println("For case Number---"+objTemp.getIntCaseNumber()+"---NOT CLASSIFIED AT ALL");
				listNotClassifiedAtAll.add(objTemp.getIntCaseNumber());
			}
			else
			{
				
				//System.out.println("listRuleNumberTrueValue(COMPLETE MATCHING)-"+listRuleNumberTrueValue);// complete matching
				//System.out.println("listRuleNumberTrueValuePartialMatching(PARTIAL MATCHING)-"+listRuleNumberTrueValuePartialMatching);// partial matching
				
				/*1 complete matching start*/
	if(listRuleNumberTrueValue.size()>0)
	{
				for(int temp:listRuleNumberTrueValue)
				{
					
					if(objTemp.getLhmRuleAndCaseDecisionTrueOrFalseMap().get(temp))
					{
						listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().put(temp, true);
								
					}
					else
					{
						listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().put(temp, false);
					}
						
				}
	}else
	{
		//System.out.println("No complete matching for this case with any of the rules");
	}
				/*1 complete matching end*/
				
				/*1 partial matching start*/
	if(listRuleNumberTrueValuePartialMatching.size()>0)
	{
				for(int temp:listRuleNumberTrueValuePartialMatching)
				{
					
					if(objTemp.getLhmRuleAndCaseDecisionTrueOrFalseMap().get(temp))
					{
						listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().put(temp, true);
								
					}
					else
					{
						listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().put(temp, false);
					}
						
				}
	}else
	{
	//System.out.println("No PARTIAL matching for this case with any of the rules");
	}
	/*1 partial matching end*/
				
				/*2 complete matching start*/
	if(listRuleNumberTrueValue.size()>0)
	{
				if(!listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().containsValue(true))
				{
					//not contains trues, i.e. all are false so this particular case is increectly classified
					//System.out.println("For case Number(COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---INCORRECTLY CLASSIFIED");
					listCompleteMatchIncorrectlyClass.add(objTemp.getIntCaseNumber());
				}
				else if(!listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().containsValue(false))
				{
					//not contains false , i.e. all are true so this particular case is correctly classified
				//	System.out.println("For case Number(COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---CORRECTLY CLASSIFIED");
					listCompleteMatchCorrectlyClass.add(objTemp.getIntCaseNumber());
				}
				else
				{
					/*i.e. contains mixture of true false so have to decide by strength or Cp*/
					
					
					/*###############################33test for partial done here START############################3333*/
					
					/*System.out.println("KEYSET---"+listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().keySet());
					
					for (Map.Entry<Integer, Boolean> entryTemp : listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().entrySet()) {
						
						
						System.out.println("corresponding true false values are---"+
								listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(entryTemp.getKey()));
						
						System.out.println("corresponding true false values are HARDCODED---"+
								listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(4));
						
						
						
						int occurrences = Collections.frequency(listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(entryTemp.getKey()), true);
						System.out.println("occurrences--------"+occurrences);
						
						int intSize=listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(4).size();
						
						System.out.println("size of the array or total number of conditions in the rules are---"+intSize);
						
					}*/
					
					/*######################################test for partial done here END############################################*/
					
					Double doubDecidingfactorCompleteMatching=0.0;
					/*
					 *  case when support of other rules not taken
					 * = (Strength or conditional probability) * ( Specifity or 1 ) * 1 ( partial matching factor)
					 * 
					 * case when support of other rules taken
					 * = Summation { (Strength or conditional probability) * ( Specifity or 1 ) * 1 ( partial matching factor)}
					 * 
					 * */
					
					
					
					//System.out.println("For case Number(COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---CHOOSE STRENGTH or CONDITIONAL PROBABILITY");
					
					Double doubStrengthOrCp=0.0;
					Double doubSpecificity=0.0;
					
					LinkedHashMap<Integer,Double> lhmTempRuleAndStrengthMap=new LinkedHashMap<Integer,Double>(); // to store rule number and respective strength*specicficty
					
					for (Map.Entry<Integer, Boolean> entryTemp : listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().entrySet()) { 
					//integer corresponds to the rule number
						/*// now if it contains mixture of true or false we need to find the strength of the corrensponding rule cases,
						 *the rule case that has larger strength wins..so if winner has true as value then corrrectly classified 
						 *else if winner has false value in this hashmap then incorrectly classified*/ 
						
					// for complete matching calculating (Strength or conditional probability) * ( Specifity or 1 )
						// so checking if user has asked for strength then fetching particular rule strength or calculating CP( strength/no. of case matching respectively
						// checking if user has asked to consider the factor associated with specificty then finding respective rule specificty else taking it as 1
						// putting in formula strength or cp X specificty or 1
						doubDecidingfactorCompleteMatching=0.0;
						doubStrengthOrCp=0.0;
						doubSpecificity=0.0;
						
						/**************************************1st factor: matching factor**************************************/
						// for complete matching always 1
						
						/***************************************2nd factor: specificity factor***************************************/
						
						if(strSpecificityFactor.trim().equalsIgnoreCase("y")) // is specificty is yes then find specificity of correspodning rule as take as 1
						{
							doubSpecificity=(double) listRulesFileDetails.get(entryTemp.getKey()-1).getIntSpecificity();
						}
						else
						{
							doubSpecificity=(double) 1;
						}
						
						/*************************************** 3rd factor: strength factor***************************************/
						
						if(strStrengthfactor.trim().equals("s")) // if strength factor is strength or conditional probability
						{
							doubStrengthOrCp=(double) listRulesFileDetails.get(entryTemp.getKey()-1).getIntStrength();
						
						} else
						{
							doubStrengthOrCp = (double) (listRulesFileDetails.get(entryTemp.getKey()-1).getIntStrength()/(double)listRulesFileDetails.get(entryTemp.getKey()-1).getIntTotNoMatchTrainCases());
						
						
						}
						
						doubDecidingfactorCompleteMatching=doubStrengthOrCp*doubSpecificity;
						lhmTempRuleAndStrengthMap.put(entryTemp.getKey(), doubDecidingfactorCompleteMatching);
						
						
					}
					
					/*************************************** 4th factor: support of other rules*******************************LEFT********/
					
					//System.out.println("lhmTempRuleAnd Deciding factor Map----"+lhmTempRuleAndStrengthMap);
					if(strSupportFactor.trim().equals("y"))
					{
						//lhmTempRuleAndStrengthMap this lhm contains rule number and deciding factor, have to make new lhm for decision value and deciding factor.
						/*// example: {1=2.0, 3=3.0}, 1 and 3 are the rule number and corresponding is the doubDecidingfactorCompleteMatching factor, higher the value will decide the fate of the rule/
						// now suppose we have  {1=2.0, 2=3.0, 3=6.0, 4=7.0 5=8.0}, but here the
						Rule 1(key): concept +
						Rule 2(key): concept -
						Rule 3(key): concept +
						Rule 4(key): concept -
						Rule 5(key)- concept *
						// so here in case of support it would be : 
						 * for concept(+)= Rule(1 +3)  // 1 and 3 include the support
						 * for concept(-)= Rule(2 +4)
						 * for concept(*)= Rule(5)
						 * higher the value for a particular concept, it decides the fate of the rule , so now suppose if concept(+) has the highest value 15 out of (-) and (*) , then if the corresponding case has concept(+) then correctly classified else
						 * incorrectly 
						 * so we have to make a linkedhashmap of 
						 * {+= sum of all doubDecidingfactorCompleteMatching with + decision value, -= sum of all doubDecidingfactorCompleteMatching with - decision value, * = sum of all doubDecidingfactorCompleteMatching with * decision value}
		*/				
						LinkedHashMap<String, Double> lhmSupportSumDecidingFactor=new LinkedHashMap<String, Double>();
						
						
						for (Map.Entry<Integer,Double> entryTemp : lhmTempRuleAndStrengthMap.entrySet()) { 
							
					Double tempDecidingFactor=0.0;		
				if(lhmSupportSumDecidingFactor.containsKey(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName())))			
				{
					tempDecidingFactor=lhmSupportSumDecidingFactor.get(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName()))+entryTemp.getValue();
					// if the decision value ( +, - or * already exist then summation of respective decision value 
					
																	  // fetching rule number     fetch decision HM // fetch the key value for this decision attribute variable corresponding deciison value
					lhmSupportSumDecidingFactor.put(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName()), tempDecidingFactor);
					// storing respective rule number's decision value along with the deciding factor
				}
				else	
				{
					lhmSupportSumDecidingFactor.put(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName()),
									entryTemp.getValue());
					// if decision value already not exist in the lhm then adding its entry there.
							
						}
				
				//System.out.println("lhmSupportSumDecidingFactor--DECISION SUPPORT PAIR--"+lhmSupportSumDecidingFactor);
				
						}		
						
						  // when user doesnt want to use supporting factor, then this lhm will contain, each conflicting rule number and corresponding deciding factor value(individual), higher the value decide the fate of the case.
						//System.out.println("lhmSupportSumDecidingFactor--SUPPORT OF OTHER RULES DECIDING FACTOR ARE(COMPLETE MATCHING)---"+lhmSupportSumDecidingFactor);
						
						//System.out.println("rule with max SUPPORT DECIDING FACTOR is(COMPLETE MATCHING)---"+Collections.max(lhmSupportSumDecidingFactor.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey());
						// with maximum support so here will get the, decision rule, either +, either - or either * ( will get 1 decision value for which deciding factor is maximum.
						
			// finding the maximum deciding factor value and corresponding key i.e. + or - or *                   and comparing it with the particular case number decision value, fetching case   , fetching decision value, key is decision name.	
		if(Collections.max(lhmSupportSumDecidingFactor.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey().equalsIgnoreCase(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getLhmCaseDecisionValue().
				get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName())))				
						
		{
			//System.out.println("For case Number (COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---CORRECTLY CLASSIFIED");
			listCompleteMatchCorrectlyClass.add(objTemp.getIntCaseNumber());
		}
		else
		{
			//System.out.println("For case Number (COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---INCORRECTLY CLASSIFIED");
			
			listCompleteMatchIncorrectlyClass.add(objTemp.getIntCaseNumber());
		}
						
				}
					else
					{  
						/*************************************** 4th factor: support of other rules NOT THERE*******************************LEFT********/
						
		// when user doesnt want to use supporting factor, then this lhm will contain, each conflicting rule number and corresponding deciding factor value(individual),
						//higher the value decide the fate of the case.
		//System.out.println("lhmTempRuleAndStrengthMap--DECIDING FACTOR ARE(COMPLETE MATCHING)---"+lhmTempRuleAndStrengthMap);
						
		/*System.out.println("rule with max DECIDING FACTOR is(COMPLETE MATCHING)---"
		+Collections.max(lhmTempRuleAndStrengthMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey());
*/
		if(listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping().get(Collections.max(lhmTempRuleAndStrengthMap.entrySet(),
				Comparator.comparingDouble(Map.Entry::getValue)).getKey())==false)
		{
			//System.out.println("For case Number (COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---INCORRECTLY CLASSIFIED");
			listCompleteMatchIncorrectlyClass.add(objTemp.getIntCaseNumber());
		}
		else
		{
			//System.out.println("For case Number (COMPLETE MATCHING)---"+objTemp.getIntCaseNumber()+"---CORRECTLY CLASSIFIED");
			listCompleteMatchCorrectlyClass.add(objTemp.getIntCaseNumber());
		}
						
					}
					
				
				}
	}
				/*2 complete matching end*/
				
				/*2 partial matching start*/
	if(listRuleNumberTrueValuePartialMatching.size()>0)
	{
				if(listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().size()>0)
				{
				if(!listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().containsValue(true))
				{
					//not contains trues, i.e. all are false so this particular case is increectly classified
					//System.out.println("For case Number(PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---INCORRECTLY CLASSIFIED");
					listPartialMatchIncorrectlyClass.add(objTemp.getIntCaseNumber());
				}
				else if(!listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().containsValue(false))
				{
					//not contains false , i.e. all are true so this particular case is correctly classified
					//System.out.println("For case Number(PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---CORRECTLY CLASSIFIED");
					listPartialMatchCorrectlyClass.add(objTemp.getIntCaseNumber());
				}
				else
				{
					/*i.e. contains mixture of true false so have to decide by strength or Cp*/
					
					Double doubDecidingfactorPartialMatching=0.0;
					Double doubStrengthOrCpPartialMatching=0.0;
					Double doubSpecificityPartialMatching=0.0;
					Double doubPartialMatchingFactorPartialMatching=0.0;
					
					/*
					 *  case when support of other rules not taken
					 * = (Strength or conditional probability) * ( Specifity or 1 ) * ( partial matching factor)
					 * 
					 * case when support of other rules taken
					 * = Summation { (Strength or conditional probability) * ( Specifity or 1 ) * ( partial matching factor)}
					 * 
					 * */
					
					//System.out.println("For case Number(PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---CHOOSE STRENGTH or CONDITIONAL PROBABILITY");
					
					LinkedHashMap<Integer,Double> lhmTempRuleAndStrengthMap=new LinkedHashMap<Integer,Double>();
					
		for (Map.Entry<Integer, Boolean> entryTemp : listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().entrySet()) { 
			 
			//integer corresponds to the rule number
				/*// now if it contains mixture of true or false we need to find the strength of the corrensponding rule cases,
				 *the rule case that has larger strength wins..so if winner has true as value then corrrectly classified 
				 *else if winner has false value in this hashmap then incorrectly classified*/ 
				
			// for complete matching calculating (Strength or conditional probability) * ( Specifity or 1 )
				// so checking if user has asked for strength then fetching particular rule strength or calculating CP( strength/no. of case matching respectively
				// checking if user has asked to consider the factor associated with specificty then finding respective rule specificty else taking it as 1
				// putting in formula strength or cp X specificty or 1
			doubDecidingfactorPartialMatching=0.0;
			doubStrengthOrCpPartialMatching=0.0;
			doubSpecificityPartialMatching=0.0;
			doubPartialMatchingFactorPartialMatching=0.0;
			
			
			/**************************************1st factor: matching factor**************************************/
			
			if(strMatchingFactor.trim().equals("y"))
			{
				/*partial matching factor= number of conditions matching the case for this rule/ total number of conditions in the rule(specificity)*/
				//listRuleNumberTrueValuePartialMatching  - contains rule number(s) that got partial matching in conditions of the rules
				//listConceptRuleCasesMapping - contains inforamtion of the cases and mapping rules and all 
				// getLhmRuleMatching ---for each case it stores values as-- {1=[false], 2=[false], 3=[false], 4=[true, false]}
				//asas
			//System.out.println("KEYSET---"+listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().keySet());
			// this lhm contains value {1=false, 3=true}, i.e. for particular case rule 1 has false match with case and rule3 has true match i.e. decision values matches
			
			
			for (Map.Entry<Integer, Boolean> entryTemp1 : listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().entrySet()) {
				
				
			//	System.out.println("corresponding true false values are---"+
					//	listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(entryTemp1.getKey()));
				
			//	System.out.println("corresponding true false values are HARDCODED---"+
				//		listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(4));
				
	Double occurrences = (double) Collections.frequency(listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(entryTemp1.getKey()), true);
				//getLhmRuleMatching contains info {1=[true], 2=[false], 3=[true], 4=[false, true]}, this particular case and respective matching with all the rules
				// this means this case, for rule 4 1st condition doesnt match and second condition matches
				
				//System.out.println("occurrences--------"+occurrences);
				
				Double intSize=(double) listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmRuleMatching().get(entryTemp1.getKey()).size();
				
			//	System.out.println("size of the array or total number of conditions in the rules are---"+intSize);
				
				doubPartialMatchingFactorPartialMatching=occurrences/intSize;// number of conditions in rule matching the case/ total number of conditions in the rule
				
			}
				
			}
			else
			{
				doubPartialMatchingFactorPartialMatching=1.0;
			}
			
			
			/***************************************2nd factor: specificity factor***************************************/
			
			if(strSpecificityFactor.trim().equalsIgnoreCase("y")) // is specificty is yes then find specificity of correspodning rule as take as 1
			{
				doubSpecificityPartialMatching=(double) listRulesFileDetails.get(entryTemp.getKey()-1).getIntSpecificity();
			}
			else
			{
				doubSpecificityPartialMatching=1.0;
			}
			
			/*************************************** 3rd factor: strength factor***************************************/	
				if(strStrengthfactor.trim().equals("s")) // if strength factor is strength or conditional probability
				{
					doubStrengthOrCpPartialMatching=(double) listRulesFileDetails.get(entryTemp.getKey()-1).getIntStrength();
				
				} else
				{
					doubStrengthOrCpPartialMatching = (double) (listRulesFileDetails.get(entryTemp.getKey()-1).getIntStrength()/(double)listRulesFileDetails.get(entryTemp.getKey()-1).getIntTotNoMatchTrainCases());
				
				
				}
		
				
									
				doubDecidingfactorPartialMatching=doubStrengthOrCpPartialMatching*doubSpecificityPartialMatching*doubPartialMatchingFactorPartialMatching;
				lhmTempRuleAndStrengthMap.put(entryTemp.getKey(), doubDecidingfactorPartialMatching);
				
				
					}
		
		
		
		/*************************************** 4th factor: support of other rules*******************************LEFT********/
		
		
		if(strSupportFactor.trim().equals("y"))
		{
			//lhmTempRuleAndStrengthMap this lhm contains rule number and deciding factor, have to make new lhm for decision value(+ - *) and deciding factor.
			/*// example: {1=2.0, 3=3.0}, 1 and 3 are the rule number and corresponding is the doubDecidingfactorPartialMatching factor, higher the value will
			 *  decide the fate of the rule/
			// now suppose we have  {1=2.0, 2=3.0, 3=6.0, 4=7.0 5=8.0}, but here the
			Rule 1(key): concept +
			Rule 2(key): concept -
			Rule 3(key): concept +
			Rule 4(key): concept -
			Rule 5(key)- concept *
			// so here in case of support it would be : 
			 * for concept(+)= Rule(1 +3)  // 1 and 3 include the support
			 * for concept(-)= Rule(2 +4)
			 * for concept(*)= Rule(5)
			 * higher the value for a particular concept, it decides the fate of the rule , so now suppose if concept(+) has the highest value 15 out of (-) and (*) ,
			 *  then if the corresponding case has concept(+) then correctly classified else		 * incorrectly 
			 * so we have to make a linkedhashmap of 
			 * {+= sum of all doubDecidingfactorCompleteMatching with + decision value, -= sum of all doubDecidingfactorCompleteMatching with - decision value,
			 *  * = sum of all doubDecidingfactorCompleteMatching with * decision value}
	*/				
			LinkedHashMap<String, Double> lhmSupportSumDecidingFactor=new LinkedHashMap<String, Double>();
			
			
			for (Map.Entry<Integer,Double> entryTemp : lhmTempRuleAndStrengthMap.entrySet()) { //traverse through {1=2.0, 2=3.0, 3=6.0, 4=7.0 5=8.0}
				
		Double tempDecidingFactor=0.0;		
		
		//lhmSupportSumDecidingFactor, checking whether this LHM contains + -  or * before hand or not, if yes then add the previous value
	if(lhmSupportSumDecidingFactor.containsKey(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).
			getStrDecisionAttrName())))			
	{
		
		tempDecidingFactor=lhmSupportSumDecidingFactor.get(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName()))+entryTemp.getValue();
		// if the decision value ( +, - or * already exist then summation of respective decision value 
		
														  // fetching rule number     fetch decision HM // fetch the key value for this decision attribute variable corresponding deciison value
		lhmSupportSumDecidingFactor.put(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).
				getStrDecisionAttrName()), tempDecidingFactor);
		// storing respective rule number's decision value along with the deciding factor
	}
	else	
	{
		lhmSupportSumDecidingFactor.put(listRulesFileDetails.get(entryTemp.getKey()-1).getLhmDecision().get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).
				getStrDecisionAttrName()),
						entryTemp.getValue());
		// if decision value already not exist in the lhm then adding its entry there.
				
			}
			}		
			
			  // when user  want to use supporting factor, then this lhm will contain, each unique decision value and corresponding deciding factor value(individual), higher the value decide the fate of the case.
			//System.out.println("lhmSupportSumDecidingFactor--SUPPORT OF OTHER RULES DECIDING FACTOR ARE(PARTIAL MATCHING)---"+lhmSupportSumDecidingFactor);
			
			//System.out.println("rule with max SUPPORT DECIDING FACTOR is(PARTIAL MATCHING)---"+Collections.max(lhmSupportSumDecidingFactor.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey());
			// with maximum support so here will get the, decision rule, either +, either - or either * ( will get 1 decision value for which deciding factor is maximum.
			
	// finding the maximum deciding factor value and corresponding key i.e. + or - or *                   and comparing it with the particular case number decision value, fetching case   , fetching decision value, key is decision name.	
	if(Collections.max(lhmSupportSumDecidingFactor.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey().equalsIgnoreCase(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getLhmCaseDecisionValue().
	get(listdataFileDetails.get(objTemp.getIntCaseNumber()-1).getStrDecisionAttrName())))				
			
	{
	//System.out.println("For case Number (PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---CORRECTLY CLASSIFIED");
	listPartialMatchCorrectlyClass.add(objTemp.getIntCaseNumber());

	}
	else
	{
	//System.out.println("For case Number (PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---INCORRECTLY CLASSIFIED");

	listPartialMatchIncorrectlyClass.add(objTemp.getIntCaseNumber());
	}
			
		}
		else
		{  
			/*************************************** 4th factor: support of other rules NOT THERE*******************************LEFT********/
		

	//when user doesnt want to use supporting factor, then this lhm will contain, each conflicting rule number and corresponding deciding factor value(individual),
			//higher the value decide the fate of the case.
	//System.out.println("lhmTempRuleAndStrengthMap--DECIDING FACTOR ARE(PARTIAL MATCHING)---"+lhmTempRuleAndStrengthMap);

	//System.out.println("rule with max DECIDING FACTOR is(PARTIAL MATCHING)---"+Collections.max(lhmTempRuleAndStrengthMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey());

	if(listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch().get(Collections.max(lhmTempRuleAndStrengthMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey())==false)
	{
	//System.out.println("For case Number (PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---INCORRECTLY CLASSIFIED");
	listPartialMatchIncorrectlyClass.add(objTemp.getIntCaseNumber());
	}
	else
	{
	//System.out.println("For case Number (PARTIAL MATCHING)---"+objTemp.getIntCaseNumber()+"---CORRECTLY CLASSIFIED");
	listPartialMatchCorrectlyClass.add(objTemp.getIntCaseNumber());
	}


		}
			
				}
				
			}
	}	//2 partial matching end 
			}
			//System.out.println("rule and corresponding decision value true or false(COMPLETE MATCHING)---"+listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMapping());
			//System.out.println("rule and corresponding decision value true or false(PARTIAL MATCHING)---"+listConceptRuleCasesMapping.get(objTemp.getIntCaseNumber()-1).getLhmForLHSTrueRHSMappingPartialMatch());
			}
			
			System.out.println("\n\n****************************GENERAL STATISTICS****************************\n\n");
			
			int intTotalConditions=0;
			for(rulesFileDetails temp :listRulesFileDetails)
			{
				intTotalConditions+=temp.getLhmConditions().size();
			}
			//System.out.println("The report was created from: " +strRuleFileName+" and from: "+strDataFileName);
			System.out.println("The total number of cases : "+listdataFileDetails.size());
			System.out.println("The total number of attributes : "+listdataFileDetails.get(0).getLhmCases().size());
			System.out.println("The total number of rules : "+listRulesFileDetails.size());
			System.out.println("The total number of conditions : "+intTotalConditions);
			System.out.println("The total number of cases that are not classified : "+listNotClassifiedAtAll.size());
			
			System.out.println("\t \t PARTIAL MATCHING");
			System.out.println("\t The total number of cases that are incorrectly classified: "+listPartialMatchIncorrectlyClass.size());
			System.out.println("\t The total number of cases that are correctly classified: "+listPartialMatchCorrectlyClass.size());
		    
			System.out.println("\t \t COMPLETE MATCHING");
			System.out.println("\t The total number of cases that are incorrectly classified: "+listCompleteMatchIncorrectlyClass.size());
			System.out.println("\t The total number of cases that are correctly classified: "+listCompleteMatchCorrectlyClass.size());
			
			Double doubTotNotClassIncorrectlyClass=	(double) (listNotClassifiedAtAll.size()+listPartialMatchIncorrectlyClass.size()+listCompleteMatchIncorrectlyClass.size());
			//Double doubTotNotClassIncorrectlyClass=	(double) (listNotClassifiedAtAll.size()+listCompleteMatchIncorrectlyClass.size());
			doubErrorRate=  Math.round(((doubTotNotClassIncorrectlyClass/listdataFileDetails.size())*100.0)*100.0)/100.0;
			
			
			System.out.println("\t \t COMPLETE MATCHING AND PARTIAL MATCHING");
			System.out.println("The total number of cases that are not classified or incorrectly classified: "+ doubTotNotClassIncorrectlyClass.intValue());
			System.out.println("Error rate :"+doubErrorRate+"%");
			// error rate= (total cases-correctly classified)/total cases)
			
			
			
			
			/***************************************concept statistics start logic*************************************** START*/
			ArrayList<String> listUniqueDecisionValue=null;
			
				listUniqueDecisionValue=new ArrayList<String>();
			for( test1.dataFileDetails objTemp: listdataFileDetails )
			{
				for (Entry<String, String> entryTemp : objTemp.getLhmCaseDecisionValue().entrySet()) {
					
					if(!listUniqueDecisionValue.contains(entryTemp.getValue()))
					{
						listUniqueDecisionValue.add(entryTemp.getValue());
					}
			}
			}
			
			//System.out.println("listUniqueDecisionValue--"+listUniqueDecisionValue);
			
			//for(String str:listUniqueDecisionValue)
			//System.out.println("\tConcept("+ listdataFileDetails.get(0).getStrDecisionAttrName()+", "+ str+")");
			
			
			
			LinkedHashMap<String, ArrayList<Integer>> listDecisionCasesMapping=new LinkedHashMap<String, ArrayList<Integer>>();
			// example: {+=[1, 3, 6], -=[2, 4, 5, 7, 8]}
			
			for(String str:listUniqueDecisionValue) // for each unique decision value checking all cases, for decision + check 1,2,3,4,5,6,7...
				//similarly in next loop for decision -, check cases 1,2,3,4,5,6,7,,....
			{
				ArrayList<Integer> listOfCasesForEachDecision=new ArrayList<Integer>();
				
			for(test1.dataFileDetails objTemp: listdataFileDetails)
			{
				
				if(objTemp.getLhmCaseDecisionValue().get(objTemp.getStrDecisionAttrName()).equalsIgnoreCase(str))
				{
					listOfCasesForEachDecision.add(objTemp.getIntCaseNumber());// creating a list of case number that belong to each unique decision value(concept)
				}
				
			}																		   //concept= list of cases associated with it in data table
			listDecisionCasesMapping.put(str, listOfCasesForEachDecision); // example: {+=[1, 3, 6], -=[2, 4, 5, 7, 8]}
			
			}
			/*System.out.println("\nNot classified at alt---"+listNotClassifiedAtAll);
			System.out.println("partial incorrect---"+listPartialMatchIncorrectlyClass);
			System.out.println("partial correct---"+listPartialMatchCorrectlyClass);
			System.out.println("Complete incorrect---"+listCompleteMatchIncorrectlyClass);
			System.out.println("Complete correct---"+listCompleteMatchCorrectlyClass);*/
			
			
			//System.out.println("concept and case mapping is---"+listDecisionCasesMapping);
			
			ArrayList<thirdOutputDecisionMapping> listThirdOutputConceptCaseMatchMapping=new ArrayList<thirdOutputDecisionMapping>();
			
			ArrayList<Integer> listPartialIncorrectlyClassDecisionMap=new ArrayList<Integer>();
			ArrayList<Integer> listPartialCorrectlyClassDecisionMap=new ArrayList<Integer>();
			ArrayList<Integer> listCompleteIncorrectlyClassDecisionMap=new ArrayList<Integer>();
			ArrayList<Integer> listCompleteCorrectlyClassDecisionMap=new ArrayList<Integer>();
			
		/*	if(strConceptStats.trim().equalsIgnoreCase("y"))
			{
			System.out.println("\n**************************CONCEPT STATITSICS**************************\n");
			}
				*/	
			for (Entry<String, ArrayList<Integer>> entryTemp : listDecisionCasesMapping.entrySet())// traversing {+=[1, 3, 6], -=[2, 4, 5, 7, 8]}
			{
				listPartialIncorrectlyClassDecisionMap=new ArrayList<Integer>();
				listPartialCorrectlyClassDecisionMap=new ArrayList<Integer>();
				listCompleteIncorrectlyClassDecisionMap=new ArrayList<Integer>();
				listCompleteCorrectlyClassDecisionMap=new ArrayList<Integer>();
				
				
				thirdOutputDecisionMapping objTemp=new thirdOutputDecisionMapping();
				
				ArrayList<Integer> listTempCase=new ArrayList<Integer>();
				
				listTempCase=entryTemp.getValue();
				
				for( Integer tempCase:listTempCase)// first loop [1, 3, 6] it will have cases [1, 3, 6] for + and second loop it will have cases [2, 4, 5, 7, 8] for decision -
				{
					if(listPartialMatchIncorrectlyClass.contains(tempCase))
					{
						listPartialIncorrectlyClassDecisionMap.add(tempCase);
					}
					if(listPartialMatchCorrectlyClass.contains(tempCase))
					{
						listPartialCorrectlyClassDecisionMap.add(tempCase);
					}
				    if(listCompleteMatchIncorrectlyClass.contains(tempCase))
					{
						listCompleteIncorrectlyClassDecisionMap.add(tempCase);
					}
					if(listCompleteMatchCorrectlyClass.contains(tempCase))
					{
						listCompleteCorrectlyClassDecisionMap.add(tempCase);
					}
				}
				objTemp.setStrDecisionValue(entryTemp.getKey());
				objTemp.setListPartialIncorrectlyClassDecisionMap(listPartialIncorrectlyClassDecisionMap);
				objTemp.setListPartialCorrectlyClassDecisionMap(listPartialCorrectlyClassDecisionMap);
				objTemp.setListCompleteIncorrectlyClassDecisionMap(listCompleteIncorrectlyClassDecisionMap);
				objTemp.setListCompleteCorrectlyClassDecisionMap(listCompleteCorrectlyClassDecisionMap);
				
				listThirdOutputConceptCaseMatchMapping.add(objTemp);
				
				/*******************************************************printing 2nd output CONCEPT STATISTICS*****************************************************/ 
				//dsd
			/*	if(strConceptStats.trim().equalsIgnoreCase("y"))
				{
				
				System.out.println("\tConcept("+ listdataFileDetails.get(0).getStrDecisionAttrName()+", "+ entryTemp.getKey()+")");
				
				System.out.println("The total number of cases that are not classified : "+listNotClassifiedAtAll.size());
				
				System.out.println("\t \t PARTIAL MATCHING");
				System.out.println("\t The total number of cases that are incorrectly classified: "+listPartialIncorrectlyClassDecisionMap.size());
				System.out.println("\t The total number of cases that are correctly classified: "+listPartialCorrectlyClassDecisionMap.size());
			    
				System.out.println("\t \t COMPLETE MATCHING");
				System.out.println("\t The total number of cases that are incorrectly classified: "+listCompleteIncorrectlyClassDecisionMap.size());
				System.out.println("\t The total number of cases that are correctly classified: "+listCompleteCorrectlyClassDecisionMap.size());
				
				System.out.println("The total number of cases in the concept: : "+entryTemp.getValue().size());
				System.out.println("\n");
			}*/
			}
			
			
			
			
			
			//System.out.println("\n");
			
			
			
			/*code for printing output 3 Starts*/
			
			/*if(strCasesConceptThirdOutput.trim().equalsIgnoreCase("y"))
			{
			System.out.println("\n**************************HOW CASES ASSOCIATED WITH CONCEPTS WERE CLASSIFIED**************************\n");
			}*/
			
			/*if(strCasesConceptThirdOutput.trim().equalsIgnoreCase("y"))
			{
				
				for(thirdOutputDecisionMapping temObj:listThirdOutputConceptCaseMatchMapping)
				{
			System.out.println("\tConcept("+ listdataFileDetails.get(0).getStrDecisionAttrName()+", "+ temObj.getStrDecisionValue()+")");
			
			System.out.println("\t List of cases that are not classified : "+listNotClassifiedAtAll);
			
			
			if(listNotClassifiedAtAll.isEmpty())
			{
				System.out.println("\t NOTHING TO LIST");
				System.out.println("\n");
			}
			else
			{ StringBuilder casePrint=new StringBuilder();
				for(Integer tempCaseNo:listNotClassifiedAtAll)
				{casePrint=new StringBuilder();
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCases().entrySet())
					{
						//
						casePrint.append(entryTemp.getValue()).append(",");
					}
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCaseDecisionValue().entrySet())
					{
						casePrint.append(entryTemp.getValue());
					}
					System.out.println("\t"+casePrint.toString());
				}
				System.out.println("\n");
			}
			
			System.out.println("\t \t PARTIAL MATCHING");
			System.out.println("\t List of cases that are incorrectly classified: "+temObj.getListPartialIncorrectlyClassDecisionMap());
			
			if(temObj.getListPartialIncorrectlyClassDecisionMap().isEmpty())
			{
				System.out.println("\t NOTHING TO LIST");
				System.out.println("\n");
			}
			else
			{ StringBuilder casePrint=new StringBuilder();
				for(Integer tempCaseNo:temObj.getListPartialIncorrectlyClassDecisionMap())
				{    
					casePrint=new StringBuilder();
					
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCases().entrySet())
					{
						//
						casePrint.append(entryTemp.getValue()).append(",");
					}
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCaseDecisionValue().entrySet())
					{
						casePrint.append(entryTemp.getValue());
					}
					System.out.println("\t"+casePrint.toString());
				}
				System.out.println("\n");
			}
			
			System.out.println("\t List of cases that are correctly classified: "+temObj.getListPartialCorrectlyClassDecisionMap());
		    
			if(temObj.getListPartialCorrectlyClassDecisionMap().isEmpty())
			{
				System.out.println("\t NOTHING TO LIST");
				System.out.println("\n");
			}
			else
			{ StringBuilder casePrint=new StringBuilder();
				for(Integer tempCaseNo:temObj.getListPartialCorrectlyClassDecisionMap())
				{    
					casePrint=new StringBuilder();
					
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCases().entrySet())
					{
						//
						casePrint.append(entryTemp.getValue()).append(",");
					}
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCaseDecisionValue().entrySet())
					{
						casePrint.append(entryTemp.getValue());
					}
					System.out.println("\t"+casePrint.toString());
				}
				System.out.println("\n");
			}
			
			System.out.println("\t \t COMPLETE MATCHING");
			System.out.println("\t List of cases that are incorrectly classified: "+temObj.getListCompleteIncorrectlyClassDecisionMap());
			
			if(temObj.getListCompleteIncorrectlyClassDecisionMap().isEmpty())
			{
				System.out.println("\t NOTHING TO LIST");
				System.out.println("\n");
			}
			else
			{ StringBuilder casePrint=new StringBuilder();
				for(Integer tempCaseNo:temObj.getListCompleteIncorrectlyClassDecisionMap())
				{    
					casePrint=new StringBuilder();
					
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCases().entrySet())
					{
						//
						casePrint.append(entryTemp.getValue()).append(",");
					}
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCaseDecisionValue().entrySet())
					{
						casePrint.append(entryTemp.getValue());
					}
					System.out.println("\t"+casePrint.toString());
				}
				System.out.println("\n");
			}
			
			System.out.println("\t List of cases that are correctly classified: "+temObj.getListCompleteCorrectlyClassDecisionMap());
			if(temObj.getListCompleteCorrectlyClassDecisionMap().isEmpty())
			{
				System.out.println("\t NOTHING TO LIST");
				System.out.println("\n");
			}
			else
			{ StringBuilder casePrint=new StringBuilder();
				for(Integer tempCaseNo:temObj.getListCompleteCorrectlyClassDecisionMap())
				{    
					casePrint=new StringBuilder();
					
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCases().entrySet())
					{
						//
						casePrint.append(entryTemp.getValue()).append(",");
					}
					for (Entry<String,String> entryTemp : listdataFileDetails.get(tempCaseNo-1).getLhmCaseDecisionValue().entrySet())
					{
						casePrint.append(entryTemp.getValue());
					}
					System.out.println("\t"+casePrint.toString());
				}
				System.out.println("\n");
			}
			
			
		}
			}*/
			
			//brDataFile.close();
		}
		catch(Exception e)
	{
			e.printStackTrace();
			System.out.println("exception");
			System.out.println("!!!!!!!!!!!!!!!!!!FILE NOT FOUND!!!!!!!!!!!!!!!!!!!!");
			exit(0);
	}
		return doubErrorRate;
		
	
	}
	
	public final static void main(String[] args) throws FileNotFoundException, IOException
	{}
	
	/*public static void methodToInitiateRuleChecker(ArrayList<dataFileDetails> listdataFileDetails, String strRuleFileName)
	{

		
		//test_msd obTest_msd=new test_msd();
		
		methodToRuleChecker( listdataFileDetails,  strRuleFileName);
		
		System.out.println("Do you want to exit the program(press e <RETURN> or <RETURN>) or to start over again(y) ?");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 in = new BufferedReader(new InputStreamReader(System.in));
		String strExitOrContinue = in.readLine();
		while (!(strExitOrContinue.trim().equalsIgnoreCase("y") || strExitOrContinue.trim().equalsIgnoreCase("n") ||
				strExitOrContinue.trim().equalsIgnoreCase("")))
		{
			System.out.println("Please enter a valid response for the question !!!.");
			strExitOrContinue = in.readLine();
		
		}
		if(strExitOrContinue.trim().equalsIgnoreCase(""))
		{
			strExitOrContinue="n";
		}
		
		if(strExitOrContinue.trim().equalsIgnoreCase("n"))
		{
			System.out.println("You selected to EXIT the program...");
			System.out.println("Exiting...");
			exit(0);
		}
		else
		{
			System.out.println("\n Starting the program again...\n");
			
			test_msd obTest_msd1=new test_msd();
		}
		
		//second
		
		if(!strExitOrContinue.trim().equalsIgnoreCase("n"))
		{
		strExitOrContinue="";
		System.out.println("Do you want to exit the program(press e <RETURN> or <RETURN>) or to start over again(y) ?");
		 in = new BufferedReader(new InputStreamReader(System.in));
		 in = new BufferedReader(new InputStreamReader(System.in));
		 strExitOrContinue = in.readLine();
		while (!(strExitOrContinue.trim().equalsIgnoreCase("y") || strExitOrContinue.trim().equalsIgnoreCase("n") ||
				strExitOrContinue.trim().equalsIgnoreCase("")))
		{
			System.out.println("Please enter a valid response for the question !!!.");
			strExitOrContinue = in.readLine();
		
		}
		if(strExitOrContinue.trim().equalsIgnoreCase(""))
		{
			strExitOrContinue="n";
		}
		
		if(strExitOrContinue.trim().equalsIgnoreCase("n"))
		{
			System.out.println("You selected to EXIT the program...");
			System.out.println("Exiting...");
			exit(0);
		}
		else
		{
			System.out.println("\n Starting the program again...\n");
			
			test_msd obTest_msd1=new test_msd();
		}
		}
		//third
		if(!strExitOrContinue.trim().equalsIgnoreCase("n"))
		{
				strExitOrContinue="";
				System.out.println("Do you want to exit the program(press e <RETURN> or <RETURN>) or to start over again(y) ?");
				 in = new BufferedReader(new InputStreamReader(System.in));
				 in = new BufferedReader(new InputStreamReader(System.in));
				 strExitOrContinue = in.readLine();
				while (!(strExitOrContinue.trim().equalsIgnoreCase("y") || strExitOrContinue.trim().equalsIgnoreCase("n") ||
						strExitOrContinue.trim().equalsIgnoreCase("")))
				{
					System.out.println("Please enter a valid response for the question !!!.");
					strExitOrContinue = in.readLine();
				
				}
				if(strExitOrContinue.trim().equalsIgnoreCase(""))
				{
					strExitOrContinue="n";
				}
				
				if(strExitOrContinue.trim().equalsIgnoreCase("n"))
				{
					System.out.println("You selected to EXIT the program...");
					System.out.println("Exiting...");
					exit(0);
				}
				else
				{
					System.out.println("\n Starting the program again...\n");
					
					test_msd obTest_msd1=new test_msd();
				}
		}
		if(!strExitOrContinue.trim().equalsIgnoreCase("n"))
		{
				//fourth
				strExitOrContinue="";
				System.out.println("Do you want to exit the program(press e <RETURN> or <RETURN>) or to start over again(y) ?");
				 in = new BufferedReader(new InputStreamReader(System.in));
				 in = new BufferedReader(new InputStreamReader(System.in));
				 strExitOrContinue = in.readLine();
				while (!(strExitOrContinue.trim().equalsIgnoreCase("y") || strExitOrContinue.trim().equalsIgnoreCase("n") ||
						strExitOrContinue.trim().equalsIgnoreCase("")))
				{
					System.out.println("Please enter a valid response for the question !!!.");
					strExitOrContinue = in.readLine();
				
				}
				if(strExitOrContinue.trim().equalsIgnoreCase(""))
				{
					strExitOrContinue="n";
				}
				
				if(strExitOrContinue.trim().equalsIgnoreCase("n"))
				{
					System.out.println("You selected to EXIT the program...");
					System.out.println("Exiting...");
					exit(0);
				}
				else
				{
					System.out.println("\n Starting the program again...\n");
					
					test_msd obTest_msd1=new test_msd();
				}
		}
		if(!strExitOrContinue.trim().equalsIgnoreCase("n"))
		{
				//fifth
				strExitOrContinue="";
				System.out.println("Do you want to exit the program(press e <RETURN> or <RETURN>) or to start over again(y) ?");
				 in = new BufferedReader(new InputStreamReader(System.in));
				 in = new BufferedReader(new InputStreamReader(System.in));
				 strExitOrContinue = in.readLine();
				while (!(strExitOrContinue.trim().equalsIgnoreCase("y") || strExitOrContinue.trim().equalsIgnoreCase("n") ||
						strExitOrContinue.trim().equalsIgnoreCase("")))
				{
					System.out.println("Please enter a valid response for the question !!!.");
					strExitOrContinue = in.readLine();
				
				}
				if(strExitOrContinue.trim().equalsIgnoreCase(""))
				{
					strExitOrContinue="n";
				}
				
				if(strExitOrContinue.trim().equalsIgnoreCase("n"))
				{
					System.out.println("You selected to EXIT the program...");
					System.out.println("Exiting...");
					exit(0);
				}
				else
				{
					System.out.println("\n Starting the program again...\n");
					
					test_msd obTest_msd1=new test_msd();
				}
		}
		System.out.println("\n \t *********************EXITING THE PROGRAM********************");
	
	}*/

	private static void exit(int i) {
		// TODO Auto-generated method stub
		
	}

	
	
}