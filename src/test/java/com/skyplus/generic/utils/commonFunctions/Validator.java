package com.skyplus.generic.utils.commonFunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class contains validator function to return the boolean for all the three classes{WaitFactoryBy,
 * WaitFactoryGeneric, WaitFactoryWebElement}.
 */
public final class Validator
{
   /**
    * Logger  Instance Variable to capture the information in the log.
    */
   private static final Logger log = LogManager.getLogger(
            Validator.class);

   /**
    * This method checks if the given boolean value is true or false and prints relevant log messages.
    *
    * @param condition Boolean condition passed for condition validation
    */
   public static void logResultOfConditionCheck(final Boolean condition)
   {
      if (condition)
      {
         log.info("All conditions are satisfied..");
      }
      else
      {
         log.info("All conditions are not satisfied..");
      }
   }

   /**
    * This method checks if the given List of element for null and returns true. if it has value, false if it contains
    * null.
    *
    * @param elements list of WebElement
    * @return true -> when the List of Element does not have any null reference
    */
   public static boolean nullValidatorForListOfObject(
            final List<WebElement> elements)
   {
      boolean flag = false;
      // if elements is not null then checks the size of the list if it
      // matches return true else false.
      flag = elements != null && (elements.stream().filter(
                        element -> element != null).collect(Collectors.toList())
               .size() == elements.size());
      log.info((flag) ? "All conditions are satisfied"
               : "All conditions are not satsfied and few null paramter exists");
      return flag;
   }

   /**
    * This method checks if the given Set of element for null and returns true. if it has value, false if it contains
    * null.
    *
    * @param SetOfElement Set of Element
    * @return true -> when the set of Element does not have any null reference
    */
   public static boolean nullValidatorForSetOfObject(
            final Set<String> SetOfElement)
   {
      boolean flag = false;
      // if elements is not null then checks the size of the list if it
      // matches return true else false.
      flag = SetOfElement != null && (SetOfElement.stream().filter(
                        element -> element != null).collect(Collectors.toSet())
               .size() == SetOfElement.size());
      log.info((flag) ? "All conditions are satisfied"
               : "All conditions are not satsfied and few null paramter exists");
      return flag;
   }

   /**
    * This method checks if the given element is null and returns true, else false.
    *
    * @param webObject Object passed to the method for Null Validation
    * @return true -> once it is located
    */
   public boolean nullValidator(final Object webObject)
   {
      boolean flag = false;
      flag = webObject != null;
      if (flag)
      {
         log.info("Object is not null and Expected condition are achieved");
      }
      else
      {
         log.info("Object is Null and the Condition are not satisfied");
      }
      return flag;
   }

}