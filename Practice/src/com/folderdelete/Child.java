package com.folderdelete;


	class Child extends DeleteFiles
	{
		public static void parentMethod()
		{
			System.out.println("in child static");
		}
		
		@Override
		public String parentMethod2()
		{
			System.out.println("in child string");
			return null;
			
		}
	}

