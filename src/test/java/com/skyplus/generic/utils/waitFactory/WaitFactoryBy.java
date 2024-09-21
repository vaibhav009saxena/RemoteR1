package com.skyplus.generic.utils.waitFactory;

import com.skyplus.enums.WaitTimeOuts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This class contains wrapper implementation for all method Which accept By as an argument in ExpectedConditions class
 */
public class WaitFactoryBy
{
   WaitFactoryPojo waitFactoryPojo;

   /**
    * Logger Instance Variable to capture the information in the log.
    */
   private final Logger log = LogManager.getLogger(WaitFactoryBy.class);
   /**
    * This is a WebDrvier Wait Instance Variable.
    */
   private final FluentWait<WebDriver> longWait;

   /**
    * This is a WaitFactoryWebElement Instance Variable.
    */
   private final WaitFactoryBy instance = null;
   Validator validate;

   /**
    *
    */
   public WaitFactoryBy(WaitFactoryPojo waitFactoryPojoObj, Validator validate)
   {
      this.waitFactoryPojo = waitFactoryPojoObj;
      this.longWait = waitFactoryPojo.getLongWait();
      this.validate = validate;
   }

   /**
    * This method checks for WebElement with given locator and waits which has a attribute with the give specific
    * value.
    *
    * @param locator        -> Web locator Object
    * @param attribute      -> Attribute to check in the locator
    * @param attributeValue ->Value to check in the attribute
    * @return true -> if element has css or html attribute with the value
    * @throws Exception caller to handle the exception
    */
   public boolean attributeContainsUsingBy(
            final By locator, final String attribute,
            final String attributeValue) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.attributeContains(locator,
                  attribute, attributeValue));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for elements attribute to "
                           + "contain the given value");
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
    * This method waits until an element is visible and enabled to be clickable.
    *
    * @param locator -> Web locator Object
    * @return true -> if the element is visible and enabled to be clickable
    * @throws Exception caller to handle the exception
    */
   public boolean elementToBeClickableUsingBy(final By locator)
            throws Exception
   {
      WebElement element = null;
      boolean flag;
      try
      {
         element = longWait.until(ExpectedConditions.elementToBeClickable(
                  locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for element to be clickable");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(element);
      }
      return flag;
   }

   /**
    * This method wait until WebElement with given locator and which has a attribute with the give specific value.
    *
    * @param locator        -> Web locator Object
    * @param attribute      -> Attribute to check in the locator
    * @param attributevalue ->Value to check in the attribute
    * @return true -> if element has css or html attribute with the given value
    * @throws Exception caller to handle the exception
    */
   public boolean attributeToBeUsingBy(final By locator,
            final String attribute, final String attributevalue)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.attributeToBe(locator,
                  attribute, attributevalue));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for element "
                           + "containing the given attribute value");
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
    * This method waits for the element selection state to be "selected".
    *
    * @param locator  -> Web locator Object
    * @param selected -> Selection state of the Locator
    * @return true -> once the element's selection stated is "selected"
    * @throws Exception Caller to handle exception
    */
   public boolean elementSelectionStateToBeUsingBy(final By locator,
            final boolean selected) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.elementSelectionStateToBe(
                  locator, selected));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for element "
                           + "selection state to be selected");
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
    * This method waits until the element to be selected.
    *
    * @param locator -> Web locator Object
    * @return true -> once the element's selection stated is selected
    * @throws Exception caller to handle exception
    */
   public boolean elementToBeSelectedUsingBy(final By locator)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.elementToBeSelected(locator));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for element "
                           + "selection state to change to selected");
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
    * This method waits until child WebElement, as a part of parent element to be visible.
    *
    * @param parentLocator ->Parent By object in the DOM
    * @param childLocator  -> Child By Object in the DOM
    * @return true ->if all the nested elements are visible and located
    * @throws Exception Caller to handle exception
    */
   public boolean visibilityOfNestedElementsLocatedByUsingBy(
            final By parentLocator, final By childLocator) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait
                  .until(ExpectedConditions
                           .visibilityOfNestedElementsLocatedBy(parentLocator,
                                    childLocator));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for all the nested element"
                           + " to be visible and located");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until the element is visible and known to be present on the DOM.
    *
    * @param locator -> Web locator Object
    * @return true -> once it is visible
    * @throws Exception Caller to handle exception
    */
   public boolean visibilityOfElementLocatedUsingBy(final By locator, WaitTimeOuts typeOfTypeOut)
            throws Exception
   {
      WebElement element = null;
      boolean flag = false;
      FluentWait<WebDriver> waitToUse;

      switch (typeOfTypeOut){
         case SHORT:
            waitToUse = waitFactoryPojo.getShortWait();
            break;

         case MEDIUM:
            waitToUse = waitFactoryPojo.getMediumWait();
            break;

         default:
            waitToUse = this.longWait;
            break;
      }
      try
      {
         element = waitToUse.until(ExpectedConditions.visibilityOfElementLocated(
                  locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the visibility of the element");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(element);
      }
      return flag;

   }

   /**
    * This method waits until all elements present on the web page that match the locator are visible.
    *
    * @param locator -> Web locator Object
    * @return true -> WebElements once they are located
    * @throws Exception Caller to handle exception
    */
   public boolean visibilityOfAllElementsLocatedByUsingBy(final By locator)
            throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(ExpectedConditions
                  .visibilityOfAllElementsLocatedBy(locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for visiblitity of all elements");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until the element is either invisible or not present on the DOM.
    *
    * @param locator -> Web locator Object
    * @return true ->when elements is not visible anymore
    * @throws Exception Caller to handle exception
    */
   public boolean invisibilityOfElementLocatedUsingBy(final By locator)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.invisibilityOfElementLocated(
                  locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the element to be invisible");
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
    * This method checks if the expected Attribute Value is present in the By locator Specified.
    *
    * @param locator                           -> Web locator Object
    * @param expectedElementValueAttributeText -> Attribute Text to check in locator
    * @return true -> once the element's value attribute contains the given text
    * @throws Exception Caller to handle exception
    */
   public boolean textToBePresentInElementValueUsingBy(final By locator,
            final String expectedElementValueAttributeText) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(
                  ExpectedConditions.textToBePresentInElementValue(locator,
                           expectedElementValueAttributeText));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the element with "
                           + "the give attribute text value");
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
    * This method waits until given text is present in the element that matches the given locator.
    *
    * @param locator      -> Web locator Object
    * @param expectedText ->Text to be present in locator
    * @return true -> If the Text is present in the By
    * @throws Exception Caller to handle exception
    */
   public boolean textToBePresentInElementLocatedUsingBy(final By locator,
            final String expectedText) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions
                  .textToBePresentInElementLocated(locator, expectedText));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the text to be present in the locator");
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
    * This method waits until child WebElement as a part of parent element to be present.
    *
    * @param parentLocator ->Parent By object in the DOM
    * @param childLocator  -> Child By Object in the DOM
    * @return WebElement -> child element identified from this method
    * @throws Exception Caller to handle exception
    */
   public boolean presenceOfNestedElementLocatedByUsingBy(
            final By parentLocator, final By childLocator) throws Exception
   {
      WebElement element = null;
      boolean flag = false;
      try
      {
         element = longWait.until(ExpectedConditions
                  .presenceOfNestedElementLocatedBy(parentLocator,
                           childLocator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the text to be present");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(element);
      }
      return flag;

   }

   /**
    * This method waits until the given frame is available to switch to and switches the frame.
    *
    * @param locatorToFindFrame -> Web locator object to find in the frame
    * @return true -> Once the method switches to given frame and the respective WebDrvier Element is returned
    * @throws Exception Caller to handle exception
    */
   public boolean frameToBeAvailableAndSwitchToItUsingBy(
            final By locatorToFindFrame) throws Exception
   {
      WebDriver driverElement = null;
      boolean flag = false;
      try
      {
         driverElement = longWait.until(ExpectedConditions
                  .frameToBeAvailableAndSwitchToIt(locatorToFindFrame));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the frame to be "
                           + "available to switch to");
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
    * This method waits until all elements from given list to be invisible.
    *
    * @param locator -> Web locator Object
    * @return true -> When all Elements are not visible
    * @throws Exception Caller to handle exception
    * @since 11-07-2018
    */
   public boolean invisibilityOfAllElementsUsingBy(
            final List<WebElement> locator) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.invisibilityOfAllElements(
                  locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for all elements to be invisible");
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
    * This Method waits until the given element with text is either invisible or not present on the DOM.
    *
    * @param locator     -> Web locator Object
    * @param textToCheck ->Text to Verify in the locator
    * @return true -> if no such element, stale element or displayed text not equal to that provided
    * @throws Exception Caller to handle exception
    * @since 11-07-2018
    */
   public boolean invisibilityOfElementWithTextUsingBy(final By locator,
            final String textToCheck) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.invisibilityOfElementWithText(
                  locator, textToCheck));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for all elements to be not visible");
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
    * This method waits until number of WebElements in given locator equal to the number specified.
    *
    * @param locator -> Web locator Object
    * @param number  -> Number of WebElement to wait for in the locator
    * @return true -> when size of elements list is equal to defined
    * @throws Exception Caller to handle exception
    */
   public boolean numberOfElementsToBeUsingBy(final By locator,
            final int number) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(ExpectedConditions
                  .numberOfElementsToBe(locator, number));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the number of "
                           + "WebElements to be located");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until number of WebElements with given locator being less than defined number.
    *
    * @param locator -> Web locator Object
    * @param number  -> Number of WebElement to wait for in the locator
    * @return true -> when size of elements list is less than defined
    * @throws Exception Caller to handle exception
    */
   public boolean numberOfElementsToBeLessThanUsingBy(final By locator,
            final int number) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(ExpectedConditions
                  .numberOfElementsToBeLessThan(locator, number));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for theuntil number of WebElements "
                           + "with given locator being less than"
                           + " defined number");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until number of WebElements with given locator being more than defined number.
    *
    * @param locator -> Web locator Object
    * @param number  ->Number of WebElement to wait for in the locator
    * @return true -> when size of elements list is more than defined
    * @throws Exception Caller to handle exception
    */
   public boolean numberOfElementsToBeMoreThanUsingBy(final By locator,
            final int number) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(ExpectedConditions
                  .numberOfElementsToBeMoreThan(locator, number));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait until number of WebElements with "
                           + "given locator being more than defined number");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until at least the presence of one element from the give list of webelements in the web page.
    *
    * @param locator -> Web locator Object
    * @return true -> once the presence of all element located
    * @throws Exception Caller to handle exception
    */
   public boolean presenceOfAllElementsLocatedByUsingBy(final By locator)
            throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(ExpectedConditions
                  .presenceOfAllElementsLocatedBy(locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the presence of the element");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until parent WebElement provided is present and waits for the presence of child webelement
    * provided.
    *
    * @param parentLocator ->Parent By object in the DOM
    * @param childLocator  -> Child By Object in the DOM
    * @return true -> all subelements are located
    * @throws Exception Caller to handle exception
    */
   public boolean presenceOfNestedElementsLocatedByUsingBy(
            final By parentLocator, final By childLocator) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait
                  .until(ExpectedConditions.presenceOfNestedElementsLocatedBy(
                           parentLocator, childLocator));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for parent webelement or child "
                           + "webelement to be present "
                           + "with the given parent webelement");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until the element is present on the DOM of the page. This does not necessarily mean that the
    * element is visible.
    *
    * @param locator -> Web locator Object
    * @return true -> once it is located
    * @throws Exception Caller to handle exception
    */
   public boolean presenceOfElementLocatedUsingBy(final By locator)
            throws Exception
   {
      WebElement element = null;
      boolean flag = false;
      try
      {
         element = longWait.until(ExpectedConditions.presenceOfElementLocated(
                  locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for the presence of the element in DOM");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         flag = validate.nullValidator(element);
      }
      return flag;
   }

   /**
    * This method waits until the given text to be present in the specified element.
    *
    * @param locator      -> Web locator Object
    * @param elementValue -> Text that needs to be present in the Object
    * @return true -> if the element contains the given text
    * @throws Exception Caller to handle exception
    */
   public boolean textToBeUsingBy(final By locator,
            final String elementValue) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.textToBe(locator,
                  elementValue));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the presence of element "
                           + "with the specified text");
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
    * This method waits for WebElement with given locator which has text with a value specified in the regular
    * expression.
    *
    * @param locator -> Web locator Object
    * @param pattern -> Pattern object to check in locator
    * @return true -> when element has text value containing given regex pattern value
    * @throws Exception Caller to handle exception
    */
   public boolean textMatchesUsingBy(final By locator, final Pattern pattern)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.textMatches(locator, pattern));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the element with given "
                           + "locator and value matching the Regex ");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.logResultOfConditionCheck(flag);
      }
      return flag;
   }

}
