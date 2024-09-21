package com.skyplus.generic.utils.locatorFactory;

import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains wrapper implementation for all methods which accept By or/and String as an argument in
 * FindElement class in Selenium.
 */

public class LocatorFactoryType
{

   /**
    * Logger Instance Variable to capture the information in the log.
    */
   private final Logger log = LogManager.getLogger(LocatorFactoryType.class);
   /**
    * This is a WebDrvier Instance Variable.
    */
   private final WebDriver driver;

   /**
    * @param webDriver WebDriver driver
    */
   public LocatorFactoryType(SkyplusFactory skyplusFactory)
   {
      this.driver = skyplusFactory.getDriver();
   }

   /**
    * This method find the WebElement by passing the Object type By.
    *
    * @param locator -> Web locator Object
    * @return WebElement -> It returns webElement based on By locator
    * @throws Exception caller to handle the exception
    */
   protected WebElement findElementUsingBy(final By locator) throws Exception
   {
      WebElement element = null;
      try
      {
         element = driver.findElement(locator);
         log.info("Locating the WebElement by passing object Type BY");
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return element;
   }

   /**
    * This method find the list of WebElements by passing the Object type By.
    *
    * @param locator -> Web locator Object
    * @return List<WebElement> -> It returns list of WebElements based on given locator
    * @throws Exception caller to handle the exception
    */
   protected List<WebElement> findElementsUsingBy(final By locator)
            throws Exception
   {
      List<WebElement> elementList = new ArrayList<WebElement>();
      try
      {
         elementList = driver.findElements(locator);
         log.info("Locating the list of WebElements by passing object Type BY");
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return elementList;
   }

   /**
    * This method extract the Locator type and value from string which has specific format(LocatorType~LocatorValue).
    *
    * @param locator -> Web locator Object
    * @return String[] -> It returns Locator type and value
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private String[] getLocatorAndValue(final String locator)
            throws LocatorFactoryException, Exception
   {
      String[] elementLocatorAndValue = null;
      try
      {
         elementLocatorAndValue = locator.split("~");
         log.info("Spliting the String into LocatorType and LocatorValue");
         log.info("Locator Type-->" + elementLocatorAndValue[0]
                  + " and Locator Value-->" + elementLocatorAndValue[1]);
         if (elementLocatorAndValue.length != 2)
         {
            throw new LocatorFactoryException(
                     "Argument is not in LocatorType~LocatorValue format");
         }
      }
      catch (final LocatorFactoryException e)
      {
         log.error(e.getMessage(), e);
         throw new LocatorFactoryException(
                  "Argument is not in LocatorType~LocatorValue format");

      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return elementLocatorAndValue;
   }

   /**
    * This method find the WebElement based on passed formated string(LocatorType~LocatorValue).
    *
    * @param locator -> String Object
    * @return WebElement -> It returns webElement based on given string locator
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   protected WebElement findElementUsingString(final String locator)
            throws LocatorFactoryException, Exception
   {
      WebElement element = null;
      try
      {
         String[] elementLocatorAndValue = getLocatorAndValue(locator);
         element = getWebElementByLocatorAndValue(elementLocatorAndValue[0],
                  elementLocatorAndValue[1]);
         log.info("Locating the WebElement by passing object Type String");
      }
      catch (final LocatorFactoryException e)
      {
         log.error(e.getMessage(), e);
         throw new LocatorFactoryException(e);
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return element;
   }

   /**
    * This method finds the list of WebElements based on given a formated(LocatorType~LocatorValue) string.
    *
    * @param locator -> String Object
    * @return List<WebElement> -> It returns webElement based on given locator
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   protected List<WebElement> findElementsUsingString(final String locator)
            throws LocatorFactoryException, Exception
   {
      List<WebElement> elements = null;
      try
      {
         String[] elementLocatorAndValue = getLocatorAndValue(locator);
         elements = getWebElementsByLocatorAndValue(elementLocatorAndValue[0],
                  elementLocatorAndValue[1]);
         log.info("Locating the list of WebElements by passing object Type String");
      }
      catch (final LocatorFactoryException e)
      {
         log.error(e.getMessage(), e);
         throw new LocatorFactoryException(e);
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return elements;
   }

   /**
    * This method find the WebElement based on passed locator type and value.
    *
    * @param locatorType  -> String Object
    * @param locatorValue -> String Object
    * @return WebElement -> It returns webElement based on given locatorType and locatorValue
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private WebElement getWebElementByLocatorAndValue(final String locatorType,
            final String locatorValue) throws LocatorFactoryException, Exception
   {
      WebElement element = null;
      try
      {
         switch (locatorType.toLowerCase())
         {
         case "id":
            element = driver.findElement(By.id(locatorValue));
            break;
         case "name":
            element = driver.findElement(By.name(locatorValue));
            break;
         case "classname":
            element = driver.findElement(By.className(locatorValue));
            break;
         case "linktext":
            element = driver.findElement(By.linkText(locatorValue));
            break;
         case "partiallinktext":
            element = driver.findElement(By.partialLinkText(locatorValue));
            break;
         case "xpath":
            element = driver.findElement(By.xpath(locatorValue));
            break;
         case "tagname":
            element = driver.findElement(By.tagName(locatorValue));
            break;
         case "cssselector":
            element = driver.findElement(By.cssSelector(locatorValue));
            break;
         case "css":
            element = driver.findElement(By.cssSelector(locatorValue));
            break;
         default:
            throw new LocatorFactoryException(
                     "Loactor type is not valid");
         }
      }
      catch (final LocatorFactoryException e)
      {
         throw new LocatorFactoryException(
                  "Loactor type is not valid");
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return element;
   }

   /**
    * This method find the list of WebElements based on passed locator type and value.
    *
    * @param locatorType  -> String Object
    * @param locatorValue -> String Object
    * @return List<WebElement> -> It returns list of webElements based on given locatorType and locatorValue
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private List<WebElement> getWebElementsByLocatorAndValue(
            final String locatorType, final String locatorValue)
            throws LocatorFactoryException, Exception
   {
      List<WebElement> elementList = null;
      try
      {
         switch (locatorType.toLowerCase())
         {
         case "id":
            elementList = driver.findElements(By.id(locatorValue));
            break;
         case "name":
            elementList = driver.findElements(By.name(locatorValue));
            break;
         case "classname":
            elementList = driver.findElements(By.className(locatorValue));
            break;
         case "linktext":
            elementList = driver.findElements(By.linkText(locatorValue));
            break;
         case "partiallinktext":
            elementList = driver.findElements(By.partialLinkText(locatorValue));
            break;
         case "xpath":
            elementList = driver.findElements(By.xpath(locatorValue));
            break;
         case "tagname":
            elementList = driver.findElements(By.tagName(locatorValue));
            break;
         case "cssselector":
            elementList = driver.findElements(By.cssSelector(locatorValue));
            break;
         case "css":
            elementList = driver.findElements(By.cssSelector(locatorValue));
            break;
         default:
            throw new LocatorFactoryException(
                     "Loactor type is not valid");
         }
      }
      catch (final LocatorFactoryException e)
      {
         log.error(e.getMessage(), e);
         throw new LocatorFactoryException(
                  "Loactor type is not valid");
      }
      catch (final Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return elementList;
   }
}
