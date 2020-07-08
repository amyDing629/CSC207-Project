package Trade.MeetingSystem;

public class MeetingSystemMenuPresenter implements IMenuPresenter {

    @Override
    public void printMenu(String menuName) {
        if (menuName.equals("meetingMain")){
            mainMenu();
        }else if(menuName.equals("meetingEdit")){
            editMenu();
        }
    }


    public void mainMenu() {
        System.out.println("------------------------------");
        System.out.print("Meeting Menu: \n " +
                "1. Enter 'ss': to set up a meeting \n" +
                "2. Enter 'ee': to edit the meeting \n" +
                "3. Enter 'cc': to confirm the meeting \n" +
                "4. Enter 'menu': to print menu"+
                "5. Enter 'exit' to quit meeting system\n");
        System.out.println("------------------------------");
    }

    public void editMenu() {
        System.out.println("------------------------------");
        System.out.print("Menu: \n " +
                "1. Enter '1': only change time \n" +
                "2. Enter '2': only change place \n" +
                "3. Enter '3': change both time and place \n" +
                "4. Enter '..' to quit edition process \n");
        System.out.println("------------------------------");
    }
}
