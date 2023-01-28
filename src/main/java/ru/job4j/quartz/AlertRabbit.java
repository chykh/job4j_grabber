package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    static private Properties readProperty() {
        final Properties prs = new Properties();
        try (InputStream is = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            prs.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prs;
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (Statement statement = connection.createStatement()) {
                String sql = "insert into rabbit values (" + System.currentTimeMillis() + ");";
                statement.execute(sql);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Properties prs = readProperty();
        Class.forName(prs.getProperty("jdbc.driver"));
        String url = prs.getProperty("jdbc.url");
        String login = prs.getProperty("jdbc.username");
        String password = prs.getProperty("jdbc.password");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);

            JobDetail job = newJob(Rabbit.class).usingJobData(data).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

}