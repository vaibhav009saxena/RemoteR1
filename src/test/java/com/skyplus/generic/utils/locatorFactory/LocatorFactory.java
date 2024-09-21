package com.skyplus.generic.utils.locatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LocatorFactory
{

   /**
    * This is a LocatorFactoryType Variable.
    */
   private final LocatorFactoryType locatorFactoryType;
   /**
    * This is a Logger Instance Variable.
    */
   private final Logger log = LogManager.getLogger(LocatorFactory.class);
   /**
    * This is String separator.
    */
   private final String separator = "~";

   /**
    *
    */
   public LocatorFactory(LocatorFactoryType locatorFactoryType)
   {
      this.locatorFactoryType = locatorFactoryType;
   }

   /**
    * This method verify the object is null or Empty.
    *
    * @param object -> (By or String)
    * @return boolean -> Return true if object is null/empty
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean isObjectNullOrEmpty(final Object object)
            throws LocatorFactoryException, Exception
   {
      try
      {
         return object == null || object == "";
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method verify the object as List is null or Empty.
    *
    * @param locators -> (By or String)
    * @return boolean -> Return true if object is null/empty
    * @throws LocatorFactoryException ->Custom exception object for LocatorFactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean isListEmptyOrNull(final List<Object> locators)
            throws LocatorFactoryException, Exception
   {
      try
      {
         return locators == null || locators.size() == 0;
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method verify the object type as By.
    *
    * @param object -> (By or String)
    * @return boolean -> Return true if object type is By
    * @throws Exception -> Exception caller to handle the exception
    */
   private boolean isObjectBy(final Object object)
            throws Exception
   {
      try
      {
         return object instanceof By;
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method verify the String contains passed character.
    *
    * @param object    -> (By or String)
    * @param separator -> String such as (~)
    * @return boolean -> Return true if String contains passed character
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean doesObjectHasSeparator(final Object object,
            final String separator) throws LocatorFactoryException, Exception
   {
      try
      {
         boolean flag = false;
         if (((String) object).contains(separator))
         {
            flag = true;
         }
         else
         {
            throw new LocatorFactoryException("No separator in String");
         }
         return flag;
      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method verify the object type as String and contains seperator(~).
    *
    * @param object -> (By or String)
    * @return boolean -> Return true if object type is String
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean isObjectOfExpectedFormat(final Object object)
            throws LocatorFactoryException, Exception
   {
      try
      {
         boolean flag = false;
         if (object instanceof String)
         {
            if (doesObjectHasSeparator(object, separator))
            {
               flag = true;
            }
            else
            {
               throw new LocatorFactoryException("Invalid format string.");
            }
         }
         return flag;
      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method verify the object type and return WebElement.
    *
    * @param locator -> (By or String)
    * @return WebElement -> Return the WebElement
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private WebElement getWebElement(final Object locator)
            throws LocatorFactoryException, Exception
   {
      WebElement element = null;

      try
      {
         if (isObjectBy(locator))
         {
            element = (locatorFactoryType.findElementUsingBy((By) locator));
         }
         else if (isObjectOfExpectedFormat(locator))
         {
            element = (locatorFactoryType.findElementUsingString(
                     (String) locator));
         }
         else
         {
            throw new LocatorFactoryException(
                     "Passed argument is not a By or String type");
         }
         return element;
      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }

   }

   /**
    * This method verify the object type and return list of WebElements.
    *
    * @param object -> (By or String)
    * @return List<WebElement> -> Return the list of WebElements
    * @throws LocatorFactoryException ->Custom exception object for LocatorFactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private List<WebElement> getWebElements(final Object object)
            throws LocatorFactoryException, Exception
   {
      List<WebElement> elements = null;
      try
      {

         //Verify Object is By or String type
         if (isObjectBy(object))
         {
            elements = locatorFactoryType.findElementsUsingBy((By) object);
         }
         else if (isObjectOfExpectedFormat(object))
         {
            elements = locatorFactoryType.findElementsUsingString((String) object);
         }
         else
         {
            throw new LocatorFactoryException(
                     "Passing argument is not a By or String type");
         }
         //Verify element found
         if (elements.size() == 0)
         {
            throw new LocatorFactoryException(
                     "No such elements found for given locator");
         }
      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return elements;
   }

   /**
    * This method find the WebElement based on object type(By or String).
    *
    * @param object -> (By or String)
    * @return WebElement -> Return the WebElement
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public WebElement findElement(final Object object)
            throws LocatorFactoryException, Exception
   {
      try
      {
         //Verify Object is null or empty
         if (isObjectNullOrEmpty(object))
         {
            throw new LocatorFactoryException(
                     "Null/Empty argument passed");
         }

         //Verify Object is By or String type
         return getWebElement(object);

      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method find the list of WebElements from given a locator.
    *
    * @param object -> (By or String)
    * @return List<WebElement> -> Returns the list of WebElements
    * @throws LocatorFactoryException ->Custom exception object for LocatorFactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<WebElement> findElements(final Object object)
            throws LocatorFactoryException, Exception
   {
      try
      {
         //Verify Object is null or empty
         if (isObjectNullOrEmpty(object))
         {
            throw new LocatorFactoryException(
                     "Null/Empty argument passed");
         }

         //Verify Object is By or String type
         return getWebElements(object);
      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method find the list of WebElements from given list of objects.
    *
    * @param locators -> list of Object(By or String)
    * @return List<WebElement> -> Return the list of WebElements
    * @throws LocatorFactoryException ->Custom exception object for LocatorFactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<WebElement> findElement(final List<Object> locators)
            throws LocatorFactoryException, Exception
   {
      List<WebElement> elements = new ArrayList<WebElement>();
      try
      {
         // Verify for the List is empty or Null
         if (isListEmptyOrNull(locators))
         {
            throw new LocatorFactoryException("Null/Empty argument list passed");
         }

         //Get list of WebElement corresponding to given locator list
         /*Parallel stream is an efficient approach for processing and iterating
          * over a big list as it finishes processing 3 times faster than the
          * sequential stream
          */
         locators.parallelStream()
                  .map(WebElementFunction.unchecked(locator -> getWebElement(locator)))
                  .forEachOrdered(locator -> elements.add(locator));

      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         //log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return elements;
   }

   /**
    * This method find the list of WebElements from given list of locators (By or String). String must have specific
    * format(LocatorType~LocatorValue).
    *
    * @param locators -> list of Object(By or String)
    * @return List<ArrayList < WebElement>> -> Return the list of WebElements list
    * @throws LocatorFactoryException ->Custom exception object for Locatorfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<ArrayList<WebElement>> findElements(final List<Object> locators)
            throws LocatorFactoryException, Exception
   {
      List<ArrayList<WebElement>> listOfListElements = new
               ArrayList<ArrayList<WebElement>>();
      try
      {
         // Verify the List is empty or null
         if (isListEmptyOrNull(locators))
         {
            throw new LocatorFactoryException("Null/Empty argument list passed");
         }

         //Get list of WebElements list corresponding to given locator list
         /*Parallel stream is an efficient approach for processing and iterating
          * over a big list as it finishes processing 3 times faster than the
          * sequential stream
          */
         locators.parallelStream()
                  .map(WebElementFunction.unchecked(locatorList -> getWebElements(locatorList)))
                  .forEachOrdered(locatorList -> listOfListElements
                           .add((ArrayList<WebElement>) locatorList));

      }
      catch (LocatorFactoryException w)
      {
         log.error(w.getMessage(), w);
         throw new LocatorFactoryException(w.getMessage(), w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return listOfListElements;
   }

   /**
    * This single method interface takes unchecked exception and handle any kind of RuntimeException.
    *
    * @return Function -> Returns the interface function
    * @throws Exception -> Exception caller to handle the exception
    */
   @FunctionalInterface
   private interface WebElementFunction<T, R, E extends Throwable>
   {
      static <T, R, E extends Throwable> Function<T, R> unchecked(
               WebElementFunction<T, R, E> f)
      {
         return t -> {
            try
            {
               return f.apply(t);
            }
            catch (Throwable e)
            {
               throw new RuntimeException(e);
            }
         };
      }

      R apply(T t) throws E;
   }
}
