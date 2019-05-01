import primos.*;

public class EjemploPrimos {
    public static void main (String []args) {
	Integer n=Integer.parseInt(args[0]);
	
	CPrimos c=new CPrimos();

	System.out.println(c.strPrimo(n));
	c.infoArrayPrimos();
    }

}
