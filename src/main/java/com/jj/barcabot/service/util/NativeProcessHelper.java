package com.jj.barcabot.service.util;

import com.jj.barcabot.exception.CoreRuntimeException;
import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 * The type Native process helper.
 */
@Service
public class NativeProcessHelper implements ProcessHelper {

  @Override
  public int executeAndWait(String command) {
    try {
      Process process = Runtime.getRuntime().exec(command);
      int exitCode = process.waitFor();
      process.destroy();
      return exitCode;
    } catch (Exception e) {
      throw new CoreRuntimeException("Error executing a native process", e);
    }
  }

  @Override
  public Process execute(String command) {
    try {
      return Runtime.getRuntime().exec(command);
    } catch (IOException e) {
      throw new CoreRuntimeException("Error executing a native process", e);
    }
  }

}
