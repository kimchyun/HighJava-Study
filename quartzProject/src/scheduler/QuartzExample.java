package scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 주기적으로 파일을 저장하는 Quartz 스케줄러를 구현한 Java 프로그램입니다.
 * 이 프로그램은 Quartz 라이브러리를 사용하여 소스 파일의 내용을 읽고 현재 날짜 및 시간과 함께 출력 파일에 추가하는 작업을 예약합니다.

 * 이 프로그램은 'QuartzExample' 및 'PrintTimeJob'이라는 두 가지 작업 클래스와 스케줄러를 관리하는 'SchedulerManager' 클래스를 정의합니다.
 * QuartzExample 클래스에는 파일 저장 작업에 대한 코드가 포함되어 있고
 * PrintTimeJob 클래스에는 현재 시간을 콘솔에 인쇄하는 작업에 대한 코드가 포함되어 있습니다.
 * RacingTestJob 클래스에는 경마 프로그램을 실행하는 코드가 포함되어 있습니다.
 * SchedulerManager 클래스는 스케줄러 시작 및 중지와 작업 예약을 담당합니다.

 * QuartzExample 작업은 QuartzExample.java라는 소스 파일의 내용을 읽고 이를 output.txt라는 출력 파일에 추가합니다.
 * 먼저 출력 파일의 기존 내용(있는 경우)을 읽고 새 내용을 여기에 추가합니다. 그런 다음 소스 파일의 내용을 읽고 출력 파일에 추가합니다.
 * 마지막으로 업데이트된 내용을 출력 파일에 씁니다.
 */
public class QuartzExample implements Job {
    private String path;            // 매개변수로 받는 파일경로
    
    /**
     * 메인 메서드입니다. 사용자로부터 입력받은 주기에 따라 Quartz 스케줄러를 설정합니다.
     */
    public void mainMethod(String path) throws SchedulerException {
        SchedulerManager schedulerManager = new SchedulerManager();
        Scanner scanner = new Scanner(System.in);
        this.path = path;
        int selectNumber;

        QuartzExample quartzExample = new QuartzExample();
        quartzExample.autoSaveFile();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while (true) {
            System.out.println("──────────────────────────────");
            System.out.println(" 1. 현재 시간 ");
            System.out.println(" 2. 경마 ");
            System.out.println(" 0. 종료 ");
            System.out.println("──────────────────────────────");
            System.out.print(" 실행할 번호 선택 : ");
            selectNumber = scanner.nextInt();

            if (selectNumber == 0) {
                System.out.println("프로그램을 종료합니다.");
                scanner.close();
                schedulerManager.shutdown();
                System.exit(0);
            }

            System.out.print("몇 번 실행하시겠습니까? : ");
            int repeatCount = scanner.nextInt();
            if (repeatCount <= 0) {
                System.out.println("반복 횟수는 1 이상이어야 합니다.");
                continue;
            }

            System.out.print("몇 초마다 실행하시겠습니까? : ");
            int seconds = scanner.nextInt();
            if (seconds <= 0) {
                System.out.println("시간 간격은 1 이상이어야 합니다.");
                continue;
            }

            // JobDetail 객체를 생성한다.
            JobDetail selectedJob;
            switch (selectNumber) {
                case 1:
                    // 현재 시간을 출력하는 PrintTimeJob 클래스를 사용해 JobDetail 객체를 생성한다.
                    selectedJob = JobBuilder.newJob(PrintTimeJob.class)
                            .withIdentity("printTimeJob", "group1")
                            .build();
                    break;
                case 2:
                    // 경마를 실행하는 RacingTestJob 클래스를 사용해 JobDetail 객체를 생성한다.
                    selectedJob = JobBuilder.newJob(RacingTestJob.class)
                            .withIdentity("racingTestJob", "group1")
                            .build();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    continue;
            }

            // 지정된 주기와 횟수로 실행할 Trigger 객체를 생성한다.
            Trigger selectedTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("selectedTrigger", "group1")
                    .startNow()     // 스케줄링 시작 시점을 현재 시간으로 설정한다.
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(seconds)     // 실행 간격을 입력받은 값으로 설정한다.
                            .withRepeatCount(repeatCount - 1)) // 입력받은 실행 횟수보다 1회 적게 설정한다.
                    .build();
            // 생성한 JobDetail 객체와 Trigger 객체를 사용해 스케줄링을 실행한다.
            schedulerManager.scheduleJob(selectedJob, selectedTrigger);

            // 스케줄링이 끝날 때까지 대기
            while (schedulerManager.checkExists(selectedJob.getKey(), selectedTrigger.getKey())) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Quartz 스케줄러에 의해 주기적으로 실행될 기존 파일을 읽어와 현재 파일 내용을 추가해서 저장하는 메서드입니다.
     */
    public void execute(JobExecutionContext context) {
        try {
            String inputFileName = path;
            if (inputFileName == null) {
                inputFileName = System.getProperty("user.dir") + "/src/scheduler/QuartzExample.java";     // 입력 파일 경로 이름
            }
            String outputFileName = "output.txt";                       // 출력 파일 이름
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 날짜 포맷 지정
            String fileContent = "\n\n파일 저장 시간 : " + dateFormat.format(new Date()) + "\n";  // 저장할 내용

            // 출력 파일의 기존 내용을 읽어옴
            StringBuilder existingContent = new StringBuilder();
            File existingFile = new File(outputFileName);
            if (existingFile.exists()) {
                Scanner scanner = new Scanner(existingFile, StandardCharsets.UTF_8.name());
                while (scanner.hasNextLine()) {
                    existingContent.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
            }

            // 새로운 내용을 기존 내용과 합침
            fileContent = existingContent + fileContent;

            // 입력 파일의 내용을 읽어옴
            File inputFile = new File(inputFileName);
            Scanner inputScanner = new Scanner(inputFile, StandardCharsets.UTF_8.name());
            StringBuilder inputContent = new StringBuilder();
            while (inputScanner.hasNextLine()) {
                inputContent.append(inputScanner.nextLine()).append("\n");
            }
            inputScanner.close();

            // 입력 파일의 내용을 출력 파일에 추가
            fileContent = fileContent + inputContent;

            // 출력 파일에 업데이트된 내용을 씀
            FileOutputStream fileOutputStream = new FileOutputStream(existingFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(fileContent);
            outputStreamWriter.close();

            System.out.println("파일을 성공적으로 백업했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 파일을 5분마다 자동으로 output.txt 이름으로 저장하는 메소드
     * @throws SchedulerException 스케줄러 관련 예외 발생 가능성이 있다.
     */
    private void autoSaveFile() throws SchedulerException {
        // 파일 저장 Job 생성
        JobDetail job = JobBuilder.newJob(QuartzExample.class)
                .withIdentity("fileSaverJob", "group1")
                .build();

        // 파일 저장 트리거 생성
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("fileSaverTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(5)       // 5분 주기로 실행
                        .repeatForever())               // 무한 반복
                .build();


        // 스케줄러 팩토리를 통해 스케줄러 생성 및 시작
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        // Job 과 Trigger 를 스케줄러에 등록
        scheduler.scheduleJob(job, trigger);
    }

    

    /**
     * 현재 시간을 콘솔에 출력하는 Quartz 작업을 구현하는 클래스.
     */
    public static class PrintTimeJob implements Job {
        /**
         * Quartz 스케줄러에 의해 주기적으로 실행될 메서드입니다.
         */
        public void execute(JobExecutionContext context) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초"); // 날짜 포맷 지정
            String currentTime = dateFormat.format(new Date());
            System.out.println("현재 시간 : " + currentTime);
        }
    }

    public static class RacingTestJob implements Job {
        public void execute(JobExecutionContext context) {
            System.out.println("경마 시작!");
            racingTest racingTest = new racingTest();
            racingTest.racing();
        }
    }
    /**
     * 스케줄러를 관리하는 클래스
     */
    public static class SchedulerManager {
        private Scheduler scheduler;

        /**
         * SchedulerManager 생성자
         * SchedulerFactory를 사용하여 Scheduler 객체를 생성하고 시작한다.
         */
        public SchedulerManager() {
            try {
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                this.scheduler = schedulerFactory.getScheduler();
                this.scheduler.start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        /**
         * 지정된 JobDetail 객체와 Trigger 객체를 사용하여 스케줄링을 실행한다.
         *
         * @param job     스케줄링할 JobDetail 객체
         * @param trigger 스케줄링할 Trigger 객체
         */
        public void scheduleJob(JobDetail job, Trigger trigger) {
            try {
                scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        /**
         * 스케줄러를 종료한다.
         */
        public void shutdown() {
            try {
                scheduler.shutdown();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        /**
         * 지정된 JobKey와 TriggerKey가 스케줄러에 존재하는지 확인한다.
         * @param jobKey     확인할 JobKey
         * @param triggerKey 확인할 TriggerKey
         * @return 스케줄러에 지정된 JobKey와 TriggerKey가 모두 존재하면 true, 그렇지 않으면 false
         */
        public boolean checkExists(JobKey jobKey, TriggerKey triggerKey) {
            try {
                return scheduler.checkExists(triggerKey) && scheduler.checkExists(jobKey);
            } catch (SchedulerException e) {
                e.printStackTrace();
                return false;
            }
        }

    }
}


