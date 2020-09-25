package org.salesanalysis.model;

import lombok.Builder;

public class SalesMan extends Person {
   private Long cnpj;
   private String businessArea;

   @Builder(builderMethodName = "salesManBuilder")
   public SalesMan (String name, Long cnpj, String businessArea){
      super(name);
      this.cnpj=cnpj;
      this.businessArea=businessArea;
   }
}
