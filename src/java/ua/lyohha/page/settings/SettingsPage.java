package ua.lyohha.page.settings;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ua.lyohha.language.Language;
import ua.lyohha.language.LanguageChangeEvent;
import ua.lyohha.page.Page;
import ua.lyohha.themes.Themes;

public class SettingsPage extends Page implements LanguageChangeEvent {

    private boolean blockThemeComboBox = false;
    private String styleClassDark = "/assets/page/settings/SettingsPageDark.css";
    private String styleClassLight = "/assets/page/settings/SettingsPageLight.css";
    private String page = "/assets/page/settings/SettingsPage.fxml";

    public ComboBox themeComboBox;
    public Button backButton;
    public Label menuNameLabel;
    public Label languageLabel;
    public Label themeLabel;
    public ComboBox languageComboBox;

    @Override
    public Parent getParent() {
        return null;
    }

    @Override
    public String getStyleClass() {
        switch (Themes.getTheme()) {
            case LIGHT:
                return styleClassLight;
            case DARK:
            default:
                return styleClassDark;
        }
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public void initializeComponent() {
        languageComboBox.getItems().clear();
        String[] languages = Language.getLanguages();
        for (String s : languages)
            languageComboBox.getItems().add(s);
        languageComboBox.setValue(Language.getLanguage());
        Language.addEvent(this);
        setLocalizedNames();
    }

    public void languageComboBoxSelectionChanged(ActionEvent actionEvent) {
        Language.setLanguage((String) languageComboBox.getValue());
    }

    public void themeComboBoxSelectionChanged(ActionEvent actionEvent) {
        if (blockThemeComboBox)
            return;
        String value = (String) themeComboBox.getValue();
        Themes.Theme[] themes = Themes.getThemes();
        for (Themes.Theme th : themes) {
            if (value.equals(Language.getLocalized(Themes.getUnlocalizedName(th)))) {
                Themes.setTheme(th);
                break;
            }
        }
    }

    public void backButtonClick(ActionEvent actionEvent) {
        Language.removeEvent(this);
        navigation.navigateBack();
    }

    private void setLocalizedNames() {
        menuNameLabel.setText(Language.getLocalized("settings_menu.name"));
        themeLabel.setText(Language.getLocalized("settings_menu.theme.name"));
        languageLabel.setText(Language.getLocalized("settings_menu.language.name"));
        backButton.setText(Language.getLocalized("settings_menu.back_button.text"));

        blockThemeComboBox = true;
        themeComboBox.getItems().clear();

        Themes.Theme[] themes = Themes.getThemes();
        for (Themes.Theme th : themes)
            themeComboBox.getItems().add(Language.getLocalized(Themes.getUnlocalizedName(th)));
        themeComboBox.setValue(Language.getLocalized(Themes.getUnlocalizedName(Themes.getTheme())));
        blockThemeComboBox = false;
    }

    @Override
    public void onLanguageChange() {
        setLocalizedNames();
    }
}
