package com.skyplus.generic.utils.waitFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * This class contains wrapper implementation for all Methods Which accept any argument other than By or Web Element as
 * an argument from the ExpectedConditions class in Selenium WebDrvierWait.
 */
public class WaitFactoryGeneric
{
   /**
    * This is a WebDrvier Wait Instance Variable.
    */
   private final FluentWait<WebDriver> wait;
   /**
    * Logger Instance Variable to capture the information in the log.
    */
   private final Logger log = LogManager.getLogger(WaitFactoryGeneric.class);
   /**
    * This is a WaitFactoryGeneric Instance Variable.
    */
   private final WaitFactoryGeneric instance = null;
   Validator validate;
   /**
    * This is a JavaScriptObject of type Object Instance Variable.
    */
   private Object javaScriptObject = null;
   /**
    * This is a Alert Instance Variable.
    */
   private Alert alert;
   /**
    * This is a ExpectedCondition Instance Variable.
    */
   private ExpectedCondition expectedCondition;

   /**
    * This Constructor initializes the WebDriverWait for the WaitFactory Generic Class.
    */
   public WaitFactoryGeneric(WaitFactoryPojo waitFactoryPojo, Validator validate)
   {
      this.wait = waitFactoryPojo.getLongWait();
      this.validate = validate;
   }

   /**
    * This Method wait for the alert is present.
    *
    * @return Alert -> when it is present
    * @throws Exception - caller needs to handle it
    */
   public boolean alertIsPresentInPage() throws Exception
   {
      boolean flag = false;
      try
      {
         alert = wait.until(ExpectedConditions.alertIsPresent());
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for alert on the page");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(alert);
      }
      return flag;
   }

   /**
    * Unimplemented method This methods wait for the given number of Windows.
    *
    * @param numberOfWindows -> Number of browser window
    * @return true -> If matches with the given number
    * @throws Exception - needs to handle it
    */
   public boolean expectedConditionnumberOfWindowsToBe(
            final int numberOfWindows) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.numberOfWindowsToBe(
                  numberOfWindows));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("unable to wait for expected windows " + numberOfWindows);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method wait for the title which contains the specified a case-sensitive value.
    *
    * @param title -> Title of the page
    * @return true -> when the title matches, false otherwise
    * @throws Exception caller to handle the exception
    */
   public boolean titleContains(final String title) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.titleContains(title));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for elements title "
                           + "to contain the given value");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method wait for the title to match to the specified value.
    *
    * @param title -> Title of the page
    * @return true -> when the title matches, false otherwise
    * @throws Exception caller to handle the exception
    */
   public boolean titleIs(final String title) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.titleIs(title));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for Title to be matched");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method wait for URL which contains the specific text.
    *
    * @param url -> URL String
    * @return true -> when the URL contains the text
    * @throws Exception - Exception which call needs to handle
    */
   public boolean urlContains(final String url) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.urlContains(url));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for URL which contains the " + url);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method wait for the URL to match a specific regular expression.
    *
    * @param urlRegex ->Regular expression String
    * @return true -> if the URL matches the specified regular expression
    * @throws Exception - Exception which call needs to handle
    */
   public boolean urlMatches(final String urlRegex) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.urlMatches(urlRegex));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for URL which contains the " + urlRegex);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method waits for given frame is available to switch to. If the frame is available it switches the given
    * driver to the specified frameIndex.
    *
    * @param locator ->locator String
    * @return true ->instance after frame has been switched
    * @throws Exception - Caller need to handle it
    */
   public boolean frameToBeAvailableAndSwitchToItbyFrameId(final int locator)
            throws Exception
   {
      WebDriver driverElement = null;
      boolean flag = false;
      try
      {
         driverElement = wait.until(ExpectedConditions
                  .frameToBeAvailableAndSwitchToIt(locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the frame " + locator);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(driverElement);
      }
      return flag;
   }

   /**
    * This method waits for the given frame is available to switch to. If the frame is available it switches the given
    * driver to the specified frame.
    *
    * @param locator ->locator String
    * @return true -> instance after frame has been switched
    * @throws Exception - Caller needs to handle it
    */
   public boolean frameToBeAvailableAndSwitchToItbyFrameName(
            final String locator) throws Exception
   {
      WebDriver driverElement = null;
      boolean flag = false;
      try
      {
         driverElement = wait.until(ExpectedConditions
                  .frameToBeAvailableAndSwitchToIt(locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the frame " + locator);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(driverElement);
      }
      return flag;
   }

   /**
    * This method wait for the URL of the current page to be a specific url.
    *
    * @param urlToBe ->URL String
    * @return true -> when the URL is what it should be
    * @throws Exception - Caller needs to handle
    */
   public boolean urlToBe(final String urlToBe) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.urlMatches(urlToBe));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the URL " + urlToBe);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * An expectation to check if js executable. Useful when you know that there should be a Javascript value or
    * something at the stage.
    *
    * @param javaScript ->JavaScript string
    * @return true -> once javaScript executed without errors
    * @throws Exception - Caller needs to handle
    */
   public boolean javaScriptThrowsNoExceptions(final String javaScript)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(
                  javaScript));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for the JS where it throws no error"
                  + javaScript);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This methods wait for the An expectation for String value from javascript.
    *
    * @param javaScript ->JavaScript string
    * @return true -> once js return string
    * @throws Exception - Caller needs to handle
    */
   public boolean jsReturnsValue(final String javaScript) throws Exception
   {
      try
      {
         javaScriptObject = wait.until(ExpectedConditions.jsReturnsValue(
                  javaScript));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("unable to wait for the JS Error " + javaScript);
         throw new Exception(e);
      }
      return validate.nullValidator(javaScriptObject);
   }

   /**
    * This method wait for an expectation with the logical and condition of the given list of conditions. Each condition
    * is checked until all of them return true or not null
    *
    * @param conditions ->List of Expected conditions
    * @return true -> once all conditions are satisfied
    * @throws Exception - Caller needs to handle
    */
   public boolean andBasedWaitUntilAllConditionsAreSatisfied(
            final ExpectedCondition... conditions) throws Exception
   {
      Boolean condition = false;
      try
      {
         condition = wait.until(ExpectedConditions.and(conditions));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("unable to wait for the conditions" + conditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(condition);
      }
      return condition;
   }

   /**
    * This method wait for an expectation with the logical or condition of the given list of conditions. Each condition
    * is checked until at least one of them returns true or not null.
    *
    * @param conditions ->List of Expected conditions
    * @return true -> once one of conditions is satisfied
    * @throws Exception - Caller needs to handle
    */
   public boolean or(final ExpectedCondition... conditions) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.or(conditions));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for the expectation" + conditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method wait for an expectation with the logical opposite condition of the given condition. Note that if the
    * Condition you are inverting throws an exception that is caught by the Ignored Exceptions, the inversion will not
    * take place and lead to confusing results.
    *
    * @param conditions ->List of Expected conditions
    * @return true -> once the condition is satisfied
    * @throws Exception - Caller needs to handle
    */
   public boolean not(final ExpectedCondition<?> conditions) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(ExpectedConditions.not(conditions));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for the expectation" + conditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This is Wrapper for a condition, which allows for elements to update by redrawing. This works around the problem
    * of conditions which have two parts: find an element and then check for some condition on it. For these conditions
    * it is possible that an element is located and then subsequently it is redrawn on the client. When this happens a
    * StaleElementReferenceException is thrown when the second part of the condition is checked.
    *
    * @param conditions ->List of expected conditions
    * @return result of the provided condition
    * @throws Exception - caller needs to handle it
    */
   public ExpectedCondition<?> refreshed(final ExpectedCondition<?> conditions)
            throws Exception
   {
      try
      {
         expectedCondition = (ExpectedCondition) wait.until(
                  ExpectedConditions.refreshed(conditions));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for given condition" + conditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      return expectedCondition;
   }

   /**
    * This methods wait for Js Script execution and returns the value.
    *
    * @return true -> once js return string
    * @throws Exception - Caller needs to handle
    */
   public boolean waitForPageLoadUsingJSExecution()
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = wait.until(
                  webDriver -> ((JavascriptExecutor) webDriver).executeScript(
                           "return document.readyState").equals("complete"));
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for the expectation"
                  + "to be satisfied");
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }
}
