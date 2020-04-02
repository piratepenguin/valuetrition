 # ![alt-text](data/NutritivityLogo.png)

## The best value and quality of food

**Oftentimes, we hear the phrase "eat healthy". But what does healthy look like?**
Nutritivity is here to show you. It's the perfect all-around program to keep track of
and improve your diet. It tracks calories and all the major macro/micro nutrients. It also keeps track of food prices in relation to their
nutritive value - great for anyone on a budget. 

Nutritive value considers factors such as: 
 - calories
 - proteins 
 - vitamins 
 - even your preferences in taste!
 
 After as little as just a week of tracking, this program will offer useful insights you can use to upgrade your diet
 and lifestyle. So, you won't need to track your diet for months to see results. One week of tracking, and then you're free
 to implement the advice with or without the program. As such, Nutritivity is great for anyone just starting out with diet
 and fitness, as well as students on a budget, athletes tracking their calories, and food lovers around the world.
 
I'm very passionate about this project because while I've been trying to build a *healthy* diet I've found it very 
difficult to keep track of all the factors that make up the term *healthy*. I also wish there was a program that would analyze my 
food for me and show statistics such as what foods I'm wasting money on, what foods are the most beneficial, and how 
good my overall diet is.

## User Stories:

#### Basics
1.  As a user, I want to create my own version of a certain food by inputting its nutrients and cost

2.  As a user, I want to be able to select a food and view its nutrients and economical value

3.  As a user, I want to add a food to the log of foods I've eaten today

#### Saving
4\. As a user, I want to be able to save my log and the food database

5\. As a user, I want the application to load my saved log when i open it


## Instructions For Grader:

1. Run 'Main'

2. Login Menu will open.
    - Nutritivity logo on screen, title bar, desktop taskbar .............. Audiovisual component
    - enter a random username and password, then click "sign up"
        - your account will be added to the database
        - new save files will be made for your account
        - you can log in with this account, but for demo clarity don't yet
        - ......................................................................................... Adding an X to a Y
    - log in to my account
        - username: deniskov 
        - password: b2 
    - The saved log for deniskov will now load ............................... (User Story #5, Loading)

3. This is the 'Dashboard'.
    - You can:
        - see what the current day is  (in this case, day 5)
        - open the Menu
        - save & exit
    - Click "Next Day"
        - the day increases by 1 
        - now you will be logging for day 6
    - Click "Open Menu"
        - this is where you can perform actions like:
            - return to dashboard
            - "View Food Info" = view info for a certain food ......... (User Story #2, Viewing X's added to Y)
            - "Create a new Food" = create own version of a food (User Story #1, Add X to Y)
            - "View log for Another Day" = view log for some day.. Viewing X's added to Y
            - "View log for Today" = view log for today ..................  Viewing X's added to Y
            - "Log Meal" = add a meal to the log ........................... (User Story #3, Add X to Y)
    - Once you are done, navigate back to the 'Dashboard'
    - Click "Save & Exit"
        - any changed you made will now be saved to files ........... (User Story #4, Saving)
    
           
           
## Phase 4: Task 2
     
The AccountList class, which stores all the account usernames and passwords, both uses a HashMap<<a>String, Account>
and is an example of a robust class that throws a checked exception.

The hashmap stores usernames as keys, and accounts as values, so when the user enters a username and password, 
the program checks whether the account mapped to that username has the same password as the one entered.

If the user enters a username that is not recognized by AccountList, AccountList throws an AccountNotFoundException.
This exception is caught by LoginScreen, which in turn pops up an alert box notifying the user that the provided information is invalid.

It is tested thoroughly, including the checked exception, in AccountListTest

It uses an account as the mapped value instead of a password because after a successful entry, 
the AccountList passes the Account to the login system which then loads that account's data


## Phase 4: Task 3
###1.
I had an instance of coupling in my Account class & its dependents, where the directory of my accounts folder was missing a "." in the start of the file name

was: (/data/accounts/...) should be: (./data/accounts/...) 

Some references included the dot and some didn't, and some even used "\\" instead of "/" so it took me hours to find what was causing the problem. 
Now I changed it to be a static final string declared in Account, so whenever i need it, it will always reference the same, proper directory.

###2. 
