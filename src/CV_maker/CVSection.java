package CV_maker;

public class CVSection {
    private String title;
    private String description;
    private int titleFontSize = 18;
    private int descriptionFontSize = 14;

    public CVSection(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(int size) {
        this.titleFontSize = size;
    }

    public int getDescriptionFontSize() {
        return descriptionFontSize;
    }

    public void setDescriptionFontSize(int size) {
        this.descriptionFontSize = size;
    }
}
