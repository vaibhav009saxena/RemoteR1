package com.skyplus.generic.utils.commonFunctions;

import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * This class contains all commom functions.
 */

public final class CommonFunction
{

   /**
    * Variable to hold the instance of CommonFunction.
    */
   private final CommonFunction instance = null;
   /**
    * Variable to hold the instance of CommonFunctionBy class.
    */
   private final CommonFunctionBy commonFunctionBy;
   /**
    * Variable to hold the instance of CommonFunctionWebElement class.
    */
   private final CommonFunctionWebElement commonFunctionWebElement;
   /**
    * Variable to hold the instance of CommonFunctionGeneric class.
    */
   private final CommonFunctionGeneric commonFunctionGeneric;
   /**
    * Variable to hold the instance of WaitFactory class.
    */
   private final WaitFactory waitFactory;
   /**
    * This is a Logger Instance Variable.
    */
   private final Logger log = LogManager.getLogger(CommonFunction.class);
   private final CommonFunctionPOJO commonFunctionPOJO;
   private final Validator validate;

   /**
    * This is a private constructor for the CommonFunction Class. This constructor accepts Web Driver, Time Units and
    * pollTime.
    */
   public CommonFunction(CommonFunctionPOJO commonFunctionPOJO, CommonFunctionGeneric commonFunctionGeneric,
            CommonFunctionWebElement commonFunctionWebElement, CommonFunctionBy commonFunctionBy, Validator validate)
   {
      this.commonFunctionPOJO = commonFunctionPOJO;

      this.waitFactory = commonFunctionPOJO.getWaitFactory();

      this.commonFunctionGeneric = commonFunctionGeneric;
      this.commonFunctionWebElement = commonFunctionWebElement;
      this.commonFunctionBy = commonFunctionBy;
      this.validate = validate;
   }

   /**
    * This method used to select a drop down value using Index.
    *
    * @param index   -> select dropdown index that needs to be selected
    * @param element -> (By or WebElement)
    * @return True -> once it selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean selectByIndex(final int index, final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.selectByIndexUsingBy(index, (By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.selectByIndexUsingWebElement(index,
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to select a drop down value using String value.
    *
    * @param value   -> String value that needs to be selected
    * @param element -> (By or WebElement)
    * @return True -> once it selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean selectByValue(final String value, final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.selectByValueUsingBy(value, (By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.selectByValueUsingWebElement(value,
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to select a drop down value using visible text.
    *
    * @param visibleText -> visible text that would be used to select from dropdown
    * @param element     -> (By or WebElement)
    * @return True -> once it selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean selectByVisbleText(final String visibleText,
            final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.selectByVisibleTextUsingBy(visibleText,
                     (By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.selectByVisibleTextUsingWebElement(
                     visibleText,
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to deselect a drop down value using Index.
    *
    * @param index   -> select dropdown index that needs to be deselected
    * @param element -> (By or WebElement)
    * @return True -> once it selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean deselectByIndex(final int index, final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.deselectByIndexUsingBy(index, (By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.deselectByIndexUsingWebElement(index,
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to deselect a drop down value using value.
    *
    * @param value   -> select dropdown index that needs to be deselected
    * @param element -> (By or WebElement)
    * @return True -> once it selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean deselectByValue(final String value, final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.deselectByValueUsingBy(value, (By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.deselectByValueUsingWebElement(value,
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to deselect a drop down value using visible text.
    *
    * @param visibleText -> select dropdown value that needs to be deselected by visible Text
    * @param element     -> (By or WebElement)
    * @return True -> once it selected
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean deselectByVisibleText(final String visibleText,
            final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.deselectByVisibleTextUsingBy(visibleText,
                     (By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.deselectByVisibleTextUsingWebElement(
                     visibleText,
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to deselectAll a drop down value .
    *
    * @param element -> (By or WebElement)
    * @return True -> when all elements are de-selected else false
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectAll(final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.deselectAllUsingBy((By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.deselectAllUsingWebElement(
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to getAllSelectedOption from the drop down value .
    *
    * @param element -> drop down element from which all selected options to display
    * @return allSelectedOptions -> all selected option from dropdown else null
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public List<WebElement> getAllSelectedOption(final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      List<WebElement> allSelectedOptions = null;
      try
      {
         if (element instanceof By)
         {
            allSelectedOptions = commonFunctionBy.getAllSelectedOptionUsingBy(
                     (By) element);
         }
         else if (element instanceof WebElement)
         {
            allSelectedOptions = commonFunctionWebElement
                     .getAllSelectedOptionUsingWebElement(
                              (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return allSelectedOptions;
   }

   /**
    * This method used to get First Selected Option from the drop down value .
    *
    * @param element -> drop down element from which all selected options to display
    * @return fisrtSelectedOption -> return the first selected option else null
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public WebElement getFirstSelectedOption(final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      WebElement fisrtSelectedOption = null;
      try
      {
         if (element instanceof By)
         {
            fisrtSelectedOption = commonFunctionBy.getFirstSelectedOptionUsingBy(
                     (By) element);
         }
         else if (element instanceof WebElement)
         {
            fisrtSelectedOption = commonFunctionWebElement
                     .getFirstSelectedOptionUsingWebElement(
                              (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return fisrtSelectedOption;
   }

   /**
    * This method used to getOption from the drop down value .
    *
    * @param element -> drop down element
    * @return allOptions -> return the all option in dropdown else null
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public List<WebElement> getOption(final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      List<WebElement> allOptions = null;
      try
      {
         if (element instanceof By)
         {
            allOptions = commonFunctionBy.getOptionUsingBy((By) element);
         }
         else if (element instanceof WebElement)
         {
            allOptions = commonFunctionWebElement.getOptionUsingWebElement(
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return allOptions;
   }

   /**
    * This method used to check if the select drodown has multiple selection option .
    *
    * @param element -> drop down element
    * @return flag -> return true if select dropdown allows multiple selection else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean isMultiple(final Object element)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.isMultipleUsingBy((By) element);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement.isMultipleUsingWebElement(
                     (WebElement) element);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to drag and drop element.
    *
    * @param sourceElement      -> element that need to be dragged i.e source
    * @param destinationElement -> location where it need to be dropped i.e destination
    * @return flag -> return true if select dropdown allows multiple selection else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean dragAndDrop(final Object sourceElement,
            final Object destinationElement)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (sourceElement instanceof By && destinationElement instanceof By)
         {
            flag = commonFunctionBy.dragAndDropUsingBy((By) sourceElement,
                     (By) destinationElement);
         }
         else if (sourceElement instanceof WebElement
                  && destinationElement instanceof WebElement)
         {
            flag = commonFunctionWebElement.dragAndDropUsingWebElement(
                     (WebElement) sourceElement, (WebElement) destinationElement);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to click Using Offset provided.
    *
    * @param locator -> element to click
    * @param xOffset -> x Coordinate of the locator
    * @param yOffset -> y Coordinate of the locator
    * @return flag -> return true if able to click on element using offset else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean clickUsingOffset(final Object locator, final int xOffset,
            final int yOffset)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.clickUsingOffsetUsingBy((By) locator, xOffset,
                     yOffset);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement.clickUsingOffsetUsingWebElement(
                     (WebElement) locator, xOffset,
                     yOffset);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to mouse Hower on the locator specified.
    *
    * @param locator -> element to hower
    * @return flag -> return true if able to mouse hower on the element
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean mouseHover(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.mouseHoverUsingBy((By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement.mouseHoverUsingWebElement(
                     (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method used to mouse Hower on the locator specified.
    *
    * @param locator -> element to move to
    * @return flag -> return true if able to move to element else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean movetoElement(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.movetoElementUsingBy((By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement.movetoElementUsingWebElement(
                     (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This movetoElementUsingBy method moves to the given element in X and Y. offset provided.
    *
    * @param locator -> element on which right click to be performed
    * @param xOffset -> x Coordinate of the locator
    * @param yOffset -> y Coordinate of the locator
    * @return flag -> return true if able to move to element else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean movetoElementUsingOffset(final Object locator,
            final int xOffset, final int yOffset)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.movetoElementUsingOffsetUsingBy((By) locator,
                     xOffset, yOffset);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement.movetoElementUsingOffsetWebElement(
                     (WebElement) locator, xOffset, yOffset);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This clickAndHoldSourceAndDropOnTarget method used to click and hold a soruce element and drops in the required
    * destination location.
    *
    * @param sourceElement      -> element to drag and drop
    * @param destinationElement -> location to drop element
    * @return flag -> return true if able to move to element else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean clickAndHoldSourceAndDropOnTarget(final Object sourceElement,
            final Object destinationElement)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (sourceElement instanceof By && destinationElement instanceof By)
         {
            flag = commonFunctionBy.clickAndHoldSourceAndDropOnTargetUsingBy(
                     (By) sourceElement, (By) destinationElement);
         }
         else if (sourceElement instanceof WebElement
                  && destinationElement instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .clickAndHoldSourceAndDropOnTargetUsingWebElement(
                              (WebElement) sourceElement, (WebElement) destinationElement);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This contextClick method used to right click on the element.
    *
    * @param locator -> element on which right click to be performed
    * @return flag -> return true if able to context click on element else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean contextClick(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.contextClickUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .contextClickUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This dragAndDropElementbyXandYOffset method used drag and drop element in the offset provided.
    *
    * @param element -> element to drag and drop
    * @param xOffset -> x offset of the locator
    * @param yOffset -> y offset of the locator
    * @return flag -> return true if able to context click on element else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean dragAndDropElementbyXandYOffset(final Object element,
            final int xOffset,
            final int yOffset)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (element instanceof By)
         {
            flag = commonFunctionBy.dragAndDropElementbyXandYOffsetUsingBy(
                     (By) element, xOffset, yOffset);
         }
         else if (element instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .dragAndDropElementbyXandYOffsetUsingWebElement(
                              (WebElement) element, xOffset, yOffset);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This isElementSelected method used to verify if the check box/radio button/drop down is selected.
    *
    * @param locator -> element which needs to be verified if the element is selected
    * @return flag -> return true if element is selected else false
    */
   public boolean isElementSelected(final Object locator)
            throws Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.isElementSelectedUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .isElementSelectedUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException("Check the argument passed is not Null/empty ."
                  + " It should be of type By/WebElement", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException("Unable to wait for the element looking for", w);
      }
      catch (Exception e)
      {
         throw e;
      }
      return flag;
   }

   /**
    * This method switches to the frame specified. frame can be specified in index, Name or ID, By/WebElement.
    *
    * @param frameObject -> frame to switch
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void switchToFrame(final Object frameObject)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (frameObject instanceof String)
         {
            commonFunctionGeneric.switchToFrameUsingIDorName((String) frameObject);
         }
         else if (frameObject instanceof Integer)
         {
            commonFunctionGeneric.switchToFrame((Integer) frameObject);
         }
         else if (frameObject instanceof WebElement)
         {
            commonFunctionWebElement.switchToFrameUsingWebElement(
                     (WebElement) frameObject);
         }
         else if (frameObject instanceof By)
         {
            commonFunctionBy.switchToFrameUsingBy((By) frameObject);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This isElementDisplayed method used to verify if the element is displayed.
    *
    * @param locator -> element which needs to be verified if the element is displayed in DOM
    * @return flag -> return true if element is selected else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean isElementDisplayed(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.isElementDisplayedUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .isElementDisplayedUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This getWindow method returns the window opened by selenium.
    *
    * @return flag -> return true if able to context click on element else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public String getWindow()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      String windowID = null;
      try
      {
         windowID = commonFunctionGeneric.getWindowHandle();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  c.getMessage(),
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return windowID;
   }

   /**
    * This isElementEnabled method used to verify if the element is enabled to perform action.
    *
    * @param locator -> element which needs to be verified if the element is selected
    * @return flag -> return true if element is selected else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean isElementEnabled(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.isElementEnabledUsingBy(
                     (By) locator);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .isElementEnabledUsingWebElement(
                              (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This getWindowHandles method returns all the window opened by selenium.
    *
    * @return allWindowID -> return the window id as a set<String> else null.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public Set<String> getAllWindows()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      Set<String> allWindowID = null;
      try
      {
         allWindowID = commonFunctionGeneric.getAllWindows();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  c.getMessage(),
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return allWindowID;
   }

   /**
    * This clickOnElement method clicks the element.
    *
    * @param locator -> element that needs to be clicked
    */
   public void clickOnElement(final Object locator) throws Exception
   {
      try
      {
         if (locator instanceof By)
         {
            commonFunctionBy.clickUsingBy(((By) locator));
         }
         else if (locator instanceof WebElement)
         {
            commonFunctionWebElement.clickUsingWebElement((WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         log.error("Check the argument passed is not Null/empty ."
                  + " It should be of type By/WebElement");
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
         throw e;
      }
   }

   /**
    * This getAllWindowsCountAndCompare method compares the actual number of windows count with the expected value.
    *
    * @param expectedCountOfID -> Number of Windows expected.
    * @return flag -> return true if count matches else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean getAllWindowsCountAndCompare(final Object expectedCountOfID)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (expectedCountOfID instanceof Integer)
         {
            flag = commonFunctionGeneric.getAllWindowsCountAndCompare(
                     (Integer) expectedCountOfID);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type Integer");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty .", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This clickElementUsingJavaScript method clicks the element.
    *
    * @param object -> element that needs to be clicked
    * @return
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean clickElementUsingJavaScript(final Object object)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (object instanceof By)
         {
            commonFunctionBy.clickUsingJavascriptWithBy(((By) object));
         }
         else if (object instanceof WebElement)
         {
            commonFunctionWebElement.clickUsingJavaScriptWithWebElement(
                     (WebElement) object);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }

      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty.", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element.", w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
       return false;
   }

   /**
    * The enterTextUsingJavaScript method enters the value in element.
    *
    * @param object      -> element that needs to be entered
    * @param textToEnter -> Text that needs to enter
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void enterTextUsingJavaScript(final Object object,
            final String textToEnter)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (object instanceof By)
         {
            commonFunctionBy.enterTextUsingJavaScriptUsingBy((By) object, textToEnter);
         }
         else if (object instanceof WebElement)
         {
            commonFunctionWebElement.enterTextUsingJavaScriptUsingWebElement(
                     (WebElement) object, textToEnter);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty.", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element.", w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method switches from the current window to the given WindowID. And returns true if it is switched successfull
    * else false.
    *
    * @param windowToSwitch ->WindowId as a string
    * @return flag -> return true if able to switch to the window provided else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean switchToWindow(final Object windowToSwitch)

            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (windowToSwitch instanceof String)
         {
            flag = commonFunctionGeneric.switchToWindow(
                     (String) windowToSwitch);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type String");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty .", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method is used to enter the text in the element provided.
    *
    * @param locator     -> element that needs to be clicked
    * @param textToEnter -> Text that needs to entered in the field
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void enterText(final Object locator, final String textToEnter)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (locator instanceof By)
         {
            commonFunctionBy.enterTextUsingBy(((By) locator), textToEnter);
         }
         else if (locator instanceof WebElement)
         {
            commonFunctionWebElement
                     .enterTextUsingWebElement(
                              (WebElement) locator, textToEnter);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type String",
                  c);
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
         throw e;
      }
   }

   /**
    * This method is used to clear the text in the element provided.
    *
    * @param locator -> element that needs to be clicked
    * @return flag -> True if text is cleared, else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean clearText(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.clearTextUsingBy(((By) locator));
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement.clearTextUsingWebElement(
                     (WebElement) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type String",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method is used to get text from the element provided.
    *
    * @param locator -> element that needs to be clicked
    * @return textFromElement -> returns the text retrieved from the element specified.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public String getTextFromElement(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      String textFromElement = null;
      try
      {
         if (locator instanceof By)
         {
            textFromElement = commonFunctionBy.getTextUsingBy(((By) locator));
         }
         else if (locator instanceof WebElement)
         {
            textFromElement = commonFunctionWebElement
                     .getTextUsingWebElement(
                              (WebElement) locator);

         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         validate.nullValidator(textFromElement);
      }
      return textFromElement;
   }

   /**
    * This method closes All the windows opened by Selenium.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void closeAllWindow()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.closeAllWindow();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Unable to close All the windows",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method switchToParentFrame switches from the current frame to parent frame.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void switchToParentFrame()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.switchToParentFrame();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Unable to switch to parent frame", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method is used to get the Text from element and Compare with the given text.
    *
    * @param locator      -> element that needs to be clicked
    * @param expectedText -> expected text from the element
    * @return flag -> true if the text matches, else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean getTextAndCompare(final Object locator,
            final String expectedText) throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.getTextAndCompareUsingBy(((By) locator),
                     expectedText);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .getTextAndCompareUsingWebElement(
                              (WebElement) locator, expectedText);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);

         throw new CommonFunctionException("Check the argument passed is not Null/empty ."
                  + " It should be of type By/WebElement", c);
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
         throw e;
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method switchToDefaultContext selects either the first frame on the page, or the main document when a page
    * contains iframes.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void switchToDefaultContext()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.switchToDefaultContext();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Unable to switch to default content",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This launches the URL specified.
    *
    * @param locator -> URL that needs to be launched
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void getURL(final Object locator)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (locator instanceof String)
         {
            commonFunctionGeneric.getURL(
                     (String) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
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
    * @return URL -> Returns the URL
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public String getCurrentURL()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      String uRL = null;
      try
      {
         uRL = commonFunctionGeneric.getCurrentURL();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Unable to get the current URL", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(uRL);
      }
      return uRL;
   }

   /**
    * This launches the URL specified.
    *
    * @param locator -> URL that needs to be launched
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void navigateToURL(final Object locator) throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (locator instanceof String)
         {
            commonFunctionGeneric.navigateToURL(
                     (String) locator);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
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
   }

   /**
    * This method compares the given URL with the current URL.
    *
    * @param expectedURL -> expected URL
    * @return URL -> Returns the URL
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public boolean getCurrentURLAndCompare(final String expectedURL)

            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (expectedURL instanceof String)
         {
            flag = commonFunctionGeneric.getCurrentURLAndCompareWithExpected(
                     expectedURL);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type String");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty .",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
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
    * This navigate back methods Move back a single "item" in the browser's.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    * @throws CommonFunctionException -> CommonFunctionException caller to handle the exception
    */
   public void navigateBack()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.navigateBack();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method fetches the title of the current page.
    *
    * @return pageTitle -> Returns the page title.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getTitleOfThePage() throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      String pageTitle = null;
      try
      {
         pageTitle = commonFunctionGeneric.getTitleOfThePage();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException("Unable to get the current URL", c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException("Failed to fetch title",
                  w);
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
    * This method highlights the given locator using the JavaScript command.
    *
    * @param object -> locator that needs to be highlighted.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void highlightElement(final Object object)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (object instanceof By)
         {
            commonFunctionBy.highlightElementUsingBy(((By) object));
         }
         else if (object instanceof WebElement)
         {
            commonFunctionWebElement.highlightElementUsingWebElement(
                     (WebElement) object);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }

      }
      catch (CommonFunctionException c)
      {
         log.error("Unable to highlight the given element", c);
         throw new CommonFunctionException(c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method scroll the screen till reach the element using the JavaScript command.
    *
    * @param object -> Scroll the screen to that element.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void scrollInToElement(final Object object)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         if (object instanceof By)
         {
            commonFunctionBy.scrollToElementUsingBy(((By) object));
         }
         else if (object instanceof WebElement)
         {
            commonFunctionWebElement.scrollToElementUsingWebElement(
                     (WebElement) object);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error("Unable to scroll till the given element", c);
         throw new CommonFunctionException(c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element.", w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method fetches the property/attribute value of the given attribute and locator combination.
    *
    * @param locator   -> locator for which attribute value has to be fetched.
    * @param attribute -> attribute for which value needs to be retrieved.
    * @return attributeValue -> Returns the attribute value of the object specified.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getAttributeValue(final Object locator, final String attribute)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      String attributeValue = null;
      try
      {
         if (locator instanceof By)
         {
            attributeValue = commonFunctionBy.getAttributeValueUsingBy(
                     (By) locator, attribute);
         }
         else if (locator instanceof WebElement)
         {
            attributeValue = commonFunctionWebElement
                     .getAttributeValueUsingWebElement((WebElement) locator,
                              attribute);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(attributeValue);
      }
      return attributeValue;
   }

   /**
    * This navigate forward methods Move a single "item" forward in the browser's history. Does nothing if we are on the
    * latest page viewed.
    *
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void navigateForward()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.navigateForward();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method fetches the property/attribute value of the given attribute and compares with expected.
    *
    * @param locator       -> locator for which attribute value has to be fetched.
    * @param attribute     -> attribute for which value needs to be retrieved.
    * @param expectedValue -> expected Value for the given attribute
    * @return flag -> Returns true if the value matches else false
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAttributeValueAndCompare(final Object locator,
            final String attribute, final String expectedValue)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.getAttributeValueAndCompareUsingBy(
                     (By) locator,
                     attribute, expectedValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .getAttributeValueAndCompareUsingWebElement((WebElement) locator,
                              attribute, expectedValue);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
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
    * This method fetches the property/attribute value of the given attribute and verifies if the actual value contains
    * expected.
    *
    * @param locator       -> locator for which attribute value has to be fetched.
    * @param attribute     -> attribute for which value needs to be retrieved.
    * @param expectedValue -> expected Value for the given attribute
    * @return flag -> Returns true if the Actual value contains expectedValue else false
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAttributeValueAndVerifyitContains(final Object locator,
            final String attribute, final String expectedValue)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.getAttributeValueAndVerifyitContainsUsingBy(
                     (By) locator,
                     attribute, expectedValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .getAttributeValueAndVerifyitContainsUsingWebElement(
                              (WebElement) locator,
                              attribute, expectedValue);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
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
    * This method fetches the CSS value of the given attribute and locator combination.
    *
    * @param locator   -> locator for which CSS value has to be fetched.
    * @param attribute -> attribute for which CSS value needs to be retrieved.
    * @return cssProperyValue -> Returns property/attribute value of the attribute specified. Else null
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getCSSValueOfProperty(final Object locator,
            final String attribute)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      String attributeValue = null;
      try
      {
         if (locator instanceof By)
         {
            attributeValue = commonFunctionBy.getCSSValueOfPropertyUsingBy(
                     (By) locator, attribute);
         }
         else if (locator instanceof WebElement)
         {
            attributeValue = commonFunctionWebElement
                     .getCSSValueOfPropertyUsingWebElement((WebElement) locator,
                              attributeValue);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(attributeValue);
      }
      return attributeValue;
   }

   /**
    * This refreshpage methods Refresh the current page.
    *
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void refreshPage()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.refreshPage();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method fetches the CSS value and compare with of the given attribute and locator combination.
    *
    * @param locator       -> locator for which CSS value has to be fetched.
    * @param attribute     -> attribute for which CSS value needs to be retrieved.
    * @param expectedValue -> expected property value of CSS property.
    * @return flag -> Returns true if value matches else false.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCSSValueOfPropertyAndCompare(final Object locator,
            final String attribute, final String expectedValue)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.getCSSValueOfPropertyAndCompareUsingBy(
                     (By) locator,
                     attribute, expectedValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .getCSSValueOfPropertyAndCompareUsingWebElement(
                              (WebElement) locator,
                              attribute, expectedValue);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
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
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void closeBrowser()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.closeBrowser();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method fetches the CSS value and checks if the value actual value contain the expected value.
    *
    * @param locator       -> locator for which CSS value has to be fetched.
    * @param attribute     -> attribute for which CSS value needs to be retrieved.
    * @param expectedValue -> expected property value of CSS property to check if it is actual.
    * @return flag -> Returns true if actual value contains expected. else false.
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCSSValueOfPropertyAndContains(final Object locator,
            final String attribute, final String expectedValue)
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (locator instanceof By)
         {
            flag = commonFunctionBy.getCSSValueOfPropertyAndContainsUsingBy(
                     (By) locator,
                     attribute, expectedValue);
         }
         else if (locator instanceof WebElement)
         {
            flag = commonFunctionWebElement
                     .getCSSValueOfPropertyAndContainsUsingWebElement(
                              (WebElement) locator,
                              attribute, expectedValue);
         }
         else
         {
            throw new CommonFunctionException(
                     "Check argument passed is not Null/empty/Invalid."
                              + " It should be of type By/WebElement");
         }
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
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
    * This method Closes all the windows which were opened in that selenium session.
    *
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void quiteAllWindow()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      try
      {
         commonFunctionGeneric.quitAllWindow();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
                  w);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method click on the 'OK' button of the alert.
    *
    * @return true, if successful
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean acceptAlert()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         flag = commonFunctionGeneric.acceptAlert();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * This method click on the 'Cancel' button of the alert.
    *
    * @return true, if successful
    * @throws CommonFunctionException ->Custom exception object for CommonFunction caller to handle exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean dismissAlert()
            throws CommonFunctionException, WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         flag = commonFunctionGeneric.dismissAlert();
      }
      catch (CommonFunctionException c)
      {
         log.error(c.getMessage(), c);
         throw new CommonFunctionException(
                  "Check the argument passed is not Null/empty ."
                           + " It should be of type By/WebElement",
                  c);
      }
      catch (WaitFactoryUseException w)
      {
         log.error(w.getMessage(), w);
         throw new WaitFactoryUseException(
                  "Unable to wait for the element looking for",
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
    * Perform keyboard key action
    *
    * @param keyToPress Keyboard key to be pressed
    */
   public void performAction(Keys keyToPress)
   {
      Actions actions = new Actions(commonFunctionPOJO.getDriver());

      actions.keyDown(keyToPress)
               .keyUp(keyToPress)
               .perform();
   }

   /**
    * This method is used to get the Text from element and Compare with the given text.
    *
    * @param actualText   -> text to compare against
    * @param expectedText -> expected text from to be compared
    * @return flag -> true if the text matches, else false
    */
   public boolean compareText(final String actualText,
            final String expectedText) throws Exception
   {
      boolean flag = false;
      try
      {
         flag = commonFunctionGeneric.compareText(actualText,
                  expectedText);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw e;
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method will return jsonobject after parsing json file
    * @param filePath path of json file
    * @return jsonobject after parsing
    */
   public JSONObject getJsonObjectByParsing(String filePath){
      JSONParser jsonperser = new JSONParser();
      FileReader reader = null;
      JSONObject jsonobject = null;
      try {
         reader = new FileReader(filePath);
         Object obj = jsonperser.parse(reader);
         jsonobject = (JSONObject) obj;

      } catch (FileNotFoundException e) {
         log.error("File not found in given path : "+filePath);
         e.printStackTrace();
      }
     return jsonobject;
   }

   /**
    * This method is to covert json object to Map
    *
    * @param jsonObject
    * @return map as a key value pair
    */
   public Map<String, String> convertJsonObjToMap(JSONObject jsonObject) {
      Map<String, String> map = new HashMap<String, String>();
      Iterator<String> keysItr = jsonObject.keySet().iterator();
      while (keysItr.hasNext()) {
         String key = keysItr.next();
         map.put(key, (String) jsonObject.get(key));
      }
      return map;
   }

}