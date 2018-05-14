package JuiceShopAutomation.automation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class JuiceShopMethods
{

	  private static WebDriver driver;
	  private static String pathChar="#";
	  private static String backslash="/";
	  private static String PartPath="~ / ";


	  public static void addDataToLists(String addDataToLists)
	  {

		  JuiceShopDataLists.XSSList.add("<script>alert(\"XSS\")</script>");
		  JuiceShopDataLists.XSSList.add("<script>alert(\"XSS1\")</script>");
		  JuiceShopDataLists.adminSectionList.add("administration");
		  JuiceShopDataLists.adminSectionList.add("administrator");
		  JuiceShopDataLists.adminSectionList.add("admin");
		  JuiceShopDataLists.adminSectionList.add("controlpanel");
		  JuiceShopDataLists.adminSectionList.add("adminpage");


	  }

		@Before
		public static WebDriver initalize( )
		{
			JuiceShopMethods.addDataToLists("addDataToLists");
			try
				{
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				}
			catch(IOException e)
				{
					e.printStackTrace();
				}


			//Adds data to the XSSList Array


			return driver;


		}

	    @Test
	    public static void openBrowser(String Navigate)
	    {


	        System.setProperty("driver.wedriver.chrome", "C://Webdrivers//chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.get(TestRunTest.JuiceShopBaseURL("Navigate"));
	       // driver.manage().window().maximize();

	    }

	    public static void waitForLoad()
	    {

			try {
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
	    }

		public  static void scoreBoard()
		{
			ArrayList<String> ScoreBoard=new ArrayList<String>();

			String pageSource = driver.getPageSource();


			String regex = "([^a-zA-Z\\\\d]|$)score-boar([^a-zA-Z\\\\d]|)?d";
			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(pageSource);

			 while (matcher.find())
			 {
			        String TempString1=matcher.group(0).toString();
			        String TempString2=TempString1.replace(">"+"\"&doublequote;\"","");
			        ScoreBoard.add(TempString2);
			 }


			 String ScoreBoardURL=TestRunTest.JuiceShopBaseURL("BaseURL").toString()+pathChar+ScoreBoard.get(0).toString();
			 driver.navigate().to(ScoreBoardURL);

		}

		public static void XSS_Tier_0()
		{

			 driver.findElement(By.xpath("//input[@type='search']")).sendKeys(JuiceShopDataLists.XSSList.get(0)); //Take the first ArrayElement from XSSList
			 driver.findElement(By.xpath("//button[@id='searchButton']/span")).click();

			Alert alert=driver.switchTo().alert();
			String xssMsg =alert.getText();

			 if (xssMsg == "XSS")
			  {
			   System.out.print("Attack is detected");
			   alert.dismiss();

			  }
			  else
			  {
			   System.out.print("No XSS match");
			  }
	      }

		public static void XSS_Tier_1()
		{
//			XSSList.add("<script>alert(\"XSS\")</script>");
			driver.findElement(By.xpath("//input[@type='search']")).sendKeys(JuiceShopDataLists.XSSList.get(0));
			driver.findElement(By.xpath("//button[@id='searchButton']/span")).click();

		}

		public static void Quit()
		{
			driver.close();
		}

		public static void adminSection()
		{
			 driver = new ChromeDriver();
			 	for(int i = 0; i < JuiceShopDataLists.adminSectionList.size(); i++)
				{

					System.setProperty("driver.wedriver.chrome", "C://Webdrivers//chromedriver.exe");
					String x=JuiceShopDataLists.adminSectionList.get(i).toString();
					String URLString="https://juice-shop.herokuapp.com/#/"+(x);
					driver.navigate().to(TestRunTest.JuiceShopBaseURL("BaseURL")+pathChar+(x));

					waitForLoad();

					if(driver.getCurrentUrl()!="https://juice-shop.herokuapp.com/#/search")
					{
						System.out.println(driver.getCurrentUrl());
					}
					else
						{
							System.out.println(driver.getCurrentUrl());
							waitForLoad();
						}
				}
			 	Quit();
		}

		public static void ConfidentalDocument()
	{


		Scanner scanner = null;
		try
		{
		scanner = new Scanner(new File("list1.txt"));
		} catch (FileNotFoundException e)

		{
			e.printStackTrace();
		}
//		System.out.println("a");
        while (scanner.hasNext())
        {
            String line = scanner.nextLine();

            JuiceShopDataLists.ConfDen.add(line);
            //System.out.println("added");
        }
	}

		public static void findFolders() throws InterruptedException
		{



			System.setProperty("driver.wedriver.chrome", "C://Webdrivers//chromedriver.exe");
			driver = new ChromeDriver();
			Thread.sleep(200);

			for(String label:JuiceShopDataLists.ConfDen)
			{
			//System.out.println(label);
			}
			for(int z = 0; z < JuiceShopDataLists.ConfDen.size(); z++)
			{
				String x=JuiceShopDataLists.ConfDen.get(z).toString();
				driver.navigate().to(TestRunTest.JuiceShopBaseURL("BaseURL")+(x)+backslash);
				Thread.sleep(500);

				if(driver.getCurrentUrl()!="https://juice-shop.herokuapp.com/search")
				{


					String src=driver.getPageSource();
					final String regex = "\".ftp.+\\w+\"";
					final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
					final Matcher matcher = pattern.matcher(src);

					while (matcher.find())
						{
					    System.out.println(matcher.group(0));
					    for (int i = 1; i <= matcher.groupCount(); i++)
					    {
					      //  System.out.println("Group " + i + ": " + matcher.group(i));
					    }
					}


				}
				else
					{
						System.out.println("Loop z"+driver.getCurrentUrl());
					}
				Quit();
			}

		}
}


















