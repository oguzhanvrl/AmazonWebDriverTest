# /src/test/java/com/aws/main/AppTest.java 
Chrome ile test edecekler i�in AppTest.java class 'n�n i�inde :  

- **"C:/Users/MONSTER/Desktop/chromedriver_win32/chromedriver.exe"**

- **chromedriver.exe dosyas�n� kendi projenizin k�k dosyas�na yerle�tirdi�iniz yere g�re url'i de�i�tirin**


	**@BeforeTest**
	**public void Start(){**
	**System.setProperty("webdriver.chrome.driver",**"C:/Users/MONSTER/Desktop/chromedriver_win32/chromedriver.exe");
		**driver = new ChromeDriver();**
		**System.out.println("Test Ba�lad� ...");**
	**}**