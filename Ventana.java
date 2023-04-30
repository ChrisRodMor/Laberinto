import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	int contador = 0;
	int segundosTotales;
	int horas,minutos,segundos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.setVisible(true);
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
		
		setLayout(new BorderLayout());
		setBounds(500, 100, 1000, 703);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Juego = new JPanel();
		Juego.setBackground(new Color(113,105,59));
		getContentPane().add(Juego, BorderLayout.CENTER);
		
        FlowLayout flowlayout = new FlowLayout();
        flowlayout.setHgap(110);

		JPanel panelAbajo = new JPanel(flowlayout);
		panelAbajo.setBackground(Color.black);
        panelAbajo.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		
		JLabel trash = new JLabel("             ",JLabel.LEFT);
		JLabel trash2 = new JLabel("                          ",JLabel.LEFT);

        JLabel tiempo = new JLabel("T i e m p o: [00:00:00]",JLabel.LEFT);
        tiempo.setForeground(new Color(234,215,42));
        tiempo.setFont(new Font("Times New Roman", Font.ITALIC, 25));

		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				segundosTotales = contador++;
                horas = segundosTotales / 3600;
                minutos = (segundosTotales % 3600) / 60;
                segundos = segundosTotales % 60;
                tiempo.setText("T i e m p o: [" + String.format("%02d:%02d:%02d", horas, minutos, segundos) + "]");
			}
		});
        timer.start();

        
		JButton Reiniciar = new JButton("   R e i n i c i a r   ");
        Reiniciar.setBackground(Color.black);
        Reiniciar.setFocusPainted(false);
        Reiniciar.setForeground(new Color(234,215,42));
        Reiniciar.setBorderPainted(true);
        Reiniciar.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		Reiniciar.setBorder(BorderFactory.createLineBorder(new Color(234,215,42), 1));
        UIManager.put("Button.select", new Color(234,215,42));
        
        Reiniciar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

				
				contador = 0;

				segundosTotales = contador;
				horas = segundosTotales / 3600;
				minutos = (segundosTotales % 3600) / 60;
				segundos = segundosTotales % 60;
				tiempo.setText("T i e m p o: [" + String.format("%02d:%02d:%02d", horas, minutos, segundos) + "]");

				try {

					// Crear objeto Clip para reproducir el audio
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("FireBoyDeath.wav").getAbsoluteFile()));
		
					//bajar volumen audio
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float volume = 0.04f; // volumen
					float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);
					
					// Reproducir el audio
					clip.start();
		
					// Detener la reproducción
					//clip.stop();
					//clip.close();


		
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException l) {
					l.printStackTrace();
				}

                player_x =  0;
                player_y = 0;
                Juego.repaint();
                Juego.revalidate();
                Juego.requestFocus(true);
            }
            
            
            
        });
        
		panelAbajo.add(trash);
		panelAbajo.add(trash2);
		panelAbajo.add(Reiniciar);
        panelAbajo.add(tiempo);

		try {
            // Cargar archivo de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Adventure.wav").getAbsoluteFile());

            // Crear objeto Clip para reproducir el audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

			//bajar volumen audio
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float volume = 0.4f; // volumen
			float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
			gainControl.setValue(dB);
			
            // Reproducir el audio
            //clip.start();

			//loopearlo
			clip.loop(Clip.LOOP_CONTINUOUSLY);

			// Detener la reproducción
			//clip.stop();
			//clip.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
		
		
		Juego.add(new MyGraphics());
		
		Juego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
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
	        g.setColor(new Color(44,46,12));
	        g.fillRect(0, 0, 800, 600);
	        //g.setColor(Color.BLACK);
	        //g.drawRect(40, 40, 560, 60);

	        
	        //Rectángulo redondeado	
	        //g.setColor(Color.red);
	        //g.drawRoundRect(420, 100, 350, 60, 50, 50);
	        
	        
	        Rect r = new Rect(player_x,player_y,20,20,Color.red);
	        
	        Rect p = new Rect(700,60,20,20,Color.cyan);
	        g.setColor(p.c);
	        g.fillRect(p.x, p.y, p.w, p.h);

            if(r.colision(p)){
				try {
					// Crear objeto Clip para reproducir el audio
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("FinishAdventure.wav").getAbsoluteFile()));
		
					//bajar volumen audio
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float volume = 0.4f; // volumen
					float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);
					
					// Reproducir el audio
					//clip.start();
		
					//loopearlo
					clip.start();
		
					// Detener la reproducción
					//clip.stop();
					//clip.close();
		
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Has finalizado el laberinto en [" + String.format("%02d:%02d:%02d", horas, minutos, segundos) + "]", "Felicidades!", 1, null);
			}

	        //player
	        g.fillRect(player_x, player_y, 20, 20);
	        g.setColor(r.c);
	        g.fillRect(r.x, r.y, r.w, r.h);
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
