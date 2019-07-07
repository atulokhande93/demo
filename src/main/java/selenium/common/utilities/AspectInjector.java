package selenium.common.utilities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.testng.Reporter;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

@Aspect
public class AspectInjector 
{
	
	@Pointcut("call(* selenium.common.utilities.DemoClass.add())")
	public void callmethod()
	{
		
	}
	
	@After("callmethod()")
	public void assertMethod(JoinPoint jp)
	{
		System.out.println(jp.toLongString());
		System.out.println("calling method asser");
	}

	@Pointcut("call(static * org.testng.Assert.assert*(..))")
	public void assertThat(){
	}
	
	@After("assertThat()")
	public void reportAssert(JoinPoint jp)
	{
		MethodSignature signature = (MethodSignature) jp.getSignature();
		String SignatureName = signature.getName();
		System.out.println("Signature Name : "+signature.getName());
		Object args[] = jp.getArgs();
		int i=0;
		
		Map<Integer,String> parameterMap = new HashMap<Integer, String>();
		
		for(String parameterName : signature.getParameterNames())
		{
			String currntArgs = (args[i] != null)?args[i].toString():"NULL";
			System.out.println("CurrentArgs : "+currntArgs);
			parameterMap.put(i, currntArgs);
			i++;
		}
		
		Reporter.log(SignatureName+" : "+parameterMap.get(0)+" : "+parameterMap.get(1));
	}
	
}
