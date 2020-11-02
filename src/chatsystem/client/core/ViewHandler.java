package chatsystem.client.core;

import chatsystem.client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewHandler
{

  private ViewModelFactory vmf;
  private Stage stage;
  private Scene loginScene;
  private Scene chatScene;

  public ViewHandler(ViewModelFactory vmf)
  {
    this.vmf = vmf;
  }

  public void start() {
    stage = new Stage();
    openLogin();
  }

  private void openLogin()
  {
    if (loginScene == null) {
      try {
        Parent root = loadFXML("../views/login/LoginView.fxml");

        stage.setTitle("Login");
        loginScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    stage.setScene(loginScene);
    stage.show();
  }

  public void openChat() {
    if (chatScene == null) {
      try {
        Parent root = loadFXML("../views/chat/ChatView.fxml");

        chatScene = new Scene(root);
        stage.setTitle("Chat");

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    stage.setScene(chatScene);
    stage.show();
  }

  private Parent loadFXML(String path) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = loader.load();

    ViewController ctrl = loader.getController();
    ctrl.init(this, vmf);
    return root;
  }
}
