package GUI.Util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class TextLimiter {

    public void textLimit(TextField textField, String textPattern){
        //pattern used to check against
        //.{0,4}|\d*|\d+\,\d*
        Pattern pattern = Pattern.compile(textPattern);
        //new instance of the textFormatter
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {

            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        //sets the textFormatter
        textField.setTextFormatter(formatter);
    }
}
