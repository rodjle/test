package org.salesanalysis.parse;

import org.salesanalysis.load.Load;
import org.salesanalysis.model.Person;
import org.salesanalysis.model.SalesMan;

public class ParseSalesMan implements IParse<Person> {

  public Person parse(String row) {
    String rowData[] = row.split(Load.SEP);
    SalesMan salesMan = null;

    try {

      salesMan = SalesMan.salesManBuilder().cnpj(Long.valueOf(rowData[1]))
          .name(rowData[2])
          .businessArea(rowData[2])
          .build();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return salesMan;
  }
}
