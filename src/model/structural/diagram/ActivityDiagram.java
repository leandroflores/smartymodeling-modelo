package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.activity.base.association.FlowUML;

/**
 * <p>Class of Model <b>ActivityDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Activity Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  17/07/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.activity.base.ActivityUML
 * @see    model.structural.diagram.activity.base.DecisionUML
 * @see    model.structural.diagram.activity.base.FinalUML
 * @see    model.structural.diagram.activity.base.InitialUML
 * @see    model.structural.diagram.activity.base.JoinUML
 */
public final class ActivityDiagram extends Diagram {
    private HashMap<String, ActivityUML> activities;
    private HashMap<String, DecisionUML> decisions;
    private HashMap<String, InitialUML>  initials;
    private HashMap<String, FinalUML>    finals;
    private HashMap<String, Association> flows;

    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ActivityDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ActivityDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }
    
    @Override
    public void init() {
        this.type       = "Activity";
        this.activities = new HashMap<>();
        this.decisions  = new HashMap<>();
        this.initials   = new HashMap<>();
        this.finals     = new HashMap<>();
        this.flows      = new HashMap<>();
    }
    
    /**
     * Method responsible for adding a Activity UML.
     * @param activity Activity UML.
     */
    public void addActivity(ActivityUML activity) {
        activity.setId(this.nextId(activity));
        if (this.activities.get(activity.getId()) == null) {
            this.activities.put(activity.getId(), activity);
            this.addElement(activity);
        }
    }
    
    /**
     * Method responsible for removing a Activity UML.
     * @param activity Activity UML.
     */
    public void removeActivity(ActivityUML activity) {
        this.removeAssociations(activity);
        this.removeElement(activity);
        this.activities.remove(activity.getId());
    }
    
    /**
     * Method responsible for returning the Activities List.
     * @return Activities List.
     */
    public List<ActivityUML> getActivitiesList() {
        return new ArrayList<>(this.activities.values());
    }
    
    /**
     * Method responsible for adding a Decision UML.
     * @param decision Decision UML.
     */
    public void addDecision(DecisionUML decision) {
        decision.setId(this.nextId(decision));
        if (this.decisions.get(decision.getId()) == null) {
            this.decisions.put(decision.getId(), decision);
            this.addElement(decision);
        }
    }
    
    /**
     * Method responsible for removing a Decision UML.
     * @param decision Decision UML.
     */
    public void removeDecision(DecisionUML decision) {
        this.removeAssociations(decision);
        this.removeElement(decision);
        this.decisions.remove(decision.getId());
    }
    
    /**
     * Method responsible for returning the Decisions List.
     * @return Decisions List.
     */
    public List<DecisionUML> getDecisionsList() {
        return new ArrayList<>(this.decisions.values());
    }
    
    /**
     * Method responsible for adding a Initial UML.
     * @param initial Initial UML.
     */
    public void addInitial(InitialUML initial) {
        initial.setId(this.nextId(initial));
        if (this.initials.get(initial.getId()) == null) {
            this.initials.put(initial.getId(), initial);
            this.addElement(initial);
        }
    }
    
    /**
     * Method responsible for removing a Initial UML.
     * @param initial Initial UML.
     */
    public void removeInitial(InitialUML initial) {
        this.removeAssociations(initial);
        this.removeElement(initial);
        this.initials.remove(initial.getId());
    }
    
    /**
     * Method responsible for adding a Final UML.
     * @param final_ Final UML.
     */
    public void addFinal(FinalUML final_) {
        final_.setId(this.nextId(final_));
        if (this.finals.get(final_.getId()) == null) {
            this.finals.put(final_.getId(), final_);
            this.addElement(final_);
        }
    }
    
    /**
     * Method responsible for removing a Final UML.
     * @param final_ Final UML.
     */
    public void removeFinal(FinalUML final_) {
        this.removeAssociations(final_);
        this.removeElement(final_);
        this.finals.remove(final_.getId());
    }
    
    /**
     * Method responsible for adding a Flow UML.
     * @param flow Flow UML.
     */
    public void addFlow(FlowUML flow) {
        flow.setId(this.nextId(flow));
        if (this.flows.get(flow.getId()) == null) {
            this.flows.put(flow.getId(), flow);
            this.addAssociation(flow);
        }
    }
    
    /**
     * Method responsible for removing a Flow UML.
     * @param flow Flow UML.
     */
    public void removeFlow(FlowUML flow) {
        super.removeAssociation(flow);
        this.flows.remove(flow.getId());
    }
    
    /**
     * Method responsible for returning the Flows List.
     * @return Flows List.
     */
    public List<FlowUML> getFlowsList() {
        ArrayList<FlowUML> list = new ArrayList<>();
        for (Association association : this.flows.values())
               list.add((FlowUML) association);
        return list;
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.flows);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof FlowUML)
            this.removeFlow((FlowUML) association);
        else
            super.removeAssociation(association);
    }
    
    @Override
    public List<Element> getTreeElementsList() {
        List<Element> filter  = new ArrayList<>();
        for (Element  element : this.getElementsList()) {
            if (element.getType().equals("activity"))
                filter.add(element);
        }
        return  filter;
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "activity.png";
    }

    @Override
    public String getInstanceIcon() {
        return "icons/product/instance/instance-activity.png";
    }
    
    @Override
    public Diagram getClone() {
        try {
            ActivityDiagram diagram = (ActivityDiagram) super.clone();
                            diagram.setElements(new HashMap<>(this.elements));
                            diagram.setAssociations(new HashMap<>(this.associations));
                            diagram.setVariabilities(new HashMap<>(this.variabilities));
            return          diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}