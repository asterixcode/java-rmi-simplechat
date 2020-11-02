package chatsystem.client.views.login;

import chatsystem.client.core.ViewHandler;
import chatsystem.client.core.ViewModelFactory;
import chatsystem.client.views.ViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginViewController implements ViewController
{
  @FXML private TextField usernameTextField;
  private LoginViewModel loginViewModel;
  private ViewHandler vh;

  @Override
  public void init(ViewHandler vh, ViewModelFactory vmf) {
    this.vh = vh;
    loginViewModel = vmf.getLoginViewModel();
    usernameTextField.textProperty().bindBidirectional(loginViewModel.getUsernameProperty());

    usernameTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
    {
      @Override
      public void handle(KeyEvent keyEvent)
      {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
          goToChatView();
        }
      }
    });
  }

  public void goToChatView() {
    loginViewModel.login();
    vh.openChat();
  }


}
