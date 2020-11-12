package com.testApi.springBootTestApi.Tests;

import com.testApi.springBootTestApi.Entities.Command;
import com.testApi.springBootTestApi.Entities.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class GenericTest {

    public WebDriver webDriver;

    public Parameters Testing(Parameters parameters){

        String browserName=parameters.getBrowser();
        if(parameters.getBrowser().equals("Chrome")){
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
                webDriver = new ChromeDriver();
        }else if(browserName.equals("Firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        }
        webDriver.get(parameters.getUrl());
        Parameters incorrectParams = new Parameters();
        List<Command> incorrectcmndList = new ArrayList<Command>();

        for(Command command:parameters.getCommands()){
            WebElement webElement = null;

            if(command.getTargetType().equals("id")){
                webElement= new WebDriverWait(webDriver, 20)
                        .until(driver -> driver.findElement(By.id(command.getTarget())));
                        //webDriver.findElement(By.id(command.getTarget()));
            }else if(command.getTargetType().equals("classname")){
                long firstDate=System.currentTimeMillis();
                while(webDriver.findElements(By.className(command.getTarget())).size() == 0){
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }
                webElement=webDriver.findElement(By.className(command.getTarget()));
            }else if(command.getTargetType().equals("name")){
                long firstDate=System.currentTimeMillis();
                while(webDriver.findElements(By.name(command.getTarget())).size() == 0){
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }
                webElement=webDriver.findElement(By.name(command.getTarget()));
            }else if(command.getTargetType().equals("tagname")){
                long firstDate=System.currentTimeMillis();
                while(webDriver.findElements(By.tagName(command.getTarget())).size() == 0){
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }

                webElement=webDriver.findElement(By.tagName(command.getTarget()));
            }else if(command.getTargetType().equals("linktext")){
                long firstDate=System.currentTimeMillis();
                while(webDriver.findElements(By.linkText(command.getTarget())).size() == 0){
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }

                webElement=webDriver.findElement(By.linkText(command.getTarget()));

            }else if(command.getTargetType().equals("partiallinktext")){
                long firstDate=System.currentTimeMillis();
                while(webDriver.findElements(By.partialLinkText(command.getTarget())).size() == 0){
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }
                webElement=webDriver.findElement(By.partialLinkText(command.getTarget()));
            }else if(command.getTargetType().equals("xpath")){
                long firstDate=System.currentTimeMillis();/*
                int scrollpixel=0;
                JavascriptExecutor js = ((JavascriptExecutor) webDriver);*/
                while(webDriver.findElements(By.xpath(command.getTarget())).size() == 0){
                    /*
                    int contentHeight = ((Number) js.executeScript("return window.innerHeight")).intValue();
                    if(scrollpixel>contentHeight)
                        break;
                    js.executeScript("window.focus();window.scrollTo(0"+scrollpixel+" );");
                    scrollpixel+=10;
*/
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }
                webElement=webDriver.findElement(By.xpath(command.getTarget()));

            }else if(command.getTargetType().equals("cssselector")){
                long firstDate=System.currentTimeMillis();
                while(webDriver.findElements(By.cssSelector(command.getTarget())).size() == 0){
                    if(System.currentTimeMillis()-firstDate>20000)
                        break;
                }
                webElement=webDriver.findElement(By.cssSelector(command.getTarget()));

            }else if(command.getTargetType().equals("scroll")){
                JavascriptExecutor js = ((JavascriptExecutor) webDriver);
                js.executeScript(command.getValue());
            }


            if(command.getCommand().equals("title")){
                if(!webDriver.getTitle().equals(command.getValue())){
                    incorrectcmndList.add(command);
                }
            }else if(command.getCommand().equals("click")){
                webElement.click();

            }else if(command.getCommand().equals("text")){
                String text=webElement.getText();
                System.out.println(text);
                if(!text.equals(command.getValue())){
                    incorrectcmndList.add(command);
                }
            }else if(command.getCommand().equals("sendkeys")){
                webElement.sendKeys(command.getValue());
            }else if(command.getCommand().equals("submit")){
                webElement.submit();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        incorrectParams.setCommands(incorrectcmndList);
        incorrectParams.setBrowser(parameters.getBrowser());
        incorrectParams.setUrl(parameters.getUrl());

        webDriver.close();
        return incorrectParams;
    }


}
