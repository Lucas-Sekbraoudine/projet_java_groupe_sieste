package Controller;

import Client.Client;
import Common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class AccueilController {


    @FXML
    public TextField messageSend;

    @FXML
    public TextArea messageArea;

    @FXML
    public String receivedMessage;





    public AccueilController() throws IOException {
    }

    @FXML
    public void handleSendMessagePress(ActionEvent event) throws IOException, ClassNotFoundException {
        Message message = new Message("moi",  messageSend.getText());
        try {
            Client client = new Client("127.0.0.1", 3360);
            client.sendMessage(message);
            if (messageArea.getText().equals(""))
                messageArea.appendText(messageSend.getText());
            else{
                messageArea.appendText("\n" + messageSend.getText());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
