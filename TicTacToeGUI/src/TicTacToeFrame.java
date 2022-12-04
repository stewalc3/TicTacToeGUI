import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToeFrame extends JFrame {

    JPanel MainPanel;
    JButton QuitButton;

    JPanel ButtonPanel;
    ArrayList<TicTacToeTile> GridButtons;


    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    int turn = 1;



    public TicTacToeFrame() throws HeadlessException {
        MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.PAGE_AXIS));
        ButtonPanel();
        GameBoard();
        QuitButton = new JButton("Quit");
        QuitButton.addActionListener((ActionEvent ae) -> {System.exit(0);});
        MainPanel.add(ButtonPanel);
        MainPanel.add(QuitButton);
        add(MainPanel);
    }

    private void GameBoard() {
        clearBoard();
        turn = 1;
        for (JButton btn : GridButtons){
            btn.setText(" ");
        }
    }

    private void ButtonPanel() {
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout(3,3));
        GridButtons = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            GridButtons.add(new TicTacToeTile(i));
        }
        for (JButton button : GridButtons){
            button.addActionListener(new GameListener());
            ButtonPanel.add(button);
            button.setText(" ");
        }

    }
    public class GameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int row = ((TicTacToeTile) actionEvent.getSource()).getRow();
            int col = ((TicTacToeTile) actionEvent.getSource()).getCol();

            if (turn % 2 == 1){
                if(isValidMove(row, col)){
                    board[row][col] = "X";
                    ((TicTacToeTile) actionEvent.getSource()).setText("X");
                    turn++;
                }
                else{
                    JOptionPane.showMessageDialog(MainPanel, "Move not valid, please choose another space.");
                }

                if(turn >= 5){
                    if(isWin("X")){
                        int result = JOptionPane.showConfirmDialog(null, "Player 1 Wins!\n Do you wish to play again?", "Player 1 Wins!\n Do you wish to play again?", JOptionPane.YES_NO_OPTION);
                        switch (result) {
                            case JOptionPane.YES_OPTION:
                                GameBoard();
                                break;
                            case JOptionPane.NO_OPTION:
                                System.exit(0);
                                break;
                        }
                    }
                }

                if(turn >= 7){
                    if(isTie()){
                        int result = JOptionPane.showConfirmDialog(null, "Tie Game!\n Do you wish to play again?", "Tie Game!\n Do you wish to play again?", JOptionPane.YES_NO_OPTION);
                        switch (result) {
                            case JOptionPane.YES_OPTION:
                                GameBoard();
                                break;
                            case JOptionPane.NO_OPTION:
                                System.exit(0);
                                break;
                        }
                    }
                }

            }
            else{
                if(isValidMove(row, col)){
                    board[row][col] = "O";
                    ((TicTacToeTile) actionEvent.getSource()).setText("O");
                    turn++;
                }
                else{
                    JOptionPane.showMessageDialog(MainPanel, "Move not valid, please choose another space.");
                }

                if(turn >= 5){
                    if(isWin("O")){
                        int result = JOptionPane.showConfirmDialog(null, "Player 2 Wins!\n Do you wish to play again?", "Player 2 Wins!\n Do you wish to play again?", JOptionPane.YES_NO_OPTION);
                        switch (result) {
                            case JOptionPane.YES_OPTION:
                                GameBoard();
                                break;
                            case JOptionPane.NO_OPTION:
                                System.exit(0);
                                break;
                        }
                    }
                }

                if(turn >= 7){
                    if(isTie()){
                        int result = JOptionPane.showConfirmDialog(null, "Tie Game!\n Do you wish to play again?", "Tie Game!\n Do you wish to play again?", JOptionPane.YES_NO_OPTION);
                        switch (result) {
                            case JOptionPane.YES_OPTION:
                                GameBoard();
                                break;
                            case JOptionPane.NO_OPTION:
                                System.exit(0);
                                break;
                        }
                    }
                }
            }

        }
    }

    public class TicTacToeTile extends JButton {
        int GridNumber;
        public TicTacToeTile(int input) {
            GridNumber = input;
        }
        public int getRow() {
            return GridNumber / 3;
        }
        public int getCol() {
            return GridNumber % 3;
        }
    }

    private static void clearBoard() {
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if(board[row][col].equals(" "))
            retVal = true;

        return retVal;

    }

    private static boolean isWin(String player) {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }

    private static boolean isColWin(String player) {
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {

        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X"))
            {
                xFlag = true;
            }
            if(board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O"))
            {
                oFlag = true;
            }

            if(! (xFlag && oFlag) )
            {
                return false;
            }

            xFlag = oFlag = false;

        }
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X"))
            {
                xFlag = true;
            }
            if(board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O"))
            {
                oFlag = true;
            }

            if(! (xFlag && oFlag) )
            {
                return false;
            }
        }

        xFlag = oFlag = false;

        if(board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false;
        }
        xFlag = oFlag = false;

        if(board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false;
        }
        return true;
    }
}
