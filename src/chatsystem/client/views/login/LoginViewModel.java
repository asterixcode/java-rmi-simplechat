package chatsystem.client.views.login;

import chatsystem.client.model.ChatModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
  private ChatModel chatModel;
  private StringProperty username;

  public LoginViewModel(ChatModel chatModel){
    this.chatModel = chatModel;
    username = new SimpleStringProperty();
  }

  public void login() {
    chatModel.setUsername(username.getValue());
    chatModel.createUsername(username.getValue());
  }

  public StringProperty getUsernameProperty() {
    return username;
  }

}
