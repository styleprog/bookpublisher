package pl.codinglab.bookpublisher.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogUtil {

    private DialogUtil() {
    }

    public static Stage showDialog(Parent view, Stage primaryStage) {
        final Stage dialogStage = new Stage(StageStyle.UTILITY);
        dialogStage.initOwner(primaryStage);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        if (dialogStage.getScene() == null) {
            Scene dialogScene = new Scene(view);
            dialogStage.setScene(dialogScene);
            dialogStage.sizeToScene();
            dialogStage.show();
            return dialogStage;
        }
        return null;
    }
}
