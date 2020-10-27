package model.structural.diagram.sequence.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.usecase.base.ActorUML;

/**
 * <p>Class of Model <b>LifelineUML</b>.</p>
 * <p>Class responsible for representing the <b>Lifeline UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-24
 * @see    model.structural.base.Element
 */
public class LifelineUML extends Element {
    private ActorUML actor;
    
    /**
     * Default constructor method of Class.
     * @param diagram Sequence Diagram.
     */
    public LifelineUML(Diagram diagram) {
        super(diagram);
        this.name  = "Lifeline";
        this.size  = new Point(200, 350);
        this.actor = null;
        this.type  = "lifeline";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Sequence Diagram.
     */
    public LifelineUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.type = "lifeline";
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
            this.setName((this.actor == null) ? "lifeline" : this.actor.getName().toLowerCase().trim());
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
     * Method responsible for returning the Actor UML.
     * @return Actor UML.
     */
    public ActorUML getActor() {
        return this.actor;
    }

    /**
     * Method responsible for setting the Actor UML.
     * @param actor Actor UML.
     */
    public void setActor(ActorUML actor) {
        if (actor != null)
            this.actor =  actor;
        this.updateName();
    }

    /**
     * Method responsible for returning the Name Size.
     * @return Name Size.
     */
    public Integer getNameSize() {
        return 10 * this.name.length();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.name + " : " + this.getActorName();
    }
    
    /**
     * Method responsible for returning the Actor Name.
     * @return Actor Name.
     */
    public String getActorName() {
        if (this.actor != null)
            return this.actor.getName();
        return "Actor";
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "sequence/lifeline.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleLifelineUML";
    }
    
    /**
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        return style;
    }
    
    /**
     * Method responsible for returning Line Style.
     * @return Line Style.
     */
    public Map getLineStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    /**
     * Method responsible for returning the End Point Style.
     * @return End Point Style.
     */
    public Map getEndPointStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
        return style;
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
     * Method responsible for returning the Actor Id.
     * @return Actor Id.
     */
    private String getActorId() {
        if (this.actor != null)
            return this.actor.getId();
        return "";
    }
    
    @Override
    public String export() {
        String export  = "    <"         + this.type;
               export += " id=\""        + this.id           + "\"";
               export += " name=\""      + this.name         + "\"";
               export += " actor=\""     + this.getActorId() + "\"";
               export += " mandatory=\"" + this.mandatory    + "\"";
               export += " x=\""         + this.getX()       + "\"";
               export += " y=\""         + this.getY()       + "\"";
               export += " height=\""    + this.getHeight()  + "\"";
               export += " width=\""     + this.getWidth()   + "\"";
               export += "/>\n";
        return export;
    }
}