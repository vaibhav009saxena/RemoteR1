package com.skyplus.pageObjects;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.commonFunctions.CommonFunctionException;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

import static org.eclipse.jgit.lib.ObjectChecker.parent;
import static org.junit.Assert.assertEquals;

public class BookingWidgetPage {

    public final CommonFunctionIndigo commonFunctionsIndigo;
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final SkyPlusContainer skyPlusContainer;
    public WaitFactory waitFactory;

    private final String armedforce="Armed Forces";

    private final String doctorandnurses= "Doctors & Nurses";
    private final String familyandfriends="Family & Friends";

    private final String UNMR="Unaccompanied Minor";
    private final String students="Students";
    private final String vaccinated="Vaccinated";


    SearchSectionPage searchSectionPage;
    protected Logger log = LogManager.getLogger();

    public BookingWidgetPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                             CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, SearchSectionPage searchSectionPage) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        Properties prop = skyplusFactory.getProp();
        this.searchSectionPage = searchSectionPage;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "(//button[contains(@class,'cmp-custom-drop-down__btn')])[1]")
    private WebElement validtaetriptype;

    @FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'To')]")
    private WebElement verifyTofield;
    @FindBy(how = How.CSS, using = "div.widget-container__filter-bar > div.cmp-custom-drop-down > button")
    private WebElement tripTypeDropDown;

    @FindBy(how = How.CSS, using = "input[placeholder='From']")
    private WebElement sourceInputFld;
    @FindBy(how = How.CSS, using = "*[placeholder='To']")
    private WebElement destinationInputFld;
    @FindBy(how = How.CSS, using = ".widget-container__filter-bar__pax-selection > .cmp-custom-drop-down > .cmp-custom-drop-down__btn")
    private WebElement addPaxDropDownBtn;

    @FindBy(how = How.XPATH, using = "//a[text()='Special Assistance ?']")
    private WebElement specialAsistance;
    @FindBy(how = How.XPATH, using = "//h1[@class='static-common-title custom-p-0']")
    private WebElement specialAsistancelbl;

    @FindBy(how =How.XPATH ,using ="//a[text()='Click Here']")
    private WebElement clickheretab;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'1 Double Seat Selected')]")
    private WebElement verifySeatTagInfo;

    @FindBy(how = How.XPATH, using = "//span[text()='1 Triple Seats Selected']")
    private WebElement verifyTripleSeatTagInfo;

    @FindBy(how = How.CSS, using = ".cmp-custom-drop-down__options ul li span")
    private List<WebElement> tripOptions;

//    @FindBy(how = How.CSS, using = ".widget-container__filter-bar__pax-selection > .cmp-custom-drop-down > .cmp-custom-drop-down__btn")
//    public WebElement addPaxDropDownBtn;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button pax-dropdown__action__done']")
    private WebElement addPaxDoneBtn;

    @FindBy(how = How.XPATH, using = "(//div[@class='pax-dropdown__wrapper']//label[@class='pax-dropdown__label pax-dropdown__label--children']//button)[2]")
    private WebElement ChildbuttonDisable;

    @FindBy(how = How.XPATH, using = "((//div[@class='pax-dropdown__wrapper'])[1]//label//span/..//div//button)[1]")
    private WebElement VerifyAddAdultDisable;

    @FindBy(how = How.XPATH, using = "//input[@class='stepper-input__input']")
    private List<WebElement> VerifyPaxCount;

    @FindBy(how = How.XPATH, using = "((//div[@class='pax-dropdown__wrapper'])[4]/..//input)[4]")
    private WebElement InfantpaxValue;

    @FindBy(how = How.XPATH, using = "//*[@id=\"container-fabdaf897e\"]/div/div[1]/div/div/section/div/div[1]/div[2]/div[1]/button/text()")
    private WebElement VerifyPaxValue;

    @FindBy(how = How.XPATH, using = "//button[@class='stepper-input__btn stepper-input__btn--plus']")
    private List<WebElement> Addpaxbuttondisable;

    @FindBy(how = How.XPATH, using = "//div[@class='pax-dropdown__wrapper']//label[@class='pax-dropdown__label pax-dropdown__label--adult']//input")
    private WebElement Adultpaxvalue;

    @FindBy(how = How.XPATH, using = "//div[@class='pax-dropdown__wrapper']//label[@class='pax-dropdown__label pax-dropdown__label--senior']//input")
    private WebElement Seniorpaxvalue;

    @FindBy(how = How.XPATH, using = "//div[@class='pax-dropdown__wrapper']//label[@class='pax-dropdown__label pax-dropdown__label--infant']//input")
    private WebElement Infantpaxvalue;

    @FindBy(how = How.XPATH, using = "(//div[@class='stepper-input']//button)[8]")
    private WebElement InfantAddValue;

    @FindBy(how = How.CSS, using = ".faresLabel")
    private WebElement specialFareDdown;

    @FindBy(how = How.CSS, using = "li[name='discountType']")
    private List<WebElement> specialFareOptions;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Family & Friends')]")
    private WebElement familyAndFriends;

    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[2]")
    private WebElement paxDdown;

    @FindBy(how = How.XPATH, using = "(//li[@name='discountType'])[4]")
    private WebElement unaccompaniedFare;

    @FindBy(how = How.CSS, using = "div[class='faresLabel selected']")
    private WebElement unccompaniedtitle;

    @FindBy(how = How.XPATH, using = "(//li[@name='discountType'])[6]")
    private WebElement vaccinatedFare;

    @FindBy(how = How.CSS, using = "div.popup-modal-with-content.booking-modal-content button[class='custom-button custom-button']")
    private WebElement okBtn;

    @FindBy(how = How.XPATH, using = "(//span[@class='custom-button__label'])[1]")
    private WebElement doneBtn;

    @FindBy(how = How.CSS, using = "[placeholder='Travel Dates']")
    private WebElement TravelDate;


    public boolean verifyTripDropDownOption(String oneWay, String roundTrip,String multiCity){
        boolean flag = false;
        try {
            tripTypeDropDown.click();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(tripOptions.get(0).getText().contains(oneWay));
        Assert.assertTrue(tripOptions.get(1).getText().contains(roundTrip));
        Assert.assertTrue(tripOptions.get(2).getText().contains(multiCity));
        flag = true;

        return flag;
    }

    public boolean defaulttriptype(){
        boolean flag = false;
        try{

            waitFactory.elementToBeClickable(tripTypeDropDown);

            commonFunctions.clickElementUsingJavaScript(tripTypeDropDown);
            commonFunctions.clickElementUsingJavaScript(tripTypeDropDown);
            log.info("Clicked on Element "+tripTypeDropDown);
            waitFactory.hardWait(4);
            flag  = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info(tripOptions);
        if(tripOptions.get(0).getText().contains("One Way")){
            tripOptions.get(0).click();
            flag  = true;
        }else {
            flag  = false;
        }
        if(tripTypeDropDown.getText().contains("One Way")){
            flag  = true;
        }else {
            flag  = false;
        }

        return flag;
    }


    @FindBy(how = How.XPATH, using = "(//div[@class='react-datepicker__input-container'])[2]//input")
    private WebElement returnDateField;
    public boolean validateReturnDateStateAsPerTripType(DataTable table) throws Exception {
        boolean flag = false;


        List<List<String>> datals = table.asLists(String.class);

        for (int i = 0; i < datals.size() ; i++)
        {
            log.info("When selected " + datals.get(i).get(0) +" and "+datals.get(i).get(1));
            if(datals.get(i).get(1).contains("Empty") && datals.get(i).get(0).contains("One Way")){
                log.info("When selected " + datals.get(i).get(0));
                tripTypeDropDown.click();
                if(tripOptions.get(0).getText().contains("One Way")){
                    tripOptions.get(0).click();
                    waitFactory.elementToBeClickable(returnDateField);
                    log.info("Return Date is Empty? :"+ returnDateField.getAttribute("value").isEmpty());
                    if(returnDateField.getAttribute("value").isEmpty()){
                        flag= true;
                    }else {
                        flag = false;
                    }
                }else{
                    log.info("Unable to select Trip Type as " + datals.get(i).get(0));
                }

            } else if (datals.get(i).get(1).contains("populated") && datals.get(i).get(0).contains("Round Trip")) {
                log.info("When selected " + datals.get(i).get(0));
                tripTypeDropDown.click();

                if(tripOptions.get(1).getText().contains("Round Trip")){
                    tripOptions.get(1).click();
                    log.info("Return Date is Empty? :"+ returnDateField.getAttribute("value").isEmpty());
                    if(!returnDateField.getAttribute("value").isEmpty()){
                        flag= true;
                    }else {
                        flag = false;
                    }
                }else{
                    log.info("Unable to select Trip Type as " + datals.get(i).get(0) + "is Invalid input only One Way and Round Trip is allowed");
                    flag = false;
                }

            }
        }

        return flag;
    }


    public boolean selectTripType(String oneWay, String roundTrip,String multiCity){
        boolean flag = false;
        try{
            tripTypeDropDown.click();
            flag  = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        Assert.assertTrue(tripOptions.get(0).getText().contains(oneWay));
        Assert.assertTrue(tripOptions.get(1).getText().contains(roundTrip));
        Assert.assertTrue(tripOptions.get(2).getText().contains(multiCity));
        flag  = true;

        return flag;
    }



//    @FindBy(how = How.XPATH, using = "//div/div/section/div/div[1]/div[2]/div[1]/button")
//    private WebElement paxDropDown;

    @FindBy(how = How.CSS, using = "div.widget-container__filter-bar__pax-selection > div.cmp-custom-drop-down > button")
    private WebElement paxDropDown;

    @FindBy(how = How.CSS, using = ".stepper-input__input")
    private List<WebElement> paxValues;
    public boolean verifyMinOnePaxisRequired(){
        boolean flag = false;
        try{
            paxDropDown.click();
            int count =0;
            for(WebElement paxCount:paxValues){
                log.info(paxCount.getAttribute("value"));
                if(paxCount.getAttribute("value").equals("1")){
                    count++;
                    log.info("Pax count for Adult is : "+count);
                    break;
                }
            }
            WebElement adultReduceButton =paxValues.get(0).findElement(By.xpath("//preceding-sibling::button"));

            String disabledAttribute = adultReduceButton.getAttribute("disabled");
            if (count==Integer.parseInt(paxValues.get(0).getAttribute("value")) && disabledAttribute== null) {
                log.info("Minus button for Adult is disabled");
                flag = true;
            } else {
                flag = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @FindBy(how = How.XPATH, using = "//div[4]/label/div/button[2]")
    private WebElement paxChildrenAddBtn;
    @FindBy(how = How.XPATH, using = "//div[4]/label/div/button[1]")
    private WebElement paxChildrenMinusBtn;
    @FindBy(how = How.XPATH, using = "//div[5]/label/div/button[2]")
    private WebElement paxInfantAddBtn;
    @FindBy(how = How.XPATH, using = "//div[5]/label/div/button[1]")
    private WebElement paxInfantMinusBtn;
    @FindBy(how = How.XPATH, using = "//div[3]/label/div/button[2]")
    private WebElement paxSeniorAddBtn;
    @FindBy(how = How.XPATH, using = "//div[3]/label/div/button[1]")
    private WebElement paxSeniorMinusBtn;
    @FindBy(how = How.XPATH, using = "//div[2]/label/div/button[1]")
    private WebElement paxAdultMinusBtn;
    @FindBy(how = How.XPATH, using = " //div[2]/label/div/button[2]")
    private WebElement paxAdultAddBtn;

    public boolean verifyadultCannotBeReduced(String adultcount){
        boolean flag = false;
        try{

            waitFactory.hardWait(2);
            paxDropDown.click();
            if(!waitFactory.visibilityOf(paxChildrenAddBtn)){
                paxDropDown.click();
            }else{
                log.info("Pax Dropdown is open");
            }
            paxChildrenAddBtn.click();
            log.info("Added child");
            paxInfantAddBtn.click();
            log.info("add infant");
            WebElement adultReduceButton =paxValues.get(0).findElement(By.xpath("//preceding-sibling::button"));
            log.info(adultReduceButton);
            String disabledAttribute = adultReduceButton.getAttribute("disabled");
            log.info(disabledAttribute);
            log.info(paxValues.get(0).getAttribute("value"));
            if(paxValues.get(0).getAttribute("value").equals(adultcount) && disabledAttribute==null){
                log.info("Cannot reduce Adult Count");
                flag = true;
            }else{
                flag = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @FindBy(how = How.XPATH, using = "//div[@class='popup-modal-with-content__content undefined']//div[2]/button")
    private WebElement seniorCitizenPopupOkayBtn;
    public boolean verifySeniorCannotBeReduced(String seniorcitizencount){
        boolean flag = false;
        try{
            if(!waitFactory.visibilityOf(paxChildrenAddBtn)){
                paxDropDown.click();
                log.info("Clicked in Pax Drop Down");
            }else{
                log.info("Pax Dropdown is open");
            }
            waitFactory.visibilityOf(paxChildrenAddBtn);
            paxChildrenAddBtn.click();
            log.info("Added Children");
            paxInfantAddBtn.click();
            log.info("Add infant");
            try{
                paxSeniorAddBtn.click();
                if(waitFactory.visibilityOf(seniorCitizenPopupOkayBtn)){
                    seniorCitizenPopupOkayBtn.click();
                    log.info("Clicked on Okay Button");
                }
                paxDropDown.click();
                log.info("Clicked in Pax Drop Down");
            }catch (Exception e){
                e.printStackTrace();
            }
            paxAdultMinusBtn.click();
            log.info("Clicked on Adult Minus button");


            WebElement adultReduceButton =paxValues.get(1).findElement(By.xpath("//preceding-sibling::button"));
            String disabledAttribute = adultReduceButton.getAttribute("disabled");
            if(paxValues.get(1).getAttribute("value").equals(seniorcitizencount) && disabledAttribute== null){
                log.info("Cannot reduce Senior Citizen Count");
                flag = true;
            }else{
                flag = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean VerifyChildAddButtonDisable() throws Exception {
        boolean flag =false;
        boolean flag1 =false;
        waitFactory.visibilityOf(addPaxDropDownBtn);
        waitFactory.elementToBeClickable(addPaxDropDownBtn);
        commonFunctions.clickOnElement(addPaxDropDownBtn);
        flag  = commonFunctions.isElementEnabled(ChildbuttonDisable);
        waitFactory.visibilityOf(VerifyAddAdultDisable);
        flag = commonFunctions.isElementEnabled(VerifyAddAdultDisable);

        if(flag==false)
        {
            waitFactory.visibilityOf(addPaxDoneBtn);
//            commonFunctions.clickOnElement(addPaxDoneBtn);
        }
        flag1= waitFactory.visibilityOf(addPaxDoneBtn);
        commonFunctions.clickOnElement(addPaxDoneBtn);
        return flag1;
    }

    public boolean VerifyMaxPaxCount(int numberofAllPax) throws Exception {
        boolean flag = false;

        waitFactory.visibilityOf(addPaxDropDownBtn);
        waitFactory.elementToBeClickable(addPaxDropDownBtn);
        commonFunctions.clickOnElement(addPaxDropDownBtn);

        int paxcount=0;
        for(int i=0;i<=VerifyPaxCount.size()-1;i++)
        {
            WebElement paxCountele = driver.findElement(By.xpath("(//input[@class='stepper-input__input'])["+(i+1)+"]"));
            paxcount=Integer.parseInt(paxCountele.getAttribute("value"));
            paxcount = paxcount+paxcount;

        }

        int numberofpax= paxcount+1;
       String numberofinfant =InfantpaxValue.getAttribute("value");
      int infantvalue= Integer.parseInt(numberofinfant);
        String paxvalue = "9";
       int valuepofax= Integer.parseInt(paxvalue);
        for(int i=1;i<Addpaxbuttondisable.size()-1;i++)
          {
             flag= commonFunctions.isElementEnabled(driver.findElement(By.xpath("(//button[@class='stepper-input__btn stepper-input__btn--plus'])[" + i + "]")));
              log.info("flag value is "+ flag);
             if(flag==false)

             {
                 log.info("flag value is "+ flag);
             }

          }
      if(infantvalue!=0&&numberofpax==numberofAllPax&&valuepofax==numberofAllPax)
      {
          log.info("flag value is "+ flag);

       flag = true;
          waitFactory.visibilityOf(addPaxDropDownBtn);
          commonFunctions.clickOnElement(addPaxDoneBtn);
          waitFactory.visibilityOf(addPaxDropDownBtn);
      }
      return flag;
    }

       public boolean VerifyInfantCountWithPax(int infant) throws Exception {
           waitFactory.visibilityOf(addPaxDropDownBtn);
           waitFactory.elementToBeClickable(addPaxDropDownBtn);
           commonFunctions.clickOnElement(addPaxDropDownBtn);
           boolean flag = false;
           int Addultvalue = Integer.parseInt(Adultpaxvalue.getAttribute("value"));
           log.info("Addultvalue is " + Addultvalue);
           int seniorvalue = Integer.parseInt(Seniorpaxvalue.getAttribute("value"));
           log.info("Seniorvalue is " + seniorvalue);
           int infantvalue = Integer.parseInt(Infantpaxvalue.getAttribute("value"));
           log.info("infantvalue is " + infantvalue);
           int infantaddvalue = 4 - infantvalue;
           int totalvalue = seniorvalue + Addultvalue;
           log.info("infantaddvalue is " + infantaddvalue);
           int infantAddValueforLesspax=totalvalue-infant;
           if(totalvalue >= 4) {
               waitFactory.visibilityOf(addPaxDropDownBtn);
               commonFunctions.clickOnElement(addPaxDropDownBtn);
               waitFactory.hardWait(1);
               commonFunctions.clickOnElement(addPaxDropDownBtn);
               for (int i=0;i<infantaddvalue;i++) {
                   commonFunctions.clickOnElement(InfantAddValue);
                   log.info("Infant add button is disabled");
               }
               try
               {
                   flag = commonFunctions.isElementEnabled(InfantAddValue);
                   if(flag==false)
                   {
                     log.info("Infant add button is disabled11");
                     flag =true;
                   }
               }
                   catch (Exception e) {
                 log.info("not able to verify disable add infant button");
               }

           }
           else
       {
         if(totalvalue<4&&totalvalue>=infant)
         {
             waitFactory.visibilityOf(addPaxDropDownBtn);
             commonFunctions.clickOnElement(addPaxDropDownBtn);
             waitFactory.hardWait(1);
             commonFunctions.clickOnElement(addPaxDropDownBtn);
             for(int i=0;i<infantAddValueforLesspax;i++)
             {
                flag= commonFunctions.isElementEnabled(InfantAddValue);
                 commonFunctions.clickOnElement(InfantAddValue);
                 log.info("Infant add button is disabledextra");
             }
             try
             {
                 flag = commonFunctions.isElementEnabled(InfantAddValue);
                 if(flag==false)
                 {
                     log.info("Infant add button is disabled");
                     flag =true;
                 }
             }
             catch (Exception e) {
                 log.info("not able to verify disable add infant button");
             }
         }
       }
           return flag;
       }


            public boolean VerifyChangedTripType(String Text) {
            boolean flag = false;
            try {
            waitFactory.visibilityOf(validtaetriptype);
            waitFactory.hardWait(3);
            boolean Display = driver.findElement(By.xpath("(//button[contains(@class,'cmp-custom-drop-down__btn')])[1]")).isDisplayed();
            System.out.println("Element displayed is :" + Display);
            if (validtaetriptype.getText().equalsIgnoreCase(Text)) {
                flag = true;
            } else {
                log.info("trip type is present");
            }
            } catch (Exception e) {
            log.error("Round Trip text not visible");
            e.printStackTrace();
            }
            return flag;
    }

            public boolean verifySameCityNotEnterInToFiled(String destination) {
            boolean flag = false;
            try {
            waitFactory.visibilityOf(verifyTofield);
            if (validtaetriptype.getText().contains(destination)) {
                log.info("City is Present");
            } else {
                log.info("City is not present");
            }
            flag = true;
            } catch (Exception e) {
            log.error("result matched");
            e.printStackTrace();
            }
            return flag;
    }
             public boolean checkSeatTagInfo() {
             boolean flag = false;
             try {
             commonFunctions.clickOnElement(addPaxDropDownBtn);
             waitFactory.hardWait(4);
             if (waitFactory.visibilityOf(verifySeatTagInfo)) {
              flag = true;
             log.info("Double Seat Selected info message is visible");
             }
             } catch (Exception e) {
             log.error("unable to verify text");
             e.printStackTrace();
             }
             return flag;
    }

             public boolean VerifyseatTagForTripleseat(){
             boolean flag=false;
             try{
             commonFunctions.clickOnElement(addPaxDropDownBtn);
             waitFactory.hardWait(4);
             if (waitFactory.visibilityOf(verifyTripleSeatTagInfo)) {
              flag = true;
              log.info("Triple Seat Selected info message is visible");
             }
             }
             catch(Exception e){
               log.error("unable to verify Triple seat tag information");
             }
             return flag;
             }

            public boolean clickOninfoIconofseatTag() throws Exception {
            boolean flag = false;
            try {
            WebElement drpTripleDoublePax = driver.findElement(By.xpath("(//div[@class='cmp-custom-drop-down   '])[2]"));
            commonFunctions.clickElementUsingJavaScript(drpTripleDoublePax);
            waitFactory.hardWait(2);
            List<WebElement> infoicons = driver.findElements(By.xpath("//i[@class='add-seat-accordion__head__info skp-iconmoon-icon']"));
            for (int i = 0; i < infoicons.size(); i++) {
                System.out.println("Enter in for loop");
                waitFactory.hardWait(3);
                WebElement iconToClick = driver.findElement(By.xpath("(//i[@class='add-seat-accordion__head__info skp-iconmoon-icon'])[" + (i + 1) + "]"));
                log.info("clicked on icon " + i);
                try {
                    iconToClick.click();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    WebElement textToVerify = driver.findElement(By.xpath("//h4[contains(text(),'6E Double Seat')]"));
                    String doubleseat = textToVerify.getText();
                    System.out.println(doubleseat);
                    waitFactory.hardWait(1);
                    driver.findElement(By.cssSelector("button[class='btn back-btn modal-close align-items-center']")).click();
                    log.info("popup closed");
                } catch (Exception e) {
                    log.error("unable to verify text between opend popup");
                    e.printStackTrace();
                }
                try {
                    waitFactory.hardWait(3);
                    WebElement paxdwn = driver.findElement(By.xpath("(//div[@class='cmp-custom-drop-down   '])[2]"));
                    Thread.sleep(2000);
                    paxdwn.click();
                } catch (Exception e) {
                    log.error("unable to click on pax dropdown second time");
                }
                }
                if (waitFactory.visibilityOf(addPaxDropDownBtn)) {
                flag = true;
                }
                } catch (Exception e) {
                log.error("unable to click on info icon tab");
                e.printStackTrace();
                }
                return flag;
    }

             public boolean clickOnSpecialAssitance() {
             boolean flag = false;
             try {
//             WebElement drpTripleDoublePax = driver.findElement(By.xpath("(//div[@class='cmp-custom-drop-down   '])[2]"));
//             commonFunctions.clickElementUsingJavaScript(drpTripleDoublePax);
             waitFactory.visibilityOf(addPaxDropDownBtn);
             commonFunctions.clickElementUsingJavaScript(addPaxDropDownBtn);
             waitFactory.waitForPageLoad();
             String parent = driver.getWindowHandle();
             commonFunctions.clickElementUsingJavaScript(specialAsistance);
             waitFactory.waitForPageLoad();
             Set<String> servicepafges= driver.getWindowHandles();
             Iterator<String> i = servicepafges.iterator();
             while(i.hasNext())
             {
                 String text= i.next();
                     if(!parent.equals(text))
                     {
                         driver.switchTo().window(text);
                         waitFactory.waitForPageLoad();
                         String title=  commonFunctions.getTitleOfThePage();
                         flag = commonFunctions.compareText(title,commonFunctions.getTitleOfThePage());
                         driver.close();
                         driver.switchTo().window(parent);
                     }
                }
                } catch (Exception e) {
                log.error("unable to click on special asistance tab");
                 e.printStackTrace();
                }
                return flag;
                }
             public boolean clickOnClickHereLink(String Gbooking) throws Exception {
             boolean flag=false;
             try{
//                 WebElement drpTripleDoublePax = driver.findElement(By.xpath("(//div[@class='cmp-custom-drop-down   '])[2]"));
//                 commonFunctions.clickElementUsingJavaScript(drpTripleDoublePax);
                 waitFactory.visibilityOf(addPaxDropDownBtn);
                 commonFunctions.clickElementUsingJavaScript(addPaxDropDownBtn);
                 commonFunctions.getTextAndCompare(clickheretab,Gbooking);
                 String parent = driver.getWindowHandle();
                 commonFunctions.clickElementUsingJavaScript(clickheretab);
                 waitFactory.waitForPageLoad();
                 Set<String> servicepafges= driver.getWindowHandles();
                 Iterator<String> i = servicepafges.iterator();
                 while(i.hasNext()) {
                 String text = i.next();
                 if (!parent.equals(text)) {
                 driver.switchTo().window(text);
                 waitFactory.waitForPageLoad();
                 String title = commonFunctions.getTitleOfThePage();
                 flag = commonFunctions.compareText(title, commonFunctions.getTitleOfThePage());
                 driver.close();
                 driver.switchTo().window(parent);
                     }
                 }
                }
             catch (Exception e){
                 log.error("unable click on click here link");

             }
             return flag;

             }

    public boolean verifyVaccinatedFareDdown(DataTable vaccinatedtable) {
        boolean flag = false;
        try {
            List<String> tableData = vaccinatedtable.asList(String.class);
            log.info(tableData.get(0));
            this.commonFunctions.clickOnElement(specialFareDdown);
            for (int i = 0; i < specialFareOptions.size(); i++) {
                if (this.commonFunctions.compareText(specialFareOptions.get(i).getText(), tableData.get(0))) {
                    this.commonFunctions.clickOnElement(vaccinatedFare);
                    this.commonFunctions.clickOnElement(okBtn);
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to select unaccompanied minor special fare");
            e.printStackTrace();
        }
                return flag;
            }


    public int getIntegerValuesOf(String valueOfPaxType){

        return  Integer.parseInt(valueOfPaxType);
    }

    public String getPaxValuesOf(WebElement elementOfPaxType){

        return elementOfPaxType.findElement(By.xpath("//preceding-sibling::input")).getAttribute("value");
    }
    public String getPaxValuesOfminus(WebElement elementOfPaxType){

        return elementOfPaxType.findElement(By.xpath("//following-sibling::input")).getAttribute("value");
    }


    @FindBy(how = How.XPATH, using = "//input[@class='stepper-input__input']")
    private List<WebElement> paxValues1;
    public boolean verifyPlusMinuxButtonareClickable(){
        boolean flag = false;
        try{
            driver.navigate().refresh();
            waitFactory.waitForPageLoad();
            waitFactory.hardWait(2);
            waitFactory.visibilityOf(paxDropDown);
            paxDropDown.click();
            if(!waitFactory.visibilityOf(paxChildrenAddBtn)){
                paxDropDown.click();
                log.info("Clicked in Pax Drop Down");
            }else{
                log.info("Pax Dropdown is open");
            }
            paxChildrenAddBtn.click();
            String child_count = getPaxValuesOf(paxChildrenAddBtn);
            Assert.assertTrue(getIntegerValuesOf(child_count)>0);
            paxInfantAddBtn.click();
            String infant_count = getPaxValuesOf(paxInfantAddBtn);
            Assert.assertTrue(getIntegerValuesOf(infant_count)>0);
            paxAdultAddBtn.click();
            String adultvalue = getPaxValuesOf(paxAdultAddBtn);
            Assert.assertTrue(getIntegerValuesOf(adultvalue)>1);
            try{
                paxSeniorAddBtn.click();
                if(waitFactory.visibilityOf(seniorCitizenPopupOkayBtn)){
                    seniorCitizenPopupOkayBtn.click();
                    flag= true;
                }
                paxDropDown.click();
            }catch (Exception e){
                e.printStackTrace();
            }
            String seniorvalue = getPaxValuesOf(paxSeniorAddBtn);
            Assert.assertTrue(getIntegerValuesOf(seniorvalue)>1);
            log.info("After clicking on Add button for different pax");
            for(int i =0;i<paxValues1.size();i++){
                String paxName =driver.findElement(By.xpath("(//input[@class='stepper-input__input'])["+(i+1)+"]//../preceding-sibling::span")).getText();
                if(i==0 && (paxValues1.get(i).getAttribute("value")).contains("2")){
                    log.info(paxValues1.get(i).getAttribute("value")+" is for " +paxName);
                    flag= true;
                }else if (i!=0 && (paxValues1.get(i).getAttribute("value")).contains("1")){
                    log.info(paxValues1.get(i).getAttribute("value")+" is for " +paxName);
                    flag= true;

                }else {
                    flag= false;
                }
            }

            paxInfantMinusBtn.click();
            paxAdultMinusBtn.click();
            paxChildrenMinusBtn.click();
            paxSeniorMinusBtn.click();
            log.info("After clicking on Minus button for different pax");
            for(int i =0;i<paxValues1.size();i++){
                String paxName =driver.findElement(By.xpath("(//input[@class='stepper-input__input'])["+(i+1)+"]//../preceding-sibling::span")).getText();
                if(i==0 && paxName.contains("Adult")){
                    log.info(paxValues1.get(i).getAttribute("value")+" is for " +paxName);
                    flag= true;
                }else if (i!=0 && (paxValues1.get(i).getAttribute("value")).contains("0")){
                    log.info(paxValues1.get(i).getAttribute("value")+" is for " +paxName);
                    flag= true;

                }else {
                    flag= false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isSpeciaFareDisplayed(String specialfare) {
        boolean flag = false;
        try{
            WebElement ele = driver.findElement(By.cssSelector("div.widget-container__filter-bar__specailFare > div"));
            waitFactory.visibilityOf(ele);
            if(ele.getText().contains(specialfare)) {
                flag=true;
            }else{
                flag=false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  flag;
    }

    public boolean verifyFamilyAndFriendsSpecialFareDdown(DataTable table) {
        boolean flag = false;
        try {
            List<String> tableData = table.asList(String.class);
            log.info(tableData.get(0));
            commonFunctions.clickElementUsingJavaScript(specialFareDdown);
            this.commonFunctions.clickOnElement(familyAndFriends);
            commonFunctions.clickOnElement(specialFareDdown);
            for (int i = 0; i < specialFareOptions.size(); i++) {
                if (this.commonFunctions.compareText(specialFareOptions.get(i).getText(), tableData.get(0))) {
                    log.info("user should be able to validate that selected special fare is displayed");
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to select familyAndFriends special fare");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyfamilyfriendPaxCount(int countOfFamilyAndFriends) throws Exception {
        boolean countMatch = false;
        waitFactory.visibilityOf(paxDdown);
        commonFunctions.clickOnElement(paxDdown);
        waitFactory.hardWait(2);
        String value = paxDdown.getText();
        String[] count = value.split(" ");
        int paxCount = Integer.parseInt(count[0]);
        if (paxCount == countOfFamilyAndFriends) {
            countMatch = true;
            commonFunctions.clickElementUsingJavaScript(doneBtn);
        } else {
            log.info("Total count of FamilyAndFriends passenger does not not match");
        }
        return countMatch;
    }

    public boolean verifyunaccompaniedFareDdown(DataTable unccompaniedtable) {
        boolean flag = false;
        try {
            List<String> tableData = unccompaniedtable.asList(String.class);
            log.info(tableData.get(0));
            this.waitFactory.visibilityOf(specialFareDdown);
            commonFunctions.clickElementUsingJavaScript(specialFareDdown);
            for (int i = 0; i < specialFareOptions.size(); i++) {
                if (this.commonFunctions.compareText(specialFareOptions.get(i).getText(), tableData.get(0))) {
                    this.commonFunctions.clickOnElement(unaccompaniedFare);
                    log.info("user should be able to validate that selected special fare is displayed");
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to select unaccompanied minor special fare");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyUnaccompaniedPaxCount(int adult_count, int child_count) throws Exception {
        boolean countMatch = false;
        waitFactory.visibilityOf(paxDdown);
        commonFunctions.clickOnElement(paxDdown);
        waitFactory.hardWait(2);
        String value = paxDdown.getText();
        String[] count = value.split(" ");
        int paxCount = Integer.parseInt(count[0]);
        if (paxCount == (adult_count + child_count)) {
            countMatch = true;
            commonFunctions.clickElementUsingJavaScript(doneBtn);
        } else {
            log.info("Total count of unaccompanied pax count does not not match");
        }
        return countMatch;
    }

    public boolean verifyDate() throws Exception {
        boolean flag = false;
        try {

            driver.navigate().refresh();
            waitFactory.waitForPageLoad();
            waitFactory.hardWait(2);
            waitFactory.visibilityOf(TravelDate);
            waitFactory.hardWait(3);
            waitFactory.elementToBeClickable(TravelDate);

        } catch (Exception e) {
            e.getMessage();
        }
        WebElement travelDateEle = driver.findElement(By.cssSelector("[placeholder='Travel Dates']"));
        String currentdate = travelDateEle.getAttribute("value");
        String day1[] = currentdate.split(" ");
        int dayValue1 = Integer.parseInt(day1[0]);
        log.info(dayValue1);
        try {

            waitFactory.visibilityOf(driver.findElement(By.cssSelector("div.widget-container__filter-bar__specailFare > div")));
            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector(".faresLabel ")));
//           waitFactory.hardWait(1);
            log.info("clicked on special fare option");
            commonFunctions.clickOnElement(driver.findElement(By.xpath("(//li[@name='discountType'])[6]")));
            waitFactory.hardWait(1);
            log.info("selected vaccination");
            commonFunctions.clickElementUsingJavaScript(okBtn);
            waitFactory.hardWait(1);

        } catch (Exception e){
            log.error("Unable to select on special fare");
            e.printStackTrace();
        }

//        waitFactory.visibilityOf(travelDateEle);
        String currentdateAfterVac =travelDateEle.getAttribute("value");
        log.info(currentdateAfterVac);
        String day2[] = currentdateAfterVac.split(" ");
        int dayValue2 = Integer.parseInt(day2[0]);
        log.info(dayValue2);
        boolean difference = ((dayValue2 - dayValue1) == 15);
        log.info("difference"+difference);
        if(difference) {

            flag = true;
        } else {

            flag = false;
        }
        return flag;
    }


    public boolean selectSpecialFare() {
        boolean flag = false;
        try {
            flag = waitFactory.visibilityOf(specialFareDdown);
            commonFunctions.clickElementUsingJavaScript(specialFareDdown);
        }catch (Exception e) {
            log.error("Unable to click on special fare");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifySpecialFares() {
        boolean flag = false;
        try{
            Assert.assertTrue(specialFareOptions.get(0).getText().contains(armedforce));
            Assert.assertTrue(specialFareOptions.get(1).getText().contains(doctorandnurses));
            Assert.assertTrue(specialFareOptions.get(2).getText().contains(familyandfriends));
            Assert.assertTrue(specialFareOptions.get(3).getText().contains(UNMR));
            Assert.assertTrue(specialFareOptions.get(4).getText().contains(students));
            Assert.assertTrue(specialFareOptions.get(5).getText().contains(vaccinated));
            flag  = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}

