package com.jj.barcabot.tasks.config;

import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;

/**
 * The type Task logger.
 */
@Log4j2
public class TaskLogger {

  private static final String TASK_TYPE = "taskType";
  private static final String TASK_ID = "taskId";

  /**
   * Log task type.
   *
   * @param taskType the task type
   */
  public static void logTaskType(String taskType) {
    MDC.put(TASK_TYPE, taskType);
  }

  /**
   * Log task id.
   */
  public static void logTaskId() {
    MDC.put(TASK_ID, getTaskId());
  }

  private static String getTaskId() {
    return Optional.ofNullable(MDC.get(TASK_ID))
        .map(TaskLogger::parseInteger)
        .map(x -> x++)
        .map(String::valueOf)
        .orElse("1");
  }

  private static int parseInteger(String number) {
    try {
      return Integer.parseInt(number);
    } catch (NumberFormatException e) {
      log.warn("Error parsing task number", e);
      return 0;
    }
  }
}
