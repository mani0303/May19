package com.test.functional;

import com.test.core.RunnerHelper;
import com.test.core.TestRunner;
import com.test.support.Status;

public class Second extends TestRunner{

	public Second(RunnerHelper helper) {
		super(helper);
	}
	@Test(description= "First Test")
	public void openGooglePage(){
		String testId = data.get("Test_ID");
		runtimeData.setRuntimeData(testId, "Data 1", "Test 1");
		runtimeData.setRuntimeData(testId, "Data 2", "Test 2");
		try{
			log.log("This is from first Class", Status.INFO);			
		}catch(Exception e){
			log.log("This is from first Class "+e.getMessage(),Status.FAIL);
		}
	}
	
	
	@Test(description= "Second Test")
	public void openGoogle1Page(){
		String testId = data.get("Test_ID");
		String refId = data.get("DependentTestId");
		System.out.println(runtimeData.getRuntimeData(refId,"Data 1"));
		runtimeData.setRuntimeData(testId, "Data 3", "Test3 3");
		System.out.println(runtimeData.getRuntimeData(testId,"Data 3"));
		try{
			log.log("This is from Second Class", Status.INFO);			
		}catch(Exception e){
			log.log("This is from Second Class "+e.getMessage(),Status.FAIL);
		}
	}
}
