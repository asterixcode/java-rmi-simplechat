package chatsystem.shared.networking;

import chatsystem.shared.transferobjects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote
{
  void broadcast(Message message) throws RemoteException;
  void userList(ArrayList<String> newValue) throws RemoteException;
}
