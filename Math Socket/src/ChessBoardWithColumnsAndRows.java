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
    public JTextField username_text = new JTextField();



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
        random_list Lista_De_Cuadros = new random_list();
        Nodo current = Lista_De_Cuadros.setLista().head;

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
            gui_board.removeAll();
            initializeGui();
            username = username_text.getText();
            System.out.println(username);

            Server_Socket server = new Server_Socket();
            server.setName(username);
            try {
                server.main();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if ("start_client".equals(event.getActionCommand())){
            username = username_text.getText();
            System.out.println(username);
            Client_Socket client = new Client_Socket();
            client.setName(username);
            try {
                client.main();
            } catch (IOException e) {
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

