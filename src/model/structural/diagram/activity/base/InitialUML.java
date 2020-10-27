package model.structural.diagram.activity.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>InitialUML</b>.</p>
 * <p>Class responsible for representing the <b>Initial UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-17
 * @see    model.structural.base.Element
 */
public class InitialUML extends Element {
    
    /**
     * Default constructor method of Class.
     * @param diagram Activity Diagram.
     */
    public InitialUML(Diagram diagram) {
        super(diagram);
        this.name = "";
        this.size = new Point(30, 30);
        this.type = "initial";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Activity Diagram.
     */
    public InitialUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.type = "initial";
    }

    @Override
    public boolean allowStereotype() {
        return false;
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "activity/initial.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleInitialUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        return style;
    }
}