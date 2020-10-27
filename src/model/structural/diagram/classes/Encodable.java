package model.structural.diagram.classes;

/**
 * <p>Inteface of Model <b>Encodable</b>.</p>
 * <p>Inteface responsible for defining the <b>Code Exporting</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  18/01/2020
 */
public interface Encodable {
    
    /**
     * Method responsible for returning the Abstract Flag.
     * @return Abstract Flag.
     */
    public abstract Boolean isAbstract();
    
    /**
     * Method responsible for returning the Final Flag.
     * @return Final Flag.
     */
    public abstract Boolean isFinal();
    
    /**
     * Method responsible for returning the Static Flag.
     * @return Static Flag.
     */
    public abstract Boolean isStatic();
    
    /**
     * Method responsible for returning the Visibility Constraint.
     * @return Visibility Constraint.
     */
    public abstract String getVisibility();
    
    /**
     * Method responsible for exporting the Code.
     * @return Code.
     */
    public abstract String exportCode();
}