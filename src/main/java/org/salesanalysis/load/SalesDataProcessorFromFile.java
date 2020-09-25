package org.salesanalysis.load;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.salesanalysis.model.Person;
import org.salesanalysis.model.Sales;
import org.salesanalysis.parse.ParseCustomer;
import org.salesanalysis.parse.ParseSales;
import org.salesanalysis.parse.ParseSalesMan;

public class SalesDataProcessorFromFile implements ISalesProcessInput {

  private List<Person> customerList;
  private List<Person> salesManList;
  private List<Sales> salesList;
  private ParseCustomer customerParse;
  private ParseSalesMan salesManParse;
  private ParseSales salesParse;

  public SalesDataProcessorFromFile() {
    customerList = new ArrayList<Person>();
    salesManList = new ArrayList<Person>();
    salesList = new ArrayList<Sales>();
  }

  @Override
  public void processInput(String fileName) {

    FileInputStream stream = null;
    try {
      stream = new FileInputStream(fileName);
      InputStreamReader reader = new InputStreamReader(stream);
      BufferedReader br = new BufferedReader(reader);
      String row = "";
      while (row != null) {
        row = br.readLine();
        //System.out.println(row);
        if (row != null) {
          int typeReg = Integer.valueOf(row.split(Load.SEP)[0]).intValue();

          switch (typeReg) {
            case Load.REG_TYPE_CUSTOMER:
              customerParse = new ParseCustomer();
              Person customer = customerParse.parse(row);
              if (customer != null) {
                customerList.add(customer);
              }

              break;

            case Load.REG_TYPE_SALESMAN:
              salesManParse = new ParseSalesMan();
              Person salesMan = salesManParse.parse(row);
              if (salesMan != null) {
                salesManList.add(salesMan);
              }
              break;
            case Load.REG_TYPE_SALE:
              salesParse = new ParseSales();
              Sales sales = salesParse.parse(row);
              if (sales != null) {
                salesList.add(sales);
              }
              break;
          }
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }


  }






  @Override
  public int amountClients() {
    return salesList.size();
  }

  @Override
  public int amountSalesMan() {
    return salesManList.size();
  }

  @Override
  public int idMostExpensiveSale() {
    Collections.sort(salesList, new Comparator<Sales>() {
      @Override
      public int compare(Sales s1, Sales s2) {
        return s2.getTotal().compareTo(s1.getTotal());
      }
    });

    return salesList.get(0).getId().intValue();
  }

  @Override
  public String worstSalesManEver() {
    //group by person
    Map<Person, Double> worstSalesManEverList = salesList.stream().collect(
        Collectors.groupingBy(Sales::getSalesMan, Collectors.summingDouble(Sales::getTotal)));

    //sort by total asc
    Map<Person, Double> worstSalesManEverOrderedList = new LinkedHashMap<>();
    worstSalesManEverList.entrySet().stream()
        .sorted(Map.Entry.<Person, Double>comparingByValue()
        ).forEachOrdered(e -> worstSalesManEverOrderedList.put(e.getKey(), e.getValue()));

   // System.out.println(worstSalesManEverOrderedList.entrySet().iterator().next());

    return worstSalesManEverOrderedList.entrySet()
        .iterator()
        .next()
        .getKey()
        .getName();

  }

  @Override
  public boolean itWasProcessed() {
    return this.salesList.size() > 0;
  }
}
