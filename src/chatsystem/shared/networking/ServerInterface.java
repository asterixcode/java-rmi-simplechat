package chatsystem.shared.networking;

import chatsystem.shared.transferobjects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote
{
  void registerClient(ClientInterface client, String username) throws RemoteException;
  void login(String username) throws RemoteException;
  void disconnect(String username) throws RemoteException;
  void broadcast(Message message) throws RemoteException;
  void getUserList(String username) throws RemoteException;
}
