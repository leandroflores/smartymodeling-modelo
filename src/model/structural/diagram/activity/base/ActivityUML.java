package model.structural.diagram.activity.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>ActivityUML</b>.</p>
 * <p>Class responsible for representing the <b>Activity UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-17
 * @see    model.structural.base.Element
 */
public class ActivityUML extends Element {
    
    /**
     * Default constructor method of Class.
     * @param diagram Activity Diagram.
     */
    public ActivityUML(Diagram diagram) {
        super(diagram);
        this.name = "Activity";
        this.size = new Point(200, 80);
        this.type = "activity";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Diagram.
     */
    public ActivityUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.type = "activity";
    }

    @Override
    public String getIcon() {
        return super.getFolder() + "activity/activity.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleActivityUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_MIDDLE);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
        return style;
    }
}