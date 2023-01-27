package ru.job4j.quartz;

import java.io.InputStream;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        Properties prs = readProperty();
        int timing = Integer.parseInt(prs.getProperty("rabbit.interval"));
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(timing)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
        }
    }

    static private Properties readProperty() {
        final Properties prs = new Properties();
        try (InputStream is = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            prs.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prs;
    }

}