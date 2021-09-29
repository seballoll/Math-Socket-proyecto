import java.util.Random;

public class dice {
    /**
     * genera un numero aleatorio
     * @return numero aleatorio
     */
    public static int throw_dice(){
        Random num = new Random();
        int ranum = num.nextInt(5);
        System.out.println(ranum);
        return ranum;

    }

}
