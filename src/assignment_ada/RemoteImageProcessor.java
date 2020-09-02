/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.net.URL;
/**
 *
 * @author valar
 */
public abstract class RemoteImageProcessor {
    
    private URL url;
    
    public RemoteImageProcessor(URL url){
        this.url = url;
    }
    public URL getURL(){
        return url;
    }
    public abstract Image getImage();
}
