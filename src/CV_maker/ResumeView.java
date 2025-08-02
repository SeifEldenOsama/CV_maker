package CV_maker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ResumeView {
    private final BorderPane root = new BorderPane();

    public ResumeView(ResumeController controller) {
        VBox sectionList = new VBox(10);
        sectionList.setPadding(new Insets(10));

        ComboBox<ResumeTemplate> templatePicker = new ComboBox<>();
        templatePicker.getItems().addAll(ResumeTemplate.values());
        templatePicker.setValue(ResumeTemplate.MODERN_BLUE);
        templatePicker.setOnAction(e -> {
            controller.setTemplate(templatePicker.getValue());
        });

        Button add = new Button("➕ Add Section");
        add.getStyleClass().add("btn-primary");

        Button export = new Button("📄 Export PDF");
        export.getStyleClass().add("btn-export");

        HBox topBox = new HBox(12, new Label("Template:"), templatePicker, add, export);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-background-color: #e9ecef;");

        controller.setSectionView(sectionList);

        add.setOnAction(e -> controller.openAddSectionWindow());
        export.setOnAction(e -> controller.exportPDF());

        ScrollPane scroll = new ScrollPane(sectionList);
        scroll.setFitToWidth(true);

        root.setTop(topBox);
        root.setCenter(scroll);
    }

    public BorderPane getRoot() {
        return root;
    }
}
