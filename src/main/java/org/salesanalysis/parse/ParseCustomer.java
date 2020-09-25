package org.salesanalysis.parse;

import java.math.BigDecimal;
import org.salesanalysis.load.Load;
import org.salesanalysis.model.Customer;
import org.salesanalysis.model.Person;

public class ParseCustomer implements IParse<Person> {


  public Person parse(String row) {
    String rowData[] = row.split(Load.SEP);
    Person customer = null;
    try {
      customer = Customer.customerBuilder().cpf(Long.valueOf(rowData[1]))
          .name(rowData[2])
          .salary(new BigDecimal(rowData[3]))
          .build();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return customer;
  }
}
