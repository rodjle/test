package org.salesanalysis.load;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import org.salesanalysis.model.Person;
import org.salesanalysis.model.Sales;

public class Load {
    public static final String SEP="ç";
    private List<Person> Custumers;
    private List<Person> SalesMan;
    private List<Sales> sales;
    public static final int REG_TYPE_CUSTOMER=1;
    public static final int REG_TYPE_SALESMAN=2;
    public static final int REG_TYPE_SALE=3;
    //public static final String PATH_OUT="c:\\out";
    //private static final String PATH_IN="C:\\in";
    public static final String PATH_OUT="/%HOMEPATH%/data/out";
    private static final String PATH_IN="/%HOMEPATH%/data/in";

    public static final String FILE_NAME_OUT="salessnalysis.done.dat";
    private static IDirRead readDirFiles;
    private static final long TIME=5000l;



    public static void main (String args[]){


        readDirFiles=new ReadDirFiles();
        Timer timer=new Timer();
        timer.schedule(readDirFiles.readFilesDir(PATH_IN),0,TIME);

    }

}
