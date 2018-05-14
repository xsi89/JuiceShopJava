package JuiceShopAutomation.automation;

import java.io.FileNotFoundException;


import org.junit.Test;



public class TestRunTest {

	public static  String JuiceShopBaseURL(String BaseURL)
	{
		//BaseURL="http://192.168.1.231:3000/";
		BaseURL="https://juice-shop.herokuapp.com/";
		return BaseURL;
	}
	public static String JuiceShopBaseSourceURL(String PageSourceURL)
	{
		PageSourceURL="view-source:https://juice-shop.herokuapp.com";
		return PageSourceURL;
	}

	/*JuiceShop Challange #Score Board*/
	//@Test
	public void ScoreBoard()
	{
		JuiceShopMethods.initalize();
		JuiceShopMethods.openBrowser(TestRunTest.JuiceShopBaseURL("BaseURL"));
		JuiceShopMethods.scoreBoard();
		JuiceShopMethods.waitForLoad();
		JuiceShopMethods.Quit();
		JuiceShopMethods.waitForLoad();
	}

	/*JuiceShop Challange #XSS Tier 0*/
	//@Test
	public void XSS_Tier0()
	{

		JuiceShopMethods.initalize();
		JuiceShopMethods.openBrowser(TestRunTest.JuiceShopBaseURL("BaseURL"));
		JuiceShopMethods.XSS_Tier_0();
		JuiceShopMethods.waitForLoad();
		JuiceShopMethods.Quit();

	}

	/* Under progress need to be done with stuff before*/
//	@Test
	public void XSS_Tier1()

	{
//		JuiceShopMethods.initalize();
//		JuiceShopMethods.openBrowser(TestRunTest.JuiceShopBaseURL("BaseURL"));
//		JuiceShopMethods.XSS_Tier_1();
//		JuiceShopMethods.waitForLoad();
//		JuiceShopMethods.Quit();

	}

	/*JuiceShop Challange #Admin Section*/
//	@Test
	public void AdminSection ()
	{
		JuiceShopMethods.addDataToLists("addDataToLists");
		JuiceShopMethods.adminSection();
	}

	/*JuiceShop Challange #Confidential Document*/
@Test
	public void ConfidentalDocument() throws InterruptedException
	{
		JuiceShopMethods.initalize();
		JuiceShopMethods.ConfidentalDocument();
		JuiceShopMethods.findFolders();
	}





}
