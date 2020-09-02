/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Component;
import java.awt.Image;
import java.net.URL;

/**
 *
 * @author valar
 */
public class ConcreteImaging extends RemoteImageProcessor{

    private Image imaging;
    private Component component;
    public ConcreteImaging(URL url) {
        super(url);
    }

    @Override
    public Image getImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
}
