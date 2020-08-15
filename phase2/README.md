# CSC207 Project

This is a program that allows users to trade different items with each other.
 
# Table of contents
 | File | Content |
 |------|:---------------|
 |`FeatureList.md`|the features we have done for phase 2|
 |`VideoAccess.md`|the link for sample usage videos, and basic txt elaborations on test functions|
 |`DesignPattern.md`|the design patterns we have applied with detailed explanations|
 |`src/Main/mainRun.java`|main test file; to start testing the functionalities from here :)|
 
# Prerequisites
  - Language: Java
  - IntelliJ
--- 
  - SDK: java version 1.8
  - Project Language level: 8 - Lambdas, type annotations, etc
---
  - Ensure all the database file (.ser format) are empty before running
  - Do **NOT** quit the whole program or stop the execution of program when running tests, 
  **follow the menu to exit instead**.

# Tests
Run `phase2/src/Main/mainRun`. To simplify the testing, we provide **three** login windows 
so that 3 users can log in and communicate through the application at the same time.

For better testing all the available functionality, you need to:
  - **log in** an Admin account (automatically created, already exists in the system)
    > username: `admin`, password: `123`
                                                               
  - **register** two regular-user account into the system. 
    e.g.
    > username: `qqq`, password: `123`

    and
    > username: `www`, password: `123`
  - P.S. 
    - ClientUser: ClientUser can only perform ordinary (ClientUser) functionalities
    - AdminUser: AdminUsers are able to perform ordinary (ClientUser) functionalities and admin functionalities
  - add items into at least two users' lend wish list.


# Authors and Contribution 
*(names listed in no particular order)

| Name | Divide of work |
|------|:---------------|
|**Yuhan Zheng (Ensouled)**|AdminUser & GUI, Mandatory Extension 1|
|**Yiyun Ding (Amy)**|Trade & GUI, Inventory & GUI, Mandatory Extension 2|
|**Changyan Xu (Rella)**|Meeting System & GUI, Mandatory Extension 3|
|**Yuxin Yang (Amber)**|Point System, Mandatory Extension 4|
|**Siwei Tang**|User, Mandatory Extension 5|


# Illustrations

# Scope of functionalities 

# Examples of use
Please see `VideoAccess.md`.




















# ======================================