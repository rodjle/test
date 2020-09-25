package org.salesanalysis.load;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SalesAnalysisResultToFile implements ISalesProcessOutput {


  @Override
  public void processOutput(ISalesProcessInput salesDataProcessor, String fileName) {
      writeFile(fileName,salesDataProcessor);
  }



  private void writeFile(String fileName, ISalesProcessInput salesDataProcessor) {
    FileWriter fileWriter= null;
    try {
      fileWriter = new FileWriter(fileName);
      PrintWriter printWriter=new PrintWriter(fileWriter);
      printWriter.println("Amount of clients :"+salesDataProcessor.amountClients());
      printWriter.println("Amount of salesman:"+salesDataProcessor.amountSalesMan());
      printWriter.println("ID of the most expensive sale:"+salesDataProcessor.idMostExpensiveSale());
      printWriter.println("Worst salesman ever:"+salesDataProcessor.worstSalesManEver());
      fileWriter.close();
    } catch (IOException e) {
      System.out.println("Directory :"+Load.PATH_OUT+" Doesn't exists!");
    }

  }



}
