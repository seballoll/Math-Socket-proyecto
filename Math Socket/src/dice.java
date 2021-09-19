import java.util.Random;
public class dice {
    public static int throw_dice(){
        Random num = new Random();
        return num.nextInt(5);
    }
}
