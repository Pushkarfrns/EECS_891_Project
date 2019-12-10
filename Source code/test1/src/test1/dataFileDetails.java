package test1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
/**
 * @author PUSHKAR SINGH NEGI
 * KU ID: 2946319
 * Programming Project: Classification System : EECS 839 : SPRING 2018
 * Start Date:
 * Modified Date:
 */
public class dataFileDetails {
	
	private LinkedHashMap<String,String> lhmCases=new LinkedHashMap<String,String>(); //
	// to store each case in a LinkedHashMap  (entire row, attribute value pair along with decision value will be stored here )
	
	private LinkedHashMap<String,String> lhmCaseDecisionValue=new LinkedHashMap<String,String>();
	
	private String strDecisionAttrName="";
	
	private int intCaseNumber=0;
	
	
	public LinkedHashMap<String, String> getLhmCases() {
		return lhmCases;
	}

	public void setLhmCases(LinkedHashMap<String, String> lhmCases) {
		this.lhmCases = lhmCases;
	}

	public int getIntCaseNumber() {
		return intCaseNumber;
	}

	public void setIntCaseNumber(int intCaseNumber) {
		this.intCaseNumber = intCaseNumber;
	}

	public LinkedHashMap<String, String> getLhmCaseDecisionValue() {
		return lhmCaseDecisionValue;
	}

	public void setLhmCaseDecisionValue(LinkedHashMap<String, String> lhmCaseDecisionValue) {
		this.lhmCaseDecisionValue = lhmCaseDecisionValue;
	}

	public String getStrDecisionAttrName() {
		return strDecisionAttrName;
	}

	public void setStrDecisionAttrName(String strDecisionAttrName) {
		this.strDecisionAttrName = strDecisionAttrName;
	}
		

}
