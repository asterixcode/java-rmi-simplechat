package chatsystem.client;

import javafx.application.Application;

import java.rmi.RMISecurityManager;

public class RunApp
{
  public static void main(String[] args)
  {
    Application.launch(ChatSystemApp.class);

    //    System.setProperty("java.security.policy", "all.policy");
    //    if (System.getSecurityManager() == null){
    //      System.setSecurityManager(new RMISecurityManager());
    //    }
    //    RmiClient client = new RmiClient();
  }
}
