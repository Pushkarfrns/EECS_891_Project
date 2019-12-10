package ruleChecker;

import java.util.LinkedHashMap;
/**
 * @author PUSHKAR SINGH NEGI
 * KU ID: 2946319
 * Programming Project: Classification System : EECS 839 : SPRING 2018
 * Start Date:
 * Modified Date:
 */

public class rulesFileDetails {
	
	private int intSpecificity=0;
	private int intStrength=0;
	private int intTotNoMatchTrainCases=0;
	private int intRuleNumber=0;
	
	private LinkedHashMap<String,String> lhmConditions=new LinkedHashMap<String,String>(); // to store the condition and value in Map
	private LinkedHashMap<String,String> lhmDecision=new LinkedHashMap<String,String>();// to store the decision and value in map
	public int getIntSpecificity() {
		return intSpecificity;
	}
	public void setIntSpecificity(int intSpecificity) {
		this.intSpecificity = intSpecificity;
	}
	public int getIntStrength() {
		return intStrength;
	}
	public void setIntStrength(int intStrength) {
		this.intStrength = intStrength;
	}
	public int getIntTotNoMatchTrainCases() {
		return intTotNoMatchTrainCases;
	}
	public void setIntTotNoMatchTrainCases(int intTotNoMatchTrainCases) {
		this.intTotNoMatchTrainCases = intTotNoMatchTrainCases;
	}
	public LinkedHashMap<String, String> getLhmConditions() {
		return lhmConditions;
	}
	public void setLhmConditions(LinkedHashMap<String, String> lhmConditions) {
		this.lhmConditions = lhmConditions;
	}
	public LinkedHashMap<String, String> getLhmDecision() {
		return lhmDecision;
	}
	public void setLhmDecision(LinkedHashMap<String, String> lhmDecision) {
		this.lhmDecision = lhmDecision;
	}
	public int getIntRuleNumber() {
		return intRuleNumber;
	}
	public void setIntRuleNumber(int intRuleNumber) {
		this.intRuleNumber = intRuleNumber;
	}
	
	

}
