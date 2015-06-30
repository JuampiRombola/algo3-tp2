package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import algoCraft.Musica;

public class MouseListenerSonidoClick extends MouseAdapter{
	@Override
	public void mousePressed(MouseEvent e) {
		try {
			(new Musica("recursos/Musica/click.wav")).play();
		} catch (IOException e1) {}	
	}
}