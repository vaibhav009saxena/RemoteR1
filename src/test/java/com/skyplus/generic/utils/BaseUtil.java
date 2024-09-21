package com.skyplus.generic.utils;

import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.stepdefs.SkyplusFactory;
import lombok.Data;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Data
public class BaseUtil
{

   /**
    * This class has all the fields , getters and
    *         setters required for waitfactory
    */
   /**
    * WebDrvier Instance Variable.
    */

   protected WebDriver driver;


   /**
    * TimeOut specified in seconds for the wait.
    */

   protected long timeOutInseconds;



   /**
    * TimeOut specified in seconds for the polling.
    */

   protected long pollTimeOutInseconds;
   /**
    * SkyplusFactory object to obtain required base configurations
    */
   protected SkyplusFactory skyplusFactory;


   /**
    * WaitFactory object to handle the Explicit wait.
    */

   protected WaitFactory waitFactory;


   protected BaseUtil(SkyplusFactory skyplusFactory, WaitFactory waitFactory)
   {
      this.skyplusFactory = skyplusFactory;
      this.waitFactory = waitFactory;
      Properties prop = skyplusFactory.getProp();
      this.timeOutInseconds = Long.parseLong(prop.getProperty("longWaitTimeOutInSeconds"));
      this.pollTimeOutInseconds = Long.parseLong(prop.getProperty("waitPollTimeInSeconds"));
      this.driver = skyplusFactory.getDriver();

   }
}
