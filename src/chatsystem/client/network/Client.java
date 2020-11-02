package chatsystem.client.network;

import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.Subject;


public interface Client extends Subject {
  void startClient();
  void login(String username);
  void sendMessage(Message message);
  void getUserList(String username);
  void disconnect(String username);
}
