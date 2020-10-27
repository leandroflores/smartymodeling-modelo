package model.structural.diagram.sequence.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.classes.base.ClassUML;

/**
 * <p>Class of Model <b>InstanceUML</b>.</p>
 * <p>Class responsible for representing the <b>Instance UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-24
 * @see    model.structural.base.Element
 */
public class InstanceUML extends Element {
    private ClassUML classUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Sequence Diagram.
     */
    public InstanceUML(Diagram diagram) {
        super(diagram);
        this.name     = "Instance";
        this.size     = new Point(200, 380);
        this.classUML = null;
        this.type     = "instance";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Sequence Diagram.
     */
    public InstanceUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.classUML = null;
        this.type     = "instance";
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        this.setMinWidth();
    }

    /**
     * Method responsible for updating the Name.
     */
    public void updateName() {
        if (this.name.trim().equals(""))
            this.setName((this.classUML == null) ? "instance" : this.classUML.getName().toLowerCase().trim());
    }
    
    /**
     * Method responsible for returning the Signature Size.
     * @return Signature Size.
     */
    public Integer getSignatureSize() {
        return 10 * this.getSignature().length();
    }
    
    /**
     * Method responsible for returning the Min Width.
     * @return Min Width.
     */
    public Integer getMinWidth() {
        return Math.max(200, this.getSignatureSize());
    }
    
    /**
     * Method responsible for setting the Min Width.
     */
    public void setMinWidth() {
        Integer width = this.getMinWidth();
        this.setWidth(width  >  this.getWidth() ?  width : this.getWidth());
    }
    
    /**
     * Method responsible for returning the Class UML.
     * @return Class UML.
     */
    public ClassUML getClassUML() {
        return this.classUML;
    }

    /**
     * Method responsible for setting the Class UML.
     * @param classUML Class UML.
     */
    public void setClassUML(ClassUML classUML) {
        if (classUML != null)
            this.classUML =  classUML;
        this.updateName();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.name + " : " + this.getClassName();
    }
    
    /**
     * Method responsible for returning the Class Name.
     * @return Class Name.
     */
    public String getClassName() {
        if (this.classUML != null)
            return this.classUML.getName();
        return "";
    }

    @Override
    public String getIcon() {
        return super.getFolder() + "sequence/instance.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleInstanceUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    /**
     * Method responsible for returning the Class Id.
     * @return Class Id.
     */
    private String getClassId() {
        if (this.classUML != null)
            return this.classUML.getId();
        return "";
    }
    
    @Override
    public String export() {
        String export  = "    <"         + this.type;
               export += " id=\""        + this.id           + "\"";
               export += " name=\""      + this.name         + "\"";
               export += " class=\""     + this.getClassId() + "\"";
               export += " mandatory=\"" + this.mandatory    + "\"";
               export += " x=\""         + this.getX()       + "\"";
               export += " y=\""         + this.getY()       + "\"";
               export += " height=\""    + this.getHeight()  + "\"";
               export += " width=\""     + this.getWidth()   + "\"";
               export += "/>\n";
        return export;
    }
}