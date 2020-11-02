package chatsystem.server;


import chatsystem.server.model.ConnectionPool;
import chatsystem.server.network.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    RMIServer server = new RMIServer(new ConnectionPool());
    server.start();
    System.out.println("Server started...");
  }
}
