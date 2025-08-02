package CV_maker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ResumeController {
    private final List<CVSection> sections = new ArrayList<>();
    private VBox sectionView;
    private ResumeTemplate currentTemplate = ResumeTemplate.MODERN_BLUE;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSectionView(VBox view) {
        this.sectionView = view;
        refreshSectionView();
    }

    public void setTemplate(ResumeTemplate template) {
        this.currentTemplate = template;
        refreshSectionView();
    }

    public void openAddSectionWindow() {
        openEditSectionWindow(new CVSection("", ""), null);
    }

    public void openEditSectionWindow(CVSection section, VBox sectionBox) {
        boolean isNew = !sections.contains(section);

        Stage inputStage = new Stage();
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.setTitle(isNew ? "➕ Add Resume Section" : "✏️ Edit Resume Section");

        TextField titleField = new TextField(section.getTitle());
        titleField.getStyleClass().add("custom-textfield");

        TextArea descField = new TextArea(section.getDescription());
        descField.setPrefRowCount(10);
        descField.setWrapText(true);
        descField.getStyleClass().add("custom-textarea");

        ComboBox<Integer> titleFontSize = new ComboBox<>();
        ComboBox<Integer> descFontSize = new ComboBox<>();
        for (int i = 12; i <= 30; i += 2) {
            titleFontSize.getItems().add(i);
            descFontSize.getItems().add(i);
        }
        titleFontSize.setValue(section.getTitleFontSize());
        descFontSize.setValue(section.getDescriptionFontSize());

        Button saveBtn = new Button("💾 Save Changes");
        saveBtn.getStyleClass().add("btn-save");

        VBox layout = new VBox(12,
                new Label("Title:"), titleField,
                new Label("Title Font Size:"), titleFontSize,
                new Label("Description:"), descField,
                new Label("Description Font Size:"), descFontSize,
                saveBtn
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);

        saveBtn.setOnAction(e -> {
            String title = titleField.getText();
            String desc = descField.getText();
            if (!title.isEmpty() && !desc.isEmpty()) {
                section.setTitle(title);
                section.setDescription(desc);
                section.setTitleFontSize(titleFontSize.getValue());
                section.setDescriptionFontSize(descFontSize.getValue());

                if (isNew) {
                    sections.add(section);
                    sectionView.getChildren().add(createSectionUI(section));
                } else {
                    refreshSectionView();
                }

                inputStage.close();
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill out both fields.").show();
            }
        });

        inputStage.setScene(new Scene(layout, 500, 500));
        inputStage.show();
    }

    private VBox createSectionUI(CVSection section) {
        TemplateStyle style = TemplateStyle.getStyle(currentTemplate);

        Label sectionTitle = new Label(section.getTitle());
        sectionTitle.setStyle("-fx-font-size: " + section.getTitleFontSize() + "px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + style.titleColor + ";");

        Label sectionDesc = new Label(section.getDescription());
        sectionDesc.setWrapText(true);
        sectionDesc.setStyle("-fx-font-size: " + section.getDescriptionFontSize() + "px;" +
                "-fx-text-fill: " + style.descriptionColor + ";");

        VBox sectionBox = new VBox(6, sectionTitle, sectionDesc);
        sectionBox.setPadding(new Insets(10));
        sectionBox.setStyle("-fx-background-color: " + style.backgroundColor + ";" +
                "-fx-border-color: " + style.borderColor + ";" +
                "-fx-border-width: 1;");

        Button editBtn = new Button("✏️ Edit");
        Button removeBtn = new Button("🗑️ Remove");
        editBtn.getStyleClass().add("btn-edit");
        removeBtn.getStyleClass().add("btn-remove");

        editBtn.setOnAction(e -> openEditSectionWindow(section, sectionBox));
        removeBtn.setOnAction(e -> {
            sectionView.getChildren().remove(sectionBox);
            sections.remove(section);
        });

        HBox buttonBox = new HBox(10, editBtn, removeBtn);
        sectionBox.getChildren().add(buttonBox);

        return sectionBox;
    }

    private void refreshSectionView() {
        sectionView.getChildren().clear();
        for (CVSection section : sections) {
            sectionView.getChildren().add(createSectionUI(section));
        }
    }

    public void exportPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Resume");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("resume.pdf");

        try {
            var file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(file));
                doc.open();

                TemplateStyle style = TemplateStyle.getStyle(currentTemplate);

                for (CVSection s : sections) {
                    PdfPCell titleCell = new PdfPCell(new Phrase(s.getTitle(), style.titleFont));
                    titleCell.setBackgroundColor(style.titleBgColor);
                    titleCell.setBorder(Rectangle.NO_BORDER);
                    titleCell.setPadding(10f);
                    titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);

                    PdfPTable titleTable = new PdfPTable(1);
                    titleTable.setWidthPercentage(100f);
                    titleTable.addCell(titleCell);
                    doc.add(titleTable);

                    PdfPCell descCell = new PdfPCell(new Phrase(s.getDescription(), style.descFont));
                    descCell.setBackgroundColor(style.descBgColor);
                    descCell.setBorder(Rectangle.NO_BORDER);
                    descCell.setPadding(8f);
                    descCell.setHorizontalAlignment(Element.ALIGN_LEFT);

                    PdfPTable descTable = new PdfPTable(1);
                    descTable.setWidthPercentage(100f);
                    descTable.addCell(descCell);
                    doc.add(descTable);

                    doc.add(new Chunk(new LineSeparator(0.5f, 100, style.separatorColor, Element.ALIGN_CENTER, -2)));
                    doc.add(Chunk.NEWLINE);
                }

                doc.close();
                new Alert(Alert.AlertType.INFORMATION, "PDF exported successfully!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to export PDF.").show();
        }
    }
}