package com.skyplus.generic.utils.commonFunctions;

import com.skyplus.generic.utils.BaseUtil;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.stepdefs.SkyplusFactory;
import org.openqa.selenium.WebDriver;

/**
 * This class contains the CommonFunctionPOJO.
 */
public class CommonFunctionPOJO extends BaseUtil
{

   private WaitFactory waitFactory;

   public CommonFunctionPOJO(SkyplusFactory skyplusFactory, WaitFactory waitFactory)
   {
      super(skyplusFactory, waitFactory);
      setWaitFactory();
   }

   /**
    * This method is is used to set the WaitFactory.
    */
   public void setWaitFactory()
   {
      this.waitFactory = super.getWaitFactory();
   }

   /**
    * This method is is used to return the WaitFactory.
    *
    * @return WaitFactory to Return Waitfactory
    */
   public WaitFactory getWaitFactory()
   {
      return this.waitFactory;
   }

   /**
    * This method is is used to get the current driver instance.
    *
    * @return current Driver
    */
   public WebDriver getDriver()
   {
      return super.getDriver();
   }

}
