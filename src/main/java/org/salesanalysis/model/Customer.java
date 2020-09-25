package org.salesanalysis.model;

import java.math.BigDecimal;
import lombok.Builder;


public class Customer extends Person {
   private Long cpf;
   private BigDecimal salary;

   @Builder(builderMethodName = "customerBuilder")
   public Customer (String name, Long cpf,BigDecimal salary){
      super(name);
      this.cpf=cpf;
      this.salary=salary;
   }
}
