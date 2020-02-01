package com.jj.barcabot.service.util;

/**
 * The interface Process helper.
 */
public interface ProcessHelper {

  /**
   * Execute int.
   *
   * @param command the command
   * @return the int
   */
  int executeAndWait(String command);

  Process execute(String command);
}
