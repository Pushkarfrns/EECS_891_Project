package ruleChecker;

import java.util.ArrayList;
/**
 * @author PUSHKAR SINGH NEGI
 * KU ID: 2946319
 * Programming Project: Classification System : EECS 839 : SPRING 2018
 * Start Date:
 * Modified Date:
 */
public class thirdOutputDecisionMapping {

	private String strDecisionName="";
	private String strDecisionValue="";
	
	private ArrayList<Integer> listPartialIncorrectlyClassDecisionMap=new ArrayList<Integer>();
	private ArrayList<Integer> listPartialCorrectlyClassDecisionMap=new ArrayList<Integer>();
	private ArrayList<Integer> listCompleteIncorrectlyClassDecisionMap=new ArrayList<Integer>();
	private ArrayList<Integer> listCompleteCorrectlyClassDecisionMap=new ArrayList<Integer>();
	public String getStrDecisionName() {
		return strDecisionName;
	}
	public void setStrDecisionName(String strDecisionName) {
		this.strDecisionName = strDecisionName;
	}
	public String getStrDecisionValue() {
		return strDecisionValue;
	}
	public void setStrDecisionValue(String strDecisionValue) {
		this.strDecisionValue = strDecisionValue;
	}
	public ArrayList<Integer> getListPartialIncorrectlyClassDecisionMap() {
		return listPartialIncorrectlyClassDecisionMap;
	}
	public void setListPartialIncorrectlyClassDecisionMap(ArrayList<Integer> listPartialIncorrectlyClassDecisionMap) {
		this.listPartialIncorrectlyClassDecisionMap = listPartialIncorrectlyClassDecisionMap;
	}
	public ArrayList<Integer> getListPartialCorrectlyClassDecisionMap() {
		return listPartialCorrectlyClassDecisionMap;
	}
	public void setListPartialCorrectlyClassDecisionMap(ArrayList<Integer> listPartialCorrectlyClassDecisionMap) {
		this.listPartialCorrectlyClassDecisionMap = listPartialCorrectlyClassDecisionMap;
	}
	public ArrayList<Integer> getListCompleteIncorrectlyClassDecisionMap() {
		return listCompleteIncorrectlyClassDecisionMap;
	}
	public void setListCompleteIncorrectlyClassDecisionMap(ArrayList<Integer> listCompleteIncorrectlyClassDecisionMap) {
		this.listCompleteIncorrectlyClassDecisionMap = listCompleteIncorrectlyClassDecisionMap;
	}
	public ArrayList<Integer> getListCompleteCorrectlyClassDecisionMap() {
		return listCompleteCorrectlyClassDecisionMap;
	}
	public void setListCompleteCorrectlyClassDecisionMap(ArrayList<Integer> listCompleteCorrectlyClassDecisionMap) {
		this.listCompleteCorrectlyClassDecisionMap = listCompleteCorrectlyClassDecisionMap;
	}
	
	
	
}
