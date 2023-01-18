package GUI.Controllers;

import GUI.Models.ModelsHandler;

public abstract class BaseController {
    private ModelsHandler modelsHandler;

    public void setModel(ModelsHandler modelsHandler) {
        this.modelsHandler = modelsHandler;
    }

    public ModelsHandler getModelsHandler() {
        return modelsHandler;
    }

    /**
     * Checks if a given rating is valid.
     * @param rating The rating to check.
     * @return true if the rating is valid, otherwise false.
     */
    public boolean isRatingInputValid(String rating) {
        try {
            double doubleRating = Double.parseDouble(rating);

            if (doubleRating <= 10.0 && doubleRating >= 0.0) return true;
        }
        catch (NumberFormatException ignore) {}

        return false;
    }

    public abstract void setup();
}
