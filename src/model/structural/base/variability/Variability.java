package model.structural.base.variability;

import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Variability</b>.</p>
 * <p>Class responsible for representing <b>Variability</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 */
public class Variability implements Exportable {
    private String  id;
    private String  name;
    private Element variationPoint;
    private String  constraint;
    private String  bindingTime;
    private Integer minimum;
    private Integer maximum;
    private List<Element> variants;
    
    /**
     * Default constructor method of Class.
     */
    public Variability() {
        this.name           = "Variability Name";
        this.variationPoint = null;
        this.constraint     = "Exclusive";
        this.bindingTime    = "DESIGN_TIME";
        this.variants       = new ArrayList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param variationPoint Variation Point.
     */
    public Variability(Element variationPoint) {
        this();
        this.variationPoint = variationPoint;
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Variability(org.w3c.dom.Element element) {
        this.id          = element.getAttribute("id");
        this.name        = element.getAttribute("name");
        this.constraint  = element.getAttribute("constraint");
        this.bindingTime = element.getAttribute("bindingTime");
        this.minimum     = Integer.parseInt(element.getAttribute("min"));
        this.maximum     = Integer.parseInt(element.getAttribute("max"));
        this.variants    = new ArrayList<>();
    }

    /**
     * Method responsible for returning the Variability Id.
     * @return Variability Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Variability Id.
     * @param id Variability Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Variability Name.
     * @return Variability Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining the Variability Name.
     * @param name Variability Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Variability Variation Point.
     * @return Variability Variation Point.
     */
    public Element getVariationPoint() {
        return this.variationPoint;
    }

    /**
     * Method responsible for defining the Variability Variation Point.
     * @param variationPoint Variability Variation Point.
     */
    public void setVariationPoint(Element variationPoint) {
        this.variationPoint = variationPoint;
    }

    /**
     * Method responsible for returning the Variability Constraint.
     * @return Variability Constraint.
     */
    public String getConstraint() {
        return this.constraint;
    }

    /**
     * Method responsible for defining the Variability Constraint.
     * @param constraint Variability Constraint.
     */
    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    /**
     * Method responsible for returning the Variability Binding Time.
     * @return Variability Binding Time.
     */
    public String getBindingTime() {
        return this.bindingTime;
    }

    /**
     * Method responsible for defining the Variability Binding Time.
     * @param bindingTime Variability Binding Time.
     */
    public void setBindingTime(String bindingTime) {
        this.bindingTime = bindingTime;
    }

    /**
     * Method responsible for returning the Variability Minimum.
     * @return Variability Minimum.
     */
    public Integer getMinimum() {
        return this.minimum;
    }

    /**
     * Method responsible for defining the Variability Minimum.
     * @param minimum Variability Minimum.
     */
    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    /**
     * Method responsible for returning the Variability Maximum.
     * @return Variability Maximum.
     */
    public Integer getMaximum() {
        return this.maximum;
    }

    /**
     * Method responsible for defining the Variability Maximum.
     * @param maximum Variability Maximum.
     */
    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }
    
    /**
     * Method responsible for returning the Variants.
     * @return Variants.
     */
    public List<Element> getVariants() {
        return this.variants;
    }
    
    /**
     * Method responsible for returning the Variants List.
     * @return Variants List.
     */
    public String getVariantsList() {
        if (this.variants.size() == 1)
            return "{" + this.variants.get(0).getName() + "}";
        String toReturn  = "{" + this.variants.get(0) + ", \n";
        for (int i = 1; i < this.variants.size() - 1; i++)
            toReturn += this.variants.get(i).getName() + ", \n";
               toReturn += this.variants.get(this.variants.size() - 1).getName() + "}";
        return toReturn;
    }
    
    /**
     * Method responsible for adding a Variant.
     * @param variant Variant.
     */
    public void addVariant(Element variant) {
        if (this.variants.contains(variant) == false)
            this.variants.add(variant);
    }
    
    /**
     * Method responsible for checking if an Element is Variant.
     * @param  element Element.
     * @return Element is Variant.
     */
    public boolean isVariant(Element element) {
        return this.variants.contains(element);
    }
    
    /**
     * Method responsible for checking if an Element is Variant.
     * @param  element Element Name.
     * @return Element is Variant.
     */
    public boolean isVariant(String element) {
        for (Element variant : this.variants) {
            if (variant.getName().equalsIgnoreCase(element))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing a Variant.
     * @param variant Variant.
     */
    public void removeVariant(Element variant) {
        if (this.variants.contains(variant))
            this.variants.remove(variant);
    }
    
    /**
     * Method responsible for checking if is no Variants.
     * @return No Variants.
     */
    public boolean emptyVariants() {
        return this.variants.isEmpty();
    }
    
    /**
     * Method responsible for defining Variability Variants.
     * @param variants Variability Variants.
     */
    public void setVariants(List<Element> variants) {
        this.variants = variants;
    }
    
    /**
     * Method responsible for returning the Variability Icon.
     * @return Variability Icon.
     */
    public String getIcon() {
        return "icons/variability/variability.png";
    }
    
    /**
     * Method responsible for returning the Element Icon in a Variability.
     * @param  element Element.
     * @return Element Icon in a Variability.
     */
    public String getIcon(Element element) {
        if (this.getVariants().contains(element) && this.getConstraint().equalsIgnoreCase("inclusive"))
            return "icons/variability/inclusive.png";
        if (this.getVariants().contains(element) && this.getConstraint().equalsIgnoreCase("exclusive"))
            return "icons/variability/exclusive.png";
        return element.getIcon();
    }
    
    /**
     * Method responsible for returning the Variability Summary.
     * @return Variability Summary.
     */
    public String getSummary() {
        return "Variability: " + this.getName();
    }
    
    /**
     * Method responsible for exporting the Variation Point String.
     * @return Variation Point String.
     */
    private String exportVariationPoint() {
        return " variationPoint=\"" + this.variationPoint.getId() + "\"";
    }
    
    /**
     * Method responsible for exporting the Variants String.
     * @return Variants String.
     */
    private String exportVariants() {
        String export  = "";
        for (Element variant : this.variants)
               export += "      <variant id=\"" + variant.getId() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "    <variability";
               export += " id=\""          + this.id          + "\"";
               export += " name=\""        + this.name        + "\"";
               export += this.exportVariationPoint();
               export += " constraint=\""  + this.constraint  + "\"";
               export += " bindingTime=\"" + this.bindingTime + "\"";
               export += " min=\""         + this.minimum     + "\"";
               export += " max=\""         + this.maximum     + "\"";
               export += ">\n";
               export += this.exportVariants();
               export += "    </variability>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}