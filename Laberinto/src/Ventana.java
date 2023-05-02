
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
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
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
	
	int player_x;
	int player_y;
	int contador = 0;
	int var = 20;
	int segundosTotales;
	int horas,minutos,segundos;
	Random rand = new Random();
    int nivel = rand.nextInt(2) + 1;
	Clip clip;

	Rect player;
	Rect meta;

	private int[][] laberinto = {
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,0,1,0,1,1,0,1,0,0,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,0,0,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,1,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,0,1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1},
		{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		
	};

	private int[][] laberinto2 = {
		{1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,1},
		{1,0,1,1,1,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1},
		{1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,1,1,1,1,1,1,1},
		{1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1},
		{1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,0,1},
		{1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1},
		{1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,1},
		{1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1},
		{1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,0,1,0,1,1,1,0,1,0,1},
		{1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,1,0,1},
		{1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,1,1,1,0,1,0,1},
		{1,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1},
		{1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1},
		{1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1},
		{1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,0,0,1,0,1,0,0,1,0,0,0,1,0,1,0,1},
		{1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,0,1},
		{1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1},
		{1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
		{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1},
		{1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
		{1,1,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
		{1,0,1,0,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		
	};

	


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

		if(nivel == 1){
			player_x = 100;
			player_y = 440;
		}
		else if(nivel == 2) {
			player_x = 60;
			player_y = 0;
		}
		
		setTitle("Labyrinth");
		setLayout(new BorderLayout());
		setBounds(500, 100, 1000, 703);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
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

					clip.open(AudioSystem.getAudioInputStream(new File("Laberinto\\src\\FireBoyDeath.wav").getAbsoluteFile()));
		
					//bajar volumen audio
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float volume = 0.04f; // volumen
					float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);
					
					// Reproducir el audio
					clip.start();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException l) {
					l.printStackTrace();
				}

                if(nivel == 1){
					player_x = 100;
					player_y = 440;
				}
				else if(nivel == 2) {
					player_x = 60;
					player_y = 0;
				}

                Juego.repaint();
                Juego.revalidate();
                Juego.requestFocus(true);
            }
            
            
            
        });
        
		panelAbajo.add(trash);
		panelAbajo.add(trash2);
		panelAbajo.add(Reiniciar);
        panelAbajo.add(tiempo);

		Sound();

		Juego.add(new MyGraphics());
		
		Juego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e){}

			@Override
			public void keyPressed(KeyEvent e) {

				
				if(nivel == 1){
					player = new Rect(player_x,player_y,20,20,Color.red);
					meta = new Rect(660,60,20,20,Color.cyan);
				}else if(nivel == 2){
					player = new Rect(player_x,player_y,20,20,Color.red);
					meta = new Rect(760,560,20,20,Color.cyan);
				}

				if(player.colision(meta)){
					try {
						
						timer.stop();
						clip.stop();
						// Crear objeto Clip para reproducir el audio
						Clip clip = AudioSystem.getClip();

						if(nivel == 1){
							clip.open(AudioSystem.getAudioInputStream(new File("Laberinto\\src\\FinishAdventure.wav").getAbsoluteFile()));
						}else if(nivel ==2){
							clip.open(AudioSystem.getAudioInputStream(new File("Laberinto\\src\\FinishSpeed.wav").getAbsoluteFile()));
						}
			
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
			
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException o) {
						o.printStackTrace();
					}
	
					Object[] options = {"Siguiente Nivel", "Repetir nivel"};
					int choice = JOptionPane.showOptionDialog(null, "Has finalizado el laberinto en [" + String.format("%02d:%02d:%02d", horas, minutos, segundos) + "]!", "Felicidades!", JOptionPane.YES_NO_OPTION, 1, null, options, options[0]);
					
					switch (choice) {

						case JOptionPane.YES_OPTION:
						
							if(nivel ==1){
								nivel = 2;
							}else if(nivel == 2){
								nivel = 1;
								
							}
							contador = 0;
							timer.start();
							if(nivel == 1){
								player_x = 100;
								player_y = 440;
							}
							else if(nivel == 2) {
								player_x = 60;
								player_y = 0;
							}

							Sound();
						break;

						case JOptionPane.NO_OPTION:
							
							contador = 0;
							timer.start();

							if(nivel == 1){
								player_x = 100;
								player_y = 440;
							}
							else if(nivel == 2) {
								player_x = 60;
								player_y = 0;
							}

							Sound();
						break;

						case JOptionPane.CLOSED_OPTION:
							System.exit(0);
						break;
					}
				
				}


				if(nivel == 1){
					//Arriba
					if(e.getKeyCode() == 87 && player_y > 0 && laberinto[player_y/var-1][player_x/var] !=1) {
						player_y -= 20;
					}
					
					//Izquierda
					if(e.getKeyCode() == 65 && player_x > 0 && laberinto[player_y/var][player_x/var-1] !=1) {
						player_x -= 20;
					}
					
					//Abajo
					if(e.getKeyCode() == 83 && player_y < 600-20 && laberinto[player_y/var+1][player_x/var] !=1) {
						player_y += 20;
					}
					
					//Derecha
					if(e.getKeyCode() == 68 && player_x < 800-20 && laberinto[player_y/var][player_x/var+1] !=1) {
						player_x += 20;
					}
				}
				
				else if(nivel == 2){
					//Arriba
					if(e.getKeyCode() == 87 && player_y > 0 && laberinto2[player_y/var-1][player_x/var] !=1) {
						player_y -= 20;
					}
					
					//Izquierda
					if(e.getKeyCode() == 65 && player_x > 0 && laberinto2[player_y/var][player_x/var-1] !=1) {
						player_x -= 20;
					}
					
					//Abajo
					if(e.getKeyCode() == 83 && player_y < 600-20 && laberinto2[player_y/var+1][player_x/var] !=1) {
						player_y += 20;
					}
					
					//Derecha
					if(e.getKeyCode() == 68 && player_x < 800-20 && laberinto2[player_y/var][player_x/var+1] !=1) {
						player_x += 20;
					}
				}

                Juego.repaint();
				Juego.revalidate();
			}

			@Override
			public void keyReleased(KeyEvent e){}
			
		});
		
		
		Juego.setFocusable(true);
		Juego.requestFocus();
        Juego.repaint();
        Juego.revalidate();
		
	}

	public void Sound(){

		try {

            clip = AudioSystem.getClip();

			
			if(nivel == 1){
				clip.open(AudioSystem.getAudioInputStream(new File("Laberinto\\src\\Adventure.wav").getAbsoluteFile()));
				
			}else if(nivel ==2){
				clip.open(AudioSystem.getAudioInputStream(new File("Laberinto\\src\\Speed.wav").getAbsoluteFile()));
			}
			

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



	}
	
	public class MyGraphics extends JComponent{
		private static final long serialVersionUID = 1L;
		
		MyGraphics(){
			setPreferredSize(new Dimension(800,600));
		}
		
		public void paint(Graphics g){
			
			super.paintComponent(g);
			
			//fondo
			g.setColor(new Color(44,46,12));
			g.fillRect(0, 0, 800, 600);
			
			if(nivel == 1){
				
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 40; j++) {
						
						if(laberinto[i][j]==0){
							
						}
						
						if(laberinto[i][j]==1){
							//BORDE/BARRERA
	
							g.setColor(new Color(38,40,6));
							g.fillRect(j*var,i*var,20,20);
	
							
						}
						
					}
				}

				//Metahitbox
				Rect metahitbox = new Rect(660,60,20,20,Color.cyan);
				g.setColor(metahitbox.c);
				g.fillRect(metahitbox.x, metahitbox.y, metahitbox.w, metahitbox.h);
				
				//playerhitbox
				Rect playerhitbox = new Rect(player_x,player_y,20,20,Color.red);
				g.fillRect(player_x, player_y, 20, 20);
				g.setColor(playerhitbox.c);
				g.fillRect(playerhitbox.x, playerhitbox.y, playerhitbox.w, playerhitbox.h);

			}else if(nivel == 2){
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 40; j++) {
						
						if(laberinto2[i][j]==0){
							
						}
						
						if(laberinto2[i][j]==1){
							//BORDE/BARRERA
	
							g.setColor(new Color(38,40,6));
							g.fillRect(j*var,i*var,20,20);
	
							
						}
						
					}
				}

				//Metahitbox
				Rect metahitbox = new Rect(760,560,20,20,Color.cyan);
				g.setColor(metahitbox.c);
				g.fillRect(metahitbox.x, metahitbox.y, metahitbox.w, metahitbox.h);
				
				//playerhitbox
				Rect playerhitbox = new Rect(player_x,player_y,20,20,Color.red);
				g.fillRect(player_x, player_y, 20, 20);
				g.setColor(playerhitbox.c);
				g.fillRect(playerhitbox.x, playerhitbox.y, playerhitbox.w, playerhitbox.h);


			}
			
			
			

	        
	    }
		
	}
	
	public class Rect{
		
		int x,y,w,h;
		Color c = Color.black;
		
		Rect(int x,int y, int w, int h, Color c){
			
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.c = c;

		}
		
		public boolean colision(Rect target) {
			if(this.x < target.x + target.w && this.x + this.w > target.x
			&& this.y < target.y + target.h && this.y + this.h > target.y) {

                return true;
            }
			return false;
		}


	}
	
}
