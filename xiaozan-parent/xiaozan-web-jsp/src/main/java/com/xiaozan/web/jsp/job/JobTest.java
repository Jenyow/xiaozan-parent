package com.xiaozan.web.jsp.job;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
public class JobTest implements SchedulingConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(JobTest.class);
	
	@Scheduled(cron = "0/1 * * * * ?")
	@Scope("prototype")
	public void run() {
		logger.info("Job Start");
		long begin = System.currentTimeMillis(); // 这段代码放在程序执行前
		for (int i = 0; i < 10000; i++) {
			logger.info(String.valueOf(i));
		}
		long end = System.currentTimeMillis() - begin; // 这段代码放在程序执行后
		logger.info("耗时：" + end + "毫秒");
		logger.info("Job ENd");
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}
	
	@Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(6);
    }
}