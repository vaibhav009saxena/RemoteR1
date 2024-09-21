package com.skyplus.generic.utils.waitFactory;

import com.skyplus.stepdefs.SkyplusFactory;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * POJO Class This class has all the fields , getters and setters required for waitfactory
 */
@Getter
public class WaitFactoryPojo
{

   /**
    * WebDrvierWait Instance Variable.
    */
   private FluentWait<WebDriver> longWait;
   private FluentWait<WebDriver> mediumWait;
   private FluentWait<WebDriver> shortWait;

   /**
    * WebDrvier Instance Variable.
    */
   private WebDriver driver;

   /**
    * TimeOut specified in seconds for the wait.
    */
   private long longTimeOutInseconds;

   /**
    * Short period time out in seconds for wait
    */
   private long shortTimeOutInSeconds;

   /**
    * Medium period time out in seconds for wait
    */
   private long mediumTimeoutInSeconds;

   /**
    * TimeOut specified in seconds for the polling.
    */
   private long pollTimeOutInseconds;

   public WaitFactoryPojo(SkyplusFactory skyplusFactory)
   {
      Properties prop = skyplusFactory.getProp();
      this.longTimeOutInseconds = Long.parseLong(prop.getProperty("longWaitTimeOutInSeconds"));
      this.mediumTimeoutInSeconds = Long.parseLong(prop.getProperty("mediumWaitTimeOutInSeconds"));
      this.shortTimeOutInSeconds = Long.parseLong(prop.getProperty("shortWaitTimeOutInSeconds"));
      this.pollTimeOutInseconds = Long.parseLong(prop.getProperty("waitPollTimeInSeconds"));
      WebDriverWait webDriverWaitLong = new WebDriverWait(skyplusFactory.getDriver(), Duration.ofSeconds(longTimeOutInseconds));
      WebDriverWait webDriverWaitMedium = new WebDriverWait(skyplusFactory.getDriver(), Duration.ofSeconds(mediumTimeoutInSeconds));
      WebDriverWait webDriverWaitShort = new WebDriverWait(skyplusFactory.getDriver(), Duration.ofSeconds(shortTimeOutInSeconds));
      this.setLongWait(webDriverWaitLong);
      this.setMediumWait(webDriverWaitMedium);
      this.setShortWait(webDriverWaitShort);
      this.setDriver(skyplusFactory.getDriver());
   }

   /**
    * This method is used to get the wait.
    *
    * @return current wait
    */
   public FluentWait<WebDriver> getLongWait()
   {
      return longWait;
   }

   /**
    * This method is used to set the wait.
    *
    * @param webDriverWait wait to set for long timeout
    */
   public void setLongWait(final WebDriverWait webDriverWait)
   {
      this.longWait = webDriverWait.ignoring(NoSuchElementException.class)
               .pollingEvery(Duration.ofSeconds(this.pollTimeOutInseconds));
   }

   /**
    * This method is used to set medium wait.
    *
    * @param mediumWebDriverWait wait to set for medium timeout
    */
   public void setMediumWait(final WebDriverWait mediumWebDriverWait)
   {
      this.mediumWait = mediumWebDriverWait.ignoring(NoSuchElementException.class)
              .pollingEvery(Duration.ofSeconds(this.pollTimeOutInseconds));
   }

   /**
    * This method is is used to set the wait.
    *
    * @param shortWebDriverWait wait to  for short timeout
    */
   public void setShortWait(final WebDriverWait shortWebDriverWait)
   {
      this.shortWait = shortWebDriverWait.ignoring(NoSuchElementException.class)
              .pollingEvery(Duration.ofSeconds(this.pollTimeOutInseconds));
   }

   /**
    * This method is is used to get the current driver instance.
    *
    * @return current Driver
    */
   public WebDriver getDriver()
   {
      return driver;
   }

   /**
    * This method is is used to set the driver instance.
    *
    * @param webDriver driver to set
    */
   public void setDriver(final WebDriver webDriver)
   {
      this.driver = webDriver;
   }

   /**
    * This method is is used to get the time out in seconds.
    *
    * @return current timeOutInseconds
    */
   public long getLongTimeOutInseconds()
   {
      return longTimeOutInseconds;
   }

   /**
    * This method is used to set the time out.
    *
    * @param timeOutUnitInseconds timeOutInseconds to set
    */
   public void setLongTimeOutInseconds(final long timeOutUnitInseconds)
   {
      this.longTimeOutInseconds = timeOutUnitInseconds;
   }

   /**
    * This method is used to set the poll time out.
    *
    * @param pollTime polling interval
    */
   public void setPollTime(final long pollTime)
   {
      this.pollTimeOutInseconds = pollTime;
   }
}
