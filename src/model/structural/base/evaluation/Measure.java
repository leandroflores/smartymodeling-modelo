package model.structural.base.evaluation;

import funct.FunctDate;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Measure</b>.</p>
 * <p>Class responsible for representing the <b>Measure</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-02
 * @see    model.structural.base.evaluation.Metric
 * @see    model.structural.base.interfaces.Exportable
 */
public final class Measure implements Exportable {
    private String id;
    private String name;
    private String date;
    private Metric metric;
    private String target;
    private Double value;
    
     /**
     * Default constructor method of Class.
     */
    public Measure() {
        this.id     = "";
        this.name   = "NewMeasure";
        this.date   = new FunctDate().getCurrentUSFormattedDate();
        this.metric = null;
        this.target = "Project";
        this.value  = 0.0d;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Measure(org.w3c.dom.Element element) {
        this.id     = element.getAttribute("id");
        this.name   = element.getAttribute("name");
        this.date   = new FunctDate().getCurrentUSFormattedDate();
        this.metric = null;
        this.target = element.getAttribute("target");
        this.setValue(element);
    }
    
    /**
     * Method responsible for returning the Measure Id.
     * @return Measure Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Measure Id.
     * @param id Measure Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }

    /**
     * Method responsible for returning the Measure Name.
     * @return Measure Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining the Measure Name.
     * @param name Measure Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning the Measure Date.
     * @return Measure Date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Method responsible for setting the Measure Date.
     * @param date Measure Date.
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Method responsible for returning the Measure Metric.
     * @return Measure Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }

    /**
     * Method responsible for setting the Measure Metric.
     * @param metric Measure Metric.
     */
    public void setMetric(Metric metric) {
        this.metric = metric;
    }
    
    /**
     * Method responsible for returning the Measure Target.
     * @return Measure Target.
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Method responsible for setting the Measure Target.
     * @param target Measure Target.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Method responsible for returning the Measure Value.
     * @return Measure Value.
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * Method responsible for setting the Value by W3C Element.
     * @param  element W3C Element.
     */
    public void setValue(org.w3c.dom.Element element) {
        try {
            this.value = Double.parseDouble(element.getAttribute("valor").trim());
        }catch (NumberFormatException exception) {
            this.value = 0.0d;
        }
    }
    
    /**
     * Method responsible for setting the Measure Value.
     * @param value Measure Value.
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Method responsible for returning the Measure Icon.
     * @return Measure Icon.
     */
    public String getIcon() {
        return "icons/menu/evaluation/measure.png";
    }
    
    @Override
    public String export() {
        String export  = "  <measure";
               export += " id=\""     + this.id             + "\"";
               export += " name=\""   + this.name           + "\"";
               export += " date=\""   + this.date           + "\"";
               export += " metric=\"" + this.metric.getId() + "\"";
               export += " target=\"" + this.target         + "\"";
               export += " value=\""  + this.value          + "\"/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name + " = " + this.value;
    }
}