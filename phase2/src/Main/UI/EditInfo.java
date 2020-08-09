//package Main.UI;
//
//import Inventory.Inventory;
//import User.Entity.ClientUser;
//import User.UseCase.AdminActivityManager;
//import User.UseCase.ItemApprovalManager;
//import User.UseCase.UserManager;
//
//import java.util.Scanner;
//
///**
// * [ClientUser Interface]
// * shows the interface that the user uses
// */
//public class EditInfo {
//
//    /**
//     * read input
//     */
//    public Scanner sc;
//    /**
//     * the object that edits the user list of input gateway
//     */
//    public UserManager a;
//
//    /**
//     * user in user system
//     */
//    public ClientUser user;
//
//    /**
//     * the object in the Item list of input gateway
//     */
//    public Inventory v;
//
//    /**
//     * the place we store information
//     */
//    public ItemApprovalManager iam ;
//    public AdminActivityManager aam;
//    public UIcontoller uc;
//    /**
//     * [constructor]
//     * @param u the input user
//     * @param aam
//     * @param uc
//     */
//    public EditInfo(ClientUser u, UserManager um, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam, UIcontoller uc){
//        user=u;
//        a= um;
//        v = iv;
//        this.iam = iam;
//        this.aam=aam;
//        this.uc=uc;
//    }
//
//    /**
//     * run the system
//     */
//    public void run(){
//        Scanner sc=new Scanner(System.in);
//        int exit=-1;
//        while(exit!=0) {
//            System.out.println("--------------------\nEdit user information");
//            uc.UserDisplayStatus(user);
//            System.out.println("Actions:\n1.Change password\n2.ClientUser Freeze System");
//            if (a.getIsAdmin(user)) {
//                System.out.print("3.Change user's limit\n4.add new item into the system\n");
//                if(a.getUsername(user).equals("admin")){
//                    System.out.print("5.create new admin\n");
//                }
//            }
//            System.out.println("0.exit");
//            int input = uc.getNumber("Please enter a number");
//            System.out.println("-----------------------------");
//            switch (input) {
//                case 1:
//                    ChangePass cp=new ChangePass(user,a,uc);
//                    cp.run();
//                    break;
//                case 2:
//                    UserFreezeSystem ufs=new UserFreezeSystem(user,a, iam,aam,uc);
//                    ufs.run();
//                    break;
//                case 3:
//                    UserLimit ul=new UserLimit(user,a,iam,uc,aam);
//                    ul.run();
//                    break;
//                case 5:
//                    CreateAdmin ca=new CreateAdmin(user,a,iam,aam,uc);
//                    ca.run();
//                    break;
//                case 4:
//                    AddNewItem ani=new AddNewItem(user,a,v,iam);
//                    ani.run();
//                    break;
//                case 0:
//                    exit=0;
//                    break;
//            }
//        }
//    }
//}
