package Trade.MeetingSystem;

public class MeetingSystemMenuPresenter implements IMenuPresenter {

    @Override
    public void printMenu(MeetingMenuName menuName) {
        if (menuName.equals(MeetingMenuName.SETUP)) {
            setUpSessionMenu();
        } else if (menuName.equals(MeetingMenuName.EDIT)) {
            editMenu();
        } else if (menuName.equals(MeetingMenuName.AGREE)) {
            editAgreeSessionMenu();
        } else if (menuName.equals(MeetingMenuName.CONFIRM)) {
            confirmSessionMenu();
        } else if (menuName.equals(MeetingMenuName.CANCEL)) {
            editMenu();
        }
    }


    void setUpSessionMenu() {
        System.out.println("<Set-Up-Meeting Session> \n " +
                "Enter \"ok\" to continue, or anything else to quit this session.");
    }

    void editMenu() {
        System.out.println("------------------------------");
        System.out.print("Menu: \n " +
                "1. Enter '1': only change time \n" +
                "2. Enter '2': only change place \n" +
                "3. Enter '3': change both time and place \n" +
                "4. Enter '..' to quit edition process \n");
        System.out.println("------------------------------");
    }

    void editAgreeSessionMenu() {

    }

    void confirmSessionMenu() {

    }

}
