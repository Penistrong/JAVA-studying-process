package UDP–≠“È;

public class ChatClient {
	public static void main(String[] args) {
		ChatReceiver chatReceiver = new ChatReceiver();
		chatReceiver.start();
		
		ChatSender chatSender = new ChatSender();
		chatSender.start();
	}
}
