package ruleChecker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 * @author PUSHKAR SINGH NEGI
 * KU ID: 2946319
 * Programming Project: Classification System : EECS 839 : SPRING 2018
 * Start Date:
 * Modified Date:
 */
public class conceptRuleCasesMapping {

	private int intCaseNumber=0;
	
	private LinkedHashMap<Integer, Boolean> lhmRuleTrueOrFalseMap=new LinkedHashMap<Integer, Boolean>();
	/*1. to store that for each case, every possiblity of rule whether it matches or not matches, if matches then ruleNumber:true else ruleNumber:false in hashmap*/
	
	private LinkedHashMap<Integer, Boolean> lhmRuleTrueOrFalsePartialMatchingMap=new LinkedHashMap<Integer, Boolean>();
	/*1 is for complete matching and this map is partial matching so 
	
	Rule number and matching mapping is---{1=[false], 2=[false], 3=[false], 4=[true, false]}
	1 will store Rule number and matching FINAL CONVERTED TO ONE mapping is (COMPLETE MATCHING)---{1=false, 2=false, 3=false, 4=false}
	and this will store {1=false, 2=false, 3=false, 4=true} for 4 it is true because of partial matching so whenever, 
	lhmRuleMatching this map contains any 1 true for 
	
	 *Rule number and matching mapping is---{1=[false], 2=[false], 3=[false], 4=[true, false]}
	 *of boolean array contains 1 false and 1 true, then its a case for partial matching 
	 *
	 */
	
	
	private LinkedHashMap<Integer, Boolean> lhmRuleAndCaseDecisionTrueOrFalseMap=new LinkedHashMap<Integer, Boolean>();
	//for each case, for every rule, it will store whether the decision value of the case matches with the decision value of rule(RHS)
	
	private LinkedHashMap<Integer, Boolean> lhmForLHSTrueRHSMapping=new LinkedHashMap<Integer, Boolean>();
	private LinkedHashMap<Integer, Boolean> lhmForLHSTrueRHSMappingPartialMatch=new LinkedHashMap<Integer, Boolean>();
	/*lhmForLHSTrueRHSMapping, lhmForLHSTrueRHSMappingPartialMatch
	 * 
	 * rule and corresponding decision value true or false---{1=true}  --- if this LHM contains all true then--- CORECCTLY CLASSIFIED
	rule and corresponding decision value true or false---{1=true, 2=true}--if this LHM contains all true then--- CORECCTLY CLASSIFIED
	rule and corresponding decision value true or false---{1=false, 3=true}// If this LHM contains mixture of true and false then use strength or Cp
	
	rule and corresponding decision value true or false---{1=false}-- if this LHm contains all false then incoreectly classified
*/	
	
	private LinkedHashMap<Integer, ArrayList<Boolean>> lhmRuleMatching=new LinkedHashMap<Integer, ArrayList<Boolean>>();

/*to store information of type example---
	
	For case---1---and RUle Number--1---boolean flag list is--[false]
			For case---1---and RUle Number--2---boolean flag list is--[false]
			For case---1---and RUle Number--3---boolean flag list is--[false]
			For case---1---and RUle Number--4---boolean flag list is--[true, false]
					
			For case---2---and RUle Number--1---boolean flag list is--[true]
			For case---2---and RUle Number--2---boolean flag list is--[false]
			For case---2---and RUle Number--3---boolean flag list is--[false]
			For case---2---and RUle Number--4---boolean flag list is--[false, true] and so on....	
			
			* the above foramt will convert to 
			*
			*For case Number---1
Rule number and matching mapping is---{1=[false], 2=[false], 3=[false], 4=[true, false]}
For case Number---2
Rule number and matching mapping is---{1=[true], 2=[false], 3=[false], 4=[false, true]}
For case Number---3
Rule number and matching mapping is---{1=[true], 2=[true], 3=[false], 4=[false, false]}
For case Number---4
Rule number and matching mapping is---{1=[false], 2=[false], 3=[true], 4=[true, false]}
For case Number---5
Rule number and matching mapping is---{1=[true], 2=[false], 3=[true], 4=[false, false]}
For case Number---6
Rule number and matching mapping is---{1=[true], 2=[false], 3=[false], 4=[false, false]}
For case Number---7
Rule number and matching mapping is---{1=[true], 2=[false], 3=[true], 4=[false, true]}
For case Number---8
Rule number and matching mapping is---{1=[false], 2=[false], 3=[false], 4=[true, true]}
			*
			*
			*
			*/
	
	
	public int getIntCaseNumber() {
		return intCaseNumber;
	}

	public void setIntCaseNumber(int intCaseNumber) {
		this.intCaseNumber = intCaseNumber;
	}

	public LinkedHashMap<Integer, ArrayList<Boolean>> getLhmRuleMatching() {
		return lhmRuleMatching;
	}

	public void setLhmRuleMatching(LinkedHashMap<Integer, ArrayList<Boolean>> lhmRuleMatching) {
		this.lhmRuleMatching = lhmRuleMatching;
	}

	public LinkedHashMap<Integer, Boolean> getLhmRuleTrueOrFalseMap() {
		return lhmRuleTrueOrFalseMap;
	}

	public void setLhmRuleTrueOrFalseMap(LinkedHashMap<Integer, Boolean> lhmRuleTrueOrFalseMap) {
		this.lhmRuleTrueOrFalseMap = lhmRuleTrueOrFalseMap;
	}

	public LinkedHashMap<Integer, Boolean> getLhmRuleAndCaseDecisionTrueOrFalseMap() {
		return lhmRuleAndCaseDecisionTrueOrFalseMap;
	}

	public void setLhmRuleAndCaseDecisionTrueOrFalseMap(
			LinkedHashMap<Integer, Boolean> lhmRuleAndCaseDecisionTrueOrFalseMap) {
		this.lhmRuleAndCaseDecisionTrueOrFalseMap = lhmRuleAndCaseDecisionTrueOrFalseMap;
	}

	public LinkedHashMap<Integer, Boolean> getLhmForLHSTrueRHSMapping() {
		return lhmForLHSTrueRHSMapping;
	}

	public void setLhmForLHSTrueRHSMapping(LinkedHashMap<Integer, Boolean> lhmForLHSTrueRHSMapping) {
		this.lhmForLHSTrueRHSMapping = lhmForLHSTrueRHSMapping;
	}

	public LinkedHashMap<Integer, Boolean> getLhmRuleTrueOrFalsePartialMatchingMap() {
		return lhmRuleTrueOrFalsePartialMatchingMap;
	}

	public void setLhmRuleTrueOrFalsePartialMatchingMap(
			LinkedHashMap<Integer, Boolean> lhmRuleTrueOrFalsePartialMatchingMap) {
		this.lhmRuleTrueOrFalsePartialMatchingMap = lhmRuleTrueOrFalsePartialMatchingMap;
	}

	public LinkedHashMap<Integer, Boolean> getLhmForLHSTrueRHSMappingPartialMatch() {
		return lhmForLHSTrueRHSMappingPartialMatch;
	}

	public void setLhmForLHSTrueRHSMappingPartialMatch(
			LinkedHashMap<Integer, Boolean> lhmForLHSTrueRHSMappingPartialMatch) {
		this.lhmForLHSTrueRHSMappingPartialMatch = lhmForLHSTrueRHSMappingPartialMatch;
	}
	
	
}
