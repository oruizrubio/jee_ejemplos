package primos;
import java.util.Vector;


class NoEsPrimo extends Exception {
    NoEsPrimo(int a, int b) {
	System.out.println (a+" es divisible por "+b)
	;
    }

}


public class CPrimos implements IPrimos {
    private Vector v;
    private int max;

    public CPrimos() {
	v=new Vector();
	v.add(new Integer(2));
	max=2;
    }

    
    private void calcPrimo(int n) {
	try {
	    for (int i=0;i<v.size();i++) {
		int divisor=(Integer)v.elementAt(i);
		if (n%divisor==0)
		    throw new NoEsPrimo(n, divisor);
	    }
	    v.add(new Integer(n));

	}
	catch (NoEsPrimo e){
	    System.out.println ("No es primo");
	}
    }


    public boolean esPrimo(int n) {
	if (n>max) {
	    for (int i=max+1; i<=n; i++)
		calcPrimo(i);
	    max=n;
	}
	Integer i=new Integer(n);
	return v.contains(i);
    }


    public String strPrimo(int n) {
	if (esPrimo(n))
	    return n + " es primo";
	else
	    return n + " no es primo";
    }


    public void infoArrayPrimos() {
	System.out.println ("max: "+max);
	for (int i=0; i<v.size(); i++)
	    System.out.println (v.elementAt(i));
    }
}

