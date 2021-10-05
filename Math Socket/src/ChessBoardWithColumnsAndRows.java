import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class ChessBoardWithColumnsAndRows implements ActionListener {

    public String username;
    private final JPanel gui_board = new JPanel();
    public static int server_pos[];
    public static int client_pos[];
    public static Server_Socket server;
    public static Client_Socket client;
    private static JButton[][] chessBoardSquares = new JButton[10][10];
    private static JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Welcome");
    public JTextField username_text = new JTextField();
    public static random_list casillas = new random_list();
    public static boolean is_client = false;
    public static final int KING = 1;
    public static final int[] STARTING_ROW = {
             KING
    };
    private static Image[] chessPieceImages = new Image[4];


    /**
     * crea el tablero de juego
     */
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
    }

    /**
     * inicia la pantalla de juego.
     */
    public final void initializeGui() {
        // set up the main GUI
        gui_board.setBorder(new EmptyBorder(20, 0, 5, 5));
        JToolBar tools = new JToolBar();
        gui_board.setLayout(new BorderLayout());
        gui_board.add(tools, BorderLayout.PAGE_START);

        Action nuevaAccionJuego = new AbstractAction("Lanzar dado") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (is_client) {
                    int num = dice.throw_dice();
                    set_client_pos(num);
                    try {
                        client.Send_client_pos(client_pos);
                        movement();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    int num = dice.throw_dice();
                    set_server_pos(num);
                    try {
                        server.Send_ser_pos(server_pos);
                        movement();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
        };
        Action AcualizarSocket = new AbstractAction("refrescar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (is_client) {
                    try {
                        wipe();
                        client.Receive_ser_pos();
                        movement();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }else{
                    try {
                        wipe();
                        server.Receive_client_pos();
                        movement();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }

            }
        };

        tools.add(nuevaAccionJuego);
        tools.add(AcualizarSocket);



        chessBoard = new JPanel(new GridLayout(4, 4));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui_board.add(chessBoard);
        create_casillas();
        System.out.println(casillas.lista);
        casillas.printList(casillas.lista);
        Nodo current = casillas.lista.head;


        // create the chess board squares
        Insets buttonMargi = new Insets(0,0,100,23);//manage size
        for (int ii = 0; ii < 4; ii++) {
            for (int jj = 0; jj < 4; jj++) {
                JButton b = new JButton(current.tipo);
                b.setMargin(buttonMargi);


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
        createimage();//calls void for charging images
        setupNewGame();//set images on board
    }


    /**
     * carga las imagenes de los jugadores y las carga en una lista (chess piece image).
     */
    private void createimage(){
        try{
            URL url= new URL("https://2.bp.blogspot.com/-fOKnfX3wl_4/WfEkY9coU3I/AAAAAAAAA6Q/O1huB_NomcgBt9XdMnJ3nj_lgv7_5ja7ACLcBGAs/s320/color%2Brojo.jpg");
            URL te=new URL("https://biblioteca.acropolis.org/wp-content/uploads/2014/12/azul.png");

            BufferedImage bi= ImageIO.read(url);
            BufferedImage be= ImageIO.read(te);


            for (int ii=0; ii<4; ii++){
                if (ii==1)
                    chessPieceImages[ii]=bi.getSubimage(230,0,20,20);
                else{
                    if(ii==2){
                        chessPieceImages[ii]=be.getSubimage(230,0,20,90);

                    }


                    }
                }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);

        }
    }
    public static void setServer_pos(int[] server_pos) {
        ChessBoardWithColumnsAndRows.server_pos = server_pos;
    }

    public static void setClient_pos(int[] client_pos) {
        ChessBoardWithColumnsAndRows.client_pos = client_pos;
    }


    /**
     * determina el tablero
     * @param board tablero en lista doble
     */
    public static void setCasillas(Lista_Doble board){
        casillas.lista = board;
    }

    /**
     * empieza la pantalla de incio.
     */
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

    /**
     * lector de eventos de los botones en pantalla
     * @param event evento a realizar
     */
    public void actionPerformed(ActionEvent event){
        if ("start_server".equals(event.getActionCommand())){

            username = username_text.getText();
            System.out.println(username);
            gui_board.removeAll();
            initializeGui();
            server = new Server_Socket();
            server.setName(username);
            try {
                server.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.Send_Board(casillas.lista);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }



        }else if ("start_client".equals(event.getActionCommand())){
            is_client = true;
            username = username_text.getText();
            System.out.println(username);
            client = new Client_Socket();
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
        }
    }


    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui_board;
    }

    /**
     * establece las imagenes sobre el tablero en posicion inicial
     */
    private void setupNewGame() {
        server_pos = new int[3];
        server_pos[0] = 0;
        server_pos[1] = 0;
        server_pos[2] = 1;
        client_pos = new int[3];
        client_pos[0] = 0;
        client_pos[1] = 0;
        client_pos[2] = 1;

            ImageIcon hi=new ImageIcon(chessPieceImages[1]);
            chessBoardSquares[server_pos[0]][server_pos[1]].setIcon(hi);

            JLabel label=new JLabel((new ImageIcon(chessPieceImages[2])));
            chessBoardSquares[client_pos[0]][client_pos[1]].add(label);

    }

    /**
     * metodo para mover el jugador a traves del tablero
     */
    public static final void movement() {
        JLabel label = new JLabel(new ImageIcon(chessPieceImages[2]));
        chessBoardSquares[client_pos[0]][client_pos[1]].add(label);
        ImageIcon hi = new ImageIcon(chessPieceImages[1]);
        chessBoardSquares[server_pos[0]][server_pos[1]].setIcon(hi);
        chessBoard.validate();
        chessBoard.repaint();



    }
    public static void wipe(){

        for(int i = 0; i<3;i++){
            for(int j = 0; j<3 ; j++){
                chessBoardSquares[j][i].setIcon(null);
                Component[] label = chessBoardSquares[j][i].getComponents();
                if (label.length!= 0){
                    chessBoardSquares[j][i].remove(label[0]);
                }

            }
        }
        chessBoard.validate();
        chessBoard.repaint();

    }

    /**
     * determina la posicion actual del servidor.
     * @param pos numero de movimientos en casilla
     */
    public static void set_server_pos(int pos){
        chessBoardSquares[server_pos[0]][server_pos[1]].setIcon(null);
        server_pos[2] += pos;
        server_pos[0] += pos;
        System.out.println(server_pos[0]);
        if (server_pos[0]>3){
            int temp = server_pos[0]-4;
            server_pos[0] = temp;
            server_pos[1] += 1;

        }
    }

    /**
     * determina la posicion actual del cliente.
     * @param pos numero de movimientos en casilla
     */
    public static void set_client_pos(int pos){
        Component[] label = chessBoardSquares[client_pos[0]][client_pos[1]].getComponents();
        chessBoardSquares[client_pos[0]][client_pos[1]].remove(label[0]);
        client_pos[2] += pos;
        client_pos[0] += pos;
        if (client_pos[0]>3){
            int temp = client_pos[0]-4;
            client_pos[0] = temp;
            client_pos[1] += 1;
        }
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
                f.setResizable(false);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}

