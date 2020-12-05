# Restaurants-Finder

## App logo

![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/logo.png)
Credits: freepick.com

## ER - Diagram
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Blank%20diagram%20(2).png)


## Team Members
1. Raviteja Pagidoju 
2. Shiva Rama Krishna Vodnala
3. Gopichand Bandarupalli
4. Prasad Golla Durga

## Supported Version:
* Android Marshmallow(6.0) or above
* This application is supported with minimum sdk version - API 23.
* Gradle version : 4.10.1
* Android plugin version: 3.3.1

## Installation Steps:
* Installation of this app is much more easier with just few steps
* The User can download the apk file of the Restaurant Finder App.
* By clicking on the apk file, It install's the app in your android mobile.
* Then, the the App gets installed the user can click on the App icon to open it.
* Finally, the user can access the features and register into the app and check and wishlist the restaurants they want according to their preference.

## Credentials:
* User can browse the listed restaurnats without registering for an account. 
* However, If user needs to wishlist his favourite restaurant they needs to sign in to store his favourite restaurnats.
* Signing up for an account is simple, user can register for a free account. 
* After sign up he can log into to view/add favourite restaurants. 
* Admin can also create for an account. 
* With admin credentials, they can create or delete new restaurant details. 

## Testing credentials
### Credentails
#### User Credentials:
* email: chanduhvg@gmail.com
* password: bgc@2468
#### Admin Credentials:
* email: srkrao547@gmail.com
* password: srk@2468

# Project Description

## Activities:
* As we know there are lot of food lovers out there. Everyone wants to eat the food which they like and also which falls in their budget. In a diverse place like Maryville there are students/people from across the world. Each of them has their own taste with respective to food. There is no such app currently which provides a platform to the user where he can find all the restaurants based on the style of food (Ex: American, Indian, Italian, Mexican) and also within their budget, location and Name.
* We are trying to provide a solution to such people using our app Restaurant Finder. It runs on Android platform and it’s free to download. Any person who register in our app can search restaurants directly or using a filter where they can choose food style, budget, location, Name.
* Intially, we have listed the details of various restaurants where each restaurant is categorized based on its food type. At one point of time user want to wishlist the restaurants he likes, so he needs to log in with registered credentials. Then we are storing the details of the favourite restaurants in cloud database called firebase. when user logs out and comes back he can view the saved restaurants.


## Features:
1. Restaurant List:
* Any user with/without login can open the app and see all the restaurant and its details
* User can also provide the feedback to the restaurant anonymously.

2. FAVOURITE RESTAURANT LIST:
* Once User logged in ,can add any restaurant to his favorites.
* User can view all his favourite restaurants.
* User can remove restaurant from his favorites at any time.

3. USER/ADMIN LOGIN :
* User and Admin have different login pages where he can navigate and login by entering the credentials.
* User and Admin will be displayed with their respective home screened once logged in.
* User will only be asked to login if he wants to add a restaurant as his favourite.

4. ADMIN ADD/REMOVE RESTAURANT:
* Admin once logged in can be able to add a new restaurant or delete a existing restaurant.
* Admin have to provide all the required details of the restaurant in-order to add it to database.
* Admin can also visit his home page as regular user and can have his favorite restaurants.

5. FILTER RESTAURANTS:
* Irrespective of user login, he/she can view all the restaurants based on the filter as well.
* Filter is based on Food style, City, State and Zip code.
* User can select any filter and following restaurants will be displayed.
* User cannot enter any filter detail manually, instead he should choose from the available options.

6. FORGOT PASSWORD:
* Registered user who doesn’t remember the password can reset it by forgot password option.
* User should enter registered email ID and password reset link will be sent to that email where user can reset his/her password and try logging in again.

## Functionality:
* We use the database to store the users favourite list.

## Persists on the app:
* email, password, restaurant Preference.
* For maintaining persistence of the data we have used Firebase cloud(Realtime) as backend service in our application. It basically stores data in the form of JSON data.
1. FavouriteRestaurants
2. Restaurants
3. Users

## Database Used: Firebase
* We are using the Firebase for storing the list of all restaurants and their location and its food type.
* Since we are developing a restaurant finder application, we need to have a database which stores credentials of the users, admins as well as the restaurants which we need to display to the user. 
* So, we have chosen Firebase which integrates with our android application. 
* The main reason to choose firebase is, it provides database as well as authentication to our application. 
* We are using firebase authentication to register and login with the credentials of the user.
* Here user has a chance to sign up for an account by entering his credentials. 
* Firebase then stores and secures the details of the user. 
* Whenever user tries to log in, firebase authenticates the username and password, if its valid it then redirects to homepage of the user else it redirects to same login page.

## API/Library
* We have used two api’s in our application
* Firebase Auth which is used for authentication and for registering users.
* Google gms api for sending password reset link to the respective email.

## Activity flow diagram

![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/App%20Flow.png)


## Activities List using for the App:

### APP STARTUP Page – 
* This is the start page of the App which provides all restaurants listed where user can look. Also he can choose filter button in which he can,select a criteria for restaurants. For lookup of restaurants users need not require to login.

### USER LOGIN – 
* This page allows user to login into Application and it is asked only when user wants to add a restaurant to his favorite list.

### ADMIN LOGIN – 
* This page allows Admin to login into Application.

### FORGOT PASSWORD – 
•	This page asks for already registered user Email ID to send a new password.
### Verification mail: 
* After clicking confirm a reset password link will be sent to the mail Id provided to verify the user as shown.
### SIGNUP – 
This page allows a user to register for the first Time.

### ADMIN-ADD/DELETE RESTAURANT – 
* This page is exclusively for the Admin where he can manage the restaurants that is he can add/delete a restaurant.
### ADMIN-ADD RESTAURANT –
•	When the admin clicks on the above activity, admin can enter new restaurant details and add it.
### HOME PAGE – 
•	This is the common home page for admin and user where he can find all restaurant list button, Filter Restaurant Option and Contact Us.
### FILTER RESTAURANT –
• This page allows user to search restaurants based on food style, City, State, Zip.
•	RESTAURANT LIST – This page lists either all the restaurants or restaurants based on filter.
### Favorite RESTAURANT LIST – 
•	This page lists either all the favorite restaurants of a particular user which he added before.
### RESTAURANT DETAIL– 
* This page displays individual restaurant details along with food items list of that particular restaurant.
### FEEDBACK –
* This page allows the user to provide the feedback of that restaurant.


## Layouts of our project:

### Sign up page: 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/SIGN%20UP.png)

### Login Page 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/LOGIN.png)

### App Landing Page 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/1.png)

#### User Homepage
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/6.png)

### Verification Mail
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/verification.png)

### User Filter Restaurant Page 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/2.png)

### User Filtered Restaurant Page
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/3.png)

### Favourite Restaurant List
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/7.png)

### Admin Home Page
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/8.png)

### Admin Add Restaurant Page
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/9.png)

### Restaurant Details and feedback 
![](https://github.com/RaviTeja444/restaurants-finder/blob/main/Screenshots/5.png)

## Problems faced
### Below are the problems/challenges faced during the development of the application:

* While creating recycler views/adapters and loading data from firebase.
* Setting up firebase and getting google-services json file into app to connect firebase.
* Faced few challenges if any of the team member didn’t pull the code before making changes.
## Future Improvements
* Adding more restaurant details like restaurant timings and entire menu.
* Implementing food ordering option into the application so the user can view restaurants and also order food.
* Integrating google maps into the application to track your order or visit the restaurant.
