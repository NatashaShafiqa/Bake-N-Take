# Bake N' Take
Final Project Mobile Application Development

Group Members
-
1. Nurul Asyqin Binti Haris (B032010229)
2. Muhammad Zulfikri Bin Awang (B032010087)
3. Natasha Shafiqa Binti A Najebudin (B032010228)
4. Aqil Rafiq Bin Mohammad (B032010200)

Introduction
- Bake N' Take is the mobile application of cake booking system. Customer can register as a user to the application and browse the cake based on the menus that shop provide. Customer can also put message, select quantities of cake and candles based on their preferences. This system can be useful for bakeries, pastry shops and online cake delivery businesses to automate their ordering process and improve customer experience.

Instruction to Use the Application
-
https://youtu.be/RAUHPyNLYd4

Functions include 
-
1. Navigation View : the navigation view can be locate at Drawer_base.java file.
2. Fragment : Fragment locate at shopCakeMain.java and  DetailCakeMain.java are use together with adapter in myadapter.java file.    
            : other fragment can be locate at MapsFragment.java and CheckOut.java.
4. Activity : this application mainly using multiple activity such as account.java, Login.java, signUp.java, ViewOrderMain.java and so on.



DataBase
- 
Using Firebase Database  
Object Declared :
1. Image - store cake data such as name,price,description and url.
2. Address - to store user address data include House unit and Road Address.
3. Payment - to store user payment data such as address, cake name,cake url, candle, date, message, payment method,payment id,price and quantity.
4. Profile - to store url of image of the user default profile.
5. profileUser - to store url of image of user after they change the profile.
6. users - to store users data such as username,fullname,email,phone number and password.


External REST API
- 
Using Google Map API
Function : to set current location of user and save as address of user

