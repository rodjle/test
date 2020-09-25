package org.salesanalysis.parse;

import java.util.List;
import org.salesanalysis.load.Load;
import org.salesanalysis.model.Item;
import org.salesanalysis.model.Person;
import org.salesanalysis.model.Sales;
import org.salesanalysis.model.SalesMan;

public class ParseSales implements IParse<Sales> {

  public Sales parse(String row) {
    String rowData[] = row.split(Load.SEP);
    Sales sales = null;

    try {
      IParse<List<Item>> parseItems = new ParseItems();
      List<Item> items = parseItems.parse(rowData[2]);

      Person salesMan = SalesMan.salesManBuilder().name(rowData[3]).build();
      sales = Sales.builder().id(Long.valueOf(rowData[1]))
          .items(items)
          .salesMan(salesMan)
          .build();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sales;
  }
}
