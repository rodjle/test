package org.salesanalysis.parse;

public interface IParse<T> {
    T parse(String row);
}
