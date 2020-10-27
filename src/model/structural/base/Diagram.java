package model.structural.base;

import model.structural.base.association.Association;
import model.structural.base.association.Generalization;
import funct.FunctString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.comparator.structural.base.ComparatorElement;
import model.comparator.structural.base.association.ComparatorAssociation;
import model.comparator.structural.base.variability.ComparatorVariability;
import model.structural.base.association.Dependency;
import model.structural.base.association.Link;
import model.structural.base.interfaces.Exportable;
import model.structural.diagram.classes.base.TypeUML;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Model <b>Diagram</b>.</p>
 * <p>Class responsible for representing <b>Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.interfaces.Exportable
 */
public abstract class Diagram implements Exportable {
    protected Project project;
    protected String  id;
    protected String  name;
    protected String  type;
    protected HashMap elements;
    protected HashMap associations;
    protected HashMap variabilities;
    
    /**
     * Default constructor method of Class.
     */
    public Diagram() {
        this.elements      = new LinkedHashMap<>();
        this.associations  = new LinkedHashMap<>();
        this.variabilities = new LinkedHashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     */
    public Diagram(Project project) {
        this();
        this.project = project;
        this.id      = "";
        this.name    = "Diagram";
        this.type    = "";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public Diagram(Project project, org.w3c.dom.Element element) {
        this();
        this.project = project;
        this.id      = element.getAttribute("id");
        this.name    = element.getAttribute("name");
        this.type    = element.getAttribute("type");
    }
    
    /**
     * Method responsible for initializing the HashMaps.
     */
    public abstract void init();
    
    /**
     * Method responsibel for returning the Image Default Folder.
     * @return Image Default Folder.
     */
    protected String getFolder() {
        return "icons/menu/diagram/";
    }
    
    /**
     * Method responsible for returning Diagram Icon Path.
     * @return Diagram Icon Path.
     */
    public abstract String getIcon();
    
    /**
     * Method responsible for returning the Instance Icon Path.
     * @return Instance Icon Path.
     */
    public abstract String getInstanceIcon();
    
    /**
     * Method responsible for returning Diagram Clone.
     * @return Diagram Clone.
     */
    public abstract Diagram getClone();

    /**
     * Method responsible for returning Diagram Project.
     * @return Diagram Project.
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Method responsible for defining Diagram Project.
     * @param project Diagram Project.
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning Diagram Id.
     * @return Diagram Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining Diagram Id.
     * @param id Diagram Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }

    /**
     * Method responsible for returning Diagram Name.
     * @return Diagram Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining Diagram Name.
     * @param name Diagram Name.
     */
    public void setName(String name) {
        String string = new FunctString().getString(name);
        this.name     = string.isEmpty() ? this.name : string;
    }
    
    /**
     * Method responsible for defining Default Name.
     */
    public void setDefaultName() {
        if (this.id != null)
            this.setName(new FunctString().getInitUpperCase(this.id.replaceAll("#", "").replaceAll("-", "")));
    }
    
    /**
     * Method responsible for returning Diagram Type.
     * @return Diagram Type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Method responsible for returning the Diagram Index.
     * @return Diagram Index.
     */
    public Integer getIndex() {
        switch (this.type.toLowerCase().trim()) {
            case "feature":
                return 1;
            case "usecase":
                return 2;
            case "class":
                return 3;
            case "component":
                return 4;
            case "sequence":
                return 5;
            case "activity":
                return 6;
            default:
                return 0;
        }
    }
    
    /**
     * Method responsible for defining Diagram Type.
     * @param type Diagram Type.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     *  Method responsible for returing Next Id.
     * @param  label Object Label.
     * @return Next Id.
     */
    public String nextId(String label) {
        return this.project.nextId(label);
    }
    
    /**
     * Method responsible for returning Elements HashMap.
     * @return Elements HashMap.
     */
    public HashMap<String, Element> getElements() {
        return this.elements;
    }
    
    /**
     * Method responsible for returning the Tree Elements List.
     * @return Tree Elements List.
     */
    public List<Element> getTreeElementsList() {
        return this.getElementsList();
    }
    
    /**
     * Method responsible for returning Elements List.
     * @return Elements List.
     */
    public List<Element> getElementsList() {
        List   list = new ArrayList<>(this.elements.values());
               list.sort(new ComparatorElement());
        return list;
    }
    
    /**
     * Method responsible for returning the Default Elements List.
     * @return Default Elements List.
     */
    public List<Element> getDefaultElements() {
        List<Element> filter  = new ArrayList<>();
        for (Element  element : this.getElementsList()) {
            if (element.isDefault())
                filter.add(element);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning Elements by Mandatory Flag.
     * @param  mandatory Mandatory Flag.
     * @return Elements by Mandatory Flag.
     */
    public List<Element> filterElements(boolean mandatory) {
        List<Element> list   = this.getElementsList();
        List<Element> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isMandatory() == mandatory)
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning a Entity by Name.
     * @param  name Entity Name.
     * @return Entity found.
     */
    public Entity filterEntityByName(String name) {
        for (Element element : this.getElementsList()) {
            if (element instanceof Entity &&
                element.getName().equalsIgnoreCase(name))
                return (Entity) element;
        }
        return null;
    }
    
    /**
     * Method responsible for returning a Package UML by Name.
     * @param  name Package UML Name.
     * @return Package UML found.
     */
    public PackageUML filterPackageUMLByName(String name) {
        for (Element element : this.getElementsList()) {
            if (element instanceof PackageUML &&
                element.getName().equalsIgnoreCase(name))
                return (PackageUML) element;
        }
        return null;
    }
    
    /**
     * Method responsible for returning Optional Elements.
     * @return Optional Elements.
     */
    public List<Element> filterOptionalElements() {
        List<Element> list   = this.getElementsList();
        List<Element> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).isDefault()) && (this.filterVariants(list.get(i), "").isEmpty()))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning a New Element Id.
     * @param  element Element.
     * @return New Element Id.
     */
    protected String nextId(Element element) {
        return this.nextId(element.getType().toUpperCase().trim() + "#");
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void addElement(Element element) {
        this.elements.put(element.getId(), element);
        this.project.objects.put(element.getId(), element);
        this.project.addElementStereotype(element);
    }
    
    /**
     * Method responsible for returning a Element by Id.
     * @param  id Element Id.
     * @return Element found.
     */
    public Element getElement(String id) {
        return (Element) this.elements.get(id);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        this.removeAssociation(element);
        this.removeVariability(element);
        this.project.removeRequirement(element);
        this.project.removeTraceability(element);
        this.project.removeProduct(element);
        this.project.removeLinks(element);
        this.project.objects.remove(element.getId());
        this.elements.remove(element.getId());
    }
    
    /**
     * Method responsible for defining Elements List.
     * @param elements Elements List.
     */
    public void setElements(HashMap<String, Element> elements) {
        this.elements = elements;
    }
    
    /**
     * Method responsible for returning a Associations List By Class.
     * @param  class_ Association Class.
     * @return Associations List.
     */
    private List filterAssociations(Class class_) {
        List    list = new ArrayList();
        for (Association association : this.getAssociationsList()) {
            if (association.getClass().equals(class_))
                list.add(association);
        }
        return  list;
    }
    
    /**
     * Method responsible for returning a Associations List By Element and Class.
     * @param  element Element.
     * @param  class_ Association Class.
     * @return Associations List.
     */
    public List filterAssociations(Element element, Class class_) {
        List<Association> list   = this.getAssociationsList();
        List<Association> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClass().equals(class_)
            &&  list.get(i).contains(element))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning a New Association Id.
     * @param  association Association.
     * @return New Association Id.
     */
    protected String nextId(Association association) {
        return this.nextId(association.getType().toUpperCase().trim() + "#");
    }
    
    /**
     * Method responsible for returning Associations HashMap.
     * @return Associations HashMap.
     */
    public HashMap<String, Association> getAssociations() {
        return this.associations;
    }
    
    /**
     * Method responsible for returning Associations List.
     * @return Associations List.
     */
    public List<Association> getAssociationsList() {
        List   list = new ArrayList<>(this.associations.values());
               list.sort(new ComparatorAssociation());
        return list;
    }
    
    /**
     * Method responsible for returning the Associations List by Type.
     * @param  type Association Type.
     * @return Associations List.
     */
    public List getAssociations(String type) {
        List    filter = new ArrayList<>();
        for (Association association : this.getAssociationsList()) {
            if (association.getType().equalsIgnoreCase(type))
                filter.add(association);
        }
        return  filter;
    }
    
    /**
     * Method responsible for creating a New Associations Map.
     * @param  array Associations Array.
     * @return New Associations Map.
     */
    protected Map<String, Association> createMap(Object[] array) {
        HashMap map = new HashMap<>();
        for (Object object : array)
                map.put(((Association) object).getId(), (Association) object);
        return  map;
    }
    
    /**
     * Method responsible for returning the Associations by Type and Target.
     * @param  type Type Association.
     * @param  target Target Association.
     * @return Associations List.
     */
    public List<Association> getSourceAssociations(String type, Element target) {
        List    filter = new ArrayList<>();
        for (Association association : this.getAssociationsList()) {
            if (association.getType().equalsIgnoreCase(type) && association.isTarget(target))
                filter.add(association);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Associations by Type and Source.
     * @param  type Type Association.
     * @param  source Source Association.
     * @return Associations List.
     */
    public List<Association> getTargetAssociations(String type, Element source) {
        List    filter = new ArrayList<>();
        for (Association association : this.getAssociationsList()) {
            if (association.getType().equalsIgnoreCase(type) && association.isSource(source))
                filter.add(association);
        }
        return  filter;
    }
    
    /**
     * Method responsible for adding a Requires.
     * @param requires Requires.
     */
    public void addRequires(Requires requires) {
        requires.setId(this.nextId(requires));
        if (this.associations.containsKey(requires.getId()) == false)
            this.addAssociation(requires);
    }
    
    /**
     * Method responsible for removing a Requires.
     * @param requires Requires.
     */
    public void removeRequires(Requires requires) {
        this.removeAssociation(requires);
    }
    
    /**
     * Method responsible for returning Requires List.
     * @return Requires List.
     */
    public List<Requires> getRequiresList() {
        return (List<Requires>) this.filterAssociations(Requires.class);
    }
    
    /**
     * Method responsible for returning Requires List by Element.
     * @param  element Element.
     * @return Requires List.
     */
    public List<Association> getRequiresList(Element element) {
        return this.filterAssociations(element, Requires.class);
    }
    
    /**
     * Method responsible for adding a Mutex.
     * @param mutex Mutex.
     */
    public void addMutex(Mutex mutex) {
        mutex.setId(this.nextId(mutex));
        if (this.associations.containsKey(mutex.getId()) == false)
            this.addAssociation(mutex);
    }
    
    /**
     * Method responsible for removing a Mutex.
     * @param mutex Mutex.
     */
    public void removeMutex(Mutex mutex) {
        this.removeAssociation(mutex);
    }
    
    /**
     * Method responsible for returning Mutex List.
     * @return Mutex List.
     */
    public List<Mutex> getMutexList() {
        return (List<Mutex>) this.filterAssociations(Requires.class);
    }
    
    /**
     * Method responsible for returning Mutex List by Element.
     * @param  element Element.
     * @return Mutex List.
     */
    public List<Association> getMutexList(Element element) {
        return this.filterAssociations(element, Mutex.class);
    }
    
    /**
     * Method responsible for adding a Generalization.
     * @param generalization Generalization.
     */
    public void addGeneralization(Generalization generalization) {
        generalization.setId(this.nextId(generalization));
        if (!this.associations.containsKey(generalization.getId()))
             this.addAssociation(generalization);
    }
    
    /**
     * Method responsible for removing a Generalization.
     * @param generalization Generalization.
     */
    public void removeGeneralization(Generalization generalization) {
        this.removeAssociation(generalization);
    }
    
    /**
     * Method responsible for returning the Super Generalization from a Element.
     * @param  element Element.
     * @return Super Element.
     */
    public Element getSuper(Element element) {
        for (Generalization generalization : this.getGeneralizationsList()) {
            if (generalization.isSource(element))
                return generalization.getTarget(element);
        }
        return null;
    }
    
    /**
     * Method responsible for returning the Supers List of a Element.
     * @param  element Element.
     * @return Supers List.
     */
    public List<Element> getSupers(Element element) {
        List    list    = new ArrayList<>();
        Element super_  = this.getSuper(element);
        while  (super_ != null) {
                list.add(super_);
                super_  = this.getSuper(super_);
        }    
        return  list;
    }
    
    /**
     * Method responsible for returning Generalization List.
     * @return Generalization List.
     */
    public List<Generalization> getGeneralizationsList() {
        return (List<Generalization>) this.filterAssociations(Generalization.class);
    }
    
    /**
     * Method responsible for adding a Dependency.
     * @param dependency Dependency.
     */
    public void addDependency(Dependency dependency) {
        dependency.setId(this.nextId(dependency));
        if (this.associations.containsKey(dependency.getId()) == false)
            this.addAssociation(dependency);
    }
    
    /**
     * Method responsible for returning Dependencies by Element.
     * @param  element Element.
     * @return Dependencies found.
     */
    public List<Dependency> filterDependency(Element element) {
        return (List<Dependency>) this.filterAssociations(element, Dependency.class);
    }
    
    /**
     * Method responsible for removing a Dependency.
     * @param dependency Dependency.
     */
    public void removeDependency(Dependency dependency) {
        this.removeAssociation(dependency);
    }
    
    /**
     * Method responsible for returning Dependency List.
     * @return Dependency List.
     */
    public List<Dependency> getDependenciesList() {
        return (List<Dependency>) this.filterAssociations(Dependency.class);
    }
    
    /**
     * Method responsible for adding a Association.
     * @param association Association.
     */
    public void addAssociation(Association association) {
        this.associations.put(association.getId(), association);
        this.project.objects.put(association.getId(), association);
    }
    
    /**
     * Method responsible for returning a Association by Id.
     * @param  id Association Id.
     * @return Association found.
     */
    public Association getAssociation(String id) {
        return (Association) this.associations.get(id);
    }
    
    /**
     * Method responsible for removing a Association.
     * @param association Association.
     */
    public void removeAssociation(Association association) {
        this.project.removeProduct(association);
        this.project.objects.remove(association.getId());
        this.associations.remove(association.getId());
    }
    
    /**
     * Method responsible for removing a Association by Element.
     * @param element Element.
     */
    private void removeAssociation(Element element) {
        for (Association association : this.getAssociationsList()) {
            if (association.contains(element))
                this.removeAssociation(association);
        }
    }
    
    /**
     * Method responsible for removing a Association by Element.
     * @param element Element.
     * @param map Associations Map.
     */
    protected void removeAssociation(Element element, Map<String, Association> map) {
        for (Association association : new ArrayList<>(map.values())) {
            if (association.contains(element))
                this.removeAssociation(association);
        }
    }
    
    /**
     * Method responsible for defining Associations List.
     * @param associations Associations List.
     */
    public void setAssociations(HashMap<String, Association> associations) {
        this.associations = associations;
    }
    
    /**
     * Method responsible for returning Variabilities HashMap.
     * @return Variabilities HashMap.
     */
    public HashMap<String, Variability> getVariabilities() {
        return this.variabilities;
    }
    
    /**
     * Method responsible for returning Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilitiesList() {
        List   list = new ArrayList<>(this.variabilities.values());
               list.sort(new ComparatorVariability());
        return list;
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param variability Variability.
     */
    public void addVariability(Variability variability) {
        variability.setId(this.project.nextVariabilityId());
        if (this.variabilities.get(variability.getId()) == null) {
            this.project.addVariability(variability);
            this.variabilities.put(variability.getId(), variability);
        }
    }
    
    /**
     * Method responsible for returning a Variability by Id.
     * @param  id Variability Id.
     * @return Variability found.
     */
    public Variability getVariability(String id) {
        return (Variability) this.variabilities.get(id);
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        this.project.variabilities.remove(variability.getId());
        this.variabilities.remove(variability.getId());
    }
    
    /**
     * Method responsible for removing a Variability by Element.
     * @param element Element.
     */
    private void removeVariability(Element element) {
        this.removeVariationPoint(element);
        this.removeVariants(element);
    }
    
    /**
     * Method responsible for removing Variabilities by Variation Point.
     * @param element Variation Point.
     */
    private void removeVariationPoint(Element element) {
        for (Variability variability : this.getVariationPoints(element))
            this.removeVariability(variability);
    }
    
    /**
     * Method responsible for removing Variants by Element.
     * @param element Element.
     */
    private void removeVariants(Element element) {
        for (Variability variability : this.filterVariants(element, "")) {
            variability.removeVariant(element);
            if (variability.emptyVariants())
                this.removeVariability(variability);
        }
    }
    
    /**
     * Method responsible for defining Variabilities HashMap.
     * @param variabilities Variabilities HashMap.
     */
    public void setVariabilities(HashMap<String, Variability> variabilities) {
        this.variabilities = variabilities;
    }
    
    /**
     * Method responsible for returning Object Type.
     * @return Object Type.
     */
    public TypeUML getObjectType() {
        return this.project.getObjectType();
    }
    
    /**
     * Method responsible for returning Void Type.
     * @return Void Type.
     */
    public TypeUML getVoidType() {
        return this.project.getVoidType();
    }
    
    /**
     * Method responsible for reseting the Profile Stereotypes of a Element.
     * @param element Element.
     */
    public void resetProfileStereotypes(Element element) {
        List<Link> links = this.project.getLinksByElement(element);
        for (int i = links.size() - 1; i >= 0; i--) {
            if (links.get(i).getStereotype().isPrimitive())
                this.project.removeLink(links.get(i));
        }
    }
    
    /**
     * Method responsible for updating the Stereotype Elements.
     */
    public void updateElementsStereotype() {
        for (Element element : this.getElementsList())
            this.updateStereotype(element);
    }
    
    /**
     * Method responsible for updating the Element Stereotype.
     * @param element Element.
     */
    public void updateStereotype(Element element) {
        this.resetProfileStereotypes(element);
        boolean varPoint  = this.updateVariationPointStereotype(element);
        boolean inclusive = this.updateInclusiveStereotype(element);
        boolean exclusive = this.updateExclusiveStereotype(element);
//        if (!varPoint && !inclusive && !exclusive)
        if (!inclusive && !exclusive)
            this.updateDefaultStereotype(element);
    }
    
    /**
     * Method responsible for updating the Default Stereotype of a Element.
     * @param element Element.
     */
    public void updateDefaultStereotype(Element element) {
        if (element.isDefault()) {
            this.project.removeLink(new Link(element, this.project.getProfile().getMandatory()));
            this.project.removeLink(new Link(element, this.project.getProfile().getOptional()));
            this.project.addElementStereotype(element);
        }
    }
    
    /**
     * Method responsible for updating the Variation Point Stereotype of a Element.
     * @param  element Element.
     * @return Variation Point Stereotype.
     */
    public boolean updateVariationPointStereotype(Element element) {
        Stereotype stereotype = this.getVariationPointStereotype(element);
        if (stereotype != null) {
            this.project.addLink(new Link(element, stereotype));
            return true;
        }
        return false;
    }
    
    /**
     * Method responsible for updating the Inclusive Stereotype of a Element.
     * @param  element Element.
     * @return Inclusive Stereotype.
     */
    public boolean updateInclusiveStereotype(Element element) {
        Stereotype stereotype = this.getInclusiveStereotype(element);
        if (stereotype != null) {
            this.project.addLink(new Link(element, stereotype));
            return true;
        }
        return false;
    }
    
    /**
     * Method responsible for updating the Exclusive Stereotype of a Element.
     * @param  element Element.
     * @return Exclusive Stereotype.
     */
    public boolean updateExclusiveStereotype(Element element) {
        Stereotype stereotype = this.getExclusiveStereotype(element);
        if (stereotype != null) {
            this.project.addLink(new Link(element, stereotype));
            return true;
        }
        return false;
    }
    
    /**
     * Method responsible for returning the Stereotypes by Element.
     * @param  element Element.
     * @param  delimiter Delimiter.
     * @return Stereotypes.
     */
    public String getStereotypes(Element element, String delimiter) {
        String stereotype  = "";
        for (Stereotype current : this.getStereotypesList(element))
               stereotype += current.toString() + delimiter;
        return stereotype;
    }
    
    /**
     * Method responsible for returning the Stereotypes List by Element.
     * @param  element Element.
     * @return Stereotypes List.
     */
    public List<Stereotype> getStereotypesList(Element element) {
        List   stereotypes = new ArrayList<>();
        List<Link> links   = this.project.getLinksByElement(element);
        for (int i = 0; i <    links.size(); i++)
               stereotypes.add(links.get(i).getStereotype());
        return stereotypes;
    }
    
    /**
     * Method responsible for returning Variation Point Steretype by Element.
     * @param  element Element.
     * @return Variation Point Steretype.
     */
    public Stereotype getVariationPointStereotype(Element element) {
        return this.getVariationPoints(element).isEmpty() ? null : this.getProject().getProfile().getVariationPoint();
    }
    
    /**
     * Method responsible for returning Variation Points by Element.
     * @param  element Element.
     * @return Variation Points.
     */
    public List<Variability> getVariationPoints(Element element) {
        List<Variability> list   = this.getVariabilitiesList();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVariationPoint().equals(element))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Variants List by Element and Constraint.
     * @param  element Element.
     * @param  constraint Variability Constraint.
     * @return Variants List.
     */
    public List<Variability> filterVariants(Element element, String constraint) {
        List<Variability> list   = this.getVariabilitiesList();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getConstraint().toLowerCase().contains(constraint)
            &&  list.get(i).isVariant(element))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Inclusive Stereotype by Element.
     * @param  element Element.
     * @return Inclusive Stereotype.
     */
    public Stereotype getInclusiveStereotype(Element element) {
        return this.filterVariants(element, "inclusive").isEmpty() ? null : this.getProject().getProfile().getInclusive();
    }
    
    /**
     * Method responsible for returning Exclusive Stereotype by Element.
     * @param  element Element.
     * @return Exclusive Stereotype.
     */
    public Stereotype getExclusiveStereotype(Element element) {
        return this.filterVariants(element, "exclusive").isEmpty() ? null : this.getProject().getProfile().getExclusive();
    }
    
    /**
     * Method responsible for returning the List Values.
     * @param  hash Hash Map.
     * @return List Values.
     */
    protected List getList(HashMap hash) {
        return new ArrayList<>(hash.values());
    }
    
    /**
     * Method responsible for returning Elements to export.
     * @param  elements Elements List.
     * @return Elements to Export.
     */
    protected String export(List<Element> elements) {
        String export  = "";
        for (Element current : elements)
               export += current.export();
        return export;
    }
    
    /**
     * Method responsible for returning Elements to export.
     * @return Elements to export.
     */
    protected String exportElements() {
        String export  = "";
        for (Element element : this.getElementsList()) {
            if   ((element.getType().equals("attribute") == false)
              &&  (element.getType().equals("method")    == false))
               export += element.export();
        }
        return export;
    }
    
    /**
     * Method responsible for returning Associations to export.
     * @return Associations to export.
     */
    protected String exportAssociations() {
        String export  = "";
        for (Association association : this.getAssociationsList())
               export += association.export();
        return export;
    }
    
    /**
     * Method responsible for returning Variabilities to export.
     * @return Variabilities to export.
     */
    protected String exportVariabilities() {
        String export  = "";
        for (Variability variability : this.getVariabilitiesList())
               export += variability.export();
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <diagram id=\"" + this.id + "\" name=\"" + this.name + "\" type=\"" + this.type + "\">\n";
               export += this.exportElements();
               export += this.exportAssociations();
               export += this.exportVariabilities();
               export += "  </diagram>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name + " (" + this.type + ")";
    }
}