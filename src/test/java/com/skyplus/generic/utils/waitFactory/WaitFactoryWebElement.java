package com.skyplus.generic.utils.waitFactory;

import com.skyplus.enums.WaitTimeOuts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

/**
 * This class contains wrapper implementation for all method Which accept WebElement as an argument for the method in
 * ExpectedConditions class.
 */
public class WaitFactoryWebElement
{
   WaitFactoryPojo waitFactoryPojo ;
   /**
    * Logger Instance Variable to capture the information in the log.
    */
   private final Logger log = LogManager.getLogger(WaitFactoryWebElement.class);
   /**
    * This is a WebDrvier Wait Instance Variable.
    */
   private final FluentWait<WebDriver> longWait;
   /**
    * This is a WaitFactoryWebElement Instance Variable.
    */
   private final WaitFactoryWebElement instance = null;
   Validator validate;

   /**
    * This Constructor initializes the WebDriverWait for the WaitFactory Generic Class.
    */
   public WaitFactoryWebElement(WaitFactoryPojo waitFactoryPojoObj, Validator validate)
   {
      this.waitFactoryPojo = waitFactoryPojoObj;
      this.longWait = waitFactoryPojoObj.getLongWait();
      this.validate = validate;
   }

   /**
    * This method waits until an element is visible and enabled such that you can click it.
    *
    * @param locator ->locator Element
    * @return true -> when element is clickable (visible and enabled)
    * @throws Exception caller to handle the exception
    */
   public boolean elementToBeClickableUsingWebElement(
            final WebElement locator) throws Exception
   {
      WebElement element = null;
      boolean flag = false;
      try
      {
         element = longWait
                  .until(ExpectedConditions.elementToBeClickable(locator));
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
    * This method waits until WebElement with given locator has a attribute with the give specific value.
    *
    * @param locator        ->locator Element
    * @param attribute      ->Attribute to check in the locator
    * @param attributevalue ->Value to check in the Attribute
    * @return true -> if element has css or html attribute with the value
    * @throws Exception caller to handle the exception
    */
   public boolean attributeContainsUsingWebElement(final WebElement locator,
            final String attribute, final String attributevalue)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.attributeContains(locator,
                  attribute, attributevalue));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for attribute to contain the value");
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
    * This method waits until WebElement with given locator and which has a attribute with the give specific value.
    *
    * @param locator        ->locator Element
    * @param attribute      ->Attribute to check in the locator
    * @param attributevalue ->Value to check in the Attribute
    * @return true -> if element has css or html attribute with the value
    * @throws Exception caller to handle the exception
    */
   public boolean attributeToBeUsingWebElement(final WebElement locator,
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
                  "Unable to wait for element containing css or"
                           + " html attribute with the value provided");
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
    * This method waits until the given elements selection state is selected.
    *
    * @param locator  ->locator Element
    * @param selected -> Selection state of the element
    * @return true -> once the element's selection stated is selected
    * @throws Exception caller to handle the exception
    */
   public boolean elementSelectionStateToBeWebElement(final WebElement locator,
            final boolean selected) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions
                  .elementSelectionStateToBe(locator, selected));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for elements "
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
    * This method waits until the element is selected.
    *
    * @param locator ->locator Element
    * @return true -> once the element's selection stated is selected
    * @throws Exception caller to handle the exception
    */
   public boolean elementToBeSelectedElementUsingWebElement(
            final WebElement locator) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.elementToBeSelected(locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait until element to be selected");
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
    * This method waits until child WebElement as a part of parent element to be visible.
    *
    * @param parentLocator ->Parent By object in the DOM
    * @param childLocator  -> Child By Object in the DOM
    * @return true -> all the nested elements are visible
    * @throws Exception caller to handle the exception
    */
   public boolean visibilityOfNestedElementsLocatedByUsingWebElement(
            final WebElement parentLocator, final By childLocator)
            throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(
                  ExpectedConditions.visibilityOfNestedElementsLocatedBy(
                           parentLocator, childLocator));
      }
      catch (final Exception e)
      {
         log.error(
                  "unable to wait for the visibility of "
                           + "the parent or child element part "
                           + "of parent element");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until the element is visible and known to be present on the DOM.
    *
    * @param locator ->locator Element
    * @return true -> when element it is visible
    * @throws Exception caller to handle the exception
    */
   public boolean visibilityOfElementLocatedUsingWebElement(
           final WebElement locator, WaitTimeOuts waitTimeOuts) throws Exception
   {
      WebElement element = null;
      boolean flag = false;
      FluentWait<WebDriver> waitToUse;

      switch (waitTimeOuts){
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
         element = waitToUse.until(ExpectedConditions.visibilityOf(locator));
      }
      catch (TimeoutException t)
      {
         log.error("Timedout while waiting for visibility of webelement");
         throw new TimeoutException(t);
      }
      catch (final Exception e)
      {
         log.error("unable to wait for visibility of the element");
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
    * @param locator ->locator Element
    * @return boolean -> all Webelement are located
    * @throws Exception caller to handle the exception
    */
   public boolean visibilityOfAllElementsLocatedUsingWebElement(
            final WebElement locator) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait
                  .until(ExpectedConditions.visibilityOfAllElements(locator));
      }
      catch (final Exception e)
      {
         log.error("unable to wait for all the elements to be visible");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method waits until the element to be invisible.
    *
    * @param locator ->locator Element
    * @return true ->when elements is not visible anymore
    * @throws Exception caller to handle the exception
    */
   public boolean invisibilityOfElementLocatedUsingWebElement(
            final WebElement locator) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.invisibilityOf(locator));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for element to be invisible");
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
    * This method waits until the expected Attribute Value is present in the WebElement Specified.
    *
    * @param locator      ->locator Element
    * @param expectedText -> expected text in the element
    * @return true -> once the element's value attribute contains the given text
    * @throws Exception caller to handle the exception
    */
   public boolean textToBePresentInElementValueUsingWebElement(
            final WebElement locator, final String expectedText)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions
                  .textToBePresentInElementValue(locator, expectedText));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for element having value "
                           + "attribute containing the given text");
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
    * This method waits until the given text is present in the specified WebElement.
    *
    * @param locator      ->locator Element
    * @param expectedText -> expected text in the element
    * @return true -> If the Text is present in the WebElement
    * @throws Exception caller to handle the exception
    */
   public boolean textToBePresentInElementUsingWebElement(
            final WebElement locator, final String expectedText)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions
                  .textToBePresentInElement(locator, expectedText));
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for element containing the given text");
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
    * @throws Exception caller to handle the exception
    */
   public boolean presenceOfNestedElementLocatedByUsingWebElement(
            final WebElement parentLocator, final By childLocator)
            throws Exception
   {
      WebElement element = null;
      try
      {
         element = longWait
                  .until(ExpectedConditions.presenceOfNestedElementLocatedBy(
                           parentLocator, childLocator));
      }
      catch (final Exception e)
      {
         log.error(
                  "unable to wait until child WebElement "
                           + "as a part of parent element");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidator(element);
   }

   /**
    * This method waits until the given frame is available to switch to and switches the frame.
    *
    * @param locatorToFindFrame ->locator element to find in frame
    * @return true -> Once the method switches to given frame
    * @throws Exception caller to handle the exception
    */
   public boolean frameToBeAvailableAndSwitchToItUsingWebElement(
            final WebElement locatorToFindFrame) throws Exception
   {
      WebDriver driverElement = null;
      try
      {
         driverElement = longWait.until(ExpectedConditions
                  .frameToBeAvailableAndSwitchToIt(locatorToFindFrame));
      }
      catch (final Exception e)
      {
         log.error("unable to wait for the frame to be available to swicth");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidator(driverElement);
   }

   /**
    * This method Waits until the Element is no longer attached to the DOM.
    *
    * @param elementToWaitFor -> Element to check for the staleness
    * @return false -> if the element is still attached to the DOM
    * @throws Exception caller to handle the exception
    */
   public boolean stalenessOfElementUsingWebElement(
            final WebElement elementToWaitFor) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.stalenessOf(elementToWaitFor));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait until the element is removed from the DOM");
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
    * This Method waits until all elements from given list to be invisible.
    *
    * @param locator ->locator Element
    * @return true -> When the element is not visible any more
    * @throws Exception caller to handle the exception
    */
   public boolean invisibilityOfAllElementsUsingWebElement(
            final WebElement locator) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(
                  ExpectedConditions.invisibilityOfAllElements(locator));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait until all elements "
                           + "from given list to be invisible");
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
    * This Method Checked if the Given element has the attribute specified with non-empty value.
    *
    * @param element                   -> WebElement
    * @param attributeToCheckInElement ->Attribute to validate in element
    * @return true -> true when element has css or html attribute with non empty value
    * @throws Exception caller to handle the exception
    */
   public boolean attributeToBeNotEmptyUsingWebElement(
            final WebElement element, final String attributeToCheckInElement)
            throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions.attributeToBeNotEmpty(element,
                  attributeToCheckInElement));
      }
      catch (final Exception e)
      {
         log.error(
                  "unable to wait for the given element to have"
                           + " the attribute specifiedwith "
                           + "non-empty attribute");
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
    * This method checks list of elements provided are visible on WebPage.
    *
    * @param elements ->List of Web Elements
    * @return true -> once all the webelement are located
    * @throws Exception caller to handle the exception
    */
   public boolean visibilityOfAllElementsUsingWebElement(
            final List<WebElement> elements) throws Exception
   {
      List<WebElement> listOfWebElement;
      try
      {
         listOfWebElement = longWait.until(
                  ExpectedConditions.visibilityOfAllElements(elements));
      }
      catch (final Exception e)
      {
         log.error(
                  "unable to wait for the element to be visible on the page");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidatorForListOfObject(listOfWebElement);
   }

   /**
    * This method wait until all elements from given list to be invisible.
    *
    * @param locator ->locator Element
    * @return true -> When all Elements are not visible
    * @throws Exception caller to handle the exception
    * @since 11-07-2018
    */
   public boolean invisibilityOfAllElementsUsingWebElement(
            final List<WebElement> locator) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(
                  ExpectedConditions.invisibilityOfAllElements(locator));
      }
      catch (final Exception e)
      {
         log.error(
                  "unable to wait for the all the element"
                           + " in the given list to be invisible");
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
    * @param locator     ->locator Element
    * @param textToCheck -> text that should not be in visible in the locator
    * @return true -> if no such element, stale element or displayed text not equal to that provided
    * @throws Exception caller to handle the exception
    * @since 11-07-2018
    */
   public boolean invisibilityOfElementWithTextUsingWebElement(
            final By locator, final String textToCheck) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = longWait.until(ExpectedConditions
                  .invisibilityOfElementWithText(locator, textToCheck));
      }
      catch (final Exception e)
      {
         log.error(
                  "Unable to wait for the element with given text is"
                           + " either invisible or not present in the DOM");
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
