# /src/test/java/com/aws/main/AppTest.java 
Chrome ile test edecekler için AppTest.java class 'nýn içinde :  

- **"C:/Users/MONSTER/Desktop/chromedriver_win32/chromedriver.exe"**

- **chromedriver.exe dosyasýný kendi projenizin kök dosyasýna yerleþtirdiðiniz yere göre url'i deðiþtirin**


	**@BeforeTest**
	**public void Start(){**
	**System.setProperty("webdriver.chrome.driver",**"C:/Users/MONSTER/Desktop/chromedriver_win32/chromedriver.exe");
		**driver = new ChromeDriver();**
		**System.out.println("Test Baþladý ...");**
	**}**