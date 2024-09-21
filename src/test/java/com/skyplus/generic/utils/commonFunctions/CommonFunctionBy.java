package com.skyplus.generic.utils.commonFunctions;

import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * This class contains the CommonFunctionBy.
 */

public final class CommonFunctionBy
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
   private final Logger log = LogManager.getLogger(CommonFunctionBy.class);
   /**
    * This is instance of Actions class.
    */
   private final Actions actions;
   /**
    * This is instance for the CommonFunctionGeneric class.
    */
   private final CommonFunctionGeneric commonFunctionGeneric;
   private final Validator validate;

   /**
    * This Constructor initializes the WaitFactory for the CommonFunctionBy Class.
    */
   public CommonFunctionBy(CommonFunctionPOJO commonFunctionPOJO, CommonFunctionGeneric commonFunctionGeneric,
            Validator validate)
   {
      this.commonFunctiondriver = commonFunctionPOJO.getDriver();
      this.actions = new Actions(commonFunctiondriver);
      this.commonFunctionGeneric = commonFunctionGeneric;
      this.waitFactory = commonFunctionPOJO.getWaitFactory();
      this.validate = validate;
   }

   /**
    * This selectByIndexUsingBy methods selects the value from the dropdown based on the index specified.
    *
    * @param index   -> index of the option to be selected from select dropdown
    * @param element -> select element locator
    * @return flag -> True if the specified index value is selected, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean selectByIndexUsingBy(final int index, final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.selectByIndex(index);
            int actualValue = Integer.parseInt((getFirstSelectedOptionUsingBy(
                     element).getAttribute("index")));
            if (actualValue == index)
            {
               log.info("Selected the matched index");
               flag = true;
            }
         }
         else
         {
            throw new Exception(
                     "Unable to select based on by Index provided");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This selectByValueUsingBy methods selects the value from the dropdown based on the value specified.
    *
    * @param value   -> drop down value that needs to be selected
    * @param element -> select element locator
    * @return flag -> True if the specified value is selected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean selectByValueUsingBy(final String value, final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.selectByValue(value);
            String expectedValue = getFirstSelectedOptionUsingBy(element)
                     .getAttribute("value");
            if (expectedValue.equals(value))
            {
               log.info("Selected the value specified");
               flag = true;
            }
            else
            {
               log.info("Expected Value =" + value + "Selected Value ="
                        + expectedValue);
            }
         }
         else
         {
            throw new Exception(
                     "unable to select based on Value provided");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This selectByVisibleTextUsingBy methods selects all options that display text matching the argument specified.
    *
    * @param visibleTextToSelect -> drop down value that needs to be selected
    * @param element             -> select element locator
    * @return flag -> True if the specified value is selected from dropdown,else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean selectByVisibleTextUsingBy(final String visibleTextToSelect,
            final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.selectByVisibleText(visibleTextToSelect);
            String actualValue = getFirstSelectedOptionUsingBy(element).getText();
            if (visibleTextToSelect.equals(actualValue))
            {
               log.info(
                        "Selected the drop down based on the visible text Specified "
                                 + visibleTextToSelect);
               flag = true;
            }
            else
            {
               log.info(
                        "Unable to select the dropdown based on the "
                                 + "visibleText Specified");
            }
         }
         else
         {
            throw new Exception(
                     "Unable to select based on visible text");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This deSelectByIndexUsingBy methods deselects the value from the dropdown based on the index specified.
    *
    * @param index   -> index of the option to be deselected from select dropdown
    * @param element -> select element locator
    * @return flag -> True if the specified index value is deselected, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectByIndexUsingBy(final int index, final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.deselectByIndex(index);
            flag = !(isElementSelectedUsingBy(element));
         }
         else
         {
            throw new Exception(
                     "Unable to deselect based on index provided");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This deselectByValueUsingBy methods deselects the value from the dropdown based on the value specified.
    *
    * @param value   -> drop down value that needs to be deselected
    * @param element -> select element locator
    * @return flag -> True if the specified value is deselected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectByValueUsingBy(final String value, final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.deselectByValue(value);
            flag = !(isElementSelectedUsingBy(element));
         }
         else
         {
            throw new Exception(
                     "Unable to deselect based on value");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This deselectByVisibleTextUsingBy methods deselects all options that display text matching the argument
    * specified.
    *
    * @param textToBeDeSelected -> drop down value that needs to be deselected
    * @param element            -> select element locator
    * @return flag -> True if the specified value is deselected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectByVisibleTextUsingBy(final String textToBeDeSelected,
            final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.deselectByVisibleText(textToBeDeSelected);
            flag = !(isElementSelectedUsingBy(element));
         }
         else
         {
            throw new Exception(
                     "unable to deselect based on visible text");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This deSelectAll methods deselects all selected options from the select dropdown.
    *
    * @param element -> select element locator
    * @return flag -> True if the specified value is deselected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectAllUsingBy(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.deselectAll();
            flag = !(isElementSelectedUsingBy(element));
         }
         else
         {
            throw new Exception(
                     "Unable to deselect all options");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This getAllSelectedOptionUsingBy methods returns all selected options from the select element specified.
    *
    * @param element -> select element locator
    * @return allSelectedElement -> returns the list of the selected element from the dropdown
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<WebElement> getAllSelectedOptionUsingBy(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      List<WebElement> allSelectedElement = null;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            allSelectedElement = select.getAllSelectedOptions();
         }
         else
         {
            throw new Exception(
                     "Unable to get all the selected options");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
         Validator.nullValidatorForListOfObject(allSelectedElement);
      }
      return allSelectedElement;
   }

   /**
    * This getFirstSelectedOptionUsingBy methods returns first selected options from the select element specified.
    *
    * @param element -> select element locator
    * @return firstSelectedElement -> returns the first element from the dropdown
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public WebElement getFirstSelectedOptionUsingBy(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      WebElement firstSelectedElement = null;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            firstSelectedElement = select.getFirstSelectedOption();
         }
         else
         {
            throw new Exception(
                     "Unable get the first selected options");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
         this.validate.nullValidator(firstSelectedElement);
      }
      return firstSelectedElement;
   }

   /**
    * This getOptionsUsingBy methods returns all options from the select element specified.
    *
    * @param element -> select element locator
    * @return allOptionsInSelectElement -> returns the list of all element from the dropdown
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<WebElement> getOptionUsingBy(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      List<WebElement> allOptionsInSelectElement = null;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            allOptionsInSelectElement = select.getOptions();
         }
         else
         {
            throw new Exception(
                     "Unable to getoptions");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
         Validator.nullValidatorForListOfObject(allOptionsInSelectElement);
      }
      return allOptionsInSelectElement;
   }

   /**
    * This isMultipleUsingBy checks if select element support selecting multiple options at the same time? This is done
    * by checking the value of the "multiple" attribute.
    *
    * @param element -> select element locator
    * @return flag -> True if multiple attribute exists for select element, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isMultipleUsingBy(final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      Select select =
               waitForElementToBePresentVisibleAndClikable(
                        element);
      try
      {
         if (select != null)
         {
            select.isMultiple();
         }
         else
         {
            throw new Exception(
                     "Unable to locate the visiblility of the element specified");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
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
    * This dragAndDropUsingBy drags element and drop at the location specified.
    *
    * @param sourceElement      -> element to drag
    * @param destinationElement -> location to drop
    * @return flag -> True if is successfully drag and dropped element else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean dragAndDropUsingBy(final By sourceElement,
            final By destinationElement)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(sourceElement)
                  && verifyWaitConditionsPresentAndVisibility(destinationElement))
         {
            actions.dragAndDrop(commonFunctiondriver.findElement(sourceElement),
                              commonFunctiondriver.findElement(destinationElement))
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to drag and drop the source element in the destination");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the visibility of the element or its "
                           + "selection state is true even before the selections");
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
    * This clickUsingOffsetUsingBy method click on the element using the offset value.
    *
    * @param locator -> element to click
    * @param xOffset -> x Coordinate of the locator
    * @param yOffset -> y Coordinate of the locator
    * @return flag -> True if is successfully drag and dropped element else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean clickUsingOffsetUsingBy(final By locator, final int xOffset,
            final int yOffset) throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(commonFunctiondriver.findElement(locator),
                              xOffset, yOffset).build()
                     .perform();
            actions.click().perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to move to element and click");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the visibility of the element or its ");
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
    * This mouseHowerUsingBy method to mouse hower on element.
    *
    * @param locator -> element to hower
    * @return flag -> True if is successfully mouse Hover on the element
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean mouseHoverUsingBy(final By locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(commonFunctiondriver.findElement(locator))
                     .build()
                     .perform();
            actions.click().perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to mouse hower on the element");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the visibility of element ");
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
    * This movetoElementUsingBy method to move to element.
    *
    * @param locator -> element to move to
    * @return flag -> True if is successfully mouse Hover on the element
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean movetoElementUsingBy(final By locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(commonFunctiondriver.findElement(locator))
                     .release()
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to move to element");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility of the element specified");
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
    * This movetoElementUsingBy method moves to the given element in X and Y offset provided.
    *
    * @param locator -> element on which right click to be performed
    * @param xOffset -> x Coordinate of the locator
    * @param yOffset -> y Coordinate of the locator
    * @return flag -> True if right click is successful
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean movetoElementUsingOffsetUsingBy(
            final By locator, final int xOffset, final int yOffset)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(commonFunctiondriver.findElement(locator),
                              xOffset, yOffset)
                     .release()
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to move to element using the coordinates specified");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility of the element specified");
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
    * This clickAndHoldSourceAndDropOnTargetUsingBy method click hold and drag and drops source element in destination
    * location.
    *
    * @param soruceElement      -> element to drag and drop
    * @param destinationElement -> location to drop element
    * @return flag -> True if is successfully drag and drop source to the destination
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean clickAndHoldSourceAndDropOnTargetUsingBy(
            final By soruceElement, final By destinationElement)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(soruceElement)
                  && verifyWaitConditionsPresentAndVisibility(
                  destinationElement))
         {
            actions.clickAndHold(commonFunctiondriver.findElement(soruceElement))
                     .moveToElement(commonFunctiondriver.findElement(
                              destinationElement))
                     .release()
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to Click,Hold and Drag and drop the element ");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error("Unable to locate the visiblility of the element specified");
         log.error(e.getMessage(), e);
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
    * This ContextClickUsingBy method does a Right Click on element.
    *
    * @param locator -> element on which right click to be performed
    * @return flag -> True if right click is successful
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean contextClickUsingBy(
            final By locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.contextClick(commonFunctiondriver.findElement(locator))
                     .release()
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new Exception(
                     "Unable to right click on the element");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility of the element specified ");
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
    * This dragAndDropElementbyXandYOffsetUsingBy method drags and drops source element in X and Y Offset.
    *
    * @param sourceElement -> element to drag and drop
    * @param xOffset       -> x offset of the locator
    * @param yOffset       -> y offset of the locator
    * @return flag -> True if is successfully drag and drop source to the destination
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean dragAndDropElementbyXandYOffsetUsingBy(
            final By sourceElement,
            final int xOffset,
            final int yOffset)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      WebElement source = commonFunctiondriver.findElement(sourceElement);
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(sourceElement))
         {
            actions.dragAndDropBy(source, xOffset, yOffset)
                     .build()
                     .perform();
            Point point = source.getLocation();
            int xCoordinate = point.getX();
            int yCoordinate = point.getY();
            if (xCoordinate == xOffset && yCoordinate == yOffset)
            {
               flag = true;
            }
         }
         else
         {
            throw new Exception(
                     "Unable to Click,Hold and Drag and drop the element ");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error("Unable to locate the visiblility of the element specified");
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
    * This isElementSelectedUsingBy method return true or false if the given element selection state is selected or
    * not.
    *
    * @param element -> element which needs to be verified for selection
    * @return flag -> True if is provided element is selected false otherwise.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isElementSelectedUsingBy(final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(element))
         {
            flag = commonFunctiondriver.findElement(element).isSelected();
         }
         else
         {
            throw new Exception(
                     "Unable to locate the visiblility of the element specified");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the visibility of the element or it's selection");
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
    * This method to waits for Visibility of element, Presence of element and if it is click able and returns the select
    * object.
    *
    * @param element -> element which need to satisfy the condion
    * @return flag -> True if is provided element matches all condition else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private Select waitForElementToBePresentVisibleAndClikable(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      WebElement webObject;
      Select select = null;
      try
      {
         if (waitFactory.visibilityOf(element)
                  &&
                  waitFactory.presenceOfElementLocated(element)
                  &&
                  waitFactory.elementToBeClickable(element))
         {
            webObject = commonFunctiondriver.findElement(element);
            select = new Select(webObject);
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element "
                           + "specified or it is not clickable");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return select;
   }

   /**
    * This method to waits for Visibility of element, Presence of element and return true if able to find the element
    * else false.
    *
    * @param element -> element which need to satisfy the condition
    * @return flag -> True if is provided element matches all condition else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean waitForElementToBePresentAndVisibleOfelement(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (waitFactory.visibilityOf(element)
                  &&
                  waitFactory.presenceOfElementLocated(element))
         {
            flag = true;
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to locate the visiblility/presence of the element ");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }

   /**
    * This isElementDisplayedUsingBy method return true or false if the given element is displayed state or not.
    *
    * @param element -> element to be verified for selection
    * @return flag -> True if is provided element is displayed false otherwise.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isElementDisplayedUsingBy(final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         flag = commonFunctiondriver.findElement(element).isDisplayed();
         log.info((flag) ? "Element is displayed"
                  : "Element is not displayed");
      }
      catch (Exception e)
      {
         log.error("Unable to locate the visiblility of the element specified");
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
    * This isElementEnabledUsingWebBy method return true or false if the given element is enabled to perform some
    * action.
    *
    * @param element -> element to be verified for enabled state
    * @return flag -> True if is provided element is selected false otherwise.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isElementEnabledUsingBy(final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (waitFactory.visibilityOf(element))
         {
            flag = commonFunctiondriver.findElement(element).isEnabled();
            log.info((flag) ? "Element is enabled"
                     : "Element is not enabled");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to locate the visiblility of the element specified");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the visibility of the element or it's enabled");
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
    * This method switches to the frame specified.
    *
    * @param frameElement -> frame to switch to
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void switchToFrameUsingBy(final By frameElement)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         if (waitFactory.frameToBeAvailableAndSwitchToIt(frameElement))
         {
            log.info("Switched to Frame " + frameElement + " Specified");
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
    * This method fetches the property/attribute value of the given attribute and locator combination.
    *
    * @param locator   -> locator for which attribute value has to be fetched.
    * @param attribute -> attribute for which value needs to be retrieved.
    * @return attributeValue -> Returns property/attribute value of the attribute specified. Else null
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getAttributeValueUsingBy(final By locator,
            final String attribute)
            throws WaitFactoryUseException, Exception
   {
      String attributeValue = null;
      try
      {
         if (waitFactory.presenceOfElementLocated(locator) && waitFactory
                  .attributeToBeNotEmpty(locator, attribute))
         {
            attributeValue = commonFunctiondriver.findElement(locator)
                     .getAttribute(
                              attribute);
            log.info("Attribute " + attribute + " value for the locator " + locator
                     + "is " + attributeValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to Wait for presence of element and attributetobeNotempty");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to to retrieve the attribute value of given "
                           + "locator&attribute combination",
                  e);
         throw new Exception(e);
      }
      return attributeValue;
   }

   /**
    * This method fetches the property/attribute value of the given attribute and compares with expected.
    *
    * @param locator       -> locator for which attribute value has to be fetched.
    * @param attribute     -> attribute for which value needs to be retrieved.
    * @param expectedValue -> expected Value for the given attribute
    * @return flag -> Returns true if the value matches else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAttributeValueAndCompareUsingBy(final By locator,
            final String attribute,
            final String expectedValue)
            throws WaitFactoryUseException, Exception
   {
      String attributeValue = null;
      boolean flag = false;
      try
      {
         attributeValue = getAttributeValueUsingBy(locator, attribute);
         log.info("Attribute " + attribute + " value for the locator " + locator
                  + "is " + attributeValue);
         flag = attributeValue.equals(expectedValue);

      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to to retrieve the attribute value of given "
                           + "locator&attribute combination",
                  e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method clicks on the given element if it is visible,displayed and clickable.
    *
    * @param objectTobeClicked -> Webelement that needs to be clicked
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void clickUsingBy(final By objectTobeClicked)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         if (waitForElementToBePresentAndVisibleOfelement(
                  objectTobeClicked))
         {
            commonFunctiondriver.findElement(objectTobeClicked).click();
            log.info("Clicked on the WebElement provided");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for the Presence/Visibility "
                              + "of element and also it is not click able ");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for the Presence/Visibility "
                           + "of element and also it is not click able");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to click on the element");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method clicks on the given element with help of Javascript if it is visible,displayed and clickable.
    *
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void clickUsingJavascriptWithBy(final By object)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         WebElement objectTobeClicked = commonFunctiondriver.findElement(object);
         if (waitFactory.elementToBeClickable(objectTobeClicked) &&
                  waitFactory.presenceOfElementLocated(objectTobeClicked))
         {
            ((JavascriptExecutor) commonFunctiondriver).executeScript(
                     "arguments[0].click();", objectTobeClicked);
            log.info("Clicked on the WebElement provided");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for visibility or presence of element"
                              + " exception is thrown");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for visibility or presence of element "
                           + "exception is thrown ");
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to click on the element");
         throw new Exception(e);
      }

   }

   /**
    * This method enters value on the given element using the Java Script
    *
    * @param textToEnter -> text that needs to enter
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void enterTextUsingJavaScriptUsingBy(
            final By object,
            final String textToEnter)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         WebElement objectTobeEntered = commonFunctiondriver.findElement(object);
         if (waitFactory.visibilityOf(objectTobeEntered) &&
                  waitFactory.presenceOfElementLocated(objectTobeEntered))
         {
            ((JavascriptExecutor) commonFunctiondriver).executeScript(
                     "arguments[0].value='" + textToEnter + "';", objectTobeEntered);
            log.info("Entered text on the WebElement provided");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for visibility of webelement"
                              + " exception is thrown");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for Java script execution or a JS "
                           + "exception is thrown ");
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to enter the text on the element");
         throw new Exception(e);
      }
   }

   /**
    * This method highlights the given locator using the JavaScript command.
    *
    * @param object -> locator that needs to be highlighted.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void highlightElementUsingBy(final By object)
            throws WaitFactoryUseException, Exception
   {
      JavascriptExecutor js = (JavascriptExecutor) commonFunctiondriver;
      try
      {
         WebElement objectTobeClicked = commonFunctiondriver.findElement(object);
         if (waitFactory.visibilityOf(objectTobeClicked) &&
                  waitFactory.presenceOfElementLocated(objectTobeClicked))
         {
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                     objectTobeClicked, "border: 3px solid red;");
            log.info("provided loacator is highlighted ");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for visibility or presence of element "
                              + "exception is thrown.");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for visibility or presence of element "
                           + "exception is thrown.");
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error("Unable to highlight the element provided", e);
         throw new Exception(e);
      }
   }

   /**
    * This method scroll into the given locator using the JavaScript command.
    *
    * @param object -> locator that needs to be highlighted.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void scrollToElementUsingBy(final By object)
            throws WaitFactoryUseException, Exception
   {
      JavascriptExecutor js = (JavascriptExecutor) commonFunctiondriver;
      try
      {
         WebElement objectTobeClicked = commonFunctiondriver.findElement(object);
         if (waitFactory.visibilityOf(objectTobeClicked) &&
                  waitFactory.presenceOfElementLocated(objectTobeClicked))
         {
            js.executeScript("arguments[0].scrollIntoView(true);", objectTobeClicked);
            log.info("Scrolled into the provided loacator ");
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for visibility or presence of element "
                              + "exception is thrown.");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for visibility or presence of element "
                           + "exception is thrown.");
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error("Unable to scrool into the provided element", e);
         throw new Exception(e);
      }
   }

   /**
    * This method enters the text in the textBox element specified.
    *
    * @param element -> textbox in which the text should be entered
    * @return flag -> True if text is cleared, else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean clearTextUsingBy(final By element)
            throws WaitFactoryUseException, Exception
   {
      String elementText;
      boolean flag = false;
      try
      {
         if (waitForElementToBePresentAndVisibleOfelement(element))
         {
            commonFunctiondriver.findElement(element).clear();
            elementText = commonFunctiondriver.findElement(element).getAttribute("value");
            if (elementText.isEmpty())
            {
               flag = true;
            }
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for Java script execution "
                              + "or a JS exception is thrown");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for Java script execution "
                           + "or a JS exception is thrown ");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to clear the text");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(flag);
      }
      return flag;
   }

   /**
    * This method enters the text in the textBox element specified.
    *
    * @param element     -> textbox in which the text should be entered
    * @param textToEnter -> Text that need to be entered in the textBox
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void enterTextUsingBy(final By element, final String textToEnter)
            throws WaitFactoryUseException, Exception
   {
      try
      {
         if (waitForElementToBePresentAndVisibleOfelement(element))
         {
            commonFunctiondriver.findElement(element).sendKeys(textToEnter);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for Java script execution "
                              + "or a JS exception is thrown");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for Java script execution "
                           + "or a JS exception is thrown ");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable enter the text");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
   }

   /**
    * This method gets the text in the textBox element specified.
    *
    * @param element -> textbox in which the text should be entered
    * @return textPresentInGivenElement -> returns the text present in the given element
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getTextUsingBy(final By element)
            throws WaitFactoryUseException, Exception
   {
      String textPresentInGivenElement = null;
      try
      {
         if (waitForElementToBePresentAndVisibleOfelement(element))
         {
            textPresentInGivenElement = commonFunctiondriver.findElement(element)
                     .getText();
            log.info("Actual text retrieved from the element specified ="
                     + textPresentInGivenElement);
            if (textPresentInGivenElement.isEmpty()
                     || textPresentInGivenElement == null)
            {
               throw new Exception(
                        "Unable to retrieve the text or the text is Null/Empty");
            }
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for presence of element "
                              + "or the element is not visible");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for presence of element "
                           + "or the element is not visible");
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
         this.validate.nullValidator(textPresentInGivenElement);
      }
      return textPresentInGivenElement;
   }

   /**
    * This method gets the text in the textBox element specified.
    *
    * @param element      -> textbox in which the text should be entered
    * @param expectedText -> expected text
    * @return flag -> True if actual and expected text are same, else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getTextAndCompareUsingBy(final By element,
            final String expectedText)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      String textPresentInGivenElement = getTextUsingBy(element);
      try
      {
         if (waitForElementToBePresentAndVisibleOfelement(element))
         {
            textPresentInGivenElement = getTextUsingBy(element);
            flag = commonFunctionGeneric.compareText(textPresentInGivenElement,
                     expectedText);
            log.info("Able to get actualtext" + textPresentInGivenElement
                     + " and Compare with the expected Text " + expectedText);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to wait for presence of element "
                              + "or the element is not visible");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(
                  "Unable to wait for presence of element "
                           + "or the element is not visible");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to get the text present in the element");
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      finally
      {
         this.validate.nullValidator(flag);
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
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAttributeValueAndVerifyitContainsUsingBy(final By locator,
            final String attribute,
            final String expectedValue)
            throws WaitFactoryUseException, Exception
   {
      String attributeValue = null;
      boolean flag = false;
      try
      {
         attributeValue = getAttributeValueUsingBy(locator, attribute);
         log.info("Attribute " + attribute + " value for the locator " + locator
                  + "is " + attributeValue);
         flag = attributeValue.contains(expectedValue);

      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to to retrieve the attribute value of given locator"
                           + "&attribute combination",
                  e);
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
    * @param locator      -> locator for which CSS value has to be fetched.
    * @param propertyName -> propertyName for which CSS value needs to be retrieved.
    * @return cssProperyValue -> Returns property/attribute value of the attribute specified. Else null
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getCSSValueOfPropertyUsingBy(final By locator,
            final String propertyName)
            throws WaitFactoryUseException, Exception
   {
      String cssProperyValue = null;
      try
      {
         if (waitFactory.presenceOfElementLocated(locator) && waitFactory
                  .attributeToBeNotEmpty(locator, propertyName))
         {
            cssProperyValue = commonFunctiondriver.findElement(locator)
                     .getCssValue(
                              propertyName);
            log.info("Property " + propertyName + " value for the locator "
                     + locator
                     + "is " + cssProperyValue);
         }
         else
         {
            throw new WaitFactoryUseException(
                     "Unable to Wait for presence of element and attributetobe"
                              + "Notempty");
         }
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to to retrieve the property value of given "
                           + "locator&property combination",
                  e);
         throw new Exception(e);
      }
      return cssProperyValue;
   }

   /**
    * This method fetches the CSS value and checks if the value actual value contain the expected value.
    *
    * @param locator                      -> locator for which CSS value has to be fetched.
    * @param propertyName                 -> propertyName for which CSS value needs to be retrieved.
    * @param expectedvalueTocheckinActual -> expected property value of CSS property to check if it is actual.
    * @return flag -> Returns true if actual value contains expected. else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCSSValueOfPropertyAndContainsUsingBy(final By locator,
            final String propertyName, final String expectedvalueTocheckinActual)
            throws WaitFactoryUseException, Exception
   {
      String cssProperyValue = null;
      boolean flag = false;
      try
      {
         cssProperyValue = getCSSValueOfPropertyUsingBy(locator, propertyName);
         log.info("Property " + propertyName + " value for the locator "
                  + locator
                  + "is " + cssProperyValue);
         flag = cssProperyValue.contains(expectedvalueTocheckinActual);
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to to retrieve the property value of "
                           + "given locator&property combination",
                  e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method fetches the CSS value and compare with of the given attribute and locator combination.
    *
    * @param locator               -> locator for which CSS value has to be fetched.
    * @param propertyName          -> propertyName for which CSS value needs to be retrieved.
    * @param expectedPropertyValue -> expected property value of CSS property.
    * @return flag -> Returns true if value matches else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCSSValueOfPropertyAndCompareUsingBy(final By locator,
            final String propertyName, final String expectedPropertyValue)
            throws WaitFactoryUseException, Exception
   {
      String cssProperyValue = null;
      boolean flag = false;
      try
      {
         cssProperyValue = getCSSValueOfPropertyUsingBy(locator, propertyName);
         log.info("Property " + propertyName + " value for the locator "
                  + locator
                  + "is " + cssProperyValue);
         flag = cssProperyValue.equals(expectedPropertyValue);
      }
      catch (WaitFactoryUseException e)
      {
         log.error(e.getMessage(), e);
      }
      catch (Exception e)
      {
         log.error(
                  "Unable to to retrieve the property value of given "
                           + "locator&property combination",
                  e);
         throw new Exception(e);
      }
      finally
      {
         Validator.logResultOfConditionCheck(flag);
      }
      return flag;
   }

   /**
    * This method to checks the conditions Visibility and Present.
    *
    * @param element -> element which need to satisfy the condion
    * @return flag -> True if is provided element matches all condition else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean verifyWaitConditionsPresentAndVisibility(
            final By element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         flag = waitFactory.visibilityOf(element)
                  &&
                  waitFactory.presenceOfElementLocated(element);
         log.info((flag) ? "Element is visble and present"
                  : "Element is either not visible or present");

      }
      catch (WaitFactoryUseException e)
      {
         log.error("Unable to locate the visiblility/presence of the element ");
         log.error(e.getMessage(), e);
         throw new WaitFactoryUseException(e);
      }
      catch (Exception e)
      {
         log.error(e.getMessage(), e);
         throw new Exception(e);
      }
      return flag;
   }
}