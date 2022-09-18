package util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Validation {

    public static Object validate(LinkedHashMap<JFXTextField,Pattern> map, JFXButton btnSave,JFXButton btnUpdate,JFXButton btnRemove) {
        for (JFXTextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()){
                    textFieldKey.setStyle("-fx-border-color: red");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-border-color: green");
        }
        btnSave.setDisable(false);
        btnUpdate.setDisable(false);
        btnRemove.setDisable(false);
        return true;
    }
}
