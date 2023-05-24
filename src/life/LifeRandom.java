package life;

import java.awt.*;
import java.util.Random;
import static life.LifePanel.*;

public class LifeRandom {

    Random random = new Random();
    public Color check (byte c){
        if (c == 1){
            double rand = random.nextDouble();
            if (rand <= 0.5){
                return c1;
            }
            else{
                return c2;
            }
        }
        else{
            return c0;
        }
    }
}
