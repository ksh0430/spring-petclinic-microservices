var webdriver = require('selenium-webdriver');
var username = process.env.SAUCE_USERNAME,
	accessKey = process.env.SAUCE_ACCESS_KEY;
// var driver = new webdriver.Builder()
// 				.withCapabilities(webdriver.Capabilities.chrome())
// 				.build();

var driver = new webdriver.Builder()
				.withCapabilities({
					'browserName' : 'chrome',
					'platform' : 'Windows XP',
					'version' : '43.0',
					'username' : username,
					'accessKey' : accessKey
				}).
				usingServer("http://" + username + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub")
				.build();

var By = webdriver.By,
	until = webdriver.until;

console.log('hello')
console.log(process.env.SAUCE_USERNAME)
console.log(process.env.SAUCE_APIKEY)

var URL = process.env.APP_URL;

//Scenario
//1. Connect to front page: http://api-gateway-sp.mybluemix.net/
driver.get(URL);
driver.sleep(1000);

// 2. Information display test
// 2-1. Click OWNERS, ALL
driver.findElement(By.xpath('//div[@id="main-navbar"]/ul[1]/li[2]/a[1]')).click();
driver.findElement(By.xpath('//*[@id="main-navbar"]/ul/li[2]/ul/li[1]/a')).click();

// 2-2. Check all owners are displayed
driver.sleep(1000);
check_owners();

// 2-3. Click Jean Coleman
driver.sleep(3000);
driver.findElement(By.xpath('/html/body/div/div/div/ui-view/owner-list/table/tbody/tr[6]/td[1]/a')).click();

// 2-4. Check pets and visits are displayed
driver.sleep(3000);
check_pets_visits();

// 2-5. Click Veterinarians
driver.sleep(3000);
driver.findElement(By.xpath('//*[@id="main-navbar"]/ul/li[3]/a')).click();

// 2-6. Check Veterinarians are displayed
driver.sleep(3000);
check_veterinarians();
	
driver.quit();

// 3. Add Owner test
	// Click OWNERS, REGISTER
	// Input owner information
	// Click new owner
	// Check information
// 4. Add Pet test
	// Click Add New Pet
	// Input pet information
	// Click owner again
	// Check information
// 5. Add Visit test
	// Click Add Visit
	// Input visit information
	// Click owner again
	// Check information



function check_veterinarians() {
	console.log('function check_veterinarians');
}

function check_pets_visits() {
	console.log('function check_pets_visits');
}

function check_owners () {
	
	// var element = driver.findElement(By.xpath('/html/body/div/div/div/ui-view/owner-list/table/tbody/tr[1]/td[1]/a'));
	// element.isDisplayed().then(text => console.log('Text is '+text));
	// element.getText().then(text => console.log('Text is '+text));

	driver.sleep(1000);

	// console.log("hi"+element.isDisplayed());

	var owners_table = driver.findElement(By.xpath('/html/body/div/div/div/ui-view/owner-list/table'));
	var owner_list = [];
	var owner_list_origin = ['George Franklin','Betty Davis','Eduardo Rodriquez','Harold Davis','Peter McTavish','Jean Coleman','Jeff Black','Maria Escobito','David Schroeder','Carlos Estaban','Jin Kong'];
	var owner_list_identical = false;

	owners_table.isDisplayed().then(function(value) {

		for (var i=1; i<=owner_list_origin.length; i++) {
			var owner_name = driver.findElement(By.xpath('/html/body/div/div/div/ui-view/owner-list/table/tbody/tr['+i+']/td[1]/a'));
			owner_name.getText().then(text => {
				owner_list.push(text); 
				console.log('owner pushed => '+text);

				if (i==owner_list_origin.length+1) {
					if (arraysAreIdentical(owner_list, owner_list_origin)==true) {
						owner_list_identical = true;
					} else {
						owner_list_identical = false;
					}					
				}

				if (owner_list_identical == true) {
					console.log('all owners are displayed =>' + owner_list_identical);
				}  
			});
		}
		console.log(value);	//success
	}, function(reason) {	
		console.log(reason); //error
	});
}

function arraysAreIdentical(arr1, arr2){
    if (arr1.length !== arr2.length) return false;
    for (var i = 0, len = arr1.length; i < len; i++){
        if (arr1[i] !== arr2[i]){
            return false;
        }
    }
    return true; 
}
