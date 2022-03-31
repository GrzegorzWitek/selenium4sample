package driver;

public class Helper {
    public void wait(int timeInMilliSec){
        try {
            Thread.sleep(timeInMilliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
