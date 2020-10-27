package model.structural.base.variability;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Mutex</b>.</p>
 * <p>Class responsible for representing <b>Mutex</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class Mutex extends Association {
    
    /**
     * Default constructor method of Classe.
     * @param source Source Element.
     * @param target Target Element.
     */
    public Mutex(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.type   = "mutex";
    }

    @Override
    public String getTitle() {
        return "<<mutex>>";
    }

    @Override
    public String getStyleLabel() {
        return "styleMutex";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "1");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
        return style;
    }
}