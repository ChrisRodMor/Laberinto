import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Dimension;

public class Ventana extends JFrame {
	
	int player_x =  200;
	int player_y = 180;

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
		
		setBounds(750, 140, 650, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Juego = new JPanel();
		Juego.setBackground(Color.cyan);
		getContentPane().add(Juego, BorderLayout.CENTER);
		
		JPanel panelAbajo = new JPanel();
		panelAbajo.setBackground(new Color(0, 255, 0));
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		
		JButton Reiniciar = new JButton("Reiniciar");
        Reiniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                player_x =  0;
                player_y = 0;
                Juego.repaint();
                Juego.requestFocus(true);
            }

            
            
        });

		panelAbajo.add(Reiniciar);
		
		Juego.add(new MyGraphics());
		
		Juego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				int tecla = e.getKeyCode();
				System.out.println(tecla);
				

				if(tecla == 87 && player_y > 0) {
					player_y -= 5;
				}
				
				if(tecla == 65 && player_x > 0) {
					player_x -= 5;
				}
				
				if(tecla == 83 && player_y < 360-50) {
					player_y += 5;
				}
				
				if(tecla == 68 && player_x < 560-50) {
					player_x += 5;
				}
				
				Juego.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
			
		});
		
		
		Juego.setFocusable(true);
		Juego.requestFocus();
		this.setVisible(true);
	}
	
	public class MyGraphics extends JComponent{
		private static final long serialVersionUID = 1L;
		
		MyGraphics(){
			setPreferredSize(new Dimension(550,400));
		}
		
		public void paint(Graphics g){
			
	        super.paintComponent(g);

	        //Línea
	        //g.setColor(Color.BLUE);
	        //g.drawLine(30, 70, 770, 70);

	        //fondo
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, 550, 500);
	        //g.setColor(Color.BLACK);
	        //g.drawRect(40, 40, 560, 60);

	        //player
	        g.setColor(Color.black);
	        g.fillRect(player_x, player_y, 50, 50);
	        
	        //Rectángulo redondeado	
	        //g.setColor(Color.red);
	        //g.drawRoundRect(420, 100, 350, 60, 50, 50);
	        
	        Rect r = new Rect(player_x,player_y,20,20,new Color(0,0,0));
	        g.setColor(r.c);
	        g.fillRect(r.x, r.y, r.w, r.h);
	        
	        
	        Rect p = new Rect(300,60,40,200,Color.decode("#FF5733"));
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
			if (this.x < target.x + target.w && 
				this.x + this.w > target.x   &&
					
				this.y < target.y + target.h && this.y + this.h > target.y) {
				
				return true;
			}
			return false;
		}
	}
	
	
}
