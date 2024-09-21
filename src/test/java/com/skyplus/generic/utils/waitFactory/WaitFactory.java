package com.skyplus.generic.utils.waitFactory;

import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * WaitFactoryUse Class
 */

public final class WaitFactory
{
   /**
    * This is a WaitFactory Instance Variable.
    */
   private static WaitFactory instance;
   /**
    * This is a WaitFactoryPojo Instance Variable.
    */
   private final WaitFactoryPojo waitFactoryPojoObj;
   /**
    * This is a WaitFactoryGeneric Instance Variable.
    */
   private final WaitFactoryGeneric waitFactoryGeneric;
   /**
    * This is a WaitFactoryWebElement Instance Variable.
    */
   private final WaitFactoryWebElement waitfactorywebelement;
   /**
    * This is a WaitFactoryBy Instance Variable.
    */
   private final WaitFactoryBy waitFactoryBy;
   /**
    * This is a Logger Instance Variable.
    */
   private final Logger log = LogManager.getLogger(WaitFactory.class);
   private final SkyplusFactory skyplusFactory;
   Validator validate;
   private WebDriverWait webDriverWait;

   /**
    * This is a private constructor for the WaitFactory Class. This constructor accepts Web Driver and the Time Units
    * for the Wait
    */
   public WaitFactory(WaitFactoryPojo waitFactoryPojo, WaitFactoryWebElement waitFactoryWebElement, Validator validate,
            WaitFactoryBy waitFactoryBy, WaitFactoryGeneric waitFactoryGeneric, SkyplusFactory skyplusFactory)
   {
      this.skyplusFactory = skyplusFactory;
      this.waitFactoryPojoObj = waitFactoryPojo;
      this.waitFactoryPojoObj.setDriver(skyplusFactory.getDriver());
      this.validate = validate;
      this.waitFactoryBy = waitFactoryBy;
      this.waitFactoryGeneric = waitFactoryGeneric;
      this.waitfactorywebelement = waitFactoryWebElement;
   }

   /**
    * This method wait for the title which contains the specified a case-sensitive value.
    *
    * @param title -> Title of the Page
    * @return true -> when the title matches, false otherwise
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean titleContains(final String title)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (title.isEmpty())
         {
            throw new WaitFactoryUseException(
                     "Check the title passed is not empty");
         }
         flag = waitFactoryGeneric.titleContains(title);
      }
      catch (WaitFactoryUseException w)
      {
         log.error("Unable to wait for the title to be matched");
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method check an element is visible and enabled such that you can click it.
    *
    * @param object -> (By or WebElement)
    * @return True -> once it is located and clickable (visible and enabled)
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean elementToBeClickable(final Object object) throws WaitFactoryUseException, Exception
   {
      boolean clickable = false;
      try
      {
         if (object instanceof By)
         {
            clickable = waitFactoryBy.elementToBeClickableUsingBy(
                     (By) object);
         }
         else if (object instanceof WebElement)
         {
            clickable = waitfactorywebelement
                     .elementToBeClickableUsingWebElement(
                              (WebElement) object);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");

         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return clickable;
   }

   /**
    * This method checks for WebElement with given locator and which has a attribute with the give specific value.
    *
    * @param locator        ->(By or WebElement) (By or WebElement)
    * @param attribute      -> Attribute to be located in the locator
    * @param attributeValue -> Attribute value to be located in the attribute
    * @return true -> if element has css or html attribute with the value
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean attributeContains(final Object locator,
            final String attribute, final String attributeValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.attributeContainsUsingBy((By) locator,
                     attribute, attributeValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement.attributeContainsUsingWebElement(
                     (WebElement) locator, attribute,
                     attributeValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checks for WebElement with given locator and which has a attribute with the give specific value.
    *
    * @param locator        ->(By or WebElement)
    * @param attribute      -> Attribute to be located in the locator
    * @param attributeValue -> Attribute value to be located in the attribute
    * @return true -> if element has css or html attribute with the value
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean attributeToBe(final Object locator, final String attribute,
            final String attributeValue) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.attributeToBeUsingBy((By) locator,
                     attribute, attributeValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement.attributeToBeUsingWebElement(
                     (WebElement) locator, attribute, attributeValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }

      return flag;
   }

   /**
    * This method checks if the element is selected.
    *
    * @param locator  ->(By or WebElement) (By or WebElement)
    * @param selected -> Element selection state
    * @return true -> once the element's selection stated is selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean elementSelectionStateToBe(final Object locator,
            final boolean selected) throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.elementSelectionStateToBeUsingBy(
                     (By) locator, selected);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .elementSelectionStateToBeWebElement(
                              (WebElement) locator, selected);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checks if the element is selected.
    *
    * @param locator ->(By or WebElement)
    * @return true -> once the element's selection stated is selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean elementToBeSelected(final Object locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.elementToBeSelectedUsingBy((By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .elementToBeSelectedElementUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method waits for checking child WebElement as a part of parent element to be visible.
    *
    * @param parentLocator (By or WebElement)
    * @param childLocator  (By or WebElement)
    * @return true -> if the visible of nested webelement is located
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean visibilityOfNestedElementsLocatedBy(
            final Object parentLocator, final By childLocator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (parentLocator instanceof By)
         {
            flag = waitFactoryBy.visibilityOfNestedElementsLocatedByUsingBy(
                     (By) parentLocator, childLocator);
         }
         else if (parentLocator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .visibilityOfNestedElementsLocatedByUsingWebElement(
                              (WebElement) parentLocator,
                              childLocator);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method waits until the element is visible and known to be present on the DOM.
    *
    * @param obj ->(By or WebElement)
    * @return boolean -> once it is visible
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean visibilityOf(final Object obj) throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (obj instanceof By)
         {
            flag = waitFactoryBy.visibilityOfElementLocatedUsingBy(
                     (By) obj, WaitTimeOuts.LONG);
         }
         else if (obj instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .visibilityOfElementLocatedUsingWebElement(
                              (WebElement) obj, WaitTimeOuts.LONG);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (TimeoutException t)
      {
         log.error("Webelement could not be found within provided timeout");
         return flag;
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw e;
      }
      return flag;
   }

   /**
    * This method waits until the element is visible as per timeout and known to be present on the DOM.
    *
    * @param obj ->(By or WebElement)
    * @return boolean -> once it is visible
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean visibilityOf(final Object obj, WaitTimeOuts waitTimeOuts) throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (obj instanceof By)
         {
            flag = waitFactoryBy.visibilityOfElementLocatedUsingBy(
                    (By) obj, waitTimeOuts);
         }
         else if (obj instanceof WebElement)
         {
            flag = waitfactorywebelement
                    .visibilityOfElementLocatedUsingWebElement(
                            (WebElement) obj, waitTimeOuts);
         }
         else
         {
            throw new WaitFactoryUseException(
                    "Check argument passed is not Null/empty/Invalid."
                            + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                 "Check the argument passed is not Null/empty ."
                         + " It should be of type By/WebElement",
                 w);
      }
      catch (TimeoutException t)
      {
         log.error("Webelement could not be found within provided timeout");
         return flag;
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw e;
      }
      return flag;
   }

   /**
    * This methods waits until all elements present on the web page that match the locator are visible.
    *
    * @param locator ->(By or WebElement)
    * @return true -> when the elements are located
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean visibilityOfAllElements(final Object locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.visibilityOfAllElementsLocatedByUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .visibilityOfAllElementsLocatedUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method waits for checking the element to be invisible.
    *
    * @param locator ->(By or WebElement)
    * @return true ->when elements is not visible anymore
    */
   public boolean invisibilityOf(final Object locator) throws Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.invisibilityOfElementLocatedUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .invisibilityOfElementLocatedUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage());
         throw e;
      }
      return flag;
   }

   /**
    * This method checks if the expected Attribute Value is present in the By locator Specified.
    *
    * @param locator                    ->(By or WebElement)
    * @param expectedAttributeTextValue -> Text that is expected in the attribute value of element
    * @return true -> once the element's value attribute contains the given text
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean textToBePresentInElementAttributeValue(final Object locator,
            final String expectedAttributeTextValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.textToBePresentInElementValueUsingBy(
                     (By) locator, expectedAttributeTextValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .textToBePresentInElementValueUsingWebElement(
                              (WebElement) locator,
                              expectedAttributeTextValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checks if the expected Attribute Value is present in the By locator Specified.
    *
    * @param locator                    ->(By or WebElement) (By or WebElement)
    * @param expectedAttributeTextValue -> Text that is expected in the attribute value of element
    * @return true -> once the element's value attribute contains the given text
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean textToBePresentInElement(final Object locator,
            final String expectedAttributeTextValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.textToBePresentInElementLocatedUsingBy(
                     (By) locator, expectedAttributeTextValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .textToBePresentInElementUsingWebElement(
                              (WebElement) locator,
                              expectedAttributeTextValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty "
                           + ". It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checks if the expected Attribute Value is present in the By locator Specified.
    *
    * @param locator                    ->(By or WebElement)
    * @param expectedAttributeTextValue -> Text that is expected in the attribute value of element
    * @return true -> once the element's value attribute contains the given text
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean textToBePresentInElementValue(final Object locator,
            final String expectedAttributeTextValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.textToBePresentInElementValueUsingBy(
                     (By) locator, expectedAttributeTextValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .textToBePresentInElementValueUsingWebElement(
                              (WebElement) locator,
                              expectedAttributeTextValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This is a method checks child WebElement as a part of parent element to be present.
    *
    * @param locator      -> (By or WebElement)
    * @param childLocator -> (By or WebElement)
    * @return True -> child element identified from this method
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean presentOfNestedElementLocatedBy(final Object locator,
            final By childLocator) throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.presenceOfNestedElementLocatedByUsingBy(
                     (By) locator, childLocator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .presenceOfNestedElementLocatedByUsingWebElement(
                              (WebElement) locator,
                              childLocator);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method wait for the title to match to the specified value.
    *
    * @param title -> Expected Title string
    * @return true -> when the title matches, false otherwise
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean titleIs(final String title) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (title.isEmpty())
         {
            throw new WaitFactoryUseException(
                     "Check the title passed is empty");
         }
         else
         {
            flag = waitFactoryGeneric.titleIs(title);
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error("Unable to wait for the title to be matched");
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }

      return flag;
   }

   /**
    * This method wait for URL which contains the specific text.
    *
    * @param urlText -> Expected string in the URL
    * @return true -> when the URL contains the text
    * @throws WaitFactoryUseException and Exception - Caller needs to handle
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean urlContains(final String urlText)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (urlText.isEmpty())
         {
            throw new WaitFactoryUseException(
                     "Check the title passed is empty");
         }
         else
         {
            flag = waitFactoryGeneric.urlContains(urlText);
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error("Unable to wait for URL which contains the " + urlText);
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method wait for the URL to match a specific regular expression.
    *
    * @param urlRegx -> Regular Expression for the expected URL String
    * @return true -> if the URL matches the specified regular expression
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean urlMatches(final String urlRegx)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (urlRegx.isEmpty())
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + urlRegx
                              + " passed is empty");
         }
         else
         {
            flag = waitFactoryGeneric.urlMatches(urlRegx);
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error("Unable to wait for the frame " + urlRegx);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * To Check the URL of the current page to be a specific url.
    *
    * @param urlToBe -> Expected URL
    * @return true -> when the URL is what it should be
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean urlToBe(final String urlToBe) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (urlToBe.isEmpty())
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + urlToBe
                              + " passed is empty");
         }
         else
         {
            flag = waitFactoryGeneric.urlToBe(urlToBe);
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for the URL " + urlToBe);
         throw new Exception(e);
      }

      return flag;
   }

   /**
    * This method wait for the given frame is available to switch to. If the frame is available it switches the given
    * driver to the specified frameIndex.
    *
    * @param locator ->(By or WebElement) (By or WebElement or String or int)
    * @return true -> after frame has been switched
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean frameToBeAvailableAndSwitchToIt(final Object locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.frameToBeAvailableAndSwitchToItUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .frameToBeAvailableAndSwitchToItUsingWebElement(
                              (WebElement) locator);
         }
         else if (locator instanceof String)
         {
            flag = waitFactoryGeneric
                     .frameToBeAvailableAndSwitchToItbyFrameName(
                              (String) locator);
         }
         else if (locator instanceof Integer)
         {
            flag = waitFactoryGeneric
                     .frameToBeAvailableAndSwitchToItbyFrameId(
                              (int) locator);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + locator
                              + " passed is empty");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error("Unable to wait for the frame " + locator);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This Method wait if the alert is present.
    *
    * @return true -> when alert it is present
    * @throws Exception -> Exception caller to handle the exception
    */
   public boolean alertIsPresent() throws Exception
   {
      boolean alertFlag;
      try
      {
         alertFlag = waitFactoryGeneric.alertIsPresentInPage();
      }
      catch (final Exception e)
      {
         log.error("Unable to wait for alert on the page");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return validate.nullValidator(alertFlag);
   }

   /**
    * This method checks if js executable. Useful when you know that there should be a Javascript value or something at
    * the stage.
    *
    * @param javaScript -> JavaScript object
    * @return true -> once javaScript executed without errors
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean javaScriptThrowsNoExceptions(final Object javaScript)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (javaScript instanceof String)
         {
            flag = waitFactoryGeneric.javaScriptThrowsNoExceptions(
                     (String) javaScript);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + javaScript
                              + " passed is empty");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error("Unable to wait for the JS where it throws no error"
                  + javaScript);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * An expectation for String value from javascript.
    *
    * @param javaScript -> JavaScript object
    * @return true -> once js return string
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean jsReturnsValue(final String javaScript)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (javaScript instanceof String)
         {
            flag = waitFactoryGeneric.jsReturnsValue(javaScript);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + javaScript
                              + " passed is empty");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error("unable to wait for the JS Error " + javaScript);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method Waits until the Element is no longer attached to the DOM.
    *
    * @param elementToWaitFor (By or WebElement)
    * @return false -> if the element is still attached to the DOM
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean stalenessofElement(final Object elementToWaitFor)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (elementToWaitFor instanceof By)
         {
            flag = waitfactorywebelement.stalenessOfElementUsingWebElement(
                     waitFactoryPojoObj.getDriver().findElement(
                              (By) elementToWaitFor));
         }
         else if (elementToWaitFor instanceof WebElement)
         {
            flag = waitfactorywebelement.stalenessOfElementUsingWebElement(
                     (WebElement) elementToWaitFor);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This Method Checks if the Given WebElement is Not Visible in the DOM.
    *
    * @return true -> When the element is not visible any more
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean invisibilityOfAllElements(final Object... element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element.length > 0)
         {
            for (Object object : element)
            {
               if (objectIsBy(object))
               {
                  flag = waitfactorywebelement
                           .invisibilityOfAllElementsUsingWebElement(
                                    waitFactoryPojoObj
                                             .getDriver().findElement((By) object));
                  if (!flag)
                  {
                     break;
                  }
               }
               else if (objectIsWebElement(object))
               {
                  flag = waitfactorywebelement
                           .invisibilityOfAllElementsUsingWebElement(
                                    (WebElement) object);
                  if (!flag)
                  {
                     break;
                  }
               }
            }
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method is used to check if parameter is WebElement or not
    *
    * @param : object Object
    * @return : true/false
    */
   private boolean objectIsWebElement(final Object object)
   {
      boolean flag = false;
      try
      {
         if (object != null)
         {
            flag = true;
            log.info("Sent object is of WebElement type..");
         }
      }
      catch (ClassCastException e)
      {
         log.warn("Object sent is not of WebElement type..");
      }
      return flag;

   }

   /**
    * This method is used to check if parameter is By or not
    *
    * @param : Object
    * @return : true/false
    */
   private boolean objectIsBy(final Object object)
   {
      boolean flag = false;
      try
      {
         if (object != null)
         {
            flag = true;
            log.info("Sent object is of By type..");
         }
      }
      catch (ClassCastException e)
      {
         log.warn("Object sent is not of By type..");
      }
      return flag;
   }

   /**
    * This Method Checked if the Given element has the attribute specified with non-empty value.
    *
    * @param locator                   ->(By or WebElement) (By or WebElement)
    * @param attributeToCheckInElement -> attribute expected in the locator
    * @return true -> true when element has css or html attribute with non empty value
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean attributeToBeNotEmpty(final Object locator,
            final String attributeToCheckInElement)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitfactorywebelement
                     .attributeToBeNotEmptyUsingWebElement(
                              waitFactoryPojoObj.getDriver().findElement(
                                       (By) locator),
                              attributeToCheckInElement);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .attributeToBeNotEmptyUsingWebElement(
                              (WebElement) locator,
                              attributeToCheckInElement);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This Methods checks list of elements identified through WebElement or By locator strategy are visible on WebPage.
    *
    * @param listOfObjects List<Object> (By or WebElement . But not mix of both)
    *                      Eg : Calling method to pass List<WebElement> or List<By> by wrapping it as below.
    *                            Collections.singletonList(list)
    * @return true -> When the Visibility of all the elements are located
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean visibilityOfAllListOfElements(final List<Object> listOfObjects)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      List<Object> listToEvaluate;
      if (listOfObjects.size() > 0)
      {
         listToEvaluate = (List<Object>) listOfObjects.get(0);
      }
      else
      {
         throw new Exception("Incorrect arguments passed");
      }
      try
      {
         if (listToEvaluate.get(0) instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .visibilityOfAllElementsUsingWebElement(
                              convertToWebElement(listToEvaluate));
         }
         else if (listToEvaluate.get(0) instanceof By)
         {
            flag = waitfactorywebelement
                     .visibilityOfAllElementsUsingWebElement(
                              convertToBy(listToEvaluate));
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This methods checks if all elements from given list to be invisible.
    *
    * @param listOfObjects (By or WebElement)
    * @return true -> When all Elements are not visible
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @since 11-07-2018
    */
   public boolean invisibilityOfAllElements(final List<Object> listOfObjects)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (listOfObjects.get(1) instanceof WebElement)
         {
            flag = waitfactorywebelement
                     .invisibilityOfAllElementsUsingWebElement(
                              convertToWebElement(listOfObjects));
         }
         else if (listOfObjects.get(1) instanceof By)
         {
            flag = waitFactoryBy.invisibilityOfAllElementsUsingBy(
                     convertToBy(
                              listOfObjects));
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This Method checks that a given element with text is either invisible or not present on the DOM.
    *
    * @param locator     ->(By or WebElement) (By or WebElement)
    * @param textToCheck -> Text expected in the locator
    * @return true -> if no such element, stale element or displayed text not equal to that provided
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @since 11-07-2018
    */
   public boolean invisibilityOfElementWithText(final Object locator,
            final String textToCheck) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.invisibilityOfElementWithTextUsingBy(
                     (By) locator, textToCheck);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.invisibilityOfElementWithTextUsingBy(
                     toByValue(
                              (WebElement) locator), textToCheck);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This methods checks for number of WebElements with given locator.
    *
    * @param locator         ->(By or WebElement) (By or WebElement)
    * @param numberOfElement -> number of Element
    * @return true -> when size of elements list is equal to defined
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean numberOfElementsToBe(final Object locator,
            final int numberOfElement) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.numberOfElementsToBeUsingBy((By) locator,
                     numberOfElement);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.numberOfElementsToBeUsingBy(toByValue(
                     (WebElement) locator), numberOfElement);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This methods checks for number of WebElements with given locator being less than defined number.
    *
    * @param locator         ->(By or WebElement) (By or WebElement)
    * @param numberOfElement -> number of Element
    * @return True -> when size of elements list is less than defined
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean numberOfElementsToBeLessThan(final Object locator,
            final int numberOfElement) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.numberOfElementsToBeLessThanUsingBy(
                     (By) locator, numberOfElement);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.numberOfElementsToBeLessThanUsingBy(
                     toByValue(
                              (WebElement) locator), numberOfElement);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This methods checks for number of WebElements with given locator being more than defined number.
    *
    * @param locator         ->(By or WebElement) (By or WebElement)
    * @param numberOfElement -> Number of Element in locator
    * @return True -> when size of elements list is more than defined
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean numberOfElementsToBeMoreThan(final Object locator,
            final int numberOfElement) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.numberOfElementsToBeMoreThanUsingBy(
                     (By) locator, numberOfElement);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.numberOfElementsToBeMoreThanUsingBy(
                     toByValue(
                              (WebElement) locator), numberOfElement);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This methods checks that there is at least one element present on a web page.
    *
    * @param locator ->(By or WebElement)
    * @return True -> once all the elements are located
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean presenceOfAllElementsLocatedBy(final Object locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.presenceOfAllElementsLocatedByUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.presenceOfAllElementsLocatedByUsingBy(
                     toByValue((WebElement) locator));
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checks if child element as a part of parent element to present. Both the argument should be of same
    * type.
    *
    * @param parentLocator (By or WebElement)
    * @param childLocator  (By or WebElement)
    * @return true -> When the presence of nested SubElement list is located
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean presenceOfNestedElementsLocatedBy(final Object parentLocator,
            final Object childLocator) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (parentLocator instanceof By && childLocator instanceof By)
         {
            flag = waitFactoryBy.presenceOfNestedElementsLocatedByUsingBy(
                     (By) parentLocator, (By) childLocator);
         }
         else if (parentLocator instanceof WebElement
                  && childLocator instanceof WebElement)
         {
            flag = waitFactoryBy.presenceOfNestedElementsLocatedByUsingBy(
                     toByValue((WebElement) parentLocator),
                     toByValue((WebElement) childLocator));
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checking that an element is present on the DOM of a page. This does not necessarily mean that the
    * element is visible.
    *
    * @param locator ->(By or WebElement)
    * @return true -> if the presence of Element is located
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean presenceOfElementLocated(final Object locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.presenceOfElementLocatedUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.presenceOfElementLocatedUsingBy(toByValue(
                     (WebElement) locator));
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method checks for WebElement with given locator has text with a value as a part of it.
    *
    * @param locator      ->(By or WebElement)
    * @param regexPattern -> Regex Pattern to check in the locator
    * @return true -> when element has text value containing given regex pattern value
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean textMatches(final Object locator,
            final Pattern regexPattern) throws WaitFactoryUseException,
            Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.textMatchesUsingBy((By) locator,
                     regexPattern);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.textMatchesUsingBy(toByValue(
                     (WebElement) locator), regexPattern);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method is for checking if the given text is present in the specified element.
    *
    * @param locator ->(By or WebElement)
    * @param value   -> Text to be checked in the element
    * @return true -> once the element contains the given text
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean textToBe(final Object locator, final String value)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (locator instanceof By)
         {
            flag = waitFactoryBy.textToBeUsingBy((By) locator, value);
         }
         else if (locator instanceof WebElement)
         {
            flag = waitFactoryBy.textToBeUsingBy(toByValue(
                     (WebElement) locator), value);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method works based on logical AND condition of the given list of conditions. Each condition is checked until
    * all of them return true or not null.
    *
    * @param conditions > List of Expected conditions
    * @return true -> once all conditions are satisfied
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean and(final ExpectedCondition... conditions)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (conditions.length > 0)
         {
            flag = waitFactoryGeneric
                     .andBasedWaitUntilAllConditionsAreSatisfied(
                              conditions);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + conditions
                              + " passed is empty");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("unable to wait for the conditions" + conditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method works based on logical OR condition of the given list of conditions. Each condition is checked until
    * at least one of them returns true or not null.
    *
    * @param conditions -> List of Expected Conditions
    * @return true -> once one of conditions is satisfied
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean or(final ExpectedCondition... conditions)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (conditions.length > 0)
         {
            flag = waitFactoryGeneric.or(conditions);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + conditions
                              + " passed is empty");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for the expectation" + conditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method works based on logical NOT opposite condition of the given condition. Note that if the Condition you
    * are inverting throws an exception that is caught by the Ignored Exceptions, the inversion will not take place and
    * lead to confusing results.
    *
    * @param conditions -> List of Expected conditions
    * @return true -> once the condition is satisfied
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean not(final ExpectedCondition... conditions)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (conditions.length > 0)
         {
            for (ExpectedCondition expectedCondition : conditions)
            {
               flag = waitFactoryGeneric.not(expectedCondition);
            }
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + Arrays.toString(conditions)
                              + " passed is empty");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty"
                           + " . It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for given condition" + Arrays.toString(conditions)
                  + "to be satisfied");
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This is Wrapper for a refreshed condition, which allows for elements to update by redrawing. This works around the
    * problem of conditions which have two parts: find an element and then check for some condition on it. For these
    * conditions it is possible that an element is located and then subsequently it is redrawn on the client. When this
    * happens a StaleElementReferenceException is thrown when the second part of the condition is checked.
    *
    * @param expectedConditions -> List of Expected conditions
    * @return Expected Conditions -> result of the provided condition
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public ExpectedCondition<?> refreshed(
            final ExpectedCondition<?> expectedConditions)
            throws WaitFactoryUseException, Exception
   {
      ExpectedCondition<?> expectedCondition;
      try
      {
         if (expectedConditions instanceof ExpectedConditions)
         {
            expectedCondition = waitFactoryGeneric.refreshed(
                     expectedConditions);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Invalid Type provided, expected  "
                              + "ExpectedConditions as arguments");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         log.error("Unable to wait for given condition" + expectedConditions
                  + "to be satisfied");
         throw new Exception(e);
      }
      return expectedCondition;
   }

   /**
    * An expectation to wait for page load to be true else return false.
    *
    * @return true -> once js return string
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean waitForPageLoad() throws WaitFactoryUseException
   {
      boolean flag = false;
      try
      {
         flag = waitFactoryGeneric.waitForPageLoadUsingJSExecution();
      }
      catch (Exception e)
      {
         log.error(e.getMessage());
         throw new WaitFactoryUseException("unable to wait for the JS Execution ");
      }
      return flag;
   }

   /**
    * Halts the executing thread for specified seconds
    * @param timeoutInSec TIme in seconds to wait for
    */
   public void hardWait(int timeoutInSec) throws WaitFactoryUseException
   {
      try
      {
         Thread.sleep(timeoutInSec * 1000L);

      }
      catch (InterruptedException e)
      {
         log.error(e.getMessage() +"\nThread failed to wait for : "+timeoutInSec + "seconds");
         throw new WaitFactoryUseException("Thread failed to wait for : "+timeoutInSec + "seconds");
      }
   }

   /**
    * This method checks if the expected number of windows are existing for switching to .
    *
    * @param numberOfWindows -> int object
    * @return true -> once the expected number of window is matched
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean expectedNumberOfWindowsToBe(final Object numberOfWindows)
            throws WaitFactoryUseException, Exception
   {
      boolean flag;
      try
      {
         if (numberOfWindows instanceof Integer)
         {
            flag = waitFactoryGeneric.expectedConditionnumberOfWindowsToBe(
                     (int) numberOfWindows);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Exception thrown as the param " + numberOfWindows
                              + " passed is NUll/Empty ");
         }
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  w);
      }
      catch (Exception e)
      {
         log.error("Unable to wait for the JS where it throws no error"
                  + numberOfWindows);
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This method Converts the list of object to list of WebElement.
    *
    * @param element -> List of Objects
    * @return webElements -> List of Web Elements
    */
   private List<WebElement> convertToWebElement(final List<Object> element)
   {
      final List<WebElement> webElements = new ArrayList<WebElement>();
      for (Object o : element)
      {
         webElements.add((WebElement) o);
      }
      return webElements;
   }

   /**
    * This method Converts the list of object to list of WebElement.
    *
    * @param listofObject -> List of Objects
    * @return webElements -> List of Web Elements
    */
   private List<WebElement> convertToBy(final List<Object> listofObject)
   {
      final List<WebElement> webElements = null;
      for (Object o : listofObject)
      {
         final WebElement byElement = (WebElement) o;
         webElements.add(byElement);
      }
      return webElements;
   }

   /**
    * This method Converts the WebElement to By object.
    *
    * @param webElement -> WebElement object
    * @return byReference -> By Object
    */
   private By toByValue(final WebElement webElement)
   {
      By byReference = null;

      final String[] locatorData = webElement.toString().split(" -> ")[1]
               .split(": ");
      final String locator = locatorData[0];
      final String locatorValue = locatorData[1].substring(0, locatorData[1]
               .length() - 1);
      switch (locator)
      {
      case "xpath":
         byReference = By.xpath(locatorValue);
         break;
      case "css selector":
         byReference = By.cssSelector(locatorValue);
         break;
      case "id":
         byReference = By.id(locatorValue);
         break;
      case "tag name":
         byReference = By.tagName(locatorValue);
         break;
      case "name":
         byReference = By.name(locatorValue);
         break;
      case "link text":
         byReference = By.linkText(locatorValue);
         break;
      case "class name":
         byReference = By.className(locatorValue);
         break;
      default:
         break;
      }
      return byReference;
   }



}
