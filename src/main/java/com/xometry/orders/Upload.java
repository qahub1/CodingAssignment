package com.xometry.orders;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Upload {
	WebDriver driver;
	public static final String APPLY_FINISH_BUTTON = "[data-test-id='apply-finish-buttons']";
	public static final String APPLY_PART_BUTTON = "[data-test-id='apply-to-part-button']";
	public static final String APPLY_PARTS_BUTTONS = "[data-test-id='apply-to-parts-buttons']";
	public static final String FINISH_TAB = "[data-test-id='finishes-tab']";
	public static final String MATERIAL_TAB = "[data-test-id='material-tab']";
	public static final String TOTAL_COST = "[ng-show='!quote.manual']";
	public static final String UPLOAD_BUTTON = "#parts-upload-btn";

	public Upload(WebDriver driver) throws AWTException {
		this.driver = driver;
	}

	public void clickBrowseUpload(String uploadFileScript) throws Exception {
		System.out.println("Starting uploading the file:" + uploadFileScript);
		findElementAndClick(By.cssSelector(UPLOAD_BUTTON));

		Runtime.getRuntime().exec(uploadFileScript);
		System.out.println("Finish uploading the file:" + uploadFileScript);
		TimeUnit.SECONDS.sleep(5);
	}

	public void clickManufacturing(String label, int i) throws Exception {
		System.out.println("Starting clickManufacturing element:" + label);
		Util.letPageLoad(driver);
		findElementsAndClick(By.xpath(Util.getLabelContainsText(label)));
		TimeUnit.SECONDS.sleep(2);
		clickManufacturingSheetMetal(label, i);
		System.out.println("Finished clickManufacturing element:" + label);
	}

	public void clickManufacturingSheetMetal(String manfacturing, int partCount) throws Exception {
		System.out.println("Starting clickManufacturingSheetMetal element:" + manfacturing);
		if ("Sheet Metal".equals(manfacturing)) {
			// select material
			moveToNthElement(By.xpath(Util.getLabelContainsText("Custom (See Notes)")), 0);
			// apply material
			clickMaterialApply(partCount);
			// close Material Tab
			gotoMaterialTab(partCount);
			// open Material Tab
			gotoMaterialTab(partCount);
		}
		System.out.println("Finished clickManufacturingSheetMetal element:" + manfacturing);
	}

	public void clickFinishLabel(String label, int i) throws Exception {
		System.out.println("Starting clicking the clickFinishLabel element:" + label);
		Util.letPageLoad(driver);
		try {
			moveToNthElement(By.xpath(Util.getLabelContainsText("Selected Parts")), 0);
		} catch (Exception e) {
			moveToNthElement(By.xpath(Util.getLabelContainsText("Standard")), 0);
		}
		TimeUnit.SECONDS.sleep(2);

		if ("Dyed Black".equals(label)) {
			findElementsAndClickNth(By.xpath(Util.getLabelContainsText(label)), -1);
		} else {
			findElementsAndClick(By.xpath(Util.getLabelContainsText(label)));
		}
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Finished Clicking the clickFinishLabel element:" + label);
	}

	public void clickMaterialLabel(String label, int i) throws Exception {
		System.out.println("Starting clicking the clickMaterialLabel element:" + label);
		Util.letPageLoad(driver);
		try {
			moveToNthElement(By.xpath(Util.getLabelContainsText("Selected Parts")), -1);
		} catch (Exception e) {
			moveToNthElement(By.xpath(Util.getLabelContainsText("Custom (See Notes)")), 0);
		}
		TimeUnit.SECONDS.sleep(1);

		if ("Casting".equals(label)) {
			findElementsAndClickNth(By.xpath(Util.getLabelContainsText(label)), -1);
		} else {
			findElementsAndClick(By.xpath(Util.getLabelContainsText(label)));
		}
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Finished Clicking the clickMaterialLabel element:" + label);
	}

	public void clickApply(String cssSelector, int i) throws Exception {
		System.out.println("Starting clicking the apply element:" + cssSelector);
		WebElement ele = findElementsAndClick(By.cssSelector(cssSelector));
		ele.sendKeys(Keys.PAGE_UP);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Finished clicking the apply element:" + cssSelector);
	}

	public void clickMaterialApply(int i) throws Exception {
		if (i == 1)
			clickApply(Upload.APPLY_PART_BUTTON, i);
		else
			clickApply(Upload.APPLY_PARTS_BUTTONS, i);
	}

	public void gotoMaterialTab(int i) throws Exception {
		if (i > 1) {
			System.out.println("Starting material tab:" + MATERIAL_TAB + " " + i);
			Util.letPageLoad(driver);
			findElementsAndClick(By.cssSelector(MATERIAL_TAB));
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Finished material tab:" + MATERIAL_TAB + " " + i);
		}
	}

	public void gotoFinishTab(int i) throws Exception {
		System.out.println("Starting finish tab:" + FINISH_TAB + " " + i);
		Util.letPageLoad(driver);
		findElementsAndClick(By.cssSelector(FINISH_TAB));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Finished clicking finish the tab:" + FINISH_TAB + " " + i);
	}

	private WebElement findElementsAndClick(By by) throws Exception {
		System.out.println("Starting findElements And Click:" + by);
		WebElement ele = null;
		List<WebElement> elements = driver.findElements(by);
		System.out.println("Clicking the element:" + by + " " + elements.size());
		ele = elements.get(elements.size() - 1);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));

		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
		// ele.click();
		System.out.println("Fnished findElements And Click:" + by);
		return ele;
	}

	private WebElement findElementsAndClickNth(By by, int n) throws Exception {
		System.out.println("Starting findElements And Click nth:" + by);
		WebElement ele = null;
		List<WebElement> elements = driver.findElements(by);
		System.out.println("Clicking the element:" + by + " " + elements.size());
		ele = elements.get(elements.size() - 1 +n);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));

		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
		// ele.click();
		System.out.println("Fnished findElements And Click nth:" + by);
		return ele;
	}

	public WebElement moveToNthElement(By by, int n) throws Exception {
		System.out.println("Starting moveToNthElement:" + by);
		WebElement ele = null;
		List<WebElement> elements = driver.findElements(by);
		System.out.println("Clicking the element:" + by + " " + elements.size());
		ele = elements.get(elements.size() - 1 + n);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));

		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
		System.out.println("Finished moveToNthElement:" + by);
		return ele;
	}

	private void findElementAndClick(By by) {
		System.out.println("Starting findElementAndClick element:" + by);
		Util.letPageLoad(driver);
		WebElement element = driver.findElement(by);
		Util.waitForElementToBeClickable(driver, element);
		element.click();
		System.out.println("Finished findElementAndClick element:" + by);
	}

	public String getTotal() throws Exception {
		System.out.println("Starting getTotal");
		Util.letPageLoad(driver);
		WebElement element = driver.findElement(By.cssSelector(TOTAL_COST));
		Util.waitForElementToBeClickable(driver, element);
		return element.getText();
	}
}
