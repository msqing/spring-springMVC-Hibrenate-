package com.techusn.tccms;

public class Test {

	public static void main(String[] args) {
		String a = "ab";
        String b = new String ("ab");
        String c = "a" + "b";
        String d = "b";
        String e = "a" + d; 

        System.out.println(a==b); 
        System.out.println(a==c); 
        System.out.println(a==e); 
        
        FatherClass fc = new FatherClass();
        ChildClass cc = new ChildClass();

	}

	
}


