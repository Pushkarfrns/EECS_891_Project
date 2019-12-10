package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ruleChecker.test_msd;

public class Test_fp {
	public static int globalInt = 0;
	public static  double alpha=0.5; // global probablistic approximation
	//public static  double beta; //  for single local probablistic approximation MLEM2 algo rule induction
	//double alpha=0.667;
	public static void main(String[] args) {
		try {
			
		
			BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter the value of beta \n");
			
			String strValueOfBeta = in1.readLine();	
			
			System.out.println("strValueOfBeta-->"+strValueOfBeta);
			
			Double beta= Double.valueOf(strValueOfBeta);
			
			System.out.println("The value entered for beta--->"+beta);
			
		System.out.println("ok!!!");
		
		ArrayList<dataFileDetails> listdataFileDetails=new ArrayList<dataFileDetails>();// one object will store individual row along with attribute-value pair in key value
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		
		/*set to store the name of all attributes that have numerical values in them
		 * so that to check whether we need to proceed to do dominant attribute discretisation or not* */
		
			
		
		/* DATA FILE NAME: INPUT FROM USER START*/
		boolean boolDataFileError=false;
		System.out.println("\nPlease enter the name of the input data file and press enter\n");
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
		}
		while (boolDataFileError) {
			
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
		    }
		
		String strFileNameWithouttxt="";
		if(strDataFileName.contains(".txt"))
		{
			String[] strSplitFileName=strDataFileName.split(".txt");
			strFileNameWithouttxt=strSplitFileName[0];
		}
		
		System.out.println(" DATA  FILE NAME value input by user is---"+strDataFileName);
		/* DATA FILE NAME: INPUT FROM USER END*/
		
		 InputStream fisDataFile = new FileInputStream(strDataFileName);
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
		    	
		    	if(lineDataFile.trim().length()>0)  // check for non empty line
		        {
		    		if(!lineDataFile.trim().startsWith("!"))
		    				{
		        if(!(lineDataFile.trim().startsWith("<") || lineDataFile.trim().endsWith(">")) ) // if line starts with ! i.e. its comment so ignore it
		        {
		        	if(lineDataFile.trim().startsWith("[") || lineDataFile.trim().endsWith("]")) // to check that the line starts with number, for the three number check
		        	{
		  String strAttributeNameSpaceSeparated=lineDataFile.trim().substring(lineDataFile.trim().indexOf("[")+1, lineDataFile.trim().indexOf("]"));
		  strAttributeName=	strAttributeNameSpaceSeparated.trim().split(" ");
		  
		
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
					    	Entry<String, String> lastEntry = entryList.get(entryList.size()-1);
			        	
					    	objdataFileDetails.getLhmCaseDecisionValue().put(lastEntry.getKey(), lastEntry.getValue()); // to store the decision namd and value in separate LHM
			        	
					    	objdataFileDetails.getLhmCases().remove(objdataFileDetails.getStrDecisionAttrName());
					    	//to remove the last entry from the LinkedHashMap i.e. to remove the decision name and its value as it is stored in LhmCaseDecisionValue LinkedHasMap
					    	
					    	listdataFileDetails.add(objdataFileDetails);
			        	intTempCaseNumber++;
			        }
		        	
		        }
		        }
		        }
		    }
		   // methodToFindRulesFromKFoldDataset(listdataFileDetails,strFileNameWithouttxt );
		    /*******for K fold shuffling the dataset STARTS********/
		    
		    
		    Collections.shuffle(listdataFileDetails);
		    int count=1;
		    
		    for(dataFileDetails objTemp: listdataFileDetails)
		    {
		    	objTemp.setIntCaseNumber(count);
		    	count++;
		    }
		    
		    int start=1;
		    int end=listdataFileDetails.size();
		    int numberBins=0;
		    if(end<=100)
		    {
		    	 numberBins= (int) Math.ceil(end/10);
		    }else
		    {
		    	 numberBins= (int) Math.ceil(end/10.0);
		    }
		    
		    
		    System.out.println("numberBins-->"+numberBins);
		    System.out.println("end-->"+end);
		  
		    
		    ArrayList<String> listkFoldRange=new  ArrayList<String> ();		    
		   
		    for(int i=start; i<=9;i++)
		    {
		    	System.out.println(i+"--"+((i-1)*numberBins+1)+"---"+i*numberBins);
		    	listkFoldRange.add((i-1)*numberBins+1+"_"+i*numberBins);		    	
		    }
		    
		    if(9*numberBins<end)
		    {
		    	System.out.println(10+"--"+(9*numberBins+1)+"---"+end);
		    	listkFoldRange.add(9*numberBins+1+"_"+end);		    	
		    }
		    
		    System.out.println("listkFoldRange-->"+listkFoldRange);
		   
		   LinkedHashMap<Double, Double> lhmBetaGlobalErrorRate=new LinkedHashMap<Double, Double>();
		   LinkedHashMap<Double, Double> lhmBetaSaturatedErrorRate=new LinkedHashMap<Double, Double>();
		 
		ArrayList<Double> listDoubErrorRateGlobal=new ArrayList<Double>();
		   ArrayList<Double> listDoubErrorRateSaturated=new ArrayList<Double>();
		    
		    for(String strIndividualRange: listkFoldRange)
				{
		    	ArrayList<dataFileDetails> listdataFileDetailsTrain=new ArrayList<dataFileDetails>();// one object will store individual row along with attribute-value pair in key value
			    ArrayList<dataFileDetails> listdataFileDetailsTest=new ArrayList<dataFileDetails>();// one object will store individual row along with attribute-value pair in key value
			  
					String[] indRange = strIndividualRange.split("_");

					System.out.println("First-->" + indRange[0] + "--Second-->" + indRange[1]);

					for (dataFileDetails objTemp : listdataFileDetails) {
						
						dataFileDetails tmp=new dataFileDetails();
				    	tmp.setIntCaseNumber(objTemp.getIntCaseNumber());
				    	tmp.setLhmCaseDecisionValue(objTemp.getLhmCaseDecisionValue());
				    	tmp.setLhmCases(objTemp.getLhmCases());
				    	tmp.setStrDecisionAttrName(objTemp.getStrDecisionAttrName());
						
						if (objTemp.getIntCaseNumber() >= Integer.valueOf(indRange[0])
								&& objTemp.getIntCaseNumber() <= Integer.valueOf(indRange[1])) {
							listdataFileDetailsTest.add(tmp);
					    	
						} else {
							
							listdataFileDetailsTrain.add(tmp);
						}

					}
					 System.out.println("listdataFileDetailsTest size--> "+listdataFileDetailsTest.size());
					    System.out.println("listdataFileDetailsTrain size--> "+listdataFileDetailsTrain.size());
					    System.out.println("TEST-->");
					    for(dataFileDetails obj:listdataFileDetailsTest)
						{					    	
					    	System.out.print(obj.getIntCaseNumber()+", ");
						} System.out.println("\n");  System.out.println("TRAIN-->");
					    for(dataFileDetails obj:listdataFileDetailsTrain)
						{					    	
					    	System.out.print(obj.getIntCaseNumber()+", ");
						}
					    System.out.println("\n\n");
					    
					    // 1. call rule induction process from where and pass the training dataset listdataFileDetailsTrain
					    // 2. call the rule checking system from here pass the dataset listdataFileDetailsTest and name of rules files generated
					    // 3. the error return from 2 store it in a list to average it
					    
					    int countTrainReset=1;
					    
					    /*starting implementing 1st point i.e., for 10 fold cross validation generate respective rule files*/
					    
					    for(dataFileDetails obj:listdataFileDetailsTrain)
						{
					    	obj.setIntCaseNumber(countTrainReset);
					    	countTrainReset++;
						}
					    
					    int countTestReset=1;
					    /*starting implementing 1st point*/
					    for(dataFileDetails obj:listdataFileDetailsTest)
						{
					    	obj.setIntCaseNumber(countTestReset);
					    	countTestReset++;
						}
					    
					    
					    
					    System.out.println("listdataFileDetailsTest size AFTER--> "+listdataFileDetailsTest.size());
					    System.out.println("listdataFileDetailsTrain size AFTER--> "+listdataFileDetailsTrain.size());
					    System.out.println("TEST AFTER-->");
					    for(dataFileDetails obj:listdataFileDetailsTest)
						{					    	
					    	System.out.print(obj.getIntCaseNumber()+", ");
						} System.out.println("\n");  System.out.println("TRAIN AFTER-->");
					    for(dataFileDetails obj:listdataFileDetailsTrain)
						{					    	
					    	System.out.print(obj.getIntCaseNumber()+", ");
						}
					    System.out.println("\n\n");
					    
					    
					    String strRuleFileName=strFileNameWithouttxt+"_testData_"+strIndividualRange;
					    methodToFindRulesFromKFoldDataset(listdataFileDetailsTrain, strRuleFileName, beta);
					    
					    System.out.println("*********************************************************************************************************");
					    System.out.println("*********************Starting Rule Checker***************************");
					    /*starting implementing 2nd point i.e., passing listdataFileDetailsTest and respective rule file for error*/
					    
				double doubErrorRateGlobal=  test_msd.methodToRuleChecker(listdataFileDetailsTest, "output/"+strRuleFileName+"_Global.txt");
				double doubErrorRateSaturated=  test_msd.methodToRuleChecker(listdataFileDetailsTest, "output/"+strRuleFileName+"_Saturated.txt");
				
				System.out.println("Error Rate GLobal--->"+doubErrorRateGlobal);  
				System.out.println("Error Rate Saturated--->"+doubErrorRateSaturated);  
				listDoubErrorRateGlobal.add(doubErrorRateGlobal);
				listDoubErrorRateSaturated.add(doubErrorRateSaturated);
					    
					    
					    
				}		    
		   
		    
		    System.out.println("For BETA VALUE--->"+beta);
		    System.out.println("listDoubErrorRateGlobal--->"+listDoubErrorRateGlobal);
		    System.out.println("listDoubErrorRateSaturated--->"+listDoubErrorRateSaturated);
		  //  System.exit(0); 
		    Double dblGlobalAvgError=0.0;
		    
		    for(Double tmpGlobalError: listDoubErrorRateGlobal)
		    {
		    	dblGlobalAvgError+=tmpGlobalError;
		    }
		    
		    Double dblSaturatedAvgError=0.0;
		    
		    for(Double tmpSatError: listDoubErrorRateSaturated)
		    {
		    	dblSaturatedAvgError+=tmpSatError;
		    }
		    System.out.println("Average global error--->"+(double)dblGlobalAvgError/listDoubErrorRateGlobal.size());
		    System.out.println("Average satrtd error--->"+(double)dblSaturatedAvgError/listDoubErrorRateSaturated.size());
		    
		      
		    /*******for K fold shuffling the dataset END**************************************/
		    
		   
		}
		catch(Exception e)
		{
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	
	static void methodToFindRulesFromKFoldDataset(ArrayList<dataFileDetails> listdataFileDetails, String strFileNameWithouttxt, Double dblBetaTemp)
	{
		try
		{
			Set<String> setNumericalAttrColName = new LinkedHashSet<String>(); 
			  // print for cases
			   //System.out.println("\n******************************************************DATA FILE CONTENT ARE--********************************************---\n");
			 // regular expression for a Integer number 
			    String strRegexInt = "[+-]?[0-9][0-9]*"; 
			    
			    Pattern pInteger = Pattern.compile(strRegexInt);
			 // Creates a matcher that will match input1 against regex 
		       
			    
			 // regular expression for a floating point number 
		        String strRegexFloat = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?"; 
		        Pattern pFloat = Pattern.compile(strRegexFloat);
		        SortedSet setCasesForDataTable = new TreeSet();
					String strDecisionNameForRulePrint="";
			    for(dataFileDetails obj:listdataFileDetails)
				{
					setCasesForDataTable.add(obj.getIntCaseNumber());

					//system.out.println("\nCase Number:- " + obj.getIntCaseNumber());
					//system.out.println("attribute value pairs are---" + obj.getLhmCases());
					//system.out.println("Decision value pairs are---" + obj.getLhmCaseDecisionValue());
					//system.out.println("Decision Attribute Name:- " + obj.getStrDecisionAttrName());				

					strDecisionNameForRulePrint = obj.getStrDecisionAttrName();
					for (Map.Entry<String, String> entryTemp : obj.getLhmCases().entrySet()) {

						Matcher mInteger = pInteger.matcher(entryTemp.getValue());
						Matcher mFloat = pFloat.matcher(entryTemp.getValue());

						if ((mInteger.find() && mInteger.group().equals(entryTemp.getValue())) ||

								(mFloat.find() && mFloat.group().equals(entryTemp.getValue()))) {
							setNumericalAttrColName.add(entryTemp.getKey());

						}
					}
				}
			    
			    /****************code start to find A* for the orginal dataset, so that it can be used to check the termination Condition.class A*=Ad**/
			    		    
			    LinkedHashMap<Integer,String> lhmCaseNoCasesOriginal=new LinkedHashMap<Integer,String>(); //
			    		    
			    		    LinkedHashMap<Integer,String> lhmCaseNoDecisionValueOriginal=new LinkedHashMap<Integer,String>(); //
			    		    //LHm to store case number as keys and values as string i.e. decision value
			    		    
			    		    for(dataFileDetails obj:listdataFileDetails)
			    			{
			    		    	StringBuilder sbTempRowValues=new StringBuilder();
			    		    	for (Map.Entry<String, String> entryTemp : obj.getLhmCases().entrySet()) { 
			    		    		
			    		    		sbTempRowValues.append(entryTemp.getValue());
			    		    		sbTempRowValues.append(",");
			    		    	}
			    		    			    	
			    		    	lhmCaseNoCasesOriginal.put(obj.getIntCaseNumber(), sbTempRowValues.toString().substring(0, sbTempRowValues.length()-1));
			    		    	lhmCaseNoDecisionValueOriginal.put(obj.getIntCaseNumber(), obj.getLhmCaseDecisionValue().get(obj.getStrDecisionAttrName()));
			    			}
			    		     //system.out.println("lhmCaseNoCasesOriginal---"+lhmCaseNoCasesOriginal);
			    		 //   System.out.println("\n");
			    		    Set<Set<Integer>> setAStarOriginal = new LinkedHashSet<Set<Integer>>();
			    		    Set<String> setTempOriginal =null;
			    		    /*to make A* from th* e lhmCaseNoCases*/	    
			    		  
			    		    Map<String, List<Integer>> reverseMapOriginal = lhmCaseNoCasesOriginal.entrySet()
			    		    	    .stream()
			    		    	    .collect(Collectors.groupingBy(Map.Entry::getValue,
			    		    	        Collectors.mapping(
			    		    	            Map.Entry::getKey,
			    		    	            Collectors.toList())));
			    		    
			    		    //system.out.println(reverseMapOriginal);
			    //{small,black,blue=[4], small,blue,blue=[1], small,blue,black=[8], large,red,blue=[3], large,blue,black=[2], large,black,black=[7], large,black,blue=[5], large,blue,blue=[6]}		    
	//{secondary,?,?=[4], *,low,high=[7], ?,high,low=[2], higher,low,high=[8], secondary,high,?=[5], primary,*,high=[3], primary,low,low=[1], higer,high,low=[6]}		    		 
			    		    
			    		    for (Map.Entry<String, List<Integer>> entryTemp : reverseMapOriginal.entrySet()) { 
			    	    		
			    		    	Set<Integer> setTempofAStar = entryTemp.getValue().stream().collect(Collectors.toSet());
			    		    	setAStarOriginal.add(setTempofAStar);
			    	    	}
			    		    	    
			    		   //system.out.println("\nA* ORIGINAL is-->"+setAStarOriginal); //************************************** final A*
			    		    
		/***************code ENDS to find A* for the orginal dataset, so that it can be used to check the termination Condition.class A*=Ad**/
			    		   
			    		   ArrayList<dataFileDetails> listdataFileDetailsDiscretizedAfterMergingFinal=new ArrayList<dataFileDetails>();
			   		    // if dataset has numerical attribute then this list of object, will contain the discretized data that needs to be inputted to the LEM2 algo for ruled induction
			    		   LinkedHashMap<String, ArrayList<String>> lhmNumAttNameCutPointsMap= new LinkedHashMap<String, ArrayList<String>>();
			   		 if(!setNumericalAttrColName.isEmpty())
			   		    {
			   			//system.out.println("Attribute/Columns with Numerical values are as follows::--->\n"+setNumericalAttrColName);
			   			
			   			lhmNumAttNameCutPointsMap.putAll(methodToHandleNumericalDataColumn(setNumericalAttrColName,listdataFileDetails));
			   			
			   			
			   		    }
			   		    	
			   		    listdataFileDetailsDiscretizedAfterMergingFinal.addAll(listdataFileDetails);
			   		 
			   		 // not chekcing for A*<=D*
			   		 
			   		/***********************LEM2: code to make the [(a,v)] structure so that to apply LEM2---START**********************/		
					 /***********************LEM2: code to make the [(a,v)] 2---START**********************/
					 
					 LinkedHashMap<String, LinkedHashMap<String, Set<Integer>>> lhmAttributeValueBlock=new LinkedHashMap<String, LinkedHashMap<String, Set<Integer>>>();;
					 
					 // lhmAttributeValueBlock for finding numerical characterstic set
					 LinkedHashMap<String, LinkedHashMap<String, Set<Integer>>> lhmAttributeValueBlockNum_K=new LinkedHashMap<String, LinkedHashMap<String, Set<Integer>>>();;			 
					 
					
					 LinkedHashMap<String, Set<Integer>> lhmValueCasesTemp=null;
					 // to to add values for attribute along with their case number
					 // small= <1,4,8>, large==<2,3,5,6,7> and then add this lhmValueCasesTemp to main lhmAttributeValueBlock
					 // where key will correspond to attribute name example size
					 
					 LinkedHashMap<String, Set<Integer>> lhmMissingValueStarCases=new LinkedHashMap<String, Set<Integer>>();
					 //Set<Integer> setCaseNo=new HashSet<Integer>();
					 Set<Integer> setCaseNo=null;
					 Set<Integer> setCaseNoMissingValueStar=null;
					 
					 for(dataFileDetails obj:listdataFileDetailsDiscretizedAfterMergingFinal)
						{
					    	//StringBuilder sbTempRowValues=new StringBuilder();
					    	for (Map.Entry<String, String> entryTemp : obj.getLhmCases().entrySet()) { 
					    		
					    		if((entryTemp.getValue().equals("*") || entryTemp.getValue().equals("?")))
					    		{	
					    			if(entryTemp.getValue().equals("*"))
					    			if(lhmMissingValueStarCases.containsKey(entryTemp.getKey()))
					    			{
					    				lhmMissingValueStarCases.get(entryTemp.getKey()).add(obj.getIntCaseNumber());
					    			}
					    			else
								{								

									setCaseNoMissingValueStar = new LinkedHashSet<Integer>();
									setCaseNoMissingValueStar.add(obj.getIntCaseNumber());
									lhmMissingValueStarCases.put(entryTemp.getKey(), setCaseNoMissingValueStar);

								}
					    			
					    		} else if(setNumericalAttrColName.contains(entryTemp.getKey()))
					    		{
					    			// below if will create a structure the first it is accessed or met during parsing
					    			//wind----->{4.0..6.0=[], 6.0..30.0=[], 4.0..10.0=[], 10.0..30.0=[], 4.0..14.0=[], 14.0..30.0=[], 4.0..23.0=[], 23.0..30.0=[]}
					    			if(!lhmAttributeValueBlock.containsKey(entryTemp.getKey()))
							{
								ArrayList<String> listCutPoints = new ArrayList<String>(
										lhmNumAttNameCutPointsMap.get(entryTemp.getKey()));

								for (String strTempCP : listCutPoints) {

									if (lhmAttributeValueBlock.containsKey(entryTemp.getKey())) {
										lhmAttributeValueBlock.get(entryTemp.getKey()).put(strTempCP,
												new HashSet<Integer>());
									} else {
										lhmValueCasesTemp = new LinkedHashMap<String, Set<Integer>>();
										lhmValueCasesTemp.put(strTempCP, new HashSet<Integer>());
										lhmAttributeValueBlock.put(entryTemp.getKey(), lhmValueCasesTemp);
									}

								}

							}
					    			
					    			
							for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAttributeValueBlock.get(entryTemp.getKey())
									.entrySet()) {

								String[] strLowerUpper = entryTemp1.getKey().split("\\.\\.");

								if (Double.valueOf(entryTemp.getValue()) >= Double.valueOf(strLowerUpper[0])
										&& Double.valueOf(entryTemp.getValue()) <= Double.valueOf(strLowerUpper[1])) {
									entryTemp1.getValue().add(obj.getIntCaseNumber());
								}

							}
					    			
					    			
					    		}
					    		else {
					    		if(lhmAttributeValueBlock.containsKey(entryTemp.getKey())) //if a attribute already exist so add to its existing LHM<String, Set<Integer>
					    		{
					    			if(lhmAttributeValueBlock.get(entryTemp.getKey()).containsKey(entryTemp.getValue()))// attr exist and its value also exist so have to append the set
					    			{
					    				lhmAttributeValueBlock.get(entryTemp.getKey()).get(entryTemp.getValue()).add(obj.getIntCaseNumber());
					    				// so attr exist, value exist so adding case number to it
					    			}
					    			else
					    			{// attribute exists but the value doesnt exist so have to make new entry for value and corresponding new set<Integer>
					    				setCaseNo=new LinkedHashSet<Integer>();
						    			setCaseNo.add(obj.getIntCaseNumber());
					    				lhmAttributeValueBlock.get(entryTemp.getKey()).put(entryTemp.getValue(), setCaseNo );
					    			}    			
					    			
					    		}else // a new entry to that lhm for the new attribute and then new value for it and new set entry for it
					    		{
					    			lhmValueCasesTemp=new LinkedHashMap<String, Set<Integer>>();
					    			setCaseNo=new LinkedHashSet<Integer>();
					    			setCaseNo.add(obj.getIntCaseNumber());
					    			
					    			lhmValueCasesTemp.put(entryTemp.getValue(), setCaseNo);
					    			
					    			lhmAttributeValueBlock.put(entryTemp.getKey(), lhmValueCasesTemp);
					    			
								}
					    		}
					    		
					    		
					    		//here for attributeValueBlock for numerical forming
					    		
					    		
					    		if((entryTemp.getValue().equals("*") || entryTemp.getValue().equals("?")))
					    		{/*	
					    			if(entryTemp.getValue().equals("*"))
					    			if(lhmMissingValueStarCases.containsKey(entryTemp.getKey()))
					    			{
					    				lhmMissingValueStarCases.get(entryTemp.getKey()).add(obj.getIntCaseNumber());
					    			}				    			
					    			
					    		*/} 
					    		else {
					    		if(lhmAttributeValueBlockNum_K.containsKey(entryTemp.getKey())) //if a attribute already exist so add to its existing LHM<String, Set<Integer>
					    		{
					    			if(lhmAttributeValueBlockNum_K.get(entryTemp.getKey()).containsKey(entryTemp.getValue()))// attr exist and its value also exist so have to append the set
					    			{
					    				lhmAttributeValueBlockNum_K.get(entryTemp.getKey()).get(entryTemp.getValue()).add(obj.getIntCaseNumber());
					    				// so attr exist, value exist so adding case number to it
					    			}
					    			else
					    			{// attribute exists but the value doesnt exist so have to make new entry for value and corresponding new set<Integer>
					    				setCaseNo=new LinkedHashSet<Integer>();
						    			setCaseNo.add(obj.getIntCaseNumber());
						    			lhmAttributeValueBlockNum_K.get(entryTemp.getKey()).put(entryTemp.getValue(), setCaseNo );
					    			}    			
					    			
					    		}else // a new entry to that lhm for the new attribute and then new value for it and new set entry for it
					    		{
					    			lhmValueCasesTemp=new LinkedHashMap<String, Set<Integer>>();
					    			setCaseNo=new LinkedHashSet<Integer>();
					    			setCaseNo.add(obj.getIntCaseNumber());
					    			
					    			lhmValueCasesTemp.put(entryTemp.getValue(), setCaseNo);
					    			
					    			lhmAttributeValueBlockNum_K.put(entryTemp.getKey(), lhmValueCasesTemp);
					    			
								}
					    		}

							}

						}
					 
					 //system.out.println("\n**************Attribute value block pair are as follows---->*************\n");
					 
					 for (Map.Entry<String, LinkedHashMap<String, Set<Integer>>> entryTemp : lhmAttributeValueBlock.entrySet()) { 
						 
						 //system.out.println(entryTemp.getKey()+"----->"+entryTemp.getValue());
						 
						/* OUTPUT--->Attribute value block pair are as follows---->

						 size----->{small=[1, 4, 8, 9], large=[2, 3, 5, 6, 7]}
						 ink-color----->{blue=[1, 2, 6, 8, 9], red=[3], black=[4, 5, 7]}
						 body-color----->{blue=[1, 3, 4, 5, 6], black=[2, 7, 8, 9]}
						 price----->{medium=[1, 2], small=[3, 4, 6], big=[5, 7, 8, 9]}*/
						 
					 }
					 
					 
					 //system.out.println("\n**************Attribute value block pair are as follows NUMERICALLLLLLL---->*************\n");
					 
					 for (Map.Entry<String, LinkedHashMap<String, Set<Integer>>> entryTemp : lhmAttributeValueBlockNum_K.entrySet()) { 
						 
						 //system.out.println(entryTemp.getKey()+"----->"+entryTemp.getValue());
						 
						/* OUTPUT--->Attribute value block pair are as follows---->

						 size----->{small=[1, 4, 8, 9], large=[2, 3, 5, 6, 7]}
						 ink-color----->{blue=[1, 2, 6, 8, 9], red=[3], black=[4, 5, 7]}
						 body-color----->{blue=[1, 3, 4, 5, 6], black=[2, 7, 8, 9]}
						 price----->{medium=[1, 2], small=[3, 4, 6], big=[5, 7, 8, 9]}*/
						 
					 }
					 
					 
					 for(Map.Entry<String, Set<Integer>> entryTemp: lhmMissingValueStarCases.entrySet())
				{
					for (Map.Entry<String, LinkedHashMap<String, Set<Integer>>> entryTemp1 : lhmAttributeValueBlockNum_K
							.entrySet())

					{
						if (entryTemp.getKey().equalsIgnoreCase(entryTemp1.getKey())) {

							for (Map.Entry<String, Set<Integer>> entryTemp2 : lhmAttributeValueBlockNum_K
									.get(entryTemp1.getKey()).entrySet()) {
								entryTemp2.getValue().addAll(entryTemp.getValue());
							}

						}

					}
					
					for (Map.Entry<String, LinkedHashMap<String, Set<Integer>>> entryTemp11 : lhmAttributeValueBlock
							.entrySet())

					{
						if (entryTemp.getKey().equalsIgnoreCase(entryTemp11.getKey())) {

							for (Map.Entry<String, Set<Integer>> entryTemp2 : lhmAttributeValueBlock
									.get(entryTemp11.getKey()).entrySet()) {
								entryTemp2.getValue().addAll(entryTemp.getValue());
							}

						}

					}

				}
					 
					 
	 //system.out.println("\n**************Attribute value block pair after adding MISSING CASES are as follows NUMERICALLLLLLL---->*************\n");
					 
					 for (Map.Entry<String, LinkedHashMap<String, Set<Integer>>> entryTemp : lhmAttributeValueBlockNum_K.entrySet()) { 
						 
						 //system.out.println(entryTemp.getKey()+"----->"+entryTemp.getValue());
						 
						/* OUTPUT--->Attribute value block pair are as follows---->

						 size----->{small=[1, 4, 8, 9], large=[2, 3, 5, 6, 7]}
						 ink-color----->{blue=[1, 2, 6, 8, 9], red=[3], black=[4, 5, 7]}
						 body-color----->{blue=[1, 3, 4, 5, 6], black=[2, 7, 8, 9]}
						 price----->{medium=[1, 2], small=[3, 4, 6], big=[5, 7, 8, 9]}*/
						 
					 }
					 
					 
					 
					 LinkedHashMap<String, Set<Integer>> lhmAtt_Value_SetCase=new LinkedHashMap<String, Set<Integer>>();
					 // simplified as LEM2 algo, first column that contains all the attribute value pairs- this will be used to find the intersection for LEM2 Algo
					 // and passed as a param in LEM2 algo
					 // for simplicity making the structure to store as
					/* size__small=[1, 4, 8, 9],
					 size__large=[2, 3, 5, 6, 7]
					 ink-color__blue=[1, 2, 6, 8, 9],
					 ink-color__red=[3]
					 ink-color__black=[4, 5, 7]}
					 body-color__blue=[1, 3, 4, 5, 6],
					 body_color__black=[2, 7, 8, 9]}
					 price__medium=[1, 2],
					 price__small=[3, 4, 6],
					 price__big=[5, 7, 8, 9]}*/
					 
				for (Map.Entry<String, LinkedHashMap<String, Set<Integer>>> entryTemp : lhmAttributeValueBlock.entrySet()) {
					for (Map.Entry<String, Set<Integer>> entryTemp1 : entryTemp.getValue().entrySet()) {
						lhmAtt_Value_SetCase.put(entryTemp.getKey() + "__" + entryTemp1.getKey(), entryTemp1.getValue());
					}
				}
					 //system.out.println("missing value star cases are-->"+lhmMissingValueStarCases);
					 //system.out.println("\n*********** FINAL DESIRE ATTRIBUTE VALUE BLOCK PAIR ARE AS FOLLOWS---->>\n");
					 for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCase.entrySet()) {
						lhmAtt_Value_SetCase.put(entryTemp1.getKey(), new TreeSet<Integer>(entryTemp1.getValue()));
					 }
					 
					 for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCase.entrySet()) {								         
						 //system.out.println(entryTemp1.getKey()+"----->"+entryTemp1.getValue());
						
					 }
					 
					 //output from above
	// attribute value block are formed with consideration of * and ? i.e., missing value case handled
	/*education__primary----->[1, 3, 7]
	education__secondary----->[4, 5, 7]
	education__higher----->[6, 7, 8]
	skills__low----->[1, 3, 7, 8]
	skills__high----->[2, 3, 5, 6]
	experience__low----->[1, 2, 6]
	experience__high----->[3, 7, 8]*/
					 
					 
					 
					 /***********************LEM2: code to make the [(a,v)] structure so that to apply LEM2---END**********************/
					  /***********************code to find characterstic set global probablistic approximation---> END**********************/
					 
					 /***********************code to find D* start -START**********************/				 
					 LinkedHashMap<Integer,String> lhmCaseNoDecisionValue=new LinkedHashMap<Integer,String>(); //
					    //LHm to store case number as keys and values as string i.e. decision value
					    
					    for(dataFileDetails obj:listdataFileDetailsDiscretizedAfterMergingFinal)
				{
					StringBuilder sbTempRowValues = new StringBuilder();
					for (Map.Entry<String, String> entryTemp : obj.getLhmCases().entrySet()) {

						sbTempRowValues.append(entryTemp.getValue());
						sbTempRowValues.append(",");
					}

					lhmCaseNoDecisionValue.put(obj.getIntCaseNumber(),
							obj.getLhmCaseDecisionValue().get(obj.getStrDecisionAttrName()));
				}				 
					 
				Set<Set<Integer>> setDStar = new LinkedHashSet<Set<Integer>>();				    
					    
				Map<String, List<Integer>> reverseMapDStar = lhmCaseNoDecisionValue.entrySet().stream().collect(Collectors
						.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

				//this list contains the name of the concepts for writing rule in the text files
				   ArrayList<String> listConceptNameConsistent=new ArrayList<String>();				   
						   
				   ArrayList<String> keys = new ArrayList<String>(reverseMapDStar.keySet());
			        for(int i=keys.size()-1; i>=0;i--){	        	
			        	
			           // //system.out.println(reverseMapDStar.get(keys.get(i)));
			            
			            Set<Integer> setTempofDStar = reverseMapDStar.get(keys.get(i)).stream().collect(Collectors.toSet());
						setDStar.add(setTempofDStar);
						 listConceptNameConsistent.add(keys.get(i));
			        }			
			        
			        //system.out.println("reverseMapDStar-->"+reverseMapDStar);
				
				//system.out.println("\nD* (star) is-->"+setDStar);
				
				//system.out.println("listConceptNameConsistent--->"+listConceptNameConsistent);
				
				//eg. D* (star) is-->[[1, 2, 3, 4], [5, 6, 7, 8]]
				/***********************code to find D* start -END**********************/
				
				
				 /***********************code to find characterstic set and global probablistic approximation---> STARTS**********************/
				 
				 //system.out.println("\n \n <----****************************-code start to find characterstic set adn global probablistic approximation-****************************---->\n");
				 
				 LinkedHashMap<Integer, LinkedHashSet<Integer>> lhmCharactersticSet_K=new LinkedHashMap<Integer, LinkedHashSet<Integer>>();
				 
				 
				 if(setNumericalAttrColName.isEmpty())
				 {
					 lhmCharactersticSet_K.putAll(methodToFindCharactersticSets( lhmAttributeValueBlock, listdataFileDetailsDiscretizedAfterMergingFinal));
				 }else
				 {
					 lhmCharactersticSet_K.putAll(methodToFindCharactersticSets( lhmAttributeValueBlockNum_K, listdataFileDetailsDiscretizedAfterMergingFinal));
				 }
				 
				 Set<TreeSet<Integer>> setGlobalProbablisticApprox= new LinkedHashSet<TreeSet<Integer>>(
						 methodToFindGlobalProbablisticApproximation(setDStar, alpha,  lhmCharactersticSet_K));		 
				 
				 
				//system.out.println("GLOBAL PROBABLISTIC APPROXIMATION IN MAIN ISS_--->\n"+setGlobalProbablisticApprox);
				
				/***********************code to find characterstic set and global probablistic approximation---> END**********************/
				
				/***********************code to find saturated probablistic approximation---> STARTS**********************/
				
	//system.out.println("\n \n <----****************************-code STARTSS to find saturated probablistic approximation-****************************---->\n");

				 	Set<TreeSet<Integer>> setSaturatedProbablisticApprox= new 
					LinkedHashSet<TreeSet<Integer>>(methodToFindSaturaedProbablisticApproximation(setDStar, alpha,  lhmCharactersticSet_K));		 

	//system.out.println("\n \n <----****************************-code ENDS to find saturated probablistic approximation-****************************---->\n");
				
				/***********************code to find saturated probablistic approximation---> ENDS**********************/
				
				/***********************MLEM2: ALGORITHM---START single local probablistic approximation START**********************/
				
				
				File fileGlobal = new File("output/"+strFileNameWithouttxt+"_Global"+".txt");
		    	if(fileGlobal.exists())
				{
		    		fileGlobal.delete();
				}
		    	fileGlobal = new File("output/"+strFileNameWithouttxt+"_Global"+".txt");
		    	
		    	File fileSaturated = new File("output/"+strFileNameWithouttxt+"_Saturated"+".txt");
		    	if(fileSaturated.exists())
				{
		    		fileSaturated.delete();
				}
		    	fileSaturated = new File("output/"+strFileNameWithouttxt+"_Saturated"+".txt");
				
				 LinkedHashMap<Set<Integer>, Set<Integer>> lhmKeyConcept_ValueLowerAprox=new LinkedHashMap<Set<Integer>, Set<Integer>>();
				   LinkedHashMap<Set<Integer>, Set<Integer>> lhmKeyConcept_ValueUpperAprox=new LinkedHashMap<Set<Integer>, Set<Integer>>();
				   ArrayList<String> listConceptNameUpperAprox=new ArrayList<String>();			   
				   ArrayList<String> listConceptNameLowerApprox=new ArrayList<String>();
				   //////////**************uncomment this to run rule induction and all alogorithm
				/*methodToLEM2AlgoRuleInduction(lhmAtt_Value_SetCase, setDStar,"consistent", file,
						 lhmKeyConcept_ValueLowerAprox,
			    		 lhmKeyConcept_ValueUpperAprox,  reverseMapDStar, listConceptNameLowerApprox, listConceptNameUpperAprox, listConceptNameConsistent,
			    		  strDecisionNameForRulePrint, listdataFileDetailsDiscretizedAfterMergingFinal,  strFileNameWithouttxt,
			    		   setNumericalAttrColName);	*/ 
				   
				   // below is the new one after global probablistic approx but before saturated probablitsic approx
				   
				   //called for rules generated from Global probablistic approximation
				   methodToLEM2AlgoRuleInduction(lhmAtt_Value_SetCase, setGlobalProbablisticApprox,"consistent", fileGlobal,
							 lhmKeyConcept_ValueLowerAprox,
				    		 lhmKeyConcept_ValueUpperAprox,  reverseMapDStar, listConceptNameLowerApprox, listConceptNameUpperAprox, listConceptNameConsistent,
				    		  strDecisionNameForRulePrint, listdataFileDetailsDiscretizedAfterMergingFinal,  strFileNameWithouttxt,
				    		   setNumericalAttrColName, "global", dblBetaTemp);
				   
				 //  called for rules generated from Saturated probablistic approximation
				   methodToLEM2AlgoRuleInduction(lhmAtt_Value_SetCase, setSaturatedProbablisticApprox,"consistent", fileSaturated,
							 lhmKeyConcept_ValueLowerAprox,
				    		 lhmKeyConcept_ValueUpperAprox,  reverseMapDStar, listConceptNameLowerApprox, listConceptNameUpperAprox, listConceptNameConsistent,
				    		  strDecisionNameForRulePrint, listdataFileDetailsDiscretizedAfterMergingFinal,  strFileNameWithouttxt,
				    		   setNumericalAttrColName, "saturated", dblBetaTemp);
				//system.out.println("\n **************************** END FOR LEM2 ALGORITHM**************************\n");
				 
				 ////system.out.println("Generated Rule File Name-->"+ strFileNameWithouttxt+".r");
				//system.out.println("Generated Rule File Name for Global-->"+ strFileNameWithouttxt+"_Saturated"+".txt");
				 //system.out.println("Generated Rule File Name for Saturated-->"+ strFileNameWithouttxt+"_Global"+".txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	 static void methodToLEM2AlgoRuleInduction(LinkedHashMap<String, Set<Integer>> lhmAtt_Value_SetCase, Set<TreeSet<Integer>> setGlobalProbablisticApprox,
	    		String strCertain_Possible_ConsistentRules,File file,
	    		LinkedHashMap<Set<Integer>, Set<Integer>> lhmKeyConcept_ValueLowerAprox,
	    		LinkedHashMap<Set<Integer>, Set<Integer>> lhmKeyConcept_ValueUpperAprox,
	    		Map<String, List<Integer>> reverseMapDStar, 
	    		ArrayList<String> listConceptNameLowerApprox,
	    		ArrayList<String> listConceptNameUpperAprox,
	    		ArrayList<String> listConceptNameConsistent,
	    		String strDecisionNameForRulePrint, ArrayList<dataFileDetails> listdataFileDetailsDiscretizedAfterMergingFinal, String strFileNameWithouttxt,
	    		Set<String> setNumericalAttrColName, String strGlobalOrSaturated, Double beta)
	    {
	    	//system.out.println("\n **************************** START FOR LEM2 ALGORITHM**************************");
	    	
	    	////system.out.println("lhmAtt_Value_SetCase---->"+lhmAtt_Value_SetCase);
	    	////system.out.println("setDStar---->"+setDStar);
	    //	//system.out.println("\n attribute value block are as follows--->\n");
	    	/*now code start to find the intersection between the attribute value block pair and concept one at a time*/
	    	int n=0;
	    	int intConceptNameCounter=0;
	    	String strConceptName;
	    	
	    	try {
				
				FileWriter fw = new FileWriter(file,true); //the true will append the new data
				if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("certain"))
			    fw.write("! Certain Rule Set");//appends the string to the file
				else if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("possible"))
				{
					fw.write("\n");
					fw.write("! Possible Rule Set");//appends the string to the file
			    
				}
				fw.write("\n");
			    fw.write("\n");
			    fw.close();
						} catch (IOException e) {
							// 
							e.printStackTrace();
						}
	    	for(Set<Integer> tempConcept: setGlobalProbablisticApprox) // to loop over all the concept and the inner loop will be to do all the rule induction for 1 concept at a time
	    	
	    	{
	    		strConceptName="";
	    		if (strCertain_Possible_ConsistentRules.equalsIgnoreCase("certain")) {
	    					
	    					strConceptName=listConceptNameLowerApprox.get(n);
	    				}

	    				else if (strCertain_Possible_ConsistentRules.equalsIgnoreCase("possible")) {
	    					
	    					strConceptName=listConceptNameUpperAprox.get(n);
	    				}

	    				else if (strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent")) {
	    					strConceptName=listConceptNameConsistent.get(n);
	    				}
	    		n+=1;
	    	//	//system.out.println("\n\n For concept---"+n+"---->"+tempConcept);
	    		ArrayList<Set<Integer>> listSetSelectAandVForSubsetCheck=new ArrayList<Set<Integer>>();
	    		Set<Integer> setUnionOfCasesForAGoalCovered=new   LinkedHashSet<Integer>(); 
	    		ArrayList<String> listRuleName=new ArrayList<String>();
	    		
	    		try {
	    			
	    			
	    			FileWriter fw = new FileWriter(file,true); //the true will append the new data
	    		   
	    		    fw.close();
	    					} catch (IOException e) {
	    						
	    						e.printStackTrace();
	    					}
	    		methodToLEM2AlgoIntermediateSteps(lhmAtt_Value_SetCase,tempConcept,tempConcept,listSetSelectAandVForSubsetCheck, lhmAtt_Value_SetCase,"main",
	    				setUnionOfCasesForAGoalCovered,listRuleName,file,
	    				lhmKeyConcept_ValueLowerAprox,
	    	    		lhmKeyConcept_ValueUpperAprox,
	    	    		reverseMapDStar,
	    	    		strConceptName,strDecisionNameForRulePrint, strCertain_Possible_ConsistentRules,  listdataFileDetailsDiscretizedAfterMergingFinal,
	    	    		strFileNameWithouttxt,  setNumericalAttrColName,  strGlobalOrSaturated, beta);
	    			// to break the loop 
	try {
		FileWriter fw = new FileWriter(file,true); //the true will append the new data
	 
	    fw.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	    	}
	    }
	 
	 // method called for each main concept to find the intersection and to apply the LEM2 algo, strMainOrInterm="interm" for beween one's and strMainOrInterm="main"
	    //for main goals: tempConcept is used for checked the Atemp* <= D* and tempSubConcept is used to to find the intersection of [(a,v)] block
	    static void methodToLEM2AlgoIntermediateSteps(LinkedHashMap<String, Set<Integer>> lhmAtt_Value_SetCase, Set<Integer> tempConcept,Set<Integer> tempSubConcept,
	    		ArrayList<Set<Integer>> listSetSelectAandVForSubsetCheck,LinkedHashMap<String, Set<Integer>> lhmAtt_Value_SetCaseOriginal, String strMainOrInterm,
	    		Set<Integer> setUnionOfCasesForAGoalCovered, ArrayList<String> listRuleName,File file,
	    		LinkedHashMap<Set<Integer>, Set<Integer>> lhmKeyConcept_ValueLowerAprox,
	    		LinkedHashMap<Set<Integer>, Set<Integer>> lhmKeyConcept_ValueUpperAprox,
	    		Map<String, List<Integer>> reverseMapDStar,
	    		String strConceptName,String strDecisionNameForRulePrint, String strCertain_Possible_ConsistentRules,
	    		ArrayList<dataFileDetails> listdataFileDetailsDiscretizedAfterMergingFinal, String strFileNameWithouttxt, Set<String> setNumericalAttrColName,
	    		String strGlobalOrSaturated,  Double beta)
	    { 
	    	
	    	////system.out.println("check for subset is-->"+tempConcept);
	    	
	    	if(strGlobalOrSaturated.equalsIgnoreCase("global"))
	    	file = new File("output/"+strFileNameWithouttxt+"_Global"+".txt");
	    	else if(strGlobalOrSaturated.equalsIgnoreCase("saturated"))
		    	file = new File("output/"+strFileNameWithouttxt+"_Saturated"+".txt");
	    	
	    	
	    	////system.out.println("C"+lhmAtt_Value_SetCaseOriginal);
	    //	Collections.unmodifiableMap(lhmAtt_Value_SetCaseOriginal);
	    	BufferedWriter output = null;
	    	if(strMainOrInterm.equalsIgnoreCase("main") && !listRuleName.isEmpty())
	    	{
	    		listRuleName.clear();
	    	}
	    	
	    	Set<Integer> setOriginalConcept = new LinkedHashSet<Integer>();
	    	for( Integer intTemp:tempConcept)
	    	{
	    		setOriginalConcept.add(intTemp);
	    	}
	    	
	    	////system.out.println("setOriginalConcept---"+setOriginalConcept);
	    	LinkedHashMap<String, Set<Integer>> lhmIntersectConcept_AttrValuePair=new  LinkedHashMap<String, Set<Integer>>();
			// to store as ink-color__blue--->[1, 6], where ink-color__blue is attr value and [1,6] is the intersection
			
			
			LinkedHashMap<String, Set<Integer>> lhmIntersection=new LinkedHashMap<String, Set<Integer>>();
			
			if(strMainOrInterm.equalsIgnoreCase("main"))
			{
				for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCaseOriginal.entrySet()) { //loop each of the attribute value pair  block to find intersection  
					 
					Set<Integer> setOneConceptAtATimeFrIntersect = new LinkedHashSet<Integer>(); //i.e.  for intersection will hold one set(concept) setDTemp
					setOneConceptAtATimeFrIntersect.addAll(tempSubConcept);
					setOneConceptAtATimeFrIntersect.retainAll(entryTemp1.getValue());// to find the intersection between the each(one at a time) concept and att value block pair
			//	//system.out.println("Intersection-->"+setOneConceptAtATimeFrIntersect);   
				lhmIntersectConcept_AttrValuePair.put(entryTemp1.getKey(), setOneConceptAtATimeFrIntersect);
				 }
			}else if(strMainOrInterm.equalsIgnoreCase("interm"))
			{
				for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCase.entrySet()) { //loop each of the attribute value pair  block to find intersection  
					 
					Set<Integer> setOneConceptAtATimeFrIntersect = new LinkedHashSet<Integer>(); //i.e.  for intersection will hold one set(concept) setDTemp
					setOneConceptAtATimeFrIntersect.addAll(tempSubConcept);
					setOneConceptAtATimeFrIntersect.retainAll(entryTemp1.getValue());// to find the intersection between the each(one at a time) concept and att value block pair
			//	//system.out.println("Intersection-->"+setOneConceptAtATimeFrIntersect);   
				lhmIntersectConcept_AttrValuePair.put(entryTemp1.getKey(), setOneConceptAtATimeFrIntersect);
				 }
			}
			
			//system.out.println("\n-----------NEXT-------------");
			Boolean boolIntersectListExhaust=true;
			/*for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmIntersectConcept_AttrValuePair.entrySet()) { 
				   			
				//system.out.println(entryTemp1.getKey()+"--lhmIntersectConcept_AttrValuePair->"+entryTemp1.getValue()); //size__small--->[1]   // attribute name_value and intersection is there
						size__large--->[3, 6]
								ink-color__blue--->[1, 6]
								ink-color__red--->[3]
								ink-color__black--->[]
								body-color__blue--->[1, 3, 6]
								body-color__black--->[]
								
								*
								*for case when list gets exhausted we get
								*skills__low--lhmIntersectConcept_AttrValuePair->[]
								 skills__high--lhmIntersectConcept_AttrValuePair->[]
								 experience__low--lhmIntersectConcept_AttrValuePair->[]
								 experience__high--lhmIntersectConcept_AttrValuePair->[]
								*
								
			}  //check if all value has no size in it **************************** check check check
*/			
			
			for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmIntersectConcept_AttrValuePair.entrySet()) {
				
				if(!entryTemp1.getValue().isEmpty())
				{
					boolIntersectListExhaust=false;
					break;
				}
			}
			//system.out.println("the inertsection of attribute value pair and concept is EMPTY-->"+boolIntersectListExhaust);
			/*have to check if the list is exhausted or not !!
			1. if yes list has been exhausted then 
			2. checkn for Probablity
			3. if prob>alpha then update  D= D union fat T (intersection when list got exhausted subset check from this) make rule of this
			4. if prob<alpha then put it in JUNK
			5. new goal= D - covered( includes for which subset or (list exhaust & prob>=alpha)) - Junk */
			
			if(!boolIntersectListExhaust)
			{			
			// when the list is not exhausted proceed as normal and go ahead with the rule prediction
			
			// now to find the best intersection
			// 1. maximum elements in the set of intersection is the best one
			// 2. if two has equal i.e. maximum number of elements in them then check the cardinality i.e. corresponding att_value that has minimum set size(min cases)
			// 3. if tie break is there in the 2nd also, then choose the top one
			LinkedHashMap<String, Integer> lhmTempAttrValue_IntersectSize=new LinkedHashMap<String, Integer>();
			// case 2 handle.. i.e. more than intersection has maximum elements so checking the [(a,v)] block pair for the size, have to select the minimum size one
			LinkedHashMap<String, Integer> lhmTempAttrValue_IntersectSizeSameCardinalityCheck=new LinkedHashMap<String, Integer>();
			
			List<String> listBestAttributeIntersected=new ArrayList<String>();
			
			for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmIntersectConcept_AttrValuePair.entrySet()) {     			
				
				lhmTempAttrValue_IntersectSize.put(entryTemp1.getKey(), entryTemp1.getValue().size()); // to store key as attrValueName and value as size of intersection.     			
			}
			//system.out.println("\n lhmTempAttrValue_IntersectSize---> (attributeValueName, size of intersection\n"+lhmTempAttrValue_IntersectSize);
			
			Integer max = lhmTempAttrValue_IntersectSize.entrySet()
		            .stream()
		            .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
		            .get()
		            .getValue();
			
			List listOfMax = lhmTempAttrValue_IntersectSize.entrySet()
		            .stream()
		            .filter(entry -> entry.getValue() == max)
		            .map(Map.Entry::getKey)
		            .collect(Collectors.toList());
			
			if(listOfMax.size()==1) //1st case i.e. after intersection only 1 set has the maximum number of the elements and that attribute will be selected for rule induction
			{
			
			
			// ex. [body-color__blue] or there can be more than one in this, so if it has one then directly proceed with subset check for rule induction else
			// if have more than one element in it then check the minimum
				
				listBestAttributeIntersected.addAll(listOfMax);
			//listBestAttributeIntersected=listOfMax;
			////system.out.println("\n number of elements in the intersection sets are as follows--->\n"+listBestAttributeIntersected);
			}
			else // 2nd case i.e. two or more sets has max and equal number of intersection so now need to see the minimum cardinaltiy., of these two
			{
				
				for( int i=0;i<listOfMax.size();i++)
				{
					if(strMainOrInterm.equalsIgnoreCase("main"))
					{
						lhmTempAttrValue_IntersectSizeSameCardinalityCheck.put((String) listOfMax.get(i), lhmAtt_Value_SetCaseOriginal.get(listOfMax.get(i)).size());
					}else if(strMainOrInterm.equalsIgnoreCase("interm"))
					{
						lhmTempAttrValue_IntersectSizeSameCardinalityCheck.put((String) listOfMax.get(i), lhmAtt_Value_SetCase.get(listOfMax.get(i)).size());	
					}
					
					
				}
				//system.out.println("\n case when more than 1 intersection with [(a,v)] and concept are there, so finding th cardinaltiy of all the maximum one \n ");
				//system.out.println(lhmTempAttrValue_IntersectSizeSameCardinalityCheck);	
			//	lhmTempAttrValue_IntersectSizeSameCardinalityCheck.put("body-color__blue", 4);
				Integer minCardinality = lhmTempAttrValue_IntersectSizeSameCardinalityCheck.entrySet()
	    	            .stream()
	    	            .max((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? 1 : -1)
	    	            .get()
	    	            .getValue();
	    		
	    		List minCardinalityList = lhmTempAttrValue_IntersectSizeSameCardinalityCheck.entrySet()
	    	            .stream()
	    	            .filter(entry -> entry.getValue() == minCardinality)
	    	            .map(Map.Entry::getKey)
	    	            .collect(Collectors.toList());
	    		
	    		//system.out.println("\n out of the confilcting maximum intersection, one that has minimum cardinality is--->\n"+minCardinalityList);
	    		//listBestAttributeIntersected=minCardinalityList;
	    		listBestAttributeIntersected.addAll(minCardinalityList);
	    		
	    		////system.out.println("C12"+lhmAtt_Value_SetCaseOriginal);
			}
			
			if(listBestAttributeIntersected.size()>1) // third case when the maximum intersection are greater than 1 and also the cardinality of more than 1 [(a,v)] block
				// are same so by heuristic selecting the 1st attribute
			{
				List<String> tempList=new ArrayList<String>(listBestAttributeIntersected);
				//tempList=listBestAttributeIntersected;
				
			//	//system.out.println("----------------------"+listBestAttributeIntersected);
			//	//system.out.println("----------------------"+tempList);
				
				//listBestAttributeIntersected.clear();
				listBestAttributeIntersected.add(tempList.get(0));
				////system.out.println("C1212"+lhmAtt_Value_SetCaseOriginal);
			}
			
			// this listBestAttributeIntersected first element stores the best attribute
			////system.out.println("\n After the check the best attribute is--->"+ listBestAttributeIntersected.get(0));
			
			
			/************making rules such that it is presentable to write on the text file start************/
			
			String[] strTemp=listBestAttributeIntersected.get(0).split("__");
			if(listRuleName.isEmpty())
			{listRuleName.add("("+strTemp[0]+","+strTemp[1]+")");
			}
			else
			{
				listRuleName.add("  &  ("+strTemp[0]+","+strTemp[1]+")");
			}
			
			/************making rules such that it is presentable to write on the text file END************/
			if(strMainOrInterm.equalsIgnoreCase("main"))
			{
				listSetSelectAandVForSubsetCheck.add(lhmAtt_Value_SetCaseOriginal.get(listBestAttributeIntersected.get(0)));
			}else if(strMainOrInterm.equalsIgnoreCase("interm"))
			{
				listSetSelectAandVForSubsetCheck.add(lhmAtt_Value_SetCase.get(listBestAttributeIntersected.get(0)));
			}
			
			// it stored intermediatory selected attribites [(a,v)] so that when finding Atemp* <= D*, all the selected [(a,v)], intersection can be computed and then
			// finding the whether its a subset or not
			
			//system.out.println("content of intersection of [(a,v)] pair that will be checked against the D* or goal are---\n"+listSetSelectAandVForSubsetCheck);
			////system.out.println("C112233"+lhmAtt_Value_SetCaseOriginal);
			
			Set<Integer> setIntersectTemp=new   LinkedHashSet<Integer>(); 
			setIntersectTemp.addAll(listSetSelectAandVForSubsetCheck.get(0));
			
			////system.out.println("C223344"+lhmAtt_Value_SetCaseOriginal);
			
			LinkedHashMap<String, Set<Integer>> lhmAtt_Value_SetCaseOriginaltemp=new LinkedHashMap<String, Set<Integer>>();
			
			
			
			for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCaseOriginal.entrySet()) { 
				
				lhmAtt_Value_SetCaseOriginaltemp.put(entryTemp1.getKey(), entryTemp1.getValue());
				
			}
			
			for (int i = 1; i < listSetSelectAandVForSubsetCheck.size(); i++) {
				////system.out.println(i+"--->before"+lhmAtt_Value_SetCaseOriginal);
				setIntersectTemp.retainAll(listSetSelectAandVForSubsetCheck.get(i));
				////system.out.println(i+"--->aftere"+lhmAtt_Value_SetCaseOriginal);
			}
	for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCaseOriginaltemp.entrySet()) { 
				
				lhmAtt_Value_SetCaseOriginal.put(entryTemp1.getKey(), entryTemp1.getValue());
				
			}
			
			
//		//system.out.println("C565656"+lhmAtt_Value_SetCaseOriginal);
			////system.out.println("***After each iteration the subset that needs to be check against the concept/goal is---****\n");
			////system.out.println(setIntersectTemp+"\n");
			//	//system.out.println("C5656561212"+lhmAtt_Value_SetCaseOriginal);
			Boolean boolTemp= isSubset(tempConcept.stream().mapToInt(Integer::intValue).toArray(),
					setIntersectTemp.stream().mapToInt(Integer::intValue).toArray(),
					tempConcept.size(),setIntersectTemp.size());
			//	//system.out.println("C666666666666"+lhmAtt_Value_SetCaseOriginal);
			////system.out.println("for A temp*==>"+setIntersectTemp +"and D* ==>"+tempConcept);
			////system.out.println("result is subest or not=====>"+boolTemp);
			LinkedHashMap<String, Set<Integer>> lhmAtt_Value_SetCaseIntermediate=new LinkedHashMap<String, Set<Integer>>();
		//	lhmAtt_Value_SetCaseIntermediate=lhmAtt_Value_SetCase;
		
			
			if(strMainOrInterm.equalsIgnoreCase("main"))
			{
				for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCaseOriginal.entrySet()) {
					
					lhmAtt_Value_SetCaseIntermediate.put(entryTemp1.getKey(), entryTemp1.getValue());
					
				}
			}else if(strMainOrInterm.equalsIgnoreCase("interm"))
			{
				for (Map.Entry<String, Set<Integer>> entryTemp1 : lhmAtt_Value_SetCase.entrySet()) {
					
					lhmAtt_Value_SetCaseIntermediate.put(entryTemp1.getKey(), entryTemp1.getValue());
					
				}
			}
			////system.out.println("C87897789"+lhmAtt_Value_SetCaseOriginal);
			if(!boolTemp) // i.e. boolTemp  is false i.e. is not a subset then moving forward for the intersection but now the [(a,v)] block will not contain the attribute
						  // that got selected in the last iteration, so storing the new [(a,v)] without the one that get selected in previous runs.
			{
				//strTemp[0] is the name of the attribute 
						
				if(!setNumericalAttrColName.contains(strTemp[0])) // if not numerical then completely remove that attribute
				{
						lhmAtt_Value_SetCaseIntermediate.keySet().removeAll(lhmAtt_Value_SetCaseIntermediate.keySet()
			                     .stream()
			                     .filter(s -> s.startsWith(strTemp[0]))
			                     .collect(Collectors.toSet()));
				}else {
					//if numerical then remove that attribute with superset
					
					lhmAtt_Value_SetCaseIntermediate.keySet().remove(listBestAttributeIntersected.get(0)); // remove that selected one
					
					String[] strMinMaxSelected=strTemp[1].split("\\.\\.");
					
					double dblSelectedMin=Double.valueOf(strMinMaxSelected[0]);
					double dblSelectedMax=Double.valueOf(strMinMaxSelected[1]);					
				
					
					////system.out.println("lhmAtt_Value_SetCaseIntermediate.keySet()-->"+lhmAtt_Value_SetCaseIntermediate.keySet());
//[wind__4.0..6.0, wind__6.0..30.0, wind__10.0..30.0, wind__4.0..14.0, wind__14.0..30.0, wind__4.0..23.0, wind__23.0..30.0,
					//humidity__low, humidity__medium, humidity__high, temperature__medium, temperature__low, temperature__high]	
					
					ArrayList<String> listKeySet= new ArrayList<String>(lhmAtt_Value_SetCaseIntermediate.keySet());
					for(String strTempKeys:listKeySet )
					{
						if(strTempKeys.startsWith(strTemp[0])) // i.e.,selecting only that numerical att that has been chosen as the best one
						{
							
							String[] strTempAttRange=strTempKeys.split("__"); //wind__4.0..6.0
							
							String[] strMinMaxSelectedTemp=strTempAttRange[1].split("\\.\\.");
							
							double dblSelectedMinTemp=Double.valueOf(strMinMaxSelectedTemp[0]);
							double dblSelectedMaxTemp=Double.valueOf(strMinMaxSelectedTemp[1]);
							
							if(dblSelectedMin==dblSelectedMinTemp && dblSelectedMaxTemp>=dblSelectedMax)
							{
								//eg 4..6 gets covered so have to remove 4..10 since superset
								lhmAtt_Value_SetCaseIntermediate.keySet().remove(strTempKeys);
								
								
							}
							else if(dblSelectedMinTemp<=dblSelectedMin && dblSelectedMaxTemp>=dblSelectedMax)
							{
								//eg dblSelectedMin 6..10 gets covered so have to remove dblSelectedMinTemp 4..20 since superset
								
								lhmAtt_Value_SetCaseIntermediate.keySet().remove(strTempKeys);
																
							}
							
						}
					}
					
				}
				// 
				//here have to change from the lhmAtt_Value_SetCaseIntermediate.keySet() have to remove all one staring from 
				
				
				
				////system.out.println("\n after checking i.e. not a subset moving forward for next iteration; new [(a,v)] is--->/n");
				////system.out.println(lhmAtt_Value_SetCaseIntermediate); // now this [(a,v)] will be used to find the intersection
				////system.out.println("lhmAtt_Value_SetCase original must have all-->\n"+lhmAtt_Value_SetCase);
				////system.out.println("\n\n****************** GOING FOR THE NEXT ITERATION***********************\n\n");
				////system.out.println("C6666666666666666666666"+lhmAtt_Value_SetCaseOriginal);
				setOriginalConcept.clear();
				Test_fp.globalInt++;
				////system.out.println("total run--->"+Test_dm.globalInt);
				//making the best selected intersection as the new goal to check for the intersection
				setOriginalConcept.addAll(lhmIntersectConcept_AttrValuePair.get(listBestAttributeIntersected.get(0)));
				methodToLEM2AlgoIntermediateSteps(lhmAtt_Value_SetCaseIntermediate,tempConcept,setOriginalConcept,listSetSelectAandVForSubsetCheck,lhmAtt_Value_SetCaseOriginal,"interm"
						,setUnionOfCasesForAGoalCovered,listRuleName,file,
						 lhmKeyConcept_ValueLowerAprox,
			    		 lhmKeyConcept_ValueUpperAprox,  reverseMapDStar,strConceptName, strDecisionNameForRulePrint, strCertain_Possible_ConsistentRules,
			    		 listdataFileDetailsDiscretizedAfterMergingFinal,  strFileNameWithouttxt, setNumericalAttrColName,  strGlobalOrSaturated, beta);
			}
				//lhmAtt_Value_SetCaseOriginal.lhmAtt_Value_SetCase
			else // i.e. intersection of [(a,v)] is subset of the original goal.concept
			{////system.out.println("C5555555555555"+lhmAtt_Value_SetCaseOriginal);
				////system.out.println("\nrule cases are------>\n" +"----->"+listSetSelectAandVForSubsetCheck +"<-----");
			//	//system.out.println("\n");
			//	//system.out.println("for three numbers---->covered----> "+setIntersectTemp);// from main concept what all are covered
				
				setUnionOfCasesForAGoalCovered.addAll(setIntersectTemp);
				
				////system.out.println("Main Goal----> "+tempConcept);// main original goal : G
				////system.out.println("setOriginalConcept----> "+setOriginalConcept);
				setOriginalConcept.removeAll(setUnionOfCasesForAGoalCovered);//setOriginalConcept contains the remaining goal
				//system.out.println("Now, need to run the next iteration to cover the following remaining goals else\n"+setOriginalConcept);
				//system.out.println("tempConcept---after--->"+tempConcept);// main original goal : G
				
	/******************************code start for dropping condition********************/
				
			//	//system.out.println("\n*****Dropping condition code starts*****\n");

				////system.out.println("The [(a,v)] block whose intersection happens to be subset---> \n"+listSetSelectAandVForSubsetCheck);
				////system.out.println("Main Goal----> "+tempConcept);// main original goal : G
				
				////system.out.println("\n*****Dropping condition code ends*****\n");
				
				/******************************code ends for dropping condition********************/
				/*start code to have the 3rd number of the triplet i.e. correctly classified*/
				ArrayList<Boolean> listBoolCompleteMatching=new ArrayList<Boolean>();
				for(Integer intCaseNo: setIntersectTemp)
				{
					
				/*////system.out.println("For case number-->"+intCaseNo+"decision value is----"+
						listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getLhmCaseDecisionValue().
					get(listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getStrDecisionAttrName()));*/
					
				if(listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getLhmCaseDecisionValue().
					get(listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getStrDecisionAttrName()).equalsIgnoreCase(strConceptName))
				{
					listBoolCompleteMatching.add(true);
				}
				
				}    				
				
				/*END code to have the 3rd number of the triplet i.e. correctly classified*/
				
				////system.out.println("lhmAtt_Value_SetCase---here here-->"+lhmAtt_Value_SetCase);
				if (!setOriginalConcept.isEmpty()) {
					ArrayList<Set<Integer>> listSetSelectAandVForSubsetCheckNew = new ArrayList<Set<Integer>>();
					// running the iteration for the remaining part or the uncovered goal
					try {
						String strTemp1=listRuleName.get(listRuleName.size()-1);////system.out.println("First strTemp1----->"+strTemp1);
						strTemp1+=" -> "+ "("+strDecisionNameForRulePrint+","+strConceptName+")";					
						
						listRuleName.set(listRuleName.size()-1, strTemp1);
					//	//system.out.println("listRuleName---->22"+listRuleName);
						FileWriter fw = new FileWriter(file,true); //the true will append the new data
						if(String.valueOf(listRuleName.toString()).contains("&"))
						{
						String[] strTotalNoCondition=String.valueOf(listRuleName.toString()).split("&");
							////system.out.println("Total number of condition for--->\n"+listRuleName+"---are---"+strTotalNoCondition.length);
							
							if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent"))
							{
							
							fw.write(strTotalNoCondition.length+", "+setIntersectTemp.size()+", "+setIntersectTemp.size());;
							}else
							{
								// i.e. DS is inconsistent and the third number denotes correctly classfiied
								// sp have to check the rule decision value and covered cases decision values,
								// this numbe denotes correctly classified
								
							//	//system.out.println("case number covered inconsistent--->"+setIntersectTemp);
							//	//system.out.println("Concept from the rule is--->"+strConceptName);
								
								
								fw.write(strTotalNoCondition.length+", "+setIntersectTemp.size()+", "+listBoolCompleteMatching.size());;
							}
						}else
						{
							////system.out.println("Total number of condition for--->\n"+listRuleName+"---are---"+1);
							if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent"))
							{
							fw.write("1"+", "+setIntersectTemp.size()+", "+setIntersectTemp.size());;
							}else
							{
								
								// i.e. DS is inconsistent and the third number denotes correctly classfiied
								// sp have to check the rule decision value and covered cases decision values,
								// this numbe denotes correctly classified
								
							//	//system.out.println("case number covered inconsistent--->"+setIntersectTemp);
							//	//system.out.println("Concept from the rule is--->"+strConceptName);
								
								fw.write("1"+", "+setIntersectTemp.size()+", "+listBoolCompleteMatching.size());;
							}
						}
						
						for(String str:listRuleName)
						{
							if(str.contains("&"))
							{
								fw.write(str);
								
							}else
							{
								
								fw.write("\n");
								fw.write(str);
								
							}
							
						}fw.write("\n");
					    fw.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}			Test_fp.globalInt++;
				////system.out.println("total run--->"+Test_dm.globalInt);
					////system.out.println("listRuleName---->"+listRuleName);				
					methodToLEM2AlgoIntermediateSteps(lhmAtt_Value_SetCaseOriginal,tempConcept ,setOriginalConcept,listSetSelectAandVForSubsetCheckNew,
							lhmAtt_Value_SetCaseOriginal,"main",setUnionOfCasesForAGoalCovered,listRuleName,file,
							lhmKeyConcept_ValueLowerAprox,
				    		 lhmKeyConcept_ValueUpperAprox,  reverseMapDStar,strConceptName,strDecisionNameForRulePrint, strCertain_Possible_ConsistentRules,
				    		 listdataFileDetailsDiscretizedAfterMergingFinal,  strFileNameWithouttxt, setNumericalAttrColName,  strGlobalOrSaturated, beta);
				} else
				{
					try {					
						String strTemp1=listRuleName.get(listRuleName.size()-1);
						
						strTemp1+=" -> "+ "("+strDecisionNameForRulePrint+","+strConceptName+")";
						listRuleName.set(listRuleName.size()-1, strTemp1);
						FileWriter fw = new FileWriter(file,true); //the true will append the new data
						if(String.valueOf(listRuleName.toString()).contains("&"))
						{
						String[] strTotalNoCondition=String.valueOf(listRuleName.toString()).split("&");
							////system.out.println("Total number of condition for--->\n"+listRuleName+"---are---"+strTotalNoCondition.length);
							if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent"))
							{
							fw.write(strTotalNoCondition.length+", "+setIntersectTemp.size()+", "+setIntersectTemp.size());;
							}else
							{
								
								// i.e. DS is inconsistent and the third number denotes correctly classfiied
								// sp have to check the rule decision value and covered cases decision values,
								// this numbe denotes correctly classified
								
							//	//system.out.println("case number covered inconsistent--->"+setIntersectTemp);
							//	//system.out.println("Concept from the rule is--->"+strConceptName);
								
								
								fw.write(strTotalNoCondition.length+", "+setIntersectTemp.size()+", "+listBoolCompleteMatching.size());;
							}
						}
						else
						{
						//	//system.out.println("Total number of condition for--->\n"+listRuleName+"---are---"+1);
							if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent"))
							{
							fw.write("1"+", "+setIntersectTemp.size()+", "+setIntersectTemp.size());}
							else
							{
								// i.e. DS is inconsistent and the third number denotes correctly classfiied
								// sp have to check the rule decision value and covered cases decision values,
								// this numbe denotes correctly classified
								
								// i.e. DS is inconsistent and the third number denotes correctly classfiied
								// sp have to check the rule decision value and covered cases decision values,
								// this numbe denotes correctly classified
								
							//	//system.out.println("case number covered inconsistent--->"+setIntersectTemp);
							//	//system.out.println("Concept from the rule is--->"+strConceptName);
								
								
								fw.write("1"+", "+setIntersectTemp.size()+", "+listBoolCompleteMatching.size());
							}
						}
						
					   // dsfdsf
						
						
						
						for(String str:listRuleName)
							{
							if(str.contains("&"))
							{
								fw.write(str);
								
							}else
							{
								fw.write("\n");
								fw.write(str);
								
							}
						}
							
					    fw.write("\n");
					    fw.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				//	//system.out.println("\n\n");
					////system.out.println("listRuleName---->"+listRuleName);
				//	//system.out.println("\n*********** RULES HAVE BEEN GENERATED FOR CONCEPT-->"+ tempConcept+"*****\n");
					
				//	//system.out.println("\n");
				}
			}
			}
			else
			{				
				//setIntersectTemp contains the intersection of all sets(previouslt selected in process) but now list is exhausted so
				// finding probablity
				//tempConcept-- contains the original goal
				// (setIntersectTemp ^ tempConcept)/ setIntersectTemp
				Set<Integer> setIntersectTemp=new   LinkedHashSet<Integer>(); 
				setIntersectTemp.addAll(listSetSelectAandVForSubsetCheck.get(0));
				
				for (int i = 1; i < listSetSelectAandVForSubsetCheck.size(); i++) {					
					setIntersectTemp.retainAll(listSetSelectAandVForSubsetCheck.get(i));					
				}
				
				Set<Integer> setNumProbablity = new HashSet<Integer>(setIntersectTemp); // use the copy constructor
				setNumProbablity.retainAll(tempConcept);
				
				double doubleProbablity=((double)setNumProbablity.size()/(double)setIntersectTemp.size());
				//system.out.println("Finding probablity between-->"+setNumProbablity+"--and intersect--"+  setIntersectTemp);
				//system.out.println("Probablity is-->"+(double)Math.round(doubleProbablity * 1000d) / 1000d);
				
				// now to check whether calculated probablity is >= or < than alpha
				// so accordingly value of D and junk will get change
				
				if((double)Math.round(doubleProbablity * 1000d) / 1000d>=beta)
				{
					try {
					//1. update D i.e., D= D union intersect and now <= subset will be check against this
					//2. and add this in the rule book // this this as to be covered as well					
					
					tempConcept.addAll(setIntersectTemp); // check for subest D
					setUnionOfCasesForAGoalCovered.addAll(setIntersectTemp); // this includes junk(discarded) or covered or T prob>=alpha
					
					Set<Integer> temp = new HashSet<Integer>(tempConcept);
					temp.removeAll(setUnionOfCasesForAGoalCovered);
					
					setOriginalConcept = temp.stream().collect(Collectors.toSet());// [] at the last i.e., nothing remaining to cover				
				
					/*start code to have the 3rd number of the triplet i.e. correctly classified*/
					ArrayList<Boolean> listBoolCompleteMatching=new ArrayList<Boolean>();
					for(Integer intCaseNo: setIntersectTemp)
					{
						
					/*////system.out.println("For case number-->"+intCaseNo+"decision value is----"+
							listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getLhmCaseDecisionValue().
						get(listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getStrDecisionAttrName()));*/
						
					if(listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getLhmCaseDecisionValue().
						get(listdataFileDetailsDiscretizedAfterMergingFinal.get(intCaseNo-1).getStrDecisionAttrName()).equalsIgnoreCase(strConceptName))
					{
						listBoolCompleteMatching.add(true);
					}
					
					
					}    
					String strTemp1=listRuleName.get(listRuleName.size()-1);////system.out.println("First strTemp1----->"+strTemp1);
					strTemp1+=" -> "+ "("+strDecisionNameForRulePrint+","+strConceptName+")";					
					
					listRuleName.set(listRuleName.size()-1, strTemp1);
				//	//system.out.println("listRuleName---->22"+listRuleName);
					FileWriter fw = new FileWriter(file,true); //the true will append the new data
					if(String.valueOf(listRuleName.toString()).contains("&"))
					{
					String[] strTotalNoCondition=String.valueOf(listRuleName.toString()).split("&");
						////system.out.println("Total number of condition for--->\n"+listRuleName+"---are---"+strTotalNoCondition.length);
						
						if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent"))
						{
						
						fw.write(strTotalNoCondition.length+", "+setIntersectTemp.size()+", "+setIntersectTemp.size());;
						}else
						{
							// i.e. DS is inconsistent and the third number denotes correctly classfiied
							// sp have to check the rule decision value and covered cases decision values,
							// this numbe denotes correctly classified
							
						//	//system.out.println("case number covered inconsistent--->"+setIntersectTemp);
						//	//system.out.println("Concept from the rule is--->"+strConceptName);
							
							
							fw.write(strTotalNoCondition.length+", "+setIntersectTemp.size()+", "+listBoolCompleteMatching.size());;
						}
					}else
					{
						////system.out.println("Total number of condition for--->\n"+listRuleName+"---are---"+1);
						if(strCertain_Possible_ConsistentRules.equalsIgnoreCase("consistent"))
						{
						fw.write("1"+", "+setIntersectTemp.size()+", "+setIntersectTemp.size());;
						}else
						{
							
							// i.e. DS is inconsistent and the third number denotes correctly classfiied
							// sp have to check the rule decision value and covered cases decision values,
							// this numbe denotes correctly classified
							
						//	//system.out.println("case number covered inconsistent--->"+setIntersectTemp);
						//	//system.out.println("Concept from the rule is--->"+strConceptName);
							
							fw.write("1"+", "+setIntersectTemp.size()+", "+listBoolCompleteMatching.size());;
						}
					}
					for(String str:listRuleName)
					{
						if(str.contains("&"))
						{
							fw.write(str);
							
						}else
						{
							//fw.write(" -> ");
							fw.write("\n");
							fw.write(str);
							//fw.write(" -> ");
						}
						
					}fw.write("\n");
				    fw.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}else
				{
					// 1. add the intersect in the junk i.e. already covered list
					// if list exhausted and probab <= alpha add in junk and update
						// a. final goal (i.e., left over to cover)
						// b. covered ( covered + junk)
					setUnionOfCasesForAGoalCovered.addAll(setIntersectTemp);			
					
					
				}
				setOriginalConcept.removeAll(setUnionOfCasesForAGoalCovered);
				//3. update new Goal (G)= D - { covered + junk + this intersect}
				
				//again check for setOriginalConcept is empty then no need to proceed
				//system.out.println();
				if(!setOriginalConcept.isEmpty())
				{
				ArrayList<Set<Integer>> listSetSelectAandVForSubsetCheckNew = new ArrayList<Set<Integer>>();
				methodToLEM2AlgoIntermediateSteps(lhmAtt_Value_SetCaseOriginal,tempConcept ,setOriginalConcept,listSetSelectAandVForSubsetCheckNew,
						lhmAtt_Value_SetCaseOriginal,"main",setUnionOfCasesForAGoalCovered,listRuleName,file,
						lhmKeyConcept_ValueLowerAprox,
			    		 lhmKeyConcept_ValueUpperAprox,  reverseMapDStar,strConceptName,strDecisionNameForRulePrint, strCertain_Possible_ConsistentRules,
			    		 listdataFileDetailsDiscretizedAfterMergingFinal,  strFileNameWithouttxt, setNumericalAttrColName,  strGlobalOrSaturated, beta);
				}
			}
		
	    }
	    
	    /* Return true if arr2[] A* is a subset of arr1[] D* */
	    static boolean isSubset(int arr1[], int arr2[], int m, 
	                                                 int n) 
	    { 
	    	////system.out.println("A temp* is--->"+Arrays.toString(arr2));
	    	////system.out.println("D* is--->"+Arrays.toString(arr1));
	    	
	        HashSet<Integer> hset= new HashSet<>(); 
	          
	        // hset stores all the values of arr1 
	        for(int i = 0; i < m; i++) 
	        { 
	            if(!hset.contains(arr1[i])) 
	                hset.add(arr1[i]); 
	        } 
	              
	        // loop to check if all elements of arr2 also 
	        // lies in arr1 
	        for(int i = 0; i < n; i++) 
	        { 
	            if(!hset.contains(arr2[i])) 
	                return false; 
	        } 
	        return true; 
	    }  
	    
	    static LinkedHashMap<String, ArrayList<String>> methodToHandleNumericalDataColumn(Set<String> setNumericalAttrColName,
	    		ArrayList<dataFileDetails> listdataFileDetails)
	    {
	    	//system.out.println("\n \n*************Numerical attribute encountered --- START to find midpoints and change to symbolic*************\n");
	    	
	    	LinkedHashMap<String, ArrayList<String>> lhmNumAttNameCutPointsMap= new LinkedHashMap<String, ArrayList<String>>();
	    	
	    	for(String strTempNumAtt: setNumericalAttrColName)
	    	{
	    		// for each numerical attribute looping in for all the cases and finding a list
	    		TreeSet<Double> numericalSortedUnique = new TreeSet<Double>();
	    		TreeSet<Double> numericalSortedUniqueMidpoint = new TreeSet<Double>();
	    		ArrayList<Double> listTempNumericalSortedUnique=new ArrayList<Double>();
	    		
	    		ArrayList<String> listNumCutPointEachNumAttr= new ArrayList<String>();
	    		
	    		double doubleMinNumber=0;
	    		double doubleMaxNumber=0;
	    		
	    		for (dataFileDetails obj : listdataFileDetails) {
	    			
	    			if(!(obj.getLhmCases().get(strTempNumAtt).equalsIgnoreCase("*") || obj.getLhmCases().get(strTempNumAtt).equalsIgnoreCase("?")))
	    			numericalSortedUnique.add(Double.parseDouble(obj.getLhmCases().get(strTempNumAtt)));
	    			
	    		}
	    		
	    		if(!numericalSortedUnique.isEmpty())
	    		{
	    			doubleMinNumber=numericalSortedUnique.first();
	    			doubleMaxNumber=numericalSortedUnique.last();
	    		}
	    		
	    		//system.out.println("For numerical attribute-->"+ strTempNumAtt+"-->"+ numericalSortedUnique);
	    		//system.out.println("Minimum-->"+doubleMinNumber+"--max--"+doubleMaxNumber);
	    		
	    		listTempNumericalSortedUnique.addAll(numericalSortedUnique);
	    		//system.out.println("list version of set is-->"+listTempNumericalSortedUnique);
	    		
	    		for(int i=0;i<listTempNumericalSortedUnique.size()-1;i++)
	    		{
	    			
	    			double mid=(listTempNumericalSortedUnique.get(i)+listTempNumericalSortedUnique.get(i+1))/2;
	    			numericalSortedUniqueMidpoint.add((double)Math.round(mid * 1000d) / 1000d);
	    		}
	    		//system.out.println("MID POINTS are-->"+numericalSortedUniqueMidpoint);
	    		
	    		for(Double temp:numericalSortedUniqueMidpoint)
	    		{
	    			listNumCutPointEachNumAttr.add(doubleMinNumber+".."+temp);
	    			listNumCutPointEachNumAttr.add(temp+".."+doubleMaxNumber);
	    			//system.out.println(doubleMinNumber+".."+temp);
	    			//system.out.println(temp+".."+doubleMaxNumber);
	    			
	    			/*list version of set is-->[4.0, 8.0, 12.0, 16.0, 30.0]
	    					MID POINTS are-->[6.0, 10.0, 14.0, 23.0]
	    					4.0..6.0
	    					6.0..30.0
	    					4.0..10.0
	    					10.0..30.0
	    					4.0..14.0
	    					14.0..30.0
	    					4.0..23.0
	    					23.0..30.0*/
	    		}
	    		lhmNumAttNameCutPointsMap.put(strTempNumAtt,listNumCutPointEachNumAttr )	;
	    	} 	
	    	
	    	//system.out.println(lhmNumAttNameCutPointsMap);
	    	
	    	//{wind=[4.0..6.0, 6.0..30.0, 4.0..10.0, 10.0..30.0, 4.0..14.0, 14.0..30.0, 4.0..23.0, 23.0..30.0]}
	    	//system.out.println("\n \n*************Numerical attribute encountered --- END to find midpoints and change to symbolic************* \n");
			return lhmNumAttNameCutPointsMap;
	    	
	    }
	    
	    public static LinkedHashMap<Integer, LinkedHashSet<Integer>> methodToFindCharactersticSets(LinkedHashMap<String, LinkedHashMap<String, Set<Integer>>>
	    lhmAttributeValueBlock, ArrayList<dataFileDetails> listdataFileDetailsDiscretizedAfterMergingFinal	 )
	    {
	    	
	    	////system.out.println(lhmAttributeValueBlock);
	    	
	    	LinkedHashMap<Integer, LinkedHashSet<Integer>> lhmCharactersticSet_K= new LinkedHashMap<Integer, LinkedHashSet<Integer>>();
	    	for(dataFileDetails objTmp: listdataFileDetailsDiscretizedAfterMergingFinal )
		{
			LinkedHashSet<Integer> setTempCharactersticSet_K = new LinkedHashSet<Integer>();

			for (Map.Entry<String, String> entry : objTmp.getLhmCases().entrySet()) {
				////system.out.println(entry.getKey());
				////system.out.println(entry.getValue());

				if (!(entry.getValue().equalsIgnoreCase("*") || entry.getValue().equalsIgnoreCase("?"))) {
					// //system.out.println(lhmAttributeValueBlock.get(entry.getKey()).get(entry.getValue()));

					if (setTempCharactersticSet_K.isEmpty()) { // adding the first element to set
						setTempCharactersticSet_K
								.addAll(lhmAttributeValueBlock.get(entry.getKey()).get(entry.getValue()));
					} else { // then after this if condition is executed set will never be empty so finding intersection
						setTempCharactersticSet_K
								.retainAll(lhmAttributeValueBlock.get(entry.getKey()).get(entry.getValue()));
					}

				}
			}

			lhmCharactersticSet_K.put(objTmp.getIntCaseNumber(), setTempCharactersticSet_K);

		}
	    	
	    	//system.out.println("characterstic sets--->\n"+lhmCharactersticSet_K);
	    	// key= case number and value= characterstic set
	    	// output--> {1=[1, 6], 2=[2, 4, 6, 7], 3=[3], 4=[2, 4, 6], 5=[5], 6=[1, 2, 4, 6], 7=[2, 3, 4, 6, 7], 8=[8]}
	    	
	    	return lhmCharactersticSet_K;
	    }
	    
	    public static LinkedHashSet<TreeSet<Integer>> methodToFindGlobalProbablisticApproximation(Set<Set<Integer>> setDStar, double alpha2,
	    		LinkedHashMap<Integer, LinkedHashSet<Integer>> lhmCharactersticSet_K)
	    {
	    	LinkedHashSet<TreeSet<Integer>> setGlobalProbablisticApprox= new LinkedHashSet<TreeSet<Integer>>();
	    	//system.out.println("\n ************************************STARTING GLOBAL PROBABLISTIC APPROXIMATION ALGORITHM************************************");
	    	LinkedHashMap<Set<Integer>, Set<LinkedHashSet<Integer>>> lhmFinalCharacterSetGreaterAlpha= new LinkedHashMap<Set<Integer>, Set<LinkedHashSet<Integer>>>();
	    	//LinkedHashMap<Integer, Set<Integer>> lhmCharactersticSet_K_Temp= new LinkedHashMap<Integer, Set<Integer>>(lhmCharactersticSet_K);
	    	int counter=0;
	    	for (Set<Integer> tempConcept:setDStar )
	    	{
	    		Set<LinkedHashSet<Integer>> setOfSetTemp=new LinkedHashSet<LinkedHashSet<Integer>>();
	    		for(Entry<Integer, LinkedHashSet<Integer>> tmpK:lhmCharactersticSet_K.entrySet() )
	    		{
	    			
	    			LinkedHashSet<Integer> tmpSet= new LinkedHashSet<Integer>(tmpK.getValue());
	    			tmpSet.retainAll(tempConcept);
	    			//tmpNumIntersectionSet.addAll(
	    			//double probablity= tmpSet.size()/tmpK.getValue().size();
	    			double probablity=((double)tmpSet.size()/(double)tmpK.getValue().size());
	    			//system.out.println((double)Math.round(probablity * 1000d) / 1000d);
	    			
	    			if((double)Math.round(probablity * 1000d) / 1000d>=alpha)
	    			{
	    				//lhmFinalCharacterSetGreaterAlpha.put(counter, tmpK.getValue());
	    				setOfSetTemp.add( (LinkedHashSet<Integer>) tmpK.getValue());
	    				
	    			}							
	    		}
	    		
	    		lhmFinalCharacterSetGreaterAlpha.put(tempConcept, setOfSetTemp);
	    		counter++;
	    	}
	    	
	    	//system.out.println(lhmFinalCharacterSetGreaterAlpha); // this will act as an input to the global probablistic approx algorithm
	    	//{[1, 2, 3, 4]=[[1, 6], [2, 4, 7, 6], [3], [4, 6, 2], [1, 4, 6, 2], [2, 4, 7, 3, 6]], [5, 6, 7, 8]=[[1, 6], [2, 4, 7, 6], [5], [8]]}
	    	// key is the original concept 
	    	// and value is the corresponding qualified set of characerstic sets
	    	// qualified charactertics sets i.e. K(x) for which >= alpha
	    	
	    	//////////////start algo
	    	
	    	
	    	
	    	for(Entry<Set<Integer>, Set<LinkedHashSet<Integer>>> entryTempConcept_K:lhmFinalCharacterSetGreaterAlpha.entrySet() )
	    	{
	    		//system.out.println("\n\nfor MAIN concept--> "+ entryTempConcept_K.getKey());
	    		//system.out.println("for this K(x) for which >=alpha--> "+ entryTempConcept_K.getValue());
	    		
	    		
	    		Set<Integer> tmpConceptG=new LinkedHashSet<Integer>(entryTempConcept_K.getKey()); // G=X
	    		TreeSet<Integer> tmpGlobalProbabApproxT=new TreeSet<Integer>();       // T:= empty set
	 LinkedHashMap<Integer, LinkedHashSet<Integer>> eligibleCharactersticSets= new LinkedHashMap<Integer, LinkedHashSet<Integer>>(); // Y
	    		int count=0;
	 for(LinkedHashSet<Integer> tmp:entryTempConcept_K.getValue() )
	 {
		 eligibleCharactersticSets.put(count, tmp);
		 count++;
	 }
	 //system.out.println(eligibleCharactersticSets); // this will be used in algorithm to remove and things
	    		//{0=[1, 6], 1=[2, 4, 7, 6], 2=[3], 3=[4, 6, 2], 4=[1, 4, 6, 2], 5=[2, 4, 7, 3, 6]}
	 			//{0=[1, 6], 1=[2, 4, 7, 6], 2=[5], 3=[8]}
	 
	 			while(tmpConceptG.size()!=0 && eligibleCharactersticSets.size()!=0) // while loop starts as per the algo in the paper
	 			{
	 				
	 				LinkedHashMap<Integer, Integer> lhm_K_and_X_Intersect=new LinkedHashMap<Integer, Integer>();
	 				// here X is the main concept entryTempConcept_K.getKey()
	 				
	 				for(Map.Entry<Integer, LinkedHashSet<Integer>> tmpIntersect: eligibleCharactersticSets.entrySet())
	 				{
	 					LinkedHashSet<Integer> intersection = new LinkedHashSet<Integer>(tmpIntersect.getValue());
	 					intersection.retainAll(entryTempConcept_K.getKey());
	 					
	 					lhm_K_and_X_Intersect.put(tmpIntersect.getKey(), intersection.size());
	 				}
	 				
	 				//system.out.println("size of intersection of K(x) and original concept-->"+lhm_K_and_X_Intersect);
	 				// so after intersection 3 cases can be there-->
	 				// 1. select K(x) for which  |K(x) ^ X| is maximum
	 				// 2. if a tie occurs select K(x) which has smallest cardinality
	 				// 3. if again tie occurs then select the first K(x)
	 				
	 				
	 				Integer max = lhm_K_and_X_Intersect.entrySet()
		            .stream()
		            .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
		            .get()
		            .getValue();
			
			List listOfMax = lhm_K_and_X_Intersect.entrySet()
		            .stream()
		            .filter(entry -> entry.getValue() == max)
		            .map(Map.Entry::getKey)
		            .collect(Collectors.toList());
			
			//system.out.println("maximum-->"+listOfMax); // returns the key of the maximum one
			
			if(listOfMax.size()==1) //1st case i.e. after intersection only 1 set has the maximum number of the elements and that attribute will be selected for rule induction
			{
				tmpGlobalProbabApproxT.addAll(eligibleCharactersticSets.get(listOfMax.get(0)));  // T= T union K(x)
				tmpConceptG.removeAll(tmpGlobalProbabApproxT); // G=G-T;
				eligibleCharactersticSets.remove(listOfMax.get(0));// Y= Y-K(x)
			}
				else 
	// 2nd case i.e. two or more intersected sets have max and equal number of intersection so now need to see the minimum cardinaltiy., of these two
			{
				 
				
				LinkedHashMap<Integer, Integer> lhmSameCardinalityCheck=new LinkedHashMap<Integer, Integer>();
				
				for( int i=0;i<listOfMax.size();i++)
				{
					lhmSameCardinalityCheck.put((Integer) listOfMax.get(i), eligibleCharactersticSets.get(listOfMax.get(i)).size());		
					
				}
				
				//system.out.println("case when same cardinality is there--> "+lhmSameCardinalityCheck);
				
				Integer minCardinality = lhmSameCardinalityCheck.entrySet()
	    	            .stream()
	    	            .max((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? 1 : -1)
	    	            .get()
	    	            .getValue();
	    		
	    		List minCardinalityList = lhmSameCardinalityCheck.entrySet()
	    	            .stream()
	    	            .filter(entry -> entry.getValue() == minCardinality)
	    	            .map(Map.Entry::getKey)
	    	            .collect(Collectors.toList());
	    		
	    		//system.out.println("\n out of the confilcting maximum intersection, one that has minimum cardinality is--->\n"+minCardinalityList);
	    		
	    		if(minCardinalityList.size()>0) // i.e., case 2 and 3, if case 2 so only (0) will be there if case 3, herustic we have to select the first one
	    		{
	    		tmpGlobalProbabApproxT.addAll(eligibleCharactersticSets.get(minCardinalityList.get(0)));  // T= T union K(x)
				tmpConceptG.removeAll(tmpGlobalProbabApproxT); // G=G-T;
				eligibleCharactersticSets.remove(minCardinalityList.get(0));// Y= Y-K(x)
	    		}
	    		
			}
			
				
	 			}
	 
	 	////system.out.println("for -->"+entryTempConcept_K.getKey()+"-->GLOBAL PROBABLISTIC APPRX IS-->"+tmpGlobalProbabApproxT);
	 	setGlobalProbablisticApprox.add(tmpGlobalProbabApproxT);
	 
	    	}
	    	
	    	//system.out.println("GLOBAL PROBABLISTIC APPROXIMATION ------>"+setGlobalProbablisticApprox);
	    	
	    	
	    	//system.out.println("\n ************************************ENDing GLOBAL PROBABLISTIC APPROXIMATION ALGORITHM************************************");
	    	
	    	return setGlobalProbablisticApprox;
	    }
	    
	    public static LinkedHashSet<TreeSet<Integer>> methodToFindSaturaedProbablisticApproximation(Set<Set<Integer>> setDStar, double alpha2,
	    		LinkedHashMap<Integer, LinkedHashSet<Integer>> lhmCharactersticSet_K)
	    {
	    	
	    	//system.out.println("\n\nINSIDE methodToFindSaturaedProbablisticApproximation---------------------->>>>");
	    	
	    	
	    	//system.out.println("lhmCharactersticSet_K-->"+lhmCharactersticSet_K);
	    	//system.out.println("setDStar-->"+setDStar);
	    	

	   
LinkedHashMap<Set<Integer>, TreeMap<Double, Set<LinkedHashSet<Integer>>>> lhmConcept_Probablity_charachertset=
new LinkedHashMap<Set<Integer>, TreeMap<Double, Set<LinkedHashSet<Integer>>>>();

LinkedHashMap<Set<Integer>, TreeMap<Integer, Set<LinkedHashSet<Integer>>>> lhmConcept_J_charachertset=
new LinkedHashMap<Set<Integer>, TreeMap<Integer, Set<LinkedHashSet<Integer>>>>();	
//key --> concept
// value is again a linked hashmap so key stores--> probablity and value stored the corresponding sets (Set<LinkedHashSet<Integer>>
																										//since multiple sets can hav same probablity
	    	
	    	
	    	
	    	for (Set<Integer> tempConcept:setDStar )
	    	{
	    		int counter=1;
	    		Set<LinkedHashSet<Integer>> setOfSetTemp=new LinkedHashSet<LinkedHashSet<Integer>>();
	    		TreeMap<Double, Set<LinkedHashSet<Integer>>> lhmProbabCharacterSet_K_Temp=new TreeMap<Double, Set<LinkedHashSet<Integer>>>(Collections.reverseOrder());
	    		TreeMap<Integer, Set<LinkedHashSet<Integer>>> lhm_J_CharacterSet_K_Temp=new TreeMap<Integer, Set<LinkedHashSet<Integer>>>();
	    		//system.out.println("\nFor concept--->"+tempConcept);
	    		for(Entry<Integer, LinkedHashSet<Integer>> tmpK:lhmCharactersticSet_K.entrySet() )
	    		{
	    			
	    			LinkedHashSet<Integer> tmpSet= new LinkedHashSet<Integer>(tmpK.getValue());
	    			tmpSet.retainAll(tempConcept);
	    			
	    			double probablity=((double)tmpSet.size()/(double)tmpK.getValue().size());
	    			
	    			////system.out.println((double)Math.round(probablity * 1000d) / 1000d);	    			
	    			double probablityRoundOff=(double)Math.round(probablity * 1000d) / 1000d;
	    			//system.out.println("Probablities are--->"+probablityRoundOff);
	    			
	    			if(probablityRoundOff!=0.0 && probablityRoundOff>=alpha)
				{
					if (lhmProbabCharacterSet_K_Temp.containsKey(probablityRoundOff)) {
						lhmProbabCharacterSet_K_Temp.get(probablityRoundOff).add(tmpK.getValue());
					} else {
						Set<LinkedHashSet<Integer>> newtmp = new LinkedHashSet<LinkedHashSet<Integer>>();
						newtmp.add(tmpK.getValue());
						lhmProbabCharacterSet_K_Temp.put(probablityRoundOff, newtmp);
					}
				}
	    		}
	    		
	    		lhmConcept_Probablity_charachertset.put(tempConcept, lhmProbabCharacterSet_K_Temp);
	    		
	    		
	    		for(Map.Entry<Double, Set<LinkedHashSet<Integer>>> tmpEntry:lhmProbabCharacterSet_K_Temp.entrySet() )
	    		{
	    			lhm_J_CharacterSet_K_Temp.put(counter, tmpEntry.getValue());
	    			counter++;
	    		}
	    		lhmConcept_J_charachertset.put(tempConcept, lhm_J_CharacterSet_K_Temp);
	    		
	    	}
	    	
	    	//system.out.println("lhmConcept_Probablity_charachertset-->\n"+lhmConcept_Probablity_charachertset);
	    	//system.out.println("lhmConcept_J_charachertset         -->\n"+lhmConcept_J_charachertset);
//{[1, 2, 3, 4]={1=[[3]], 2=[[1, 4, 6, 2]], 3=[[4, 6, 2]], 4=[[2, 4, 7, 3, 6]], 5=[[1, 6], [2, 4, 7, 6]]}, [5, 6, 7, 8]={1=[[5], [8]], 2=[[1, 6], [2, 4, 7, 6]], 3=[[2, 4, 7, 3, 6]], 4=[[4, 6, 2]], 5=[[1, 4, 6, 2]]}}	    	
/// ordered
//{[1, 2, 3, 4]={1.0=[[3]], 0.75=[[1, 4, 6, 2]], 0.667=[[4, 6, 2]], 0.6=[[2, 4, 7, 3, 6]], 0.5=[[1, 6], [2, 4, 7, 6]]}, [5, 6, 7, 8]={1.0=[[5], [8]], 0.5=[[1, 6], [2, 4, 7, 6]], 0.4=[[2, 4, 7, 3, 6]], 0.333=[[4, 6, 2]], 0.25=[[1, 4, 6, 2]]}}
	    	
	    	
	    	//algo start
	    	
	    	LinkedHashSet<TreeSet<Integer>> setSaturatedProbablisticApprox= new LinkedHashSet<TreeSet<Integer>>();
	    	
	    	LinkedHashMap<Set<Integer>, TreeMap<Integer, Set<LinkedHashSet<Integer>>>> lhmConcept_J_charachertset_Y= new 
	    			LinkedHashMap<Set<Integer>, TreeMap<Integer, Set<LinkedHashSet<Integer>>>>(lhmConcept_J_charachertset);
	    	
	    			for(Map.Entry<Set<Integer>, TreeMap<Integer, Set<LinkedHashSet<Integer>>>> tmpMain: lhmConcept_J_charachertset_Y.entrySet())	 
	    			{
	    				
	    					//system.out.println("\n\n finding saturated probablistic approx for---> "+tmpMain.getKey());
	    					TreeSet<Integer> tmpSaturatedProbabApproxT=new TreeSet<Integer>();   // T
	    				
	    					exitTheLoops:	for(int i=1;i<=tmpMain.getValue().size();i++)
	    					{
	    						//system.out.println("tmpMain.getValue().get(i)-->"+tmpMain.getValue().get(i));
	    						int numberOfSetsOfSet=1;
	    						
	    						TreeMap<Integer, Set<LinkedHashSet<Integer>>> tmpWhileCHecker=new 
	    								TreeMap<Integer, Set<LinkedHashSet<Integer>>>();
	    						
	    						tmpWhileCHecker.putAll(tmpMain.getValue());
	    						
	    						while(!tmpWhileCHecker.get(i).isEmpty()) { // while Yj(x) not empty;
	    							//system.out.println("WHILE ONEE->tmpMain.getValue().get(i)-->"+tmpMain.getValue().get(i));
	    							LinkedHashMap<Integer, Integer> lhm_K_and_X_Intersect=new LinkedHashMap<Integer, Integer>();	
	    							//LinkedHashSet<	LinkedHashSet<Integer>> selected_Kx=new LinkedHashSet< LinkedHashSet<Integer>>();
	    							LinkedHashSet<Integer> selected_Kx1=null;
	    			 				// so after intersection 3 cases can be there-->
	    			 				// 1. select K(x) for which  |K(x) ^ X| is maximum
	    			 				// 2. if a tie occurs select K(x) which has smallest cardinality
	    			 				// 3. if again tie occurs then select the first K(x)
   							
	    							for(LinkedHashSet<Integer> tmp2: tmpMain.getValue().get(i))
	    							{
	    								
	    								LinkedHashSet<Integer> tmpForIntersect=new LinkedHashSet<Integer>(tmp2);
	    								
	    								tmpForIntersect.retainAll(tmpMain.getKey());
	    								
	    								lhm_K_and_X_Intersect.put(numberOfSetsOfSet, tmpForIntersect.size());
	    								numberOfSetsOfSet++;
	    							}
	    							//system.out.println("size of intersection of K(x) and original concept-->"+lhm_K_and_X_Intersect);
	    							
	    							
					Integer max = lhm_K_and_X_Intersect.entrySet().stream()
							.max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();

					List listOfMax = lhm_K_and_X_Intersect.entrySet().stream().filter(entry -> entry.getValue() == max)
							.map(Map.Entry::getKey).collect(Collectors.toList());

					//system.out.println("maximum-->" + listOfMax); // returns the key of the maximum one

					if(!listOfMax.isEmpty())
					{
						//system.out.println("the first one to remove is-->" + listOfMax.get(0));
						//system.out.println(tmpWhileCHecker.get(i));
						int setCounterToBeRemoved = 1;
						for (LinkedHashSet<Integer> tmp2 : tmpWhileCHecker.get(i)) {

							if (setCounterToBeRemoved == Integer.valueOf(listOfMax.get(0).toString())) {
								//selected_Kx.add(tmp2);
								 selected_Kx1=new  LinkedHashSet<Integer>(tmp2);
								tmpWhileCHecker.get(i).remove(tmp2); // Yj(x)=Yj(x) - K(x)
								numberOfSetsOfSet = 1;
								break;
							}
							setCounterToBeRemoved++;
						}
					}
					 LinkedHashSet<Integer>	selected_Kx_If=new  LinkedHashSet<Integer>(selected_Kx1);
					 selected_Kx_If.removeAll(tmpSaturatedProbabApproxT);
					 selected_Kx_If.retainAll(tmpMain.getKey());
					if(!selected_Kx_If.isEmpty() )                   // if (K(x) - T) intersection X non empty
	    							
					{
						tmpSaturatedProbabApproxT.addAll(selected_Kx1);
					}
					
					
					Boolean boolAllConceptCoveredSoExist= isSubset(tmpSaturatedProbabApproxT.stream().mapToInt(Integer::intValue).toArray(),
							tmpMain.getKey().stream().mapToInt(Integer::intValue).toArray(),
							tmpSaturatedProbabApproxT.size(),tmpMain.getKey().size());
					
					if(boolAllConceptCoveredSoExist)      //X C=T then exit
					{
						break exitTheLoops;
					}
	    							
	    						//end of while
					}	
	    					}    					
	    					
	    					setSaturatedProbablisticApprox.add(tmpSaturatedProbabApproxT);
	    			}	    	
	    	
	    	//system.out.println("setSaturatedProbablisticApprox----------->"+setSaturatedProbablisticApprox);
	    	
			return setSaturatedProbablisticApprox;
	    	
	    }

}
