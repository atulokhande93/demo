package selenium.common.utilities;

import org.testng.ITestNGListener;
import org.testng.asserts.IAssert;



public interface AssertionListner extends ITestNGListener {

	  /**
	   * Run the assert command in parameter.
	   */
	  void executeAssert(IAssert<?> assertCommand);

	  /**
	   * Invoked when an assert succeeds.
	   */
	  void onAssertSuccess(IAssert<?> assertCommand);

	  /**
	   * Invoked when an assert fails.
	   * 
	   * @deprecated use onAssertFailure(IAssert assertCommand, AssertionError ex) instead of.
	   */
	  void onAssertFailure(IAssert<?> assertCommand);
	  
	  /**
	   * Invoked when an assert fails.
	   * 
	   */
	  void onAssertFailure(IAssert<?> assertCommand, AssertionError ex);

	  /**
	   * Invoked before an assert is run.
	   */
	  void onBeforeAssert(IAssert<?> assertCommand);

	  /**
	   * Invoked after an assert is run.
	   */
	  void onAfterAssert(IAssert<?> assertCommand);

}
