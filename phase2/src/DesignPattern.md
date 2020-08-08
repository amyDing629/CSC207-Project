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
 1. Classes Involved
    -   `.java`
 2. Why you implement this DP
     
 3. How you implement this DP
 
## Factory Design Pattern
 1. Classes Involved
    -   `.java`
 2. Why you implement this DP
     
 3. How you implement this DP
 
## Dependency Injection Design Pattern
 1. Classes Involved
    -   `.java`
 2. Why you implement this DP
     
 3. How you implement this DP