# Restaurants-Finder

## App logo

![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/logo.png)

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

# Project Description

## Activities:
* As we know there are lot of food lovers out there. Everyone wants to eat the food which they like and also which falls in their budget. In a diverse place like Maryville there are students/people from across the world. Each of them has their own taste with respective to food. There is no such app currently which provides a platform to the user where he can find all the restaurants based on the style of food (Ex: American, Indian, Italian, Mexican) and also within their budget, location and Name.
* We are trying to provide a solution to such people using our app Restaurant Finder. It runs on Android platform and it’s free to download. Any person who register in our app can search restaurants directly or using a filter where they can choose food style, budget, location, Name.
* Intially, we have listed the details of various restaurants where each restaurant is categorized based on its food type. At one point of time user want to wishlist the restaurants he likes, so he needs to log in with registered credentials. Then we are storing the details of the favourite restaurants in cloud database called firebase. when user logs out and comes back he can view the saved restaurants.


## Features and Models:


## Functionality:
* We use the database to store the users favourite list.

## Persists on the app:
* email, password, restaurant Preference.

## Database Used: Firebase
* We are using the Firebase for storing the list of all restaurants and their location and its food type.
* Since we are developing a restaurant finder application, we need to have a database which stores credentials of the users, admins as well as the restaurants which we need to display to the user. 
* So, we have chosen Firebase which integrates with our android application. 
* The main reason to choose firebase is, it provides database as well as authentication to our application. 
* We are using firebase authentication to register and login with the credentials of the user.
* Here user has a chance to sign up for an account by entering his credentials. 
* Firebase then stores and secures the details of the user. 
* Whenever user tries to log in, firebase authenticates the username and password, if its valid it then redirects to homepage of the user else it redirects to same login page.

## Activity flow diagram


## Layouts of our project:

### Sign up page: 
* This is where user can register for an account in our application. When a user sign up for an account, if the user is registered with an email which is already registered it throws an error and displays user that the email is already registered.
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/Screenshot%20(101).png)



### Login Page 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/IncorrectPassword.png)

## User when Sign in
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/6.png)

## App Landing Page 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/1.png)

### User Homepage
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/6.png)

## User Filter Restaurant Page 
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/2.png)

## User Filtered Restaurant Page
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/3.png)

## Favourite Restaurant List
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/7.png)

## Admin Home Page
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/8.png)

## Admin Add Restaurant Page
![](https://raw.githubusercontent.com/RaviTeja444/restaurants-finder/main/Screenshots/9.png)

## 












