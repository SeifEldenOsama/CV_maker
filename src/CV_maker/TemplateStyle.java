package CV_maker;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;

public class TemplateStyle {
    // For PDF
    public Font titleFont;
    public Font descFont;
    public BaseColor separatorColor;
    public BaseColor titleBgColor;
    public BaseColor descBgColor;
    public String layoutType;

    // For JavaFX
    public String titleColor;
    public String descriptionColor;
    public String backgroundColor;
    public String borderColor;

    public static TemplateStyle getStyle(ResumeTemplate template) {
        TemplateStyle style = new TemplateStyle();

        switch (template) {
            case MODERN_BLUE:
                style.titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                style.descFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
                style.separatorColor = BaseColor.BLUE;
                style.titleBgColor = new BaseColor(220, 230, 250);
                style.descBgColor = BaseColor.WHITE;
                style.layoutType = "full-width";

                style.titleColor = "#1565C0";
                style.descriptionColor = "#333333";
                style.backgroundColor = "#E3F2FD";
                style.borderColor = "#90CAF9";
                break;

            case CLASSIC_GRAY:
                style.titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.DARK_GRAY);
                style.descFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.GRAY);
                style.separatorColor = BaseColor.GRAY;
                style.titleBgColor = new BaseColor(240, 240, 240);
                style.descBgColor = new BaseColor(250, 250, 250);
                style.layoutType = "centered";

                style.titleColor = "#424242";
                style.descriptionColor = "#666666";
                style.backgroundColor = "#FAFAFA";
                style.borderColor = "#BDBDBD";
                break;

            case DARK_MODE:
                style.titleFont = new Font(Font.FontFamily.COURIER, 16, Font.BOLD, BaseColor.WHITE);
                style.descFont = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.LIGHT_GRAY);
                style.separatorColor = BaseColor.WHITE;
                style.titleBgColor = new BaseColor(30, 30, 30);
                style.descBgColor = new BaseColor(50, 50, 50);
                style.layoutType = "dark";

                style.titleColor = "#FFFFFF";
                style.descriptionColor = "#CCCCCC";
                style.backgroundColor = "#2E2E2E";
                style.borderColor = "#555555";
                break;

            case FANCY_PURPLE:
                style.titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, new BaseColor(103, 58, 183));
                style.descFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
                style.separatorColor = new BaseColor(103, 58, 183);
                style.titleBgColor = new BaseColor(243, 229, 245);
                style.descBgColor = new BaseColor(255, 255, 255);
                style.layoutType = "fancy";

                style.titleColor = "#6A1B9A";
                style.descriptionColor = "#424242";
                style.backgroundColor = "#F3E5F5";
                style.borderColor = "#CE93D8";
                break;

            case GREEN_ELEGANT:
                style.titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.GREEN.darker());
                style.descFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.GRAY);
                style.separatorColor = BaseColor.GREEN;
                style.titleBgColor = new BaseColor(232, 245, 233);
                style.descBgColor = new BaseColor(250, 255, 250);
                style.layoutType = "two-column";

                style.titleColor = "#2E7D32";
                style.descriptionColor = "#4E4E4E";
                style.backgroundColor = "#E8F5E9";
                style.borderColor = "#A5D6A7";
                break;
        }

        return style;
    }
}
