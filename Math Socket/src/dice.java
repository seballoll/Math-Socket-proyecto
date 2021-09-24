import java.util.Random;
public class dice {
    /**
     * genera un numero aleatorio
     * @return numero de 1 a 4
     */
    public static int throw_dice(){
        Random num = new Random();
        return num.nextInt(5);
    }
}
