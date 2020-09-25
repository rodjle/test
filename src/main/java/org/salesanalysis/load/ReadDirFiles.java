package org.salesanalysis.load;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class ReadDirFiles implements IDirRead {

  private ISalesProcessInput salesDataProcessor;
  private ISalesProcessOutput salesAnalysisResult;


  @Override
  public TimerTask readFilesDir(String path) {
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        boolean fileExists = false;
        System.out.println("Analyzing directory...");
        salesDataProcessor = new SalesDataProcessorFromFile();
        try {
          File dir = new File(path);
          File[] files = dir.listFiles();
          for (File file : files) {
            fileExists = true;
            if (file.getName().contains(".dat")) {
              try {
                salesDataProcessor.processInput(file.getCanonicalPath());
              } catch (IOException ex) {
                ex.printStackTrace();
              }
            }
          }
          if (!fileExists) {
            System.out.println("There is no files!");
          }

          if (salesDataProcessor.itWasProcessed()) {
            salesAnalysisResult = new SalesAnalysisResultToFile();
            salesAnalysisResult
                .processOutput(salesDataProcessor, Load.PATH_OUT + "/" + Load.FILE_NAME_OUT);
            System.out.println("Processed!!");
          }

        } catch (Exception e) {
          System.out.println("Directory :"+path+" doesn't exists");
        }
      }


    };

    return timerTask;


  }
}