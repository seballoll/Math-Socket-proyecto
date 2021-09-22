import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

public class ChessBoardWithColumnsAndRows implements ActionListener {

    public String username;
    private final JPanel gui_board = new JPanel();

    private JButton[][] chessBoardSquares = new JButton[10][10];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    public JTextField username_text = new JTextField();
    public static random_list casillas = new random_list();
    public boolean is_client = false;
    private Image[][] chessPieceImages = new Image[2][3];
    public static final int seen = 0, KING = 1,
            RuOK = 2, KNIGHT = 3, BIOP = 4, PAN = 5;
    public static final int[] STARTING_ROW = {
             KING
    };
    public static final int BLACK = 0, WHITE = 1;

    public void create_casillas(){
        if (is_client) {
            ;

        } else {
            random_list new_board = new random_list();
            casillas.lista = new_board.setLista();

        }
    }

    ChessBoardWithColumnsAndRows() {
        start_screen();
        //initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui_board.setBorder(new EmptyBorder(20, 0, 5, 5));
        gui_board.setLayout(new BorderLayout());


        chessBoard = new JPanel(new GridLayout(4, 4));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui_board.add(chessBoard);
        create_casillas();
        System.out.println(casillas.lista);
        casillas.printList(casillas.lista);
        Nodo current = casillas.lista.head;

        // create the chess board squares
        Insets buttonMargin = new Insets(100,50,0,23);//manage size
        for (int ii = 0; ii < 4; ii++) {
            for (int jj = 0; jj < 4; jj++) {
                JButton b = new JButton(current.tipo);
                b.setMargin(buttonMargin);


                    b.setBackground(Color.WHITE);

                chessBoardSquares[jj][ii] = b;

                current = current.next;

            }

        }


        // fill the black non-pawn piece row
        for (int ii = 0; ii < 4; ii++) {//manage buttons total
            for (int jj = 0; jj < 4; jj++) {
                switch (jj) {
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    public static void setCasillas(Lista_Doble board){
        casillas.lista = board;
    }


    public final void start_screen(){

        GridLayout layout = new GridLayout(10,10);
        gui_board.setLayout(layout);


        JLabel Titulo = new JLabel("Math Socket");
        gui_board.add(Titulo);
        gui_board.add(username_text);
        JButton start_server = new JButton("start server");
        JButton start_client = new JButton("start_client");
        start_server.setActionCommand("start_server");
        start_server.addActionListener(this);
        start_client.setActionCommand("start_client");
        start_client.addActionListener(this);
        gui_board.add(start_server);
        gui_board.add(start_client);





    }
    public void actionPerformed(ActionEvent event){
        if ("start_server".equals(event.getActionCommand())){

            username = username_text.getText();
            System.out.println(username);
            gui_board.removeAll();
            initializeGui();
            Server_Socket server = new Server_Socket();
            server.setName(username);
            try {
                server.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.Send_Board(casillas.lista);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }else if ("start_client".equals(event.getActionCommand())){
            is_client = true;
            username = username_text.getText();
            System.out.println(username);
            Client_Socket client = new Client_Socket();
            client.setName(username);
            try {
                client.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.Receive_Board();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            gui_board.removeAll();
            initializeGui();
        }else if("roll_dice".equals(event.getActionCommand())){
            dice num = new dice();
            num.throw_dice();
        }
    }


    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui_board;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {


            public void run() {
                ChessBoardWithColumnsAndRows cb =
                        new ChessBoardWithColumnsAndRows();

                JFrame f = new JFrame("Player");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);


                
                f.pack();
                // ensures the minimum size is enforced.
                f.setSize(600,600);
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}

