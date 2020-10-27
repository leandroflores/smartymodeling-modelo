package model.structural.base.product;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Model <b>Artifact</b>.</p>
 * <p>Class responsible for representing the <b>Artifact</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public class Artifact implements Exportable, Modelable {
    protected String   id;
    protected Instance instance;
    protected Element  element;
    protected Point    position;
    protected Point    global;
    protected Point    size;
    
    /**
     * Default constructor method of Class.
     */
    public Artifact() {
        this.element  = null;
        this.position = new Point(0, 0);
        this.global   = new Point(0, 0);
        this.size     = new Point(0, 0);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element Element.
     */
    public Artifact(Element element) {
        this.element  = element;
        this.position = new Point(element.getPosition());
        this.global   = new Point(element.getGlobalPosition());
        this.size     = new Point(element.getSize());
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Artifact(org.w3c.dom.Element element) {
        this.id     = element.getAttribute("id");
        this.global = new Point(0, 0);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param position Reading Coordinates.
     */
    public Artifact(org.w3c.dom.Element element, boolean position) {
        this(element);
        this.setPosition(element);
        this.setSize(element);
    }
    
    /**
     * Method responsible for returning the Artifact Id.
     * @return Artifact Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Artifact Id.
     * @param id Artifact Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }

    /**
     * Method responsible for returning the Artifact Instance.
     * @return Artifact Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }

    /**
     * Method responsible for setting the Artifact Instance.
     * @param instance Artifact Instance.
     */
    public void setInstance(Instance instance) {
        this.instance = instance;
    }
    
    /**
     * Method responsible for returning the Artifact Element.
     * @return Artifact Element.
     */
    public Element getElement() {
        return this.element;
    }

    /**
     * Method responsible for returning if the Artifact is a Package.
     * @return Artifact is a Package.
     */
    public boolean isPackage() {
        return this.element instanceof PackageUML;
    }
    
    /**
     * Method responsible for returning if the Artifact is a Entity.
     * @return Artifact is a Entity.
     */
    public boolean isEntity() {
        return this.element instanceof Entity;
    }
    
    /**
     * Method responsible for defining the Artifact Element.
     * @param element Artifact Element.
     */
    public void setElement(Element element) {
        this.element = element;
    }
    
    /**
     * Method responsible for returning the Artifact Position.
     * @return Artifact Position.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Method responsible for returning the Element X Position.
     * @return Element X Position.
     */
    public Integer getX() {
        return this.position.x;
    }
    
    /**
     * Method responsible for calculating Position Shift X.
     * @param distance Distance.
     */
    public void dx(Integer distance) {
        if (this.position.x + distance < 0)
            this.position.x  = 0;
        else
            this.position.x += distance;
    }
    
    /**
     * Method responsible for returning the Artifact Y Position.
     * @return Artifact Y Position.
     */
    public Integer getY() {
        return this.position.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Y.
     * @param distance Distance.
     */
    public void dy(Integer distance) {
        if (this.position.y + distance < 0)
            this.position.y  = 0;
        else
            this.position.y += distance;
    }
    
    /**
     * Method responsible for defining the Position Point.
     * @param  element W3C Element.
     */
    private void setPosition(org.w3c.dom.Element element) {
        Double x = 0.0d;
        Double y = 0.0d;
        try {
            x = Double.parseDouble(element.getAttribute("x"));
            y = Double.parseDouble(element.getAttribute("y"));
        }catch (NumberFormatException exception) {}
        this.setPosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Artifact Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setPosition(Integer x, Integer y) {
        this.position = new Point(x, y);
    }
    
    /**
     * Method responsible for defining the Artifact Position.
     * @param position Element Position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Method responsible for returning the Artifact Global Position.
     * @return Artifact Global Position.
     */
    public Point getGlobal() {
        return this.global;
    }
    
    /**
     * Method responsible for setting the Artifact Global Position.
     * @param global Artifact Global Position.
     */
    public void setGlobal(Point global) {
        this.global = global;
    }
    
    /**
     * Method responsible for returning the Artifact X Global Position.
     * @return Artifact X Global Position.
     */
    public Integer getGlobalX() {
        return this.global.x;
    }
    
    /**
     * Method responsible for setting the Artifact X Global Position.
     * @param x Artifact X Global Position.
     */
    public void setGlobalX(Integer x) {
        this.global.x = x;
    }
    
    /**
     * Method responsible for returning the Artifact Y Global Position.
     * @return Artifact Y Global Position.
     */
    public Integer getGlobalY() {
        return this.global.y;
    }
    
    /**
     * Method responsible for setting the Artifact Y Global Position.
     * @param y Artifact Y Global Position.
     */
    public void setGlobalY(Integer y) {
        this.global.y = y;
    }

    /**
     * Method responsible for returning the X Center.
     * @return X Center.
     */
    public Integer getXCenter() {
        return this.getX() + (this.getWidth() / 2);
    }
    
    /**
     * Method responsible for returning the Y Center.
     * @return Y Center.
     */
    public Integer getYCenter() {
        return this.getY() + (this.getHeight() / 2);
    }
    
    /**
     * Method responsible for returning the Artifact Size.
     * @return Artifact Size.
     */
    public Point getSize() {
        return this.size;
    }

    /**
     * Method responsible for returning the Artifact Height.
     * @return Artifact Height.
     */
    public Integer getHeight() {
        return this.size.y;
    }
    
    /**
     * Method responsible for updating the Artifact Height.
     * @param height Artifact Height.
     */
    public void setHeight(Integer height) {
        this.size.y = height;
    }
    
    /**
     * Method responsible for returning the Artifact Width.
     * @return Artifact Width.
     */
    public Integer getWidth() {
        return this.size.x;
    }
    
    /**
     * Method responsible for updating the Artifact Width.
     * @param width Artifact Width.
     */
    public void setWidth(Integer width) {
        this.size.x = width;
    }
    
    /**
     * Method responsible for defining the Size Point.
     * @param  element W3C Element.
     */
    private void setSize(org.w3c.dom.Element element) {
        Double height = 0.0d;
        Double width  = 0.0d;
        try {
            height = Double.parseDouble(element.getAttribute("height"));
            width  = Double.parseDouble(element.getAttribute("width"));
        }catch (NumberFormatException exception) {}
        this.setSize(new Point(width.intValue(), height.intValue()));
    }
    
    /**
     * Method responsible for defining the Artifact Position.
     * @param width Artifact Width.
     * @param height Artifact Height.
     */
    public void setSize(Integer width, Integer height) {
        this.size = new Point(height, width);
    }
    
    /**
     * Method responsible for returning the Artifact Size.
     * @param size Artifact Size.
     */
    public void setSize(Point size) {
        this.size = size;
    }
    
    @Override
    public String getTitle() {
        return this.element.getName();
    }
    
    /**
     * Method responsible for returning the Artifact Abstract.
     * @return Artifact Abstract.
     */
    public String getAbstract() {
        return "[" + this.getId() + "] " + this.element.getName();
    }
    
    /**
     * Method responsible for returning the Artifact Summary.
     * @return Artifact Summary.
     */
    public String getSummary() {
        return "Artifact: " + this.element.getName();
    }
    
    /**
     * Method responsible for returning the Artifact Icon.
     * @return Artifact Icon.
     */
    public String getIcon() {
        return this.element.getIcon();
    }
    
    @Override
    public String getStyleLabel() {
        return this.element.getStyleLabel();
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap(this.element.getStyle());
               style.put(mxConstants.STYLE_EDITABLE, "0");
        return style;
    }
    
    @Override
    public String export() {
        String export  = "        "; 
               export += "<artifact"; 
               export += " id=\""        + this.id              + "\"";
               export += " element=\""   + this.element.getId() + "\"";
               export += " x=\""         + this.getX()          + "\"";
               export += " y=\""         + this.getY()          + "\"";
               export += " height=\""    + this.getHeight()     + "\"";
               export += " width=\""     + this.getWidth()      + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Artifact == false)
            return false;
        return Objects.equals(this.id, ((Artifact) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.element.toString();
    }
}