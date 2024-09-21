package com.skyplus.generic.utils.commonFunctions;

import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * This class contains the CommonFunctionGeneric.
 */

public class CommonFunctionGeneric
{

   /**
    * This is a WaitFactory Instance Variable.
    */
   private final WaitFactory waitFactory;
   /**
    * This is a WebDriver instance to handle all selenium functions.
    */
   private final WebDriver commonFunctiondriver;
   /**
    * This is logger instance for the CommonFunctionGeneric class.
    */
   private final Logger log = LogManager.getLogger(CommonFunctionGeneric.class);
   private final Validator validate;

   /**
    * This Constructor initializes the WaitFactory for the CommonFunctionGeneric Class.
    *
    * @param commonFunctionPOJO -> CommonFunctionPOJO object
    */
   public CommonFunctionGeneric(final CommonFunctionPOJO commonFunctionPOJO, Validator validate)
   {
      this.waitFactory = commonFunctionPOJO.getWaitFactory();
      this.commonFunctiondriver = commonFunctionPOJO.getDriver();
      this.validate = validate;
   }

   /**
    * This getURL methods launches the URL specified using the Get Method of selenium.
    *
    * @param uRL -> URL to be launched
    * @return flag -> True if URL is launched successfully, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getURL(final String uRL) throws WaitFactoryUseException,
            Exception
   {
      boolean flag = false;
      try
      {
         waitFactory.waitForPageLoad();
         commonFunctiondriver.get(uRL);
         flag = waitFactory.urlToBe(uRL);
         log.info("Launched the URL" + uRL);
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the URL to be navigated to the actual URL");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This navigate methods launches the URL specified using the navigate to.
    *
    * @param uRL -> URL to be launched
    * @return flag -> True if URL is launched successfully, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean navigateToURL(final String uRL) throws Exception
   {
      boolean flag = false;
      try
      {
         commonFunctiondriver.navigate().to(uRL);
         if (waitFactory.waitForPageLoad())
         {
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for the navigated page");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the URL to be navigated to the actual URL");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This navigate back methods Move back a single "item" in the browser's history.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void navigateBack() throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         commonFunctiondriver.navigate().back();
         if (waitFactory.waitForPageLoad())
         {
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for the navigated page");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the URL to be navigated to the actual URL");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
   }

   /**
    * This navigate forward methods Move a single "item" forward in the browser's history. Does nothing if we are on the
    * latest page viewed.
    *
    * @return flag -> True if navigated back
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean navigateForward() throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         commonFunctiondriver.navigate().forward();
         if (waitFactory.waitForPageLoad())
         {
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for the navigated page");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the URL to be navigated to the actual URL");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This refreshpage methods Refresh the current page.
    *
    * @return flag -> True if navigated back
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean refreshPage() throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         commonFunctiondriver.navigate().refresh();
         if (waitFactory.waitForPageLoad())
         {
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for the navigated page");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the URL to be navigated to the actual URL");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method closes the currently active window.
    *
    * @throws Exception -> Exception caller to handle the exception
    */
   public void closeBrowser() throws Exception
   {
      try
      {
         commonFunctiondriver.close();
      }
      catch (Exception e)
      {
         log.error("Unable to close the window");
         log.error(e.getMessage(), e);
      }
   }

   /**
    * This method Closes all the windows which were opened in that selenium session.
    *
    * @throws Exception -> Exception caller to handle the exception
    */
   public void quitAllWindow() throws Exception
   {
      try
      {
         commonFunctiondriver.quit();
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to Closes all the windows which were "
                           + "opened in that selenium session");
         log.error(e.getMessage(), e);
      }
   }

   /**
    * This method compares the actual and expected text and return boolean flag.
    *
    * @param actualText   -> actual text that is retrived
    * @param expectedText -> Text that is expected by the user
    * @return flag -> true if the text matches else false
    * @throws Exception -> Exception caller to handle the exception
    */
   public boolean compareText(final String actualText,
            final String expectedText) throws Exception
   {
      boolean flag = false;
      try
      {
         log.info("Actual Text =" + actualText + " and the expected text is"
                  + expectedText);
         flag = actualText.equals(expectedText);
      }
      catch (Exception e)
      {
         log.error(
                  "Error while comparing the text ");
         throw new Exception(e.getMessage());
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method get the details of the window opened by selenium.
    *
    * @return windowDetail -> Window ID in string format if it matches the condition else null.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getWindowHandle()
            throws WaitFactoryUseException, Exception
   {
      String windowDetail = null;
      try
      {
         if (waitFactory.expectedNumberOfWindowsToBe(1))
         {
            windowDetail = commonFunctiondriver.getWindowHandle();
            log.info("Retrieved the window " + windowDetail);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Expected number of window does not match");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
      }
      finally
      {
         this.validate.nullValidator(windowDetail);
      }
      return windowDetail;
   }

   /**
    * This method compares the Number of windows opened by selenium with the expected number of Windows.
    *
    * @param expectedNumberOfwindow -> No of open windows
    * @return flag -> true if the expected and actual Number of windows matches else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAllWindowsCountAndCompare(final int expectedNumberOfwindow)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (waitFactory.expectedNumberOfWindowsToBe(
                  expectedNumberOfwindow))
         {
            log.info(
                     "Actual number of windows matches with the "
                              + "expected number of windows");
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Expected number of window does not match");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method returns the ID of all the windows opened by selenium.
    *
    * @return flag -> true if the expected and actual Number of windows matches else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public Set<String> getAllWindows()
            throws WaitFactoryUseException, Exception
   {
      Set<String> getAllWindowID = null;
      try
      {
         getAllWindowID = commonFunctiondriver.getWindowHandles();
         if (getAllWindowID.size() <= 1)
         {
            throw new WaitFactoryUseException(
                     "Number of Windows returned is equal to or less than 1");
         }
         log.info("retrived all the Windows details");
      }
      catch (WaitFactoryUseException e)
      {
         log.error("Unable to get all the window details", e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.nullValidatorForSetOfObject(getAllWindowID);
      }
      return getAllWindowID;
   }

   /**
    * This method switches from current window to the window specified by the user if exists, and returns true if
    * switched.
    *
    * @param windowToSwitch -> WindowId to switch to
    * @return flag -> true if the expected and actual Number of windows matches else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean switchToWindow(final String windowToSwitch)
            throws WaitFactoryUseException, Exception
   {
      Set<String> allWindowID = null;
      boolean flag = false;
      try
      {
         allWindowID = commonFunctiondriver.getWindowHandles();
         for (String windowID : allWindowID)
         {
            if (windowID.equals(windowToSwitch))
            {
               commonFunctiondriver.switchTo().window(windowToSwitch);
               log.info("Switched to the window specified " + windowToSwitch);
            }
         }
         if (commonFunctiondriver
                  .getWindowHandle().equals(windowToSwitch))
         {
            flag = true;
            log.info("Switched to the window Specified");
         }
         else
         {
            throw new WaitFactoryUseException("Unable to switch to window specfied"
                     + "/incorrect windowID provided");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error("Unable Switch to the window specified", e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This closes all the window opened by the selenium.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void closeAllWindow()
            throws WaitFactoryUseException, Exception
   {
      Set<String> allWindowID = null;
      try
      {
         int totalOpenWindow = commonFunctiondriver.getWindowHandles().size();
         if (totalOpenWindow == 1)
         {
            waitFactory.expectedNumberOfWindowsToBe(1);
            commonFunctiondriver.close();
         }
         else if (totalOpenWindow > 1)
         {
            waitFactory.expectedNumberOfWindowsToBe(getAllWindows().size());
            allWindowID = getAllWindows();
            for (String windowID : allWindowID)
            {
               commonFunctiondriver.switchTo().window(windowID).close();
               log.info("Closed all the window opended by Selenium");
            }
         }
         else
         {
            throw new WaitFactoryUseException("Unable to close all window or"
                     + "Unable to find windows");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method switches to the frame specified.
    *
    * @param index -> index to switch to
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void switchToFrame(final int index)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         if (waitFactory.frameToBeAvailableAndSwitchToIt(index))
         {
            log.info("Switched to the frame index " + index + " specified");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to switch to frame specified");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method switches to the frame name or ID specified.
    *
    * @param idOrName -> ID or Name of the frame to switch to
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void switchToFrameUsingIDorName(final String idOrName)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         if (waitFactory.frameToBeAvailableAndSwitchToIt(idOrName))
         {
            log.info("Switched to Frame ID or Name " + idOrName + " Specified");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to switch to frame specified");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method Change focus to the parent context. If the current context is the top level browsing context, the
    * context remains unchanged.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void switchToParentFrame()
            throws WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctiondriver.switchTo().parentFrame();
         log.info("Switch to Parent frame");
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method Selects either the first frame on the page, or the main document when a page contains iframes.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void switchToDefaultContext()
            throws WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctiondriver.switchTo().defaultContent();
         log.info("Switch to default content");
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method returns the current URL.
    *
    * @return currentURL -> Returns the current URL
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getCurrentURL()
            throws WaitFactoryUseException, Exception
   {
      String currentURL = null;
      try
      {
         currentURL = commonFunctiondriver.getCurrentUrl();
         log.info("Current URL is" + currentURL);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(currentURL);
      }
      return currentURL;
   }

   /**
    * This method compares the given URL with the current URL.
    *
    * @param expectedURL -> expected URL to compare with
    * @return flag -> Returns true if expected and current URL is same else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCurrentURLAndCompareWithExpected(final String expectedURL)
            throws WaitFactoryUseException, Exception
   {
      String currentURL = null;
      boolean flag = false;
      try
      {
         currentURL = commonFunctiondriver.getCurrentUrl();
         flag = expectedURL.equalsIgnoreCase(currentURL);
         log.info("Current URL " + currentURL + " is compared with the given URL "
                  + expectedURL);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(currentURL);
      }
      return flag;
   }

   /**
    * This method fetches the title of the current page.
    *
    * @return pageTitle -> Returns title of the page
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getTitleOfThePage()
            throws WaitFactoryUseException, Exception
   {
      String pageTitle = null;
      try
      {
         pageTitle = commonFunctiondriver.getTitle();
         log.info("Title of the Page is " + pageTitle);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(pageTitle);
      }
      return pageTitle;
   }

   /**
    * This acceptAlert method is used to accept the alert box which displays on the application.
    *
    * @return flag -> True if successfully, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean acceptAlert() throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (waitFactory.alertIsPresent())
         {
            commonFunctiondriver.switchTo().alert().accept();
            flag = !waitFactory.alertIsPresent();
         }
         else
         {
            throw new Exception("No Alerts Present & Unable to Accept alert");
         }
      }
      catch (WaitFactoryUseException we)
      {
         log.error("Unable to find the element");
         flag = false;
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for", we);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         flag = false;
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This dismissAlert method is used to dismiss the alert box which displays on the application.
    *
    * @return flag -> True if successfully, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean dismissAlert() throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (waitFactory.alertIsPresent())
         {
            commonFunctiondriver.switchTo().alert().dismiss();
            flag = waitFactory.alertIsPresent();
         }
         else
         {
            throw new Exception("No Alerts Present & Unable to dismiss alert");
         }
      }
      catch (WaitFactoryUseException we)
      {
         log.error("Unable to find the element");
         flag = false;
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for", we);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         flag = false;
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }
}