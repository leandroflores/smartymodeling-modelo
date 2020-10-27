package model.structural.diagram.component.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>ComponentUML</b>.</p>
 * <p>Class responsible for representing <b>Component UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-20
 * @see    model.structural.base.Element
 */
public class ComponentUML extends Element {

    /**
     * Default constructor method of Class.
     * @param diagram Component Diagram.
     */
    public ComponentUML(Diagram diagram) {
        super(diagram);
        this.name = "Component";
        this.size = new Point(200, 100);
        this.type = "component";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Component Diagram.
     */
    public ComponentUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.type = "component";
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "component/component.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleComponentUML";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
}