package org.salesanalysis.parse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.salesanalysis.model.Item;

public class ParseItems implements IParse<List<Item>> {

  public List<Item> parse(String row) {
    String preparedRow=row.substring(1,row.length()-1);
    String rowItems[]=preparedRow.split(",");
    List<Item> items=new ArrayList<Item>();
    try {
      for (String rowDataItem : rowItems) {
        String rowData[] = rowDataItem.split("-");
        Item item = Item.builder().id(Long.valueOf(rowData[0]))
            .quantity(new BigDecimal(rowData[1]))
            .price(new BigDecimal(rowData[2]))
            .build();
        items.add(item);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return items;
  }
}
