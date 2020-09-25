package org.salesanalysis.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sales {
  private Long id;
  private List<Item> items;
  private Person salesMan;
  public Double getTotal(){
     double total=0.0;
     for (Item item:items ){
        total+=item.getQuantity().doubleValue()*item.getPrice().doubleValue();
     }
     return Double.valueOf(total);
  }

}
