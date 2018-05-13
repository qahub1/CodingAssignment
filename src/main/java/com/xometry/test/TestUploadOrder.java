package com.xometry.test;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.xometry.orders.DriverFactory;
import com.xometry.orders.Home;
import com.xometry.orders.Login;
import com.xometry.orders.Upload;
import com.xometry.orders.Util;

import junit.framework.Assert;

public class TestUploadOrder {
	private static final String RESOURCE_FOLDER = "src/main/resources/";
	private static final String HOME_URL = "https://www.xometry.com";
	private static final String USER_NAME = "topucherlap@gmail.com";
	private static final String PASSWORD = "q@Test3P@ss";

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	}

	@Test(priority = 0)
	public void testChromeUpload() throws Exception {
		// Get chrome driver
		WebDriver driver = DriverFactory.getDriver(DriverFactory.CHROME);

		// Go to home page
		driver.get(HOME_URL);
		driver.manage().window().maximize();

		// Click log in
		new Home(driver).clickLoginLink();

		// submit user name and password
		new Login(driver).clickLogin(USER_NAME, PASSWORD);

		JSONArray partArray = Util.getJSONFile(new File(RESOURCE_FOLDER + "parts.json"));
		int partCount = 1;
		for (Object o : partArray) {
			JSONObject partJson = (JSONObject) o;
			String part = (String) partJson.get("Part");
			String manfacturing = (String) partJson.get("Manufacturing Process");
			String material = (String) partJson.get("Material");
			String finish = (String) partJson.get("Finish");
			String uploadAutoITScript = (String) partJson.get("AutoITScript");
			String cost = (String) partJson.get("Cost");
			System.out.println(part + "  " + manfacturing + "  " + material + "  " + finish + " " + uploadAutoITScript + " " + cost);

			Upload uploadPage = new Upload(driver);
			// upload the file
			uploadPage.clickBrowseUpload(RESOURCE_FOLDER + uploadAutoITScript);

			// goto Material Tab
			uploadPage.gotoMaterialTab(partCount);

			// select manufacturing
			uploadPage.clickManufacturing(manfacturing, partCount);

			// select material
			uploadPage.clickMaterialLabel(material, partCount);

			// apply material
			uploadPage.clickMaterialApply(partCount);

			// goto finish Tab
			uploadPage.gotoFinishTab(partCount);

			if (!"Standard".equals(finish)) {
				// select material
				uploadPage.clickFinishLabel(finish, partCount);
				// apply finish
				uploadPage.clickApply(Upload.APPLY_FINISH_BUTTON, partCount);
			}

			// select material
			uploadPage.gotoFinishTab(partCount);

			// Verify total price
			System.out.println("******cost: " + partCount+" "+uploadPage.getTotal() + " " + cost);
			Assert.assertTrue(uploadPage.getTotal().equals(cost));
			partCount++;
		}

		// Close browser
		driver.close();
	}

}
