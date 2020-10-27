package funct;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <p>Class of Functions <b>FunctView</b>.</p>
 * <p>Class responsible for operations involving <b>Views</b> and <b>Images</b>.</p>
 * @author Leandro
 * @since  14/01/2019
 * @see    javax.swing.ImageIcon
 */
public class FunctView {
    private static final String FOLDER = "/images/";
    
    /**
     * Method responsible for returning an ImageIcon for URL.
     * @param   imageURL Image URL.
     * @return  New ImageIcon.
     */
    public ImageIcon createImage(String imageURL) {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(FOLDER + imageURL)));
    }
    
    /**
     * Method responsible for returns an Image JLabel.
     * @param  imageURL Image URL.
     * @return New Image JLabel.
     */
    public JLabel getLabelImage(String imageURL) {
        return new JLabel((Icon) this.createImage(imageURL));
    }
    
    /**
     * Method responsible for returns an Icon Image.
     * @param imageURL Image URL.
     * @return Icon Image.
     */
    public Image getIcone(String imageURL) {
        try {
            return ImageIO.read(this.getClass().getResource(FOLDER + "components/" + imageURL));
        }catch (IOException exception) {
            return null;
        }
    }
}