package model.controller.structural.base.requirement;

import model.structural.base.Project;

/**
 * <p>Class of Controller <b>ControllerRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    model.structural.base.requirement.Requirement
 */
public class ControllerRequirement {
    public static final String[] TYPES = {"Business", "Functional", "Non-Functional", "User Interface"};
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerRequirement(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning the Requirements Array.
     * @return Requirements Array.
     */
    public Object[] getRequirements() {
        return this.project.getRequirementsList().toArray();
    }
    
    /**
     * Method responsible for returning the Requirements Target.
     * @return Requirements Target.
     */
    public Object[] getRequirementsTarget() {
        Object[] objects  = this.project.getRequirementsList().toArray();
        Object[] array    = new Object[objects.length + 1];
                 array[0] = "All Requirements";
        System.arraycopy(objects, 0, array, 1, objects.length);
        return   array;
    }
}