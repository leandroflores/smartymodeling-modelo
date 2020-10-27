package model.structural.diagram.usecase.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>ActorUML</b>.</p>
 * <p>Class responsible for representing <b>Actor UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-22
 * @see    model.structural.base.Element
 */
public class ActorUML extends Element {
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public ActorUML(Diagram diagram) {
        super(diagram);
        this.name = "Actor";
        this.size = new Point(75, 50);
        this.type = "actor";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Use Case Diagram.
     */
    public ActorUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.type = "actor";
    }

    @Override
    public String getIcon() {
        return super.getFolder() + "usecase/actor.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleActorUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/usecase/actor.jpg");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
        return style;
    }
}