# Spring2025CS151Project1
Overview:
A system representing a business that supports the renting and selling of various vehicles. The system can be booted up into 2 perspectives, one representing a customer looking to purchase or rent a vehicle, and another to represent the owner of a branch location.

Design:
The system can be broken up into 3 main categories. The first category is the main system that handles user input and guides the interactions between classes. It contains all the user prompts and calls the appropriate method in the classes based upon the user input. The next category is the Employee Class, Customer Class, and Order class which represent interactions between the system and its user. They keep track of the purchases of the user and overall ensure all references are updated correctly. The final category classes are the ones that represent an object in real life which include the Store, Inventory, and any Classes related to vehicles. These are the main objects being manipulated by the other classes. Their main function is to exist as part of the system rather than being a stand-alone class.

Additional Note:
Memory is not preserved across programs due to a lack of a file reading and writing system. The system has been preloaded with some objects, but upon closing the program the memory will be wiped.

Installation Instructions:
This program only requires a working JDK or JRE.

Usage:
Upon booting up the system, the user is given the option between choosing the customer perspective and the business perspective. After that, the user must pick the location upon which they want to engage with. 

From the customer's perspective, the user is given the option to look for any specific type of car they want to purchase/rent within the inventory of the store they have chosen. If a match is found, the user is prompted to rent it for a specific amount of days or purchase the vehicle. Upon the ending of any individual action, the user is redirected back to the customer's main menu.

From the business perspective, the user is given the option to manage their employees or their vehicles. From there the user can choose to edit the inventory and manage their employees. Like the customer's perspective, once any action is completed, the user is redirected to the business main menu.

Contributions:
James 
- Created the customer prompt menus in Main. 
- Focused on the Class Car and RentalCar

Ayman
- Focused on the Class Moving Truck.
- Implemented key rental functionality - ensured that Moving truck can be rented, extended, and charged.
- Used isAvailable and currentOwner to track rental status.

Jorge
-Customer class and setting exception if the user leaves a prompt blank
-Employee class and setting exceptions for null items if a prompt is left blank \

Vineet 
-Vehicle class and interfaces
-Business menu and structure of Main
