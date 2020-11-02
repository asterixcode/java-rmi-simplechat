package chatsystem.client.core;

import chatsystem.client.views.chat.ChatViewModel;
import chatsystem.client.views.login.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory mf;
  private LoginViewModel loginViewModel;
  private ChatViewModel chatViewModel;

  public ViewModelFactory(ModelFactory mf) {
    this.mf = mf;
  }

  public LoginViewModel getLoginViewModel() {
    if (loginViewModel == null)
      loginViewModel = new LoginViewModel(mf.getChatModel());
    return loginViewModel;
  }

  public ChatViewModel getChatViewModel() {
    if (chatViewModel == null)
      chatViewModel = new ChatViewModel(mf.getChatModel());
    return chatViewModel;
  }
}
