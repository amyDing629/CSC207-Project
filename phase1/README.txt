This is the phase 1 project for CSC207Y1 (summer) Software Design.

Project Title: CSC207 Phase1 Project
This is a program that allows users to trade different items with each other.

Prerequisites: intellij.
Before running the program, please make sure that the txt files (4 altogether) are empty and with no blank space.
All files should be in a package named phase1 in the original location relative phase1.

Running the tests:
For running the program, open class Main in package Main, and click the "run" button.
After user clicks run, he will enter the main menu in the terminal:
-->
(user should input 0, 1 or 2)
[main menu]
quit(0): stop 'main'
register(2): register an account. (ps: The first admin will be automatically created with name "admin" and password "123")
         --> enter username and password(only numbers and letters are allowed, else might cause errors.)
NonAdmin login(1): all user functions in the program. (Enter correct user name and password to login)
         [login menu]
         --> Edit information(1)
             --> change password (change password or exit)(1)
             --> exit: exit to main menu(0)
         --> Trade(2)
             [trade menu]
             --> confirm trade(1): choose an unconfirmed trade to make edition.
                 --> type 1 to change status of the trade from "unconfirmed to incomplete".
                 --> type 2 to change status of the trade from "unconfirmed to cancelled"
             --> complete trade(2): choose an incomplete trade to make edition.

             --> Trade History(3)
             --> quit(0)

         --> Inventory(3)
             [Inventory menu]
             --> Lend wishes: view a list of items that the user wants to lend
             --> Borrow wishes: view a list of items that the user wants to borrow.
             user can also select an item by typing the item name(?) to request a trade with the owner of the item.
             --> Edit lend wishes:
                 [edit lend wishes menu]
                 --> add wish: input item name and description to request to add the item the user wants to lend.
                 --> delete wish: delete item(approved by admin) from the wish lend list and from inventory.(?)
             --> Edit borrow wishes:
                 [edit borrow wishes menu]
                 --> add wish: present all the item name; input item name to add wish.
                 --> delete wish:
             --> Exit: exit to login menu


Admin login(1): admin has all functions as normal users plus its own functions (Enter correct user name and password to login)
         [login menu]
         --> Edit information(1)
             [Edit information menu]
             --> Change password(1)
             --> Freeze a user(2)
             --> Change user's limit(3)
             --> add new item into the system(4)
                 --> Add item your self
                 --> Approve request from users: choose an item to approve(input the item number)(?)
             --> exit(0)



Trade limit: incomplete trade not included