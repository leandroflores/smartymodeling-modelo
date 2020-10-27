package model.structural.base.interfaces;

import java.util.Map;

/**
 * <p>Inteface of Model <b>Modelable</b>.</p>
 * <p>Inteface responsible for defining the <b>Element Modeling</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 */
public interface Modelable {
    
    /**
     * Method responsible for returning the Title.
     * @return Title.
     */
    public abstract String getTitle();
    
    /**
     * Method responsible for returning the Style Label.
     * @return Style Label.
     */
    public abstract String getStyleLabel();
    
    /**
     * Method responsible for returning the Style.
     * @return Style.
     */
    public abstract Map getStyle();
}