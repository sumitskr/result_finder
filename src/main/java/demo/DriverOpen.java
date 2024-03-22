package demo;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
public class DriverOpen {
	private static final Logger logger = LogManager.getLogger(DriverOpen.class);

    private int start_index = 60826836;
	private int end_index = 60826840;
	private boolean loggingEnabled = true;
	private boolean headless = true;
    
    private void logInfo(String message) {
        if (loggingEnabled) {
            logger.info(message);
        }
    }
    
    public DriverOpen(int start_index,int end_index,boolean logging,boolean headless) {
	    this.start_index = start_index;
	    this.end_index = end_index;
	    this.loggingEnabled = logging;
	    this.headless = headless;
    }

    public DriverOpen() {
		
	}

	public void start_finding() {
		
		// Create FirefoxOptions object
    	logInfo("Driver Opened");
        FirefoxOptions options = new FirefoxOptions();
        
        // Enable headless mode
        logInfo("With headless : "+String.valueOf(headless));
        options.setHeadless(headless);
        WebDriver driver = new FirefoxDriver(options);
        driver.get("http://resultsarchives.nic.in/cbseresults/cbseresults2016/neet/neet_2016.htm"); //reduced time no driver open for every iteration
        while(start_index <= end_index){
        	int roll = start_index;
        	try {
	    		driver.findElement(By.name("regno")).sendKeys(String.valueOf(roll));
	            driver.findElement(By.name("dob")).sendKeys("02/09/1997");
	         // Click on the button with the name "B1"
	            driver.findElement(By.name("B1")).click();
	
	            // Get the page source after clicking the button and print it
	            String pageSourceAfterClick = driver.getPageSource();
	            if (pageSourceAfterClick.contains("Particulars of the candidate")==true){
	            	WebElement element = driver.findElement(By.xpath("/html/body/table[5]/tbody/tr[2]/td[2]/b"));
	                String elementText = element.getText();
	            	logInfo("Candidate name : "+elementText);
	            	String textToSave = "Candidate name : "+elementText +"roll : "+roll+"\n";
	            	logInfo(textToSave);
	                String filePath = "result_output.txt";
	
	                try (FileWriter writer = new FileWriter(filePath,true)) {
	                    writer.write(textToSave);
	                    logInfo("Text saved to file successfully.");
	                } catch (IOException e) {
	                    logInfo("[Script Issue]  An error occurred while saving text to file: " + e.getMessage());
	                }
	            }
			    else {
			    	logInfo("Candidate not found for : "+ String.valueOf(roll));
			    }
	            driver.navigate().back();
	            start_index++;
        	}
        	catch(Exception err) {
        		logInfo("[Script Issue] Unable to load page");
        		driver.get("http://resultsarchives.nic.in/cbseresults/cbseresults2016/neet/neet_2016.htm");
        	}
        }
    }
}
