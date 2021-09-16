import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class ChessBoardWithColumnsAndRows {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[10][10];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "!");


    ChessBoardWithColumnsAndRows() {
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(20, 0, 0, 0));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        // tools.add(new JButton("New")); // agregar funcionalidad
        //tools.add(new JButton("Save")); // agregar funcionalidad
        // tools.add(new JButton("Restore")); // agregar funcionalidad
        // tools.addSeparator();
        // tools.add(new JButton("Resign")); // agregar funcionalidad
        //tools.addSeparator();
        //tools.add(message);

        gui.add(new JLabel(""), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(4, 4));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
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

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
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
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}

