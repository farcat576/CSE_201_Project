package Tests;

import com.mygdx.tankstars.TANK_TYPE;
import com.mygdx.tankstars.Tank;
import com.mygdx.tankstars.TankStars;

public class Test1 {
    @Test
    public void widthTest(){
        assertEquals(1200,TankStars.V_WIDTH);
    }
    @Test
    public void heightTest(){
        assertEquals(720,TankStars.V_HEIGHT);
    }
}
