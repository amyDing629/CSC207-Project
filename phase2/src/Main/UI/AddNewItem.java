//package Main.UI;
//
//import Inventory.UseCase.Inventory;
//import Inventory.Entity.Item;
//import User.Adapter.ClientUserController;
//import User.Entity.ClientUser;
//import User.UseCase.ItemApprovalManager;
//import User.UseCase.UserManager;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class AddNewItem {
//    ClientUser user;
//    Scanner sc;
//    UserManager um;
//    Inventory iv;
//    UIcontoller uc;
//    ClientUserController cuc;
//    public AddNewItem(ClientUser user,UserManager um, Inventory iv, ItemApprovalManager iam){
//        this.user=user;
//        sc=new Scanner(System.in);
//        this.um=um;
//        this.iv=iv;
//    }
//
//    public void run(){
//        System.out.println("add new item into the system");
//        System.out.println("Menu:\n1.Add item for yourself.\n2.Approve request from users");
//        String inputA=uc.getString("Please enter a number!");
//        if(inputA.equals("1")) {
//            int exit1 = 0;
//            String name = "";
//            while (exit1 == 0) {
//                System.out.println("Type the name of the item");
//                name = sc.nextLine();
//                // cuc 1
//                boolean t = cuc.checkItemExist(name, iv);
//                if (t) {
//                    System.out.println("The item already exists, please enter the name again");
//                } else {
//                    exit1 = 1;
//                }
//            }
//            String des = uc.getString("Type the description of the item");
//            Item i = new Item(name, um.getUsername(user));
//            iv.setDescription(i,des);
//            iv.addItem(i);
//            user.addWishes(name);
//
//            System.out.println("Added successfully!");
//        }
//        // delete iam, put into clientUser controller
//        else if(inputA.equals("2")){
//            int x=0;
//            while(x==0) {
//                ArrayList<ArrayList<String>> hii=cuc.getIam().getItemApproval();
//                for (int i=0;i<hii.size();i++) {
//                    System.out.println("Item " + i + ": " + hii.get(i).get(1));
//                    System.out.println("Description: " + hii.get(i).get(2));
//                    System.out.println("Owner: " + hii.get(i).get(3));
//                    System.out.println("**************************");
//                }
//                if(hii.size()==0){
//                    System.out.println("There is no item currently");
//                }
//                String inputs = uc.getString("Enter the item number to approve,enter -1 to quit.");
//                int k = Integer.parseInt(inputs);
//                if (k > -1 && k < (hii.size())) {
//                    Item i = new Item(hii.get(k).get(1), hii.get(k).get(3));
//                    iv.setDescription(i, hii.get(k).get(2));
//                    um.getUser(hii.get(k).get(3)).addWishes(hii.get(k).get(1));
//                    iv.addItem(i);
//                    cuc.getIam().getItemApproval().remove(k);
//                    System.out.println("Approve successfully");
//                }else if(k==-1){
//                    x=1;
//                }
//                else {
//                    System.out.println("You enter the wrong number!");
//                }
//            }
//        }
//    }
//}
