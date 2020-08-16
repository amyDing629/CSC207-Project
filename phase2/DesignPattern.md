# Design Patterns used for Phase 2
    - Design Pattern (DP) name
    - Names of all classes involved in this DP
    - How you implement this DP
    - Why you implement this DP
    - (Optional) Why not use other DPs

## Observer Design Pattern
 1. Classes Involved
    - `phase2/src/Trade/MeetingSystem/Adapter`
        - `SetupViewPresenter.java`
        - `EditViewPresenter.java`
        - `ConfirmViewPresenter.java`
    - `phase2/src/Trade`
        - `CTradeController.java`
 2. Why you implement this DP
  
    - Architectural: decouple the presenter and controller
    
    - Instantly and promptly update key info
  
        (a) update `meetingID`
        
            we want to update the `meetingID` (of type `UUID`) to the Trade system, once a meeting sets up; 
            and allow Trade system to record the `meetingID` in `Trade` entity.
        
        (b) update Trade `status` by known Meeting `status`
     
 3. How you implement this DP
    - we made the `CTradeController` implements `Observer` interface
        
    - we made three presenters (`SetupViewPresenter`, `EditViewPresenter.java`, `ConfirmViewPresenter.java`) 
    extends `Observable`
    
        These presenters use methods:
        
            addObserver(Observer o);
            ...
            setChanged();
            notifyObservers(Object arg);
            
        Each presenter add `CTradeController` as `Observer`, and notify it with `meetingID`, when proper actions 
        performed. The proper actions can be:
        
            a meeting successfully sets up (-> first meetingID generated);
            
            a meeting is edited (-> if meeting cancels, then trade cancels);
            
            a meeting is confirmed (-> trade should updates completed);
            
    - `CTradeController` overrides `Observer` method: 
            
            update(Observable o, Object arg)
            
        so that the `CTradeController` can updates the first `meetingID` as the first meeting sets up, and 
        update trade `status` with meeting status
        
## MVP (Model-View-Presenter) Design Pattern
 1. Classes Involved
    - views: `phase2/src/Trade/MeetingSystem/Adapter`
        - `MainView.java`
        - `InputTimePlaceView.java` (abstract)
            - `SetupView.java`
            - `EditView.java`
        - `OKCancelView.java` (abstract)
            - `AgreeView.java`
            - `ConfirmView.java`
    - presenters: `phase2/src/Trade/MeetingSystem/Adapter`
        - `MPresenter.java` (interface)
            - `MainViewPresenter.java`
        - `IPresenter.java` (interface)
            - `SetupViewPresenter.java`
            - `EditViewPresenter.java`
            - `ConfirmViewPresenter.java`
    - model: `phase2/src/Trade/MeetingSystem/UseCase`
        - `Model.java` (interface)
            - `MeetingModel.java`
        - `MeetingManager.java` (interface)
            - `MeetingActionManager.java`
            
 2. Why you implement this DP
  
    To make each of the views a "dumb", without knowing any domain layer and business layer stuff.
     
 3. How you implement this DP
 
     Models do the validation work, provides presenting model, and accesses the gateway. (business/domain logic)

     Views are the UI components, which show data and allow user interactions.
     The view calls presenter method always, when a user action occurred. (Such as a button is clicked.)
     
     Presenter acts as a "middle man", and formats the view with 
     data retrieved or methods used from the model.
     
     To decouple each part, interfaces are added among the layer boundaries.
     
## Builder Design Pattern
https://www.geeksforgeeks.org/builder-design-pattern/

The link above is a basic overview of how I used builder design pattern to build GUI for inventory and trade system.
 1. Classes Involved
    - Interfaces:
     
     `GUIPlan.java`, `iPresent`, `iInput`, `BorderGUIBuilder`, `BorderLayoutGUI`, `initializeInput`
    - Other classes: 
    
    `BorderGUIEngineer`, `BorderGUI`, and all Builders.
 2. Why you implement this DP
    - Separate the construction of GUI into different parts so that the code is more organized and easy to read.
    - Separate the construction and representation of GUI
    - Easier for extension. -> Open and Close principle
 3. How you implement this DP
    - GUIPlan has basic functions of a GUI, including set visible to true and setFrame.
    - iPresent is for presenters to "output" the information to users.
    - iInput is for controllers to get user's input
    - BorderLayoutGUi decides the layout used for creating the GUI. (If you want to build GUI with another layout,
    you can just create another interface and implement it instead of this one).
    - BorderGUIBuilders defines all the steps(abstract) that must be taken in order to correctly create a GUI with BorderLayout.
    - Specific Builders: contains specific and different construction method to construct different GUIs.
    - BorderGUIEngineer controls the algorithm that generates the final product object. 
    

## MVC (Model-View-Controller) Design Pattern

 1. Classes Involved
    - Adapters in Trade, Inventory and Point System
 2. Why you implement this DP
    - Separate controllers and presenters so that multiple views are available without changing controllers.
    - Adapt to Single Responsibility Principle. Controllers are responsible for reacting to the Model with users' action, 
    such as clicking a button. While presenters are responsible for getting information from the Model and present to users.
 3. How you implement this DP
    - Builders connect controllers with view. Whenever users click a button, controller methods will be called and make 
    modifications to the backend system(Model). After an action is performed by the controller, the controller will call 
    presenter's method to show users the information(Message) from the backend system(Model). 
    - Also, controller and presenter interfaces are implemented for better decoupling.
    

## Strategy Design Pattern
 1. Classes Involved
    - data
      - interface: `DataAccess.java`
      - classes: `UserDataAccess.java` `TradeDataAccess.java` `InvDataAccess.java`
      `ApprovalItemDataAccess` `ApprovalUserDataAccess`
      - decouple: use case classes and data access classes, such as userManager and userDataAccess
    - Inventory
      - interface: `iItemController.java` `iItemPresenter.java` `iMarketPresenter.java` 
      - classes: 
        - `iItemController.java` is implemented by all controllers in inventory except market
        - `iItemPresener.java` is implemented by all presenters in inventory
        - `iMarketPresenter.java` is implemented by `marketPresenter.java`
      - decouple: 
        - builders and controllers
        - controllers and presenters 
    - Builders
      - interface: `BorderGUIBuilder.java` `BorderLayoutGUI.java` `GUIPlan` `iInitializeInput` `iInput` `iPresent`
      - classes: 
        - `BorderGUIBuilder.java` is implemented by all builders
        - other interfaces are all implemented by `BorderGUI.java`
      - decouple:
        - `BorderGUI.java` & all controllers and presenters
        - `BorderGUIEngineer` & all builders
    - Trade
      - interface: `iRequestTradeController.java` `iTradeController.java` `iTradePresenter.java` `iRTradePresenter.java`
      - classes:
        - `iRequestTradeController.java` `iTradeController.java`  are implemented by `RTradeController.java` and other 
        - `iRTradePresenter.java` `iTradePresenter.java` are implemented by `RTradePresenter.java` and other presenters
      - decouple: 
        - builders and controllers
        - controllers and presenters  
    - Meeting System
    - Point System
      - interface: `iPointController.java` `iPointPresenter.java`
      - classes:
        - `iPointController.java` is implemented by `AwardActivities.java`
        - `iPointPresenter.java` is implemented by `PointPresenter.java`
      - decouple
        - builders and controllers
        - controllers and presenters
    - User
      - interface: `IAdminController.java` `IUserController` `IUserPresenter` 
      - class: 
        - `IAdminController.java` is implemented by `adminController.java`
        - `IUserController.java` is implemented by `ClientUserController.java`
        - `IUserPresenter.java` is implemented by `clientUserPresenter.java`
      - decouple
        - GUIs and controllers
        - controllers and presenters 
        
 2. Why you implement this DP?
    - Decouple Classes, encapsulate specific implementation of methods.
    - No impact when the number of derived classes changes, 
    and no impact when the implementation of a derived class changes.
    - Better for open-close principle
 3. How you implement this DP   
    - Store interfaces rather than specific classes
    - Bury the alternative implementation details in derived classes.

            
## Factory Design Pattern
 1. Classes Involved
    -   `.java`
 2. Why you implement this DP
     
 3. How you implement this DP
 
## Dependency Injection Design Pattern
 1. Classes Involved
    - `.java`
 2. Why you implement this DP
     
 3. How you implement this DP