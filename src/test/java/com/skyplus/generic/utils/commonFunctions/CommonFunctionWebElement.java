package com.skyplus.generic.utils.commonFunctions;

import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * This class contains the CommonFunctionWebElement.
 */

public final class CommonFunctionWebElement
{

   /**
    * This is a WebDriver instance to handle all selenium functions.
    */
   private final WebDriver commonFunctiondriver;
   /**
    * This is a WaitFactory Instance Variable.
    */
   private final WaitFactory waitFactory;
   /**
    * This is logger instance for the CommonFunctionGeneric class.
    */
   private final Logger log = LogManager.getLogger(CommonFunctionWebElement.class);
   /**
    * This is instance of CommonFunctionBy.
    */
   private final CommonFunctionBy commonFunctionBy;
   /**
    * This is instance of Actions class.
    */
   private final Actions actions;

   /**
    * This Constructor initializes the WaitFactory for the CommonFunctionWebElement Class.
    *
    * @param commonFunctionPOJO -> CommonFunctionPOJO object
    */
   public CommonFunctionWebElement(
            final CommonFunctionPOJO commonFunctionPOJO, CommonFunctionBy commonFunctionBy)
   {
      this.commonFunctiondriver = commonFunctionPOJO.getDriver();
      this.waitFactory = commonFunctionPOJO.getWaitFactory();
      this.commonFunctionBy = commonFunctionBy;
      this.actions = new Actions(commonFunctiondriver);
   }

   /**
    * This selectByIndexUsingWebElement methods selects the value from the dropdown based on the index specified.
    *
    * @param index   -> index of the option to be selected from select dropdown
    * @param element -> select element locator
    * @return flag -> True if the specified index value is selected, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean selectByIndexUsingWebElement(final int index,
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.selectByIndexUsingBy(index, toByValue(element));
      return flag;

   }

   /**
    * This selectByValue methods selects the value from the dropdown based on the value specified.
    *
    * @param value   -> drop down value that needs to be selected
    * @param element -> select element locator
    * @return flag -> True if the specified value is selected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean selectByValueUsingWebElement(final String value,
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.selectByValueUsingBy(value, toByValue(element));
      return flag;

   }

   /**
    * This selectByVisibleWebElement methods selects all options that display text matching the argument specified.
    *
    * @param visibleTextToSelect -> drop down value that needs to be selected
    * @param element             -> select element locator
    * @return flag -> True if the specified value is selected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean selectByVisibleTextUsingWebElement(
            final String visibleTextToSelect, final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.selectByVisibleTextUsingBy(visibleTextToSelect,
               toByValue(element));
      return flag;

   }

   /**
    * This selectByIndexUsingWebElement methods selects the value from the dropdown based on the index specified.
    *
    * @param index   -> index of the option to be selected from select dropdown
    * @param element -> select element locator
    * @return flag -> True if the specified index value is selected, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectByIndexUsingWebElement(final int index,
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.deselectByIndexUsingBy(index, toByValue(element));
      return flag;
   }

   /**
    * This selectByValueUsingWebElement methods deselects the value from the dropdown based on the value specified.
    *
    * @param value   -> drop down value that needs to be deselected
    * @param element -> select element locator
    * @return flag -> True if the specified value is deselected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectByValueUsingWebElement(final String value,
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {

      boolean flag = false;
      flag = commonFunctionBy.deselectByValueUsingBy(value, toByValue(element));
      return flag;
   }

   /**
    * This deSelectByVisibleTextUsingWebElement methods deselects all options that matching the argument specified.
    *
    * @param textToBeDeSelected -> drop down value that needs to be deselected
    * @param element            -> select element locator
    * @return flag -> True if the specified value is deselected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectByVisibleTextUsingWebElement(
            final String textToBeDeSelected, final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.deselectByVisibleTextUsingBy(textToBeDeSelected,
               toByValue(element));
      return flag;
   }

   /**
    * This deSelectAllUsingWebElement methods deselects all selected options from the select dropdown.
    *
    * @param element -> select element locator
    * @return flag -> True if the specified value is deselected from dropdown, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean deselectAllUsingWebElement(
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.deselectAllUsingBy(toByValue(element));
      return flag;
   }

   /**
    * This getAllSelectedOptionUsingWebElement methods returns all selected options from the select element specified.
    *
    * @param element -> select element locator
    * @return allSelectedElement -> returns the list of all selected element from the dropdown
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<WebElement> getAllSelectedOptionUsingWebElement(
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      List<WebElement> allSelectedElement = null;
      allSelectedElement = commonFunctionBy.getAllSelectedOptionUsingBy(toByValue(
               element));
      return allSelectedElement;
   }

   /**
    * This getFirstSelectedOptionUsingWebElement methods returns first selected options from the select element
    * specified.
    *
    * @param element -> select element locator
    * @return firstSelectedElement -> returns the list of the selected element from the dropdown
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public WebElement getFirstSelectedOptionUsingWebElement(
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      WebElement firstSelectedElement = null;
      firstSelectedElement = commonFunctionBy.getFirstSelectedOptionUsingBy(
               toByValue(
                        element));
      return firstSelectedElement;
   }

   /**
    * This getOptionsUsingWebElement methods returns all options from the select element specified.
    *
    * @param element -> select element locator
    * @return allOptionsInSelectElement -> returns the list of all element from the dropdown
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public List<WebElement> getOptionUsingWebElement(
            final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      List<WebElement> allOptionsInSelectElement = null;
      allOptionsInSelectElement = commonFunctionBy.getOptionUsingBy(toByValue(
               element));
      return allOptionsInSelectElement;
   }

   /**
    * This isMultipleUsingWebElement checks if select element support selecting multiple options at the same time? This
    * is done by checking the value of the "multiple" attribute.
    *
    * @param element -> select element locator
    * @return flag -> True if multiple attribute exists for select element, else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isMultipleUsingWebElement(final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.isMultipleUsingBy(toByValue(element));
      return flag;
   }

   /**
    * This dragAndDropUsingWebElement drags element and drop at the location specified.
    *
    * @param sourceElement      -> element to drag
    * @param destinationElement -> location to drop
    * @return flag -> True if is successfully drag and dropped element else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean dragAndDropUsingWebElement(final WebElement sourceElement,
            final WebElement destinationElement)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(sourceElement)
                  && verifyWaitConditionsPresentAndVisibility(destinationElement))
         {
            actions.dragAndDrop(sourceElement, destinationElement)
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
    * This clickUsingOffsetWebElement method click on the element using the offset value.
    *
    * @param locator -> element to click
    * @param xOffset -> x offset of locator
    * @param yOffset -> y offset of locator
    * @return flag -> True if is successfully drag and dropped element else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean clickUsingOffsetUsingWebElement(final WebElement locator,
            final int xOffset,
            final int yOffset)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(locator, xOffset, yOffset).build()
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
    * This mouseHoverUsingWebElement method does mouse hower of element.
    *
    * @param locator -> element to hower
    * @return flag -> True if is successfully mouse Hover on the element
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean mouseHoverUsingWebElement(final WebElement locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(locator).build()
                     .perform();
//            actions.click().perform();
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
    * This clickAndHoldSourceAndDropOnTargetUsingWebElement method click hold and drag and drops source element in
    * destination location.
    *
    * @param soruceElement      -> element to drag and drop
    * @param destinationElement -> location to drop element
    * @return flag -> True if is successfully drag and drop source to the destination
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean clickAndHoldSourceAndDropOnTargetUsingWebElement(
            final WebElement soruceElement, final WebElement destinationElement)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(soruceElement)
                  && verifyWaitConditionsPresentAndVisibility(
                  destinationElement))
         {
            actions.clickAndHold(soruceElement)
                     .moveToElement(destinationElement)
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
    * This dragAndDropElementbyXandYOffsetUsingWebElement method drags and drops source element in X and Y Offset.
    *
    * @param soruceElement -> element to drag and drop
    * @param xOffset       -> x offset of the locator
    * @param yOffset       -> y offset of the locator
    * @return flag -> True if is successfully drag and drop source to the destination
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean dragAndDropElementbyXandYOffsetUsingWebElement(
            final WebElement soruceElement,
            final int xOffset,
            final int yOffset)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(soruceElement))
         {
            actions.dragAndDropBy(soruceElement, xOffset, yOffset)
                     .build()
                     .perform();
            Point point = soruceElement.getLocation();
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
    * This ContextClickUsingWebElement method does a Right Click on element.
    *
    * @param locator -> element on which right click to be performed
    * @return flag -> True if right click is successful
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean contextClickUsingWebElement(
            final WebElement locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.contextClick(locator)
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
    * This movetoElementUsingWebElement method moves to the given element.
    *
    * @param locator -> element on which right click to be performed
    * @return flag -> True if right click is successful
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean movetoElementUsingWebElement(
            final WebElement locator)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(locator)
                     .release()
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
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
    * This movetoElementUsingWebElement method moves to the given element in X and Y offset proivided.
    *
    * @param locator     -> element on which right click to be performed
    * @param xCoordinate -> x Coordinate of the locator
    * @param yCoordinate -> y Coordinate of the locator
    * @return flag -> True if right click is successful
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean movetoElementUsingOffsetWebElement(
            final WebElement locator, final int xCoordinate, final int yCoordinate)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(locator))
         {
            actions.moveToElement(locator, xCoordinate, yCoordinate)
                     .release()
                     .build()
                     .perform();
            flag = true;
         }
         else
         {
            throw new WaitFactoryUseException(
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
    * This isElementSelectedUsingWebElement method return true or false if the given element selection state is selected
    * or not.
    *
    * @param element -> element to be verified for selection
    * @return flag -> True if is provided element is selected false otherwise.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isElementSelectedUsingWebElement(final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (verifyWaitConditionsPresentAndVisibility(element))
         {
            flag = element.isSelected();
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

   /**
    * This method to checks the conditions Visibility and Present.
    *
    * @param element -> element which need to satisfy the condion
    * @return flag -> True if is provided element matches all condition else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   private boolean verifyWaitConditionsPresentAndVisibility(
            final WebElement element)
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

   /**
    * This isElementDisplayedUsingWebElement method return true or false if the given element is displayed state or
    * not.
    *
    * @param element -> element to be verified for selection
    * @return flag -> True if is provided element is displayed false otherwise.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isElementDisplayedUsingWebElement(final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         flag = element.isDisplayed();
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
    * This isElementEnabledUsingWebElement method return true or false if the given element is enabled to perform some
    * action.
    *
    * @param element -> element to be verified for enabled state
    * @return flag -> True if is provided element is selected false otherwise.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean isElementEnabledUsingWebElement(final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      try
      {
         if (waitFactory.visibilityOf(element))
         {
            flag = element.isEnabled();
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
    * @param frameElement -> frame Element of the frame to switch to
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void switchToFrameUsingWebElement(final WebElement frameElement)
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
    * This method clicks on the given element using the Java Script code snippet specified.
    *
    * @param objectTobeClicked -> element that needs to be clicked
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void clickUsingJavaScriptWithWebElement(
            final WebElement objectTobeClicked)
            throws WaitFactoryUseException, Exception
   {
      try
      {
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
    * @param objectTobeEntered -> element that needs to be entered
    * @param textToEnter       -> text that needs to enter
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void enterTextUsingJavaScriptUsingWebElement(
            final WebElement objectTobeEntered,
            final String textToEnter)
            throws WaitFactoryUseException, Exception
   {
      try
      {
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
   public void highlightElementUsingWebElement(final WebElement object)
            throws WaitFactoryUseException, Exception
   {
      JavascriptExecutor js = (JavascriptExecutor) commonFunctiondriver;
      try
      {
         if (waitFactory.visibilityOf(object) &&
                  waitFactory.presenceOfElementLocated(object))
         {
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                     object, "border: 3px solid red;");
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
   public void scrollToElementUsingWebElement(final WebElement object)
            throws WaitFactoryUseException, Exception
   {
      JavascriptExecutor js = (JavascriptExecutor) commonFunctiondriver;
      try
      {
         if (waitFactory.visibilityOf(object) &&
                  waitFactory.presenceOfElementLocated(object))
         {
            js.executeScript("arguments[0].scrollIntoView(true);", object);
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
    * This method clicks on the given element if it is visible,displayed and clickable.
    *
    * @param objectTobeClicked -> Webelement that needs to be clicked
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void clickUsingWebElement(final WebElement objectTobeClicked)
            throws WaitFactoryUseException, Exception
   {
      commonFunctionBy.clickUsingBy(toByValue(objectTobeClicked));
   }

   /**
    * This method clears the text in the textBox element specified.
    *
    * @param element -> textbox in which the text should be entered
    * @return flag -> True if text is cleared, else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean clearTextUsingWebElement(final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      return commonFunctionBy.clearTextUsingBy(toByValue(element));
   }

   /**
    * This method enters the text in the textBox element specified.
    *
    * @param element     -> textbox in which the text should be entered
    * @param textToEnter -> Text that need to be entered in the textBox
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public void enterTextUsingWebElement(final WebElement element,
            final String textToEnter)
            throws WaitFactoryUseException, Exception
   {
      commonFunctionBy.enterTextUsingBy(toByValue(element), textToEnter);
   }

   /**
    * This method gets the text in the textBox element specified.
    *
    * @param element -> textbox in which the text should be entered
    * @return actualText ->Returns the actual text from element.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getTextUsingWebElement(final WebElement element)
            throws WaitFactoryUseException, Exception
   {
      String actualText = commonFunctionBy.getTextUsingBy(toByValue(element));
      log.info("Actual text receveid from element =" + actualText);
      return actualText;
   }

   /**
    * This method gets the text in the textBox element specified and compares with the expected text.
    *
    * @param element      -> textbox in which the text should be entered
    * @param expectedText -> text that is expected to the validated
    * @return flag -> returns true if text matches
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getTextAndCompareUsingWebElement(final WebElement element,
            final String expectedText)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = commonFunctionBy.getTextAndCompareUsingBy(
               toByValue(element), expectedText);
      return flag;
   }

   /**
    * This method fetches the value of the given attribute.
    *
    * @param locator   -> locator for which attribute value has to be fetched.
    * @param attribute -> attribute for which value needs to be retrieved.
    * @return attributeValue -> value of the expected attribute
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getAttributeValueUsingWebElement(final WebElement locator,
            final String attribute)
            throws WaitFactoryUseException, Exception
   {
      String attributeValue = null;
      attributeValue = commonFunctionBy.getAttributeValueUsingBy(toByValue(
               locator), attribute);
      return attributeValue;
   }

   /**
    * This method fetches the CSS value of the given attribute and locator combination.
    *
    * @param locator      -> locator for which CSS value has to be fetched.
    * @param propertyName -> property that needs to be verified for the css.
    * @return cssProperyValue -> Returns property/attribute value of the attribute specified. Else null
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public String getCSSValueOfPropertyUsingWebElement(final WebElement locator,
            final String propertyName)
            throws WaitFactoryUseException, Exception
   {
      String cssProperyValue = null;
      cssProperyValue = commonFunctionBy.getCSSValueOfPropertyUsingBy(toByValue(
               locator), propertyName);
      return cssProperyValue;
   }

   /**
    * This method fetches the CSS value and compare with of the given attribute and locator combination.
    *
    * @param locator               -> locator for which CSS value has to be fetched.
    * @param propertyName          -> property in which css value has to be identified.
    * @param expectedPropertyValue -> expected property value of CSS property.
    * @return flag -> Returns true if value matches else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCSSValueOfPropertyAndCompareUsingWebElement(
            final WebElement locator,
            final String propertyName, final String expectedPropertyValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.getCSSValueOfPropertyAndCompareUsingBy(toByValue(
               locator), propertyName, expectedPropertyValue);
      return flag;
   }

   /**
    * This method fetches the CSS value and checks if the value actual value contain the expected value.
    *
    * @param locator               -> locator for which CSS value has to be fetched.
    * @param propertyName          -> property in which css value has to be identified.
    * @param expectedPropertyValue -> expected property value of CSS property to check if it is actual.
    * @return flag -> Returns true if actual value contains expected. else false.
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getCSSValueOfPropertyAndContainsUsingWebElement(
            final WebElement locator,
            final String propertyName, final String expectedPropertyValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.getCSSValueOfPropertyAndContainsUsingBy(toByValue(
               locator), propertyName, expectedPropertyValue);
      return flag;
   }

   /**
    * This method fetches the property/attribute value of the given attribute and compares with expected.
    *
    * @param locator               -> locator for which attribute value has to be fetched.
    * @param propertyName          -> property in which css value has to be identified.
    * @param expectedPropertyValue -> expected Value for the given attribute
    * @return flag -> Returns true if the value matches else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAttributeValueAndCompareUsingWebElement(
            final WebElement locator,
            final String propertyName, final String expectedPropertyValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.getAttributeValueAndCompareUsingBy(toByValue(
               locator), propertyName, expectedPropertyValue);
      return flag;
   }

   /**
    * This method fetches the property/attribute value of the given attribute and verifies if the actual value contains
    * expected.
    *
    * @param locator               -> locator for which attribute value has to be fetched.
    * @param propertyName          -> property in which css value has to be identified.
    * @param expectedPropertyValue -> expected Value for the given attribute
    * @return flag -> Returns true if the Actual value contains expectedValue else false
    * @throws WaitFactoryUseException ->Custom exception object for Waitfactory caller to handle the exception
    * @throws Exception               -> Exception caller to handle the exception
    */
   public boolean getAttributeValueAndVerifyitContainsUsingWebElement(
            final WebElement locator,
            final String propertyName, final String expectedPropertyValue)
            throws WaitFactoryUseException, Exception
   {
      boolean flag = false;
      flag = commonFunctionBy.
               getAttributeValueAndVerifyitContainsUsingBy(toByValue(
                        locator), propertyName, expectedPropertyValue);
      return flag;
   }
}