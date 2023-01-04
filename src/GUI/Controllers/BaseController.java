package GUI.Controllers;

import GUI.Model.MovieModel;

public abstract class BaseController {
    private MovieModel model;

    /**
     * Sets the model of the controller
     * @param model
     */
    public void setModel(MovieModel model)
    {
        this.model = model;
    }

    /**
     * Gets the model of the controller
     * @return the model of the controller
     */
    public MovieModel getModel()
    {
        return model;
    }

    /**
     * What happens when the controller starts
     */
    public abstract void setup();
}
