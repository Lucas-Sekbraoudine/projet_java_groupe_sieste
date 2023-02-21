package Controller;

import Client.Client;
import Common.LoadScene;
import Common.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.format.TextStyle;

import static java.lang.System.exit;

public class GamescreenController {
    @FXML
    public Button exit;
    @FXML
    public Button new_game;
    @FXML
    public Text winner;
    @FXML
    public Text looser;
    @FXML
    public ImageView rouge_1_1;
    @FXML
    public ImageView rouge_1_2;
    @FXML
    public ImageView rouge_1_3;
    @FXML
    public ImageView rouge_1_4;
    @FXML
    public ImageView rouge_1_5;
    @FXML
    public ImageView rouge_1_6;
    @FXML
    public ImageView rouge_3_1;
    @FXML
    public ImageView rouge_3_2;
    @FXML
    public ImageView rouge_3_3;
    @FXML
    public ImageView rouge_3_4;
    @FXML
    public ImageView rouge_3_5;
    @FXML
    public ImageView rouge_3_6;
    @FXML
    public ImageView rouge_2_1;
    @FXML
    public ImageView rouge_2_2;
    @FXML
    public ImageView rouge_2_3;
    @FXML
    public ImageView rouge_2_4;
    @FXML
    public ImageView rouge_2_5;
    @FXML
    public ImageView rouge_2_6;
    @FXML
    public ImageView rouge_4_1;
    @FXML
    public ImageView rouge_4_2;
    @FXML
    public ImageView rouge_4_3;
    @FXML
    public ImageView rouge_4_4;
    @FXML
    public ImageView rouge_4_5;
    @FXML
    public ImageView rouge_4_6;
    @FXML
    public ImageView rouge_5_1;
    @FXML
    public ImageView rouge_5_2;
    @FXML
    public ImageView rouge_5_3;
    @FXML
    public ImageView rouge_5_4;
    @FXML
    public ImageView rouge_5_5;
    @FXML
    public ImageView rouge_5_6;
    @FXML
    public ImageView rouge_6_1;
    @FXML
    public ImageView rouge_6_2;
    @FXML
    public ImageView rouge_6_3;
    @FXML
    public ImageView rouge_6_4;
    @FXML
    public ImageView rouge_6_5;
    @FXML
    public ImageView rouge_6_6;
    @FXML
    public ImageView rouge_7_1;
    @FXML
    public ImageView rouge_7_2;
    @FXML
    public ImageView rouge_7_3;
    @FXML
    public ImageView rouge_7_4;
    @FXML
    public ImageView rouge_7_5;
    @FXML
    public ImageView rouge_7_6;
    @FXML
    public ImageView jaune_1_1;
    @FXML
    public ImageView jaune_1_2;
    @FXML
    public ImageView jaune_1_3;
    @FXML
    public ImageView jaune_1_4;
    @FXML
    public ImageView jaune_1_5;
    @FXML
    public ImageView jaune_1_6;
    @FXML
    public ImageView jaune_3_1;
    @FXML
    public ImageView jaune_3_2;
    @FXML
    public ImageView jaune_3_3;
    @FXML
    public ImageView jaune_3_4;
    @FXML
    public ImageView jaune_3_5;
    @FXML
    public ImageView jaune_3_6;
    @FXML
    public ImageView jaune_2_1;
    @FXML
    public ImageView jaune_2_2;
    @FXML
    public ImageView jaune_2_3;
    @FXML
    public ImageView jaune_2_4;
    @FXML
    public ImageView jaune_2_5;
    @FXML
    public ImageView jaune_2_6;
    @FXML
    public ImageView jaune_4_1;
    @FXML
    public ImageView jaune_4_2;
    @FXML
    public ImageView jaune_4_3;
    @FXML
    public ImageView jaune_4_4;
    @FXML
    public ImageView jaune_4_5;
    @FXML
    public ImageView jaune_4_6;
    @FXML
    public ImageView jaune_5_1;
    @FXML
    public ImageView jaune_5_2;
    @FXML
    public ImageView jaune_5_3;
    @FXML
    public ImageView jaune_5_4;
    @FXML
    public ImageView jaune_5_5;
    @FXML
    public ImageView jaune_5_6;
    @FXML
    public ImageView jaune_6_1;
    @FXML
    public ImageView jaune_6_2;
    @FXML
    public ImageView jaune_6_3;
    @FXML
    public ImageView jaune_6_4;
    @FXML
    public ImageView jaune_6_5;
    @FXML
    public ImageView jaune_6_6;
    @FXML
    public ImageView jaune_7_1;
    @FXML
    public ImageView jaune_7_2;
    @FXML
    public ImageView jaune_7_3;
    @FXML
    public ImageView jaune_7_4;
    @FXML
    public ImageView jaune_7_5;
    @FXML
    public ImageView jaune_7_6;
    LoadScene loadScene = new LoadScene();
    @FXML
    public Hyperlink playersearch;
    @FXML
    public Text your_turn;

    @FXML
    public Text not_your_turn;

    @FXML
    public Text search_game;

    @FXML
    public TextField messageSender;

    @FXML
    public TextArea messageArea;

    Client client;

    public GamescreenController(Client client){

        this.client = client;

        Platform.runLater(() -> {
            Thread t = new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    search_game.setVisible(client.isSearchGame());
                    if (client.getPlayerNumber() == client.getCurrentPlayer()) {
                        your_turn.setVisible(true);
                        not_your_turn.setVisible(false);
                    } else if (client.getCurrentPlayer() == client.getAdversaire()){
                        your_turn.setVisible(false);
                        not_your_turn.setVisible(true);
                    }
                    winner.setVisible(client.getWinner());
                    looser.setVisible(client.getLooser());
                    //new_game.setVisible(client.getWinner() || client.getLooser());
                    exit.setVisible(!client.isInGame());
                    if (client.getMessage() != null && client.isInGame()){
                        messageArea.appendText("\nAdversarie: "+client.getMessage() );
                        client.setMessage(null);
                    }
                }
            });
            t.start();
        });

        Platform.runLater(() -> {
            Thread t = new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (client.isInGame()){
                        char[][] board = client.getBoard();
                        if (board != null) {
                            int rows = 6;
                            int columns =7;
                            for (int i = 0; i < rows; i++) {
                                for (int j = 0; j < columns ; j++) {
                                    char c = board[i][j];
                                    if (c == 'R') {
                                        if (i == 0) {
                                            if (j == 0) {
                                                rouge_1_6.setVisible(true);
                                            } else if (j == 1) {
                                                rouge_2_6.setVisible(true);
                                            } else if (j == 2) {
                                                rouge_3_6.setVisible(true);
                                            } else if (j == 3) {
                                                rouge_4_6.setVisible(true);
                                            } else if (j == 4) {
                                                rouge_5_6.setVisible(true);
                                            } else if (j == 5) {
                                                rouge_6_6.setVisible(true);
                                            } else if (j == 6) {
                                                rouge_7_6.setVisible(true);
                                            }
                                        } else if (i == 1) {
                                            if (j == 0) {
                                                rouge_1_5.setVisible(true);
                                            } else if (j == 1) {
                                                rouge_2_5.setVisible(true);
                                            } else if (j == 2) {
                                                rouge_3_5.setVisible(true);
                                            } else if (j == 3) {
                                                rouge_4_5.setVisible(true);
                                            } else if (j == 4) {
                                                rouge_5_5.setVisible(true);
                                            } else if (j == 5) {
                                                rouge_6_5.setVisible(true);
                                            } else if (j == 6) {
                                                rouge_7_5.setVisible(true);
                                            }

                                        } else if (i == 2) {
                                            if (j == 0) {
                                                rouge_1_4.setVisible(true);
                                            } else if (j == 1) {
                                                rouge_2_4.setVisible(true);
                                            } else if (j == 2) {
                                                rouge_3_4.setVisible(true);
                                            } else if (j == 3) {
                                                rouge_4_4.setVisible(true);
                                            } else if (j == 4) {
                                                rouge_5_4.setVisible(true);
                                            } else if (j == 5) {
                                                rouge_6_4.setVisible(true);
                                            } else if (j == 6) {
                                                rouge_7_4.setVisible(true);
                                            }
                                        } else if (i == 3) {
                                            if (j == 0) {
                                                rouge_1_3.setVisible(true);
                                            } else if (j == 1) {
                                                rouge_2_3.setVisible(true);
                                            } else if (j == 2) {
                                                rouge_3_3.setVisible(true);
                                            } else if (j == 3) {
                                                rouge_4_3.setVisible(true);
                                            } else if (j == 4) {
                                                rouge_5_3.setVisible(true);
                                            } else if (j == 5) {
                                                rouge_6_3.setVisible(true);
                                            } else if (j == 6) {
                                                rouge_7_3.setVisible(true);
                                            }
                                        } else if (i == 4) {
                                            if (j == 0) {
                                                rouge_1_2.setVisible(true);
                                            } else if (j == 1) {
                                                rouge_2_2.setVisible(true);
                                            } else if (j == 2) {
                                                rouge_3_2.setVisible(true);
                                            } else if (j == 3) {
                                                rouge_4_2.setVisible(true);
                                            } else if (j == 4) {
                                                rouge_5_2.setVisible(true);
                                            } else if (j == 5) {
                                                rouge_6_2.setVisible(true);
                                            } else if (j == 6) {
                                                rouge_7_2.setVisible(true);
                                            }
                                        } else if (i == 5) {

                                            if (j == 0) {
                                                rouge_1_1.setVisible(true);
                                            } else if (j == 1) {
                                                rouge_2_1.setVisible(true);
                                            } else if (j == 2) {
                                                rouge_3_1.setVisible(true);
                                            } else if (j == 3) {
                                                rouge_4_1.setVisible(true);
                                            } else if (j == 4) {
                                                rouge_5_1.setVisible(true);
                                            } else if (j == 5) {
                                                rouge_6_1.setVisible(true);
                                            } else if (j == 6) {
                                                rouge_7_1.setVisible(true);
                                            }
                                        }

                                    } else if (c == 'Y') {
                                        if (i == 0) {
                                            if (j == 0) {
                                                jaune_1_6.setVisible(true);
                                            } else if (j == 1) {
                                                jaune_2_6.setVisible(true);
                                            } else if (j == 2) {
                                                jaune_3_6.setVisible(true);
                                            } else if (j == 3) {
                                                jaune_4_6.setVisible(true);
                                            } else if (j == 4) {
                                                jaune_5_6.setVisible(true);
                                            } else if (j == 5) {
                                                jaune_6_6.setVisible(true);
                                            } else if (j == 6) {
                                                jaune_7_6.setVisible(true);
                                            }
                                        } else if (i == 1) {
                                            if (j == 0) {
                                                jaune_1_5.setVisible(true);
                                            } else if (j == 1) {
                                                jaune_2_5.setVisible(true);
                                            } else if (j == 2) {
                                                jaune_3_5.setVisible(true);
                                            } else if (j == 3) {
                                                jaune_4_5.setVisible(true);
                                            } else if (j == 4) {
                                                jaune_5_5.setVisible(true);
                                            } else if (j == 5) {
                                                jaune_6_5.setVisible(true);
                                            } else if (j == 6) {
                                                jaune_7_5.setVisible(true);
                                            }
                                        } else if (i == 2) {
                                            if (j == 0) {
                                                jaune_1_4.setVisible(true);
                                            } else if (j == 1) {
                                                jaune_2_4.setVisible(true);
                                            } else if (j == 2) {
                                                jaune_3_4.setVisible(true);
                                            } else if (j == 3) {
                                                jaune_4_4.setVisible(true);
                                            } else if (j == 4) {
                                                jaune_5_4.setVisible(true);
                                            } else if (j == 5) {
                                                jaune_6_4.setVisible(true);
                                            } else if (j == 6) {
                                                jaune_7_4.setVisible(true);
                                            }
                                        } else if (i == 3) {
                                            if (j == 0) {
                                                jaune_1_3.setVisible(true);
                                            } else if (j == 1) {
                                                jaune_2_3.setVisible(true);
                                            } else if (j == 2) {
                                                jaune_3_3.setVisible(true);
                                            } else if (j == 3) {
                                                jaune_4_3.setVisible(true);
                                            } else if (j == 4) {
                                                jaune_5_3.setVisible(true);
                                            } else if (j == 5) {
                                                jaune_6_3.setVisible(true);
                                            } else if (j == 6) {
                                                jaune_7_3.setVisible(true);
                                            }
                                        } else if (i == 4) {
                                            if (j == 0) {
                                                jaune_1_2.setVisible(true);
                                            } else if (j == 1) {
                                                jaune_2_2.setVisible(true);
                                            } else if (j == 2) {
                                                jaune_3_2.setVisible(true);
                                            } else if (j == 3) {
                                                jaune_4_2.setVisible(true);
                                            } else if (j == 4) {
                                                jaune_5_2.setVisible(true);
                                            } else if (j == 5) {
                                                jaune_6_2.setVisible(true);
                                            } else if (j == 6) {
                                                jaune_7_2.setVisible(true);
                                            }
                                        } else if (i == 5) {
                                            if (j == 0) {
                                                jaune_1_1.setVisible(true);
                                            } else if (j == 1) {
                                                jaune_2_1.setVisible(true);
                                            } else if (j == 2) {
                                                jaune_3_1.setVisible(true);
                                            } else if (j == 3) {
                                                jaune_4_1.setVisible(true);
                                            } else if (j == 4) {
                                                jaune_5_1.setVisible(true);
                                            } else if (j == 5) {
                                                jaune_6_1.setVisible(true);
                                            } else if (j == 6) {
                                                jaune_7_1.setVisible(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
            t.start();
        });
    }

    @FXML
    public void handleButton1(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()  && client.canDropToken(0)){
            client.setCoupJoueur(0);
        }
    }

    @FXML
    public void handleButton2(){
        if(client.getPlayerNumber() == client.getCurrentPlayer() && client.canDropToken(1)){
            client.setCoupJoueur(1);
        }
    }

    @FXML
    public void handleButton3(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()  && client.canDropToken(2)){
            client.setCoupJoueur(2);
        }
    }

    @FXML
    public void handleButton4(){
        if(client.getPlayerNumber() == client.getCurrentPlayer() && client.canDropToken(3)){
            client.setCoupJoueur(3);
        }
    }

    @FXML
    public void handleButton5(){
        if(client.getPlayerNumber() == client.getCurrentPlayer() && client.canDropToken(4)){
            client.setCoupJoueur(4);
        }
    }

    @FXML
    public void handleButton6(){
        if(client.getPlayerNumber() == client.getCurrentPlayer() && client.canDropToken(5)){
            client.setCoupJoueur(5);
        }
    }

    @FXML
    public void handleButton7(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()  && client.canDropToken(6)){
            client.setCoupJoueur(6);
        }
    }

    @FXML
    public void handleNewGame() throws IOException {
        if (!client.isSearchGame()){
            client.sendMessage((new Message("search", "search")));
            this.client.resetGame();
            resetVisible();
            client.setSearchGame(true);
        }
    }

    @FXML
    public void handleSendMessage() throws IOException {
        String text = messageSender.getText();
        Message message = new Message("message", text);
        try {
            client.sendMessage(message);
            if (messageArea.getText().equals(""))
                messageArea.appendText(text);
            else{
                messageArea.appendText("\n" + text);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetVisible(){
        jaune_1_1.setVisible(false);
        jaune_2_1.setVisible(false);
        jaune_3_1.setVisible(false);
        jaune_4_1.setVisible(false);
        jaune_5_1.setVisible(false);
        jaune_6_1.setVisible(false);
        jaune_7_1.setVisible(false);
        jaune_1_2.setVisible(false);
        jaune_2_2.setVisible(false);
        jaune_3_2.setVisible(false);
        jaune_4_2.setVisible(false);
        jaune_5_2.setVisible(false);
        jaune_6_2.setVisible(false);
        jaune_7_2.setVisible(false);
        jaune_1_3.setVisible(false);
        jaune_2_3.setVisible(false);
        jaune_3_3.setVisible(false);
        jaune_4_3.setVisible(false);
        jaune_5_3.setVisible(false);
        jaune_6_3.setVisible(false);
        jaune_7_3.setVisible(false);
        jaune_1_4.setVisible(false);
        jaune_2_4.setVisible(false);
        jaune_3_4.setVisible(false);
        jaune_4_4.setVisible(false);
        jaune_5_4.setVisible(false);
        jaune_6_4.setVisible(false);
        jaune_7_4.setVisible(false);
        jaune_1_5.setVisible(false);
        jaune_2_5.setVisible(false);
        jaune_3_5.setVisible(false);
        jaune_4_5.setVisible(false);
        jaune_5_5.setVisible(false);
        jaune_6_5.setVisible(false);
        jaune_7_5.setVisible(false);
        jaune_1_6.setVisible(false);
        jaune_2_6.setVisible(false);
        jaune_3_6.setVisible(false);
        jaune_4_6.setVisible(false);
        jaune_5_6.setVisible(false);
        jaune_6_6.setVisible(false);
        jaune_7_6.setVisible(false);
        rouge_1_1.setVisible(false);
        rouge_2_1.setVisible(false);
        rouge_3_1.setVisible(false);
        rouge_4_1.setVisible(false);
        rouge_5_1.setVisible(false);
        rouge_6_1.setVisible(false);
        rouge_7_1.setVisible(false);
        rouge_1_2.setVisible(false);
        rouge_2_2.setVisible(false);
        rouge_3_2.setVisible(false);
        rouge_4_2.setVisible(false);
        rouge_5_2.setVisible(false);
        rouge_6_2.setVisible(false);
        rouge_7_2.setVisible(false);
        rouge_1_3.setVisible(false);
        rouge_2_3.setVisible(false);
        rouge_3_3.setVisible(false);
        rouge_4_3.setVisible(false);
        rouge_5_3.setVisible(false);
        rouge_6_3.setVisible(false);
        rouge_7_3.setVisible(false);
        rouge_1_4.setVisible(false);
        rouge_2_4.setVisible(false);
        rouge_3_4.setVisible(false);
        rouge_4_4.setVisible(false);
        rouge_5_4.setVisible(false);
        rouge_6_4.setVisible(false);
        rouge_7_4.setVisible(false);
        rouge_1_5.setVisible(false);
        rouge_2_5.setVisible(false);
        rouge_3_5.setVisible(false);
        rouge_4_5.setVisible(false);
        rouge_5_5.setVisible(false);
        rouge_6_5.setVisible(false);
        rouge_7_5.setVisible(false);
        rouge_1_6.setVisible(false);
        rouge_2_6.setVisible(false);
        rouge_3_6.setVisible(false);
        rouge_4_6.setVisible(false);
        rouge_5_6.setVisible(false);
        rouge_6_6.setVisible(false);
        rouge_7_6.setVisible(false);
        your_turn.setVisible(false);
        not_your_turn.setVisible(false);
    }

    @FXML
    public void handleExit(){
        try {
            client.disconnectedServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        exit(0);
    }
}
