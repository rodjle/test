package org.salesanalysis.load;

import java.util.List;
import org.salesanalysis.model.Person;
import org.salesanalysis.model.Sales;

public interface ISalesProcessInput {




  int amountClients();

  int amountSalesMan();

  int idMostExpensiveSale();

  String worstSalesManEver();

  boolean itWasProcessed();

  void processInput(String fileName);


}
