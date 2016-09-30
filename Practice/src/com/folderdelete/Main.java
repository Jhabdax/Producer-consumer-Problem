package com.folderdelete;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;




public class Main {
	public static void main(String[] args) {
		String s = "abcdefbacde";

		 System.out.println(" Please enter the input string :" );
	        char c=firstNonRepeatedCharacter(s.trim());
	        System.out.println("The first non repeated character is :  " + c);
	}

	private static char firstNonRepeatedCharacter(String s) {

		for(char c : s.toCharArray())
		{
			if(s.indexOf(c) == s.lastIndexOf(c))
			{
				return c;
			}
				
		}
    return 'k' ;
}
	}

