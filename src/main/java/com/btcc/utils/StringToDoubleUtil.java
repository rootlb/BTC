package com.btcc.utils;

public class StringToDoubleUtil {
	
	/**
	 * a+b 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double sumString(String a, String b){
		return Double.sum(Double.parseDouble(a), Double.parseDouble(b));
	}
	
	/**
	 * a-b
	 * @param a
	 * @param b
	 * @return
	 */
	public static double minusString(String a, String b){
		return Double.sum(Double.parseDouble(a), -Double.parseDouble(b));
	}
	
	/**
	 * a > b·µ»Øtrue 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compaer(String a, String b){
		if(Double.compare(Double.valueOf(a), Double.valueOf(b)) >0){
			return true;
		}
		return false;
	}
//	public static  void main(String arge[]){
//		System.out.println(sumString("0.02","0.01"));
//	}
}
