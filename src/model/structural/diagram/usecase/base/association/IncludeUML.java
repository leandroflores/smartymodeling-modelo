package model.structural.diagram.usecase.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.usecase.base.UseCaseUML;

/**
 * <p>Class of Model <b>IncludeUML</b>.</p>
 * <p>Class responsible for representing <b>Include UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 */
public class IncludeUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Use Case UML.
     * @param target Use Case UML.
     */
    public IncludeUML(UseCaseUML source, UseCaseUML target) {
        super();
        this.source = source;
        this.target = target;
        this.type   = "include";
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public IncludeUML(org.w3c.dom.Element element) {
        super(element);
        this.type = "include";
    }
    
    @Override
    public UseCaseUML getSource() {
        return (UseCaseUML) this.source;
    }

    /**
     * Method responsible for setting Source.
     * @param source Use Case UML.
     */
    public void setSource(UseCaseUML source) {
        this.source = source;
    }

    @Override
    public UseCaseUML getTarget() {
        return (UseCaseUML) this.target;
    }

    /**
     * Method responsible for setting Target.
     * @param target Use Case UML.
     */
    public void setTarget(UseCaseUML target) {
        this.target = target;
    }
    
    @Override
    public String getTitle() {
        return "<<include>>";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleIncludeUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "1");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
}