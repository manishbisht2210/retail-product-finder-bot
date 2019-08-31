======================================================

**Retail Product Finder**

`An Online portal for managing retail bots`

======================================================

**Tech Stack**
* Spring boot 2.1.0

* Angular 7.1.0

======================================================

**Steps to run** 

1. Go to `retail-product-finder-service` and fire **mvn spring-boot:run**
2. Go to `retail-product-finder-ui` and run **npm install** and then fire **ng serve** on the same path
3. Go to `http://localhost:4200`
4. Below are user details

======================================================

**Tenant 1**

BigBazaar 

**User 1**

`UserName("mbagul");`

`Password("bagul@123");`

`Roles(ADMIN, USER);`

**User 2**

`UserName("akhajuria");`

`Password("khajuria@123");`

`Roles(ADMIN);`

======================================================

**Tenant 2**

DMart	

**User 1**

`UserName("pnair");`

`Password("nair@123");`

Roles(user, customer);

**User 2**

`UserName("mbisht");`

`Password("bisht@123");`

`Roles(user);`

======================================================

**To see client side documentation**
1. Go to `retail-product-finder-ui/documentation` and fire **http-server** 
   (you need to first run `npm install -g http-server`)

======================================================

**To see server side documentation**
1. Go to `http://localhost:8080/retail-product-finder-service/swagger-ui.html`

======================================================
