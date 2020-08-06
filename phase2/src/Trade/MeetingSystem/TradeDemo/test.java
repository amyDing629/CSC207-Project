//package Trade.MeetingSystem.Gui;
//
//import Trade.CompleteTradeGUI;
//import Trade.MeetingSystem.TradeDemo.TradeEntity;
//
//import java.util.List;
//import java.util.UUID;
//
//public class test {
//
//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//
//                TradeEntity tradeEntity = new TradeEntity(1);
//                UUID meetingID = tradeEntity.getMeetingID1();
//                System.out.println(meetingID);
//                List<UUID> users = tradeEntity.getTraderIds();
//
////                MainController mainController = new MainController(users, true, meetingID);
////                mainController.run(users.get(0), meetingID);
//
//                MainViewPresenter presenter = new MainViewPresenter(meetingID, users.get(0), users, new CompleteTradeGUI());
//                presenter.run();
//
//                tradeEntity.setMeetingID1(presenter.getMeetingID());
//                System.out.println(meetingID);
//                tradeEntity.setMeeting1Status(mainController.getStatus());
//                System.out.println(tradeEntity.getMeeting1Status());
//
////                MainViewPresenter presenter = new MainViewPresenter(meetingID, users.get(0), users);
////                presenter.run();
//
//
////                MainViewPresenter presenter = new MainViewPresenter(meetingID, users.get(1), users);
////                presenter.run();
//
//            }
//        });
//
//    }
//
//    //                LoginModel loginModel = new MyLoginModel();
////                LoginPresenter loginPresenter = new MyLoginPresenter();
////                LoginView loginView = new MyLoginView();
////
////                loginPresenter.setModel(loginModel);
////                loginPresenter.setView(loginView);
////                loginPresenter.setOnLogin(new Runnable() {
////
////                    @Override
////                    public void run() {
////                        JOptionPane.showMessageDialog(null, "Welcome user!");
////                    }
////                });
////                loginPresenter.run();
//}
//
// */
