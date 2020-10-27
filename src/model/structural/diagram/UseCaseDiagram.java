package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.association.Association;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.association.ExtendUML;
import model.structural.diagram.usecase.base.association.IncludeUML;
import model.structural.diagram.usecase.base.association.CommunicationUML;
import model.structural.diagram.usecase.base.UseCaseUML;

/**
 * <p>Class of Model <b>UseCaseDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Use Case Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Diagram
 * @see    model.structural.diagram.usecase.base.ActorUML
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 */
public final class UseCaseDiagram extends Diagram {
    private HashMap<String, ActorUML>    actorsUML;
    private HashMap<String, UseCaseUML>  useCasesUML;
    private HashMap<String, Association> communicationsUML;
    private HashMap<String, Association> extendsUML;
    private HashMap<String, Association> includesUML;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public UseCaseDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public UseCaseDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }
    
    @Override
    public void init() {
        this.type              = "UseCase";
        this.actorsUML         = new HashMap<>();
        this.useCasesUML       = new HashMap<>();
        this.communicationsUML = new HashMap<>();
        this.extendsUML        = new HashMap<>();
        this.includesUML       = new HashMap<>();
    }
    
    /**
     * Method responsible for adding a Actor UML.
     * @param actorUML Actor UML.
     */
    public void addActor(ActorUML actorUML) {
        actorUML.setId(this.nextId(actorUML));
        if (this.actorsUML.get(actorUML.getId()) == null) {
            this.actorsUML.put(actorUML.getId(), actorUML);
            this.addElement(actorUML);
        }
    }
    
    /**
     * Method responsible for removing a Actor UML.
     * @param actorUML Actor UML.
     */
    public void removeActor(ActorUML actorUML) {
        this.project.reset(actorUML);
        this.removeAssociations(actorUML);
        this.removeElement(actorUML);
        this.actorsUML.remove(actorUML.getId());
    }
    
    /**
     * Method responsible for returning Actors List.
     * @return Actors List.
     */
    public List<ActorUML> getActorsList() {
        return new ArrayList<>(this.actorsUML.values());
    }
    
    /**
     * Method responsible for adding a Use Case UML.
     * @param useCaseUML Use Case UML.
     */
    public void addUseCase(UseCaseUML useCaseUML) {
        useCaseUML.setId(this.nextId(useCaseUML));
        if (this.useCasesUML.get(useCaseUML.getId()) == null) {
            this.useCasesUML.put(useCaseUML.getId(), useCaseUML);
            this.addElement(useCaseUML);
        }
    }
    
    /**
     * Method responsible for removing a Use Case UML.
     * @param useCaseUML Use Case UML.
     */
    public void removeUseCase(UseCaseUML useCaseUML) {
        this.removeAssociations(useCaseUML);
        this.removeElement(useCaseUML);
        this.useCasesUML.remove(useCaseUML.getId());
    }
    
    /**
     * Method responsible for returning Use Cases List.
     * @return Use Cases List.
     */
    public List<UseCaseUML> getUseCasesList() {
        return new ArrayList<>(this.useCasesUML.values());
    }
    
    /**
     * Method responsible for adding a Communication UML.
     * @param communicationUML Communication UML.
     */
    public void addCommunication(CommunicationUML communicationUML) {
        communicationUML.setId(this.nextId(communicationUML));
        if (this.communicationsUML.get(communicationUML.getId()) == null) {
            this.communicationsUML.put(communicationUML.getId(), communicationUML);
            this.addAssociation(communicationUML);
        }
    }
    
    /**
     * Method responsible for removing a Communication UML.
     * @param communicationUML Communication UML.
     */
    public void removeCommunication(CommunicationUML communicationUML) {
        super.removeAssociation(communicationUML);
        this.communicationsUML.remove(communicationUML.getId());
    }
    
    /**
     * Method responsible for adding a Extend UML.
     * @param extendUML Extend UML.
     */
    public void addExtend(ExtendUML extendUML) {
        extendUML.setId(this.nextId(extendUML));
        if (this.extendsUML.get(extendUML.getId()) == null) {
            this.extendsUML.put(extendUML.getId(), extendUML);
            this.addAssociation(extendUML);
        }
    }
    
    /**
     * Method responsible for removing a Extend UML.
     * @param extendUML Extend UML.
     */
    public void removeExtend(ExtendUML extendUML) {
        super.removeAssociation(extendUML);
        this.extendsUML.remove(extendUML.getId());
    }
    
    /**
     * Method responsible for adding a Include UML.
     * @param includeUML Include UML.
     */
    public void addInclude(IncludeUML includeUML) {
        includeUML.setId(this.nextId(includeUML));
        if (this.includesUML.get(includeUML.getId()) == null) {
            this.includesUML.put(includeUML.getId(), includeUML);
            this.addAssociation(includeUML);
        }
    }
    
    /**
     * Method responsible for removing a Include UML.
     * @param includeUML Include UML.
     */
    public void removeInclude(IncludeUML includeUML) {
        super.removeAssociation(includeUML);
        this.includesUML.remove(includeUML.getId());
    }
    
    /**
     * Method responsible for removing Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.communicationsUML);
        this.removeAssociation(element, this.extendsUML);
        this.removeAssociation(element, this.includesUML);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof CommunicationUML)
            this.removeCommunication((CommunicationUML) association);
        else if (association instanceof ExtendUML)
            this.removeExtend((ExtendUML) association);
        else if (association instanceof IncludeUML)
            this.removeInclude((IncludeUML) association);
        else
            super.removeAssociation(association);
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "use-case.png";
    }
    
    @Override
    public String getInstanceIcon() {
        return "icons/product/instance/instance-use-case.png";
    }
    
    @Override
    public UseCaseDiagram getClone() {
        try {
            UseCaseDiagram diagram = (UseCaseDiagram) super.clone();
                           diagram.setElements(new HashMap<>(this.elements));
                           diagram.setAssociations(new HashMap<>(this.associations));
                           diagram.setVariabilities(new HashMap<>(this.variabilities));
            return         diagram;
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