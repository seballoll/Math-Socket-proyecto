import java.util.Random;
public class dice {
    public static int throw_dice(){
        Random num = new Random();
        System.out.println(num.nextInt(5));
        return num.nextInt(5);

    }

}