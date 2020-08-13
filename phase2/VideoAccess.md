# Videos

## Google Drive Video Link
Link: https://drive.google.com/file/d/1fVDdqURkgX-YA32CMqR3ovyetCB0bVu2/view?usp=sharing

## Videos' Content

### `V1_RegisterLogin.mp4`✅
- Login: Admin & Client User
- Register: Client User *2
- Change Password
- Creates new Admin user：Admin
    
### `V2_WishList_AddLendableItems.mp4`✅
- Add items to wish-to-lend list
- Admin agrees/decline requests (agreed items will appear in the market)
    
### `V3_WishList_AddWishList.mp4`✅
- Add item from the Market to Wishlist
- Delete item in the Wishlist
    
### `V4_TradeItems_TwoWay_Temporary.mp4`
- Initialise a TwoWay-Temporary Trade (ClientUser A): <MeetingStatus: `INCOMPLETE`>
- Add second item to trade (ClientUser B)
- Agree trading request (ClientUser B)
- First Meeting (to be COMPLETE, not CANCELLED)
    - `Setup`: initialise a time and place
        - check violating inputs (*only future time allowed*, etc.)
    - `Edit`: time and/or place, 
        - check violating inputs (*the same as time & place as former ones NOT ALLOWED*, etc.)
    - `Agree` proposal before meeting occurs : <MeetingStatus: `AGREED`>
    - `Confirm` proposal after meeting occurs: <MeetingStatus: `CONFIRMED`>
- Second Meeting
    - (Automatically generates; the same time, but exactly 1 month after; the same place)
    - `Confirm` proposal after meeting occurs: <MeetingStatus: `CONFIRMED`>
     
     
### `V5_xxx.mp4`
- a
    
### `V6_xxx.mp4`
- a
    
### `V7_xxx.mp4`
- a
    
### `V8_xxx.mp4`
- a
    
### `V9_xxx.mp4`
- a
    
### `V10_xxx.mp4`
- a
     
### `V11_xxx.mp4`
- a
    
### `V12_xxx.mp4`
- a
    
### `V13_xxx.mp4`
- a

====================================

## Video Script
---

### Users

| username | password | notes |
|:---------|:---------|:------|
|admin |123 |(already exists in system, login only)|
|admin2 |123|(create by admin)|
|qqq |123 |(new: register new user, change password to “123abcABC” )|
|www |123 |(new: register new user)|

### Inventory 
| item | description |
|:---------|:---------|
|iPad|1st generation|
|iPhone|latest released|
|toy car|red and shiny|
|tea cup|vintage|
|horror film|home-made|
|---|---|
|daydream|fresh and healthy|
|trash|aka. garbage|
|love|my pure emotion and feeling|


## == TESTS ==

## Register and login, create admin, change password


## Request adding items to inventory, Agree/Decline requests
add above items to inventory,
-  the items from the upper section  — agree request
-  the items from the lower section  — decline request


## Mandatory #3 - Market
before login -> explore (view an item)
after login -> inventory/market  (view an item)


## Point System
每complete一个trade (trade status == complete)，系统自动给这个user加一分；每5分，可以兑换一次bonus trade
TBA

## Mandatory #1 -  Roll Back
TBA

## Mandatory #2 - Suggest items to lend
TBA

## Mandatory #4 - Admin adjust all threshold
TBA

## Mandatory #5 - Admin adjust all threshold
TBA
