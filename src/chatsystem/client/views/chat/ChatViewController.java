package chatsystem.client.views.chat;

import chatsystem.client.core.ViewHandler;
import chatsystem.client.core.ViewModelFactory;
import chatsystem.client.views.ViewController;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.UserAction;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ChatViewController implements ViewController
{
  @FXML private VBox userListVBox;
  @FXML private VBox receiveTextVBox;
  @FXML private TextField sendTextField;
  @FXML private Label usernameLabel;

  private ChatViewModel chatViewModel;
  private ViewHandler vh;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    chatViewModel = vmf.getChatViewModel();

    usernameLabel.textProperty().bind(chatViewModel.getUsernameProperty());
    sendTextField.textProperty().bindBidirectional(chatViewModel.getSendProperty());

    chatViewModel.addListener(UserAction.BROADCAST.toString(), this::onReceiveMessage);
    chatViewModel.addListener(UserAction.USER_LIST.toString(), this::onReceiveUserList);

    sendTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
          onSendMessage();
        }
      }
    });
    chatViewModel.getUserList();
  }

  public void onSendMessage() {
    chatViewModel.sendMessageToServer();
    sendTextField.clear();
  }



  private void onReceiveMessage(PropertyChangeEvent event) {
    Message message = (Message) event.getNewValue();
    System.out.println("Message received back in the controller");
    Platform.runLater(() -> {
      receiveTextVBox.getChildren().add(createMessage(message));
    });
  }

  private Label createMessage(Message message) {
    Label messageLabel = new Label(message.getUsername() + ": " + message.getMessageBody());
    return messageLabel;
  }

  private void onReceiveUserList(PropertyChangeEvent event) {
    Platform.runLater(() -> {
      ArrayList<String> users = (ArrayList<String>) event.getNewValue();
      userListVBox.getChildren().clear();

      for (int i = 0; i < users.size(); i++) {
        Label label = new Label(users.get(i));
        userListVBox.getChildren().add(label);
      }
    });
  }

  public void onDisconnect() {
    chatViewModel.disconnect();
  }
}
