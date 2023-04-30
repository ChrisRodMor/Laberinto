import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.*;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Dimension;

public class Ventana extends JFrame {
	
	int player_x =  40;
	int player_y = 40;
    Color Matrix = new Color(0,255,43);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana(){
		
		setBounds(500, 100, 1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Juego = new JPanel();
		Juego.setBackground(new Color(100,186,1));
		getContentPane().add(Juego, BorderLayout.CENTER);
		

        FlowLayout flowlayout = new FlowLayout();
        flowlayout.setHgap(10);

		JPanel panelAbajo = new JPanel(flowlayout);
		panelAbajo.setBackground(Color.black);
        panelAbajo.setBorder(BorderFactory.createLineBorder(Matrix, 1));
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		
        JLabel tiempo = new JLabel("T i e m p o: [00:00:00]",JLabel.LEFT);
        tiempo.setForeground(Matrix);
        tiempo.setFont(new Font("Itim", Font.BOLD, 15));
        
        ActionListener listener = new ActionListener() {
            
            int contador = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                contador++;

                int segundosTotales = contador;
                int horas = segundosTotales / 3600;
                int minutos = (segundosTotales % 3600) / 60;
                int segundos = segundosTotales % 60;
                tiempo.setText("T i e m p o: [" + String.format("%02d:%02d:%02d", horas, minutos, segundos) + "]");
            }
        };
        
        Timer timer = new Timer(1000, listener);
        timer.start();

        
		JButton Reiniciar = new JButton("R e i n i c i a r");
        Reiniciar.setBackground(Color.black);
        Reiniciar.setFocusPainted(false);
        Reiniciar.setForeground(Matrix);
        Reiniciar.setBorderPainted(false);
        Reiniciar.setFont(new Font("Itim", Font.BOLD, 15));
        UIManager.put("Button.select", Matrix);
        
        Reiniciar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                player_x =  0;
                player_y = 0;
                Juego.repaint();
                Juego.revalidate();
                Juego.requestFocus(true);
            }
            
            
            
        });
        
		panelAbajo.add(Reiniciar);
        panelAbajo.add(tiempo);
		
		Juego.add(new MyGraphics());
		
		Juego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				int tecla = e.getKeyCode();
				//System.out.println(tecla);
				

				if(tecla == 87 && player_y > 0) {
					player_y -= 10;
				}
				
				if(tecla == 65 && player_x > 0) {
					player_x -= 10;
				}
				
				if(tecla == 83 && player_y < 600-20) {
					player_y += 10;
				}
				
				if(tecla == 68 && player_x < 800-20) {
					player_x += 10;
				}

                Juego.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
			
		});
		
		
		Juego.setFocusable(true);
		Juego.requestFocus();
        Juego.repaint();
        Juego.revalidate();
		this.setVisible(true);
	}
	
	public class MyGraphics extends JComponent{
		private static final long serialVersionUID = 1L;
		
		MyGraphics(){
			setPreferredSize(new Dimension(800,600));
		}
		
		public void paint(Graphics g){
			
	        super.paintComponent(g);

	        //Línea
	        //g.setColor(Color.BLUE);
	        //g.drawLine(30, 70, 770, 70);

	        //fondo
	        g.setColor(Color.black);
	        g.fillRect(0, 0, 800, 600);
	        //g.setColor(Color.BLACK);
	        //g.drawRect(40, 40, 560, 60);

	        //player
	        g.fillRect(player_x, player_y, 20, 20);
	        
	        //Rectángulo redondeado	
	        //g.setColor(Color.red);
	        //g.drawRoundRect(420, 100, 350, 60, 50, 50);
	        
	        Rect r = new Rect(player_x,player_y,20,20,Matrix);
	        g.setColor(r.c);
	        g.fillRect(r.x, r.y, r.w, r.h);
	        
	        
	        Rect p = new Rect(300,60,40,200,Matrix);
	        g.setColor(p.c);
	        g.fillRect(p.x, p.y, p.w, p.h);
	        
	        System.out.println(r.colision(p));
            
	    }
		
	}
	
	public class Rect{
		
		int x,y,w,h = 0;
		
		Color c = Color.black;
		
		Rect(int x,int y, int w, int h, Color c){
			
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.c = c;
		}
		
		public boolean colision(Rect target) {
			if (this.x < target.x + target.w && this.x + this.w > target.x &&               
                this.y < target.y + target.h && this.y + this.h > target.y) {
				
				return true;
                
			}
			return false;
		}
	}
	
}
