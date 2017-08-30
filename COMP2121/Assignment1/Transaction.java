import java.lang.*;

public class Transaction {
	private String sender;
	private String content;

	// getters and setters
	public void setSender(String sender) {
		// if( sender.length() != 8 )
		// {
		// 	this.sender = NULL;
		// 	return;
		// }
		// if ( sender.matches("[a-z]{4}[0-9]{4}") == false ){
		// 	this.sender = NULL;
		// 	return;
		// }
		this.sender = sender;
	}

	public void setContent(String content) {
		// if( content.length() > 70 ){
		// 	this.content = NULL;
		// 	return;
		// }else if ( content.contains('|') == true ){
		// 	this.content = NULL;
		// 	return;
		// }
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}

	public String toString() {
		return String.format("|%s|%70s|\n", sender, content);
	}

	// implement helper functions here if you need any
}
