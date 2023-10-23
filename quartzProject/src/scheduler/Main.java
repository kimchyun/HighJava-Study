package scheduler;

import org.quartz.SchedulerException;
import scheduler.QuartzExample;

public class Main {
    public static void main(String[] args) throws SchedulerException {
        QuartzExample quartzExample = new QuartzExample();
        String path = null;
        quartzExample.mainMethod(path);
    }
}