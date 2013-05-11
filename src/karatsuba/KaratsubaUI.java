package karatsuba;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KaratsubaUI extends JFrame implements ActionListener{
	
	/**
	 * @author Winder Castiblanco
	 */
	private static final long serialVersionUID = 1L;
	private Container contenedor;
	private JPanel panelboton;
	private JPanel panelresultado;
	private JPanel panelnumeros;
	private JButton buton1;
	private JTextField caja1;
	private JTextField caja2;
	private JTextField caja3;
	private JLabel label1;
	private JLabel label2;
	
	public KaratsubaUI(){
		contenedor = getContentPane();
		panelboton = new JPanel();
		panelresultado = new JPanel();
		panelnumeros = new JPanel();
		buton1 = new JButton("Calcular");
		caja1 = new JTextField(5);
		caja2 = new JTextField(5);
		caja3 = new JTextField(10);
		label1 = new JLabel("Numero 1");
		label2 = new JLabel("Numero 2");
		panelnumeros.add(label1);
		panelnumeros.add(caja1);
		panelnumeros.add(label2);
		panelnumeros.add(caja2);
		panelboton.add(buton1);
		panelresultado.add(caja3);
		contenedor.add(panelnumeros);
		contenedor.add(panelboton);
		contenedor.add(panelresultado);
		contenedor.setLayout(new GridLayout(3,1));
		setLocation(550,200);
		setTitle("Calculator Karatsuba");
		setSize(300,200);
		setVisible(true);
		
		
		buton1.addActionListener(this);
	}	
	public static void main(String[] args){
		new KaratsubaUI();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mostrar();
	}
	
	public static BigInteger karatsuba(BigInteger x, BigInteger y) {

	        int N = Math.max(x.bitLength(), y.bitLength());
	        if (N <= 10) 
	        	return x.multiply(y);               

	        N = (N / 2) + (N % 2);
	        BigInteger b = x.shiftRight(N);
	        BigInteger a = x.subtract(b.shiftLeft(N));
	        BigInteger d = y.shiftRight(N);

	        BigInteger c = y.subtract(d.shiftLeft(N));
	        BigInteger ac    = karatsuba(a, c);
	        BigInteger bd    = karatsuba(b, d);
	        BigInteger abcd  = karatsuba(a.add(b), c.add(d));

	        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));
	    }

	 public void mostrar(){
		 try {
			 String N1 = caja1.getText();
		        String N2 = caja2.getText();
		        if(Integer.parseInt(N1) <1000 
		        		||Integer.parseInt(N2)<1000
		        		||Integer.parseInt(N1)> Math.pow(2, 31)
		        		||Integer.parseInt(N2)>Math.pow(2, 31)
		        		||Integer.parseInt(N2)<0
		        		||Integer.parseInt(N1)<0){
		        	JOptionPane.showMessageDialog(null, "Debes ingresar numeros superiores 1000 y menores que 2.14E9" +
		        			" y valores mayores que cero");
		        }else{
		        BigInteger a = new BigInteger(N1);
		        BigInteger b = new BigInteger(N2);

		        BigInteger c = karatsuba(a, b);
		        caja3.setText(String.valueOf(c));
		        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debes ingresar valores numericos");
		}
	        

		
	}

}
